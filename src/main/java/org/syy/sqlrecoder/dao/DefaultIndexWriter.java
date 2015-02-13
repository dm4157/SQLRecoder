package org.syy.sqlrecoder.dao;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.syy.sqlrecoder.dao.contract.IWriter;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

/**
 * 默认的写入者
 * 写入的媒介是本地文件索引
 * Created by Administrator on 2015/2/9.
 */
@Repository
public class DefaultIndexWriter implements IWriter{

    @Autowired
    private File indexDir;

    private IndexWriter indexWriter;

    /**
     * 初始化，打开索引
     * 创建索引工具
     */
    @PostConstruct
    public void init() {
        try {
            Directory dir = FSDirectory.open(indexDir);
            Analyzer analyzer = new IKAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LATEST, analyzer);
            indexWriter = new IndexWriter(dir, iwc);
            System.out.println("索引已准备好，安心写吧。。。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束时别忘了释放资源
     */
    @PreDestroy
    public void destroy() {
        try {
            indexWriter.close();
            System.out.println("明天没戏了。。。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将SQL记录保存到本地索引中
     * Field选项是索引、存储、高亮
     * @param recoder   SQL记录
     */
    @Override
    public void write(SQLRecoder recoder) {
        Document doc = createDocument(recoder);
        try {
            indexWriter.addDocument(doc);
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(SQLRecoder newRecoder) {
        Document doc = createDocument(newRecoder);
        IKAnalyzer analyzer = new IKAnalyzer();
        try {
            indexWriter.updateDocument(new Term("uuid", newRecoder.getUuid()), doc, analyzer);
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String uuid) {
        try {
            indexWriter.deleteDocuments(new Term("uuid", uuid));
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据实体类，创建文档
     * @param recoder
     * @return
     */
    private Document createDocument(SQLRecoder recoder) {
        Document doc = new Document();
        doc.add(new StringField("uuid", recoder.getUuid(), Field.Store.YES));
        doc.add(new TextField("description", recoder.getDescription(), Field.Store.YES));
        doc.add(new TextField("sql", recoder.getSql(), Field.Store.YES));
        doc.add(new LongField("timeToken", recoder.getTimeToken(), Field.Store.YES));
        return doc;
    }
}
