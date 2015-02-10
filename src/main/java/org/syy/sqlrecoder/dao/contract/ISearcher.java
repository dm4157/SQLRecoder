package org.syy.sqlrecoder.dao.contract;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.syy.sqlrecoder.entity.SQLRecoder;

import java.util.List;

/**
 * SQL记录从本地查询的契约
 * Created by Administrator on 2015/2/9.
 */
public interface ISearcher {

    /**
     * 分页查询
     * @param query
     * @param pageNo
     * @return
     */
    public List<SQLRecoder> getPageList(Query query, int pageNo);

    /**
     * 根据关键字组装查询条件
     * @param key
     * @param field 查询域， S是sql D是description A是全部
     * @return
     */
    public Query keyQuery(String key, String field);
}
