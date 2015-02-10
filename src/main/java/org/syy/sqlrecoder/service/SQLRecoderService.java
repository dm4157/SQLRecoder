package org.syy.sqlrecoder.service;

import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.syy.sqlrecoder.dao.contract.ISearcher;
import org.syy.sqlrecoder.dao.contract.IWriter;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.exception.SaveSQLRecoderException;
import org.syy.sqlrecoder.service.contract.ISQLRecoderReader;
import org.syy.sqlrecoder.service.contract.ISQLRecoderSaver;

import java.util.List;

/**
 * 对SQL记录的各种业务逻辑
 * Created by Administrator on 2015/2/9.
 */
@Service
public class SQLRecoderService implements ISQLRecoderSaver, ISQLRecoderReader{

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
    public void saveOne(SQLRecoder recoder) throws SaveSQLRecoderException {
        Assert.isNull(recoder, "存储记录不能为null");
        Assert.isNull(recoder.getSql(), "SQL脚本不能为null");

        writer.write(recoder);
    }

    @Override
    public List<SQLRecoder> searchAllField(String key, int pageNo) {
        Query query = searcher.keyQuery(key, "A");
        List<SQLRecoder> data = searcher.getPageList(query, pageNo);
        return data;
    }

    @Override
    public List<SQLRecoder> searchSqlField(String key, int pageNo) {
        Query query = searcher.keyQuery(key, "S");
        List<SQLRecoder> data = searcher.getPageList(query, pageNo);
        return data;
    }

    @Override
    public List<SQLRecoder> searchDescriptionField(String key, int pageNo) {
        Query query = searcher.keyQuery(key, "D");
        List<SQLRecoder> data = searcher.getPageList(query, pageNo);
        return data;
    }
}

