package org.syy.sqlrecoder.dao;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.syy.sqlrecoder.constant.IndexConstants;
import org.syy.sqlrecoder.dao.contract.ISearcher;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认搜索
 * Created by Administrator on 2015/2/10.
 */
@Repository
public class DefaultIndexSearcher implements ISearcher {

    @Autowired
    private File indexDir;

    private DirectoryReader reader;

    @PostConstruct
    public void init() {
        if (!indexDir.exists()) {
            indexDir.mkdirs();
        }

        try {
            reader = DirectoryReader.open(FSDirectory.open(indexDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得索引搜索器
     * 要判断索引是否改变
     * 如果改变需要重新打开
     * @return
     */
    private IndexSearcher getSearcher() {
        return new IndexSearcher(getReader());
    }

    /**
     * 获得最新的indexReader
     * @return
     */
    private IndexReader getReader() {
        if (null != reader) {
            try {
                DirectoryReader tmp = DirectoryReader.openIfChanged(reader);
                if (null != tmp) {
                    reader.close();
                    reader = tmp;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                reader = DirectoryReader.open(FSDirectory.open(indexDir));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reader;
    }

    /**
     * 分页搜索
     * 搜索一页的数据并返回
     * @param query
     * @param pageNo
     * @return
     */
    @Override
    public List<SQLRecoder> getPageWithCondition(Query query, int pageNo) {
        IndexSearcher searcher = this.getSearcher();
        try {
            TopDocs results = searcher.search(query, pageNo * IndexConstants.PAGESIZE);
            ScoreDoc[] hits = results.scoreDocs;
            if (hits.length <= (pageNo - 1) * IndexConstants.PAGESIZE) {
                return null;
            } else {
                List<SQLRecoder> data = new ArrayList<SQLRecoder>();
                for (int i = (pageNo - 1) * IndexConstants.PAGESIZE; i < Math.min(pageNo * IndexConstants.PAGESIZE, hits.length); i++) {
                    Document doc = searcher.doc(hits[i].doc);
                    SQLRecoder recoder = new SQLRecoder(doc.get("description"), doc.get("sql"), Long.parseLong(doc.get("timeToken")));
                    data.add(recoder);
                }
                return data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int numDocsWithCondition(Query query) {
        IndexSearcher searcher = this.getSearcher();
        TopDocs results;
        try {
            results = searcher.search(query, IndexConstants.MAXUSEFULDOC);
            return results.totalHits;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 组装查询条件
     * @param key
     * @param field 查询域， S是sql D是description A是全部（默认是A哦，不正确的都算作A）
     * @return
     */
    @Override
    public Query keyQuery(String key, String field) {
        Analyzer analyzer = new IKAnalyzer();

        try {
            switch (field) {
                case "D":
                    QueryParser descriptionParser = new QueryParser("description", analyzer);
                    return descriptionParser.parse(key);
                case "S":
                    QueryParser sqlParser = new QueryParser("sql", analyzer);
                    return sqlParser.parse(key);
                case "*":
                    WildcardQuery wildcardQuery = new WildcardQuery(new Term("sql", "*"));
                    return wildcardQuery;
                default:
                    String[] fields = {"description", "sql"};
                    QueryParser allParser = new MultiFieldQueryParser(fields, analyzer);
                    return allParser.parse(key);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<SQLRecoder> getPage(int pageNo) {
        IndexSearcher searcher = this.getSearcher();
        try {
            Query query = keyQuery(null, "*");
            SortField sortField = new SortField("timeToken", SortField.Type.LONG, true);
            Sort sort = new Sort(sortField);

            TopDocs results = searcher.search(query, pageNo * IndexConstants.PAGESIZE, sort);
            ScoreDoc[] hits = results.scoreDocs;
            if (hits.length <= (pageNo - 1) * IndexConstants.PAGESIZE) {
                return null;
            } else {
                List<SQLRecoder> data = new ArrayList<SQLRecoder>();
                for (int i = (pageNo - 1) * IndexConstants.PAGESIZE; i < Math.min(pageNo * IndexConstants.PAGESIZE, hits.length); i++) {
                    Document doc = searcher.doc(hits[i].doc);
                    SQLRecoder recoder = new SQLRecoder(doc.get("description"), doc.get("sql"), Long.parseLong(doc.get("timeToken")));
                    data.add(recoder);
                }
                return data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int numDocsWithoutCondition() {
        IndexSearcher searcher = this.getSearcher();
            Query query = keyQuery(null, "*");
        try {
            TopDocs results = searcher.search(query, IndexConstants.MAXUSEFULDOC);
            return results.totalHits;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int numDocs() {
        return getReader().numDocs();
    }

}
