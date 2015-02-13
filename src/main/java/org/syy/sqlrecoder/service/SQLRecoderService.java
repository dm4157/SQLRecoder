package org.syy.sqlrecoder.service;

import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.syy.sqlrecoder.dao.contract.ISearcher;
import org.syy.sqlrecoder.dao.contract.IWriter;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.exception.SaveSQLRecoderException;
import org.syy.sqlrecoder.service.contract.ISQLRecoderEditor;
import org.syy.sqlrecoder.service.contract.ISQLRecoderReader;
import org.syy.sqlrecoder.service.contract.ISQLRecoderSaver;

import java.util.List;

/**
 * 对SQL记录的各种业务逻辑
 * Created by Administrator on 2015/2/9.
 */
@Service
public class SQLRecoderService implements ISQLRecoderSaver, ISQLRecoderReader, ISQLRecoderEditor{

    @Autowired
    private IWriter writer;

    @Autowired
    private ISearcher searcher;

    /**
     * 将SQL记录保存的本地索引中
     * @param recoder   记录实体
     * @throws SaveSQLRecoderException
     */
    @Override
    public void saveOne(SQLRecoder recoder) throws SaveSQLRecoderException, IllegalArgumentException {
        Assert.notNull(recoder, "存储记录不能为null");
        Assert.notNull(recoder.getSql(), "SQL脚本不能为null");

        writer.write(recoder);
    }

    @Override
    public List<SQLRecoder> searchAllFieldWithCondition(String key, int pageNo) {
        Query query = searcher.keyQuery(key, "A");
        List<SQLRecoder> data = searcher.getPageWithCondition(query, pageNo);
        return data;
    }

    @Override
    public List<SQLRecoder> searchSqlField(String key, int pageNo) {
        Query query = searcher.keyQuery(key, "S");
        List<SQLRecoder> data = searcher.getPageWithCondition(query, pageNo);
        return data;
    }

    @Override
    public List<SQLRecoder> searchDescriptionField(String key, int pageNo) {
        Query query = searcher.keyQuery(key, "D");
        List<SQLRecoder> data = searcher.getPageWithCondition(query, pageNo);
        return data;
    }

    @Override
    public int numDocsForAllField(String key) {
        Query query = searcher.keyQuery(key, "A");
        return searcher.numDocsWithCondition(query);
    }

    @Override
    public int numDocsForSqlField(String key) {
        Query query = searcher.keyQuery(key, "S");
        return searcher.numDocsWithCondition(query);
    }

    @Override
    public int numDocsForDescriptionField(String key) {
        Query query = searcher.keyQuery(key, "D");
        return searcher.numDocsWithCondition(query);
    }

    @Override
    public List<SQLRecoder> searchDescriptionOrderByTime(int pageNo) {
        List<SQLRecoder> data = searcher.getPage(pageNo);
        return data;
    }

    @Override
    public int numDocsWithoutCondition() {
        return searcher.numDocsWithoutCondition();
    }

    @Override
    public int numDocs() {
        return searcher.numDocs();
    }

    @Override
    public List<SQLRecoder> wildcardQuery(String key, int pageNo) {
        Query query = searcher.keyQuery(key, "WA");
        List<SQLRecoder> data = searcher.getPageWithCondition(query, pageNo);
        return data;
    }

    @Override
    public int numDocsWildcardQuery(String key) {
        Query query = searcher.keyQuery(key, "WA");
        return searcher.numDocsWithCondition(query);
    }

    @Override
    public void update(SQLRecoder recoder) {
        writer.update(recoder);
    }

    @Override
    public void delete(SQLRecoder recoder) {
        writer.delete(recoder.getUuid());
    }
}

