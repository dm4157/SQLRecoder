package org.syy.sqlrecoder.service.contract;

import org.syy.sqlrecoder.entity.SQLRecoder;

import java.util.List;

/**
 * 读取记录
 * 各种方式
 * Created by Administrator on 2015/2/10.
 */
public interface ISQLRecoderReader {

    public List<SQLRecoder> searchAllField(String key, int pageNo);
    public List<SQLRecoder> searchSqlField(String key, int pageNo);
    public List<SQLRecoder> searchDescriptionField(String key, int pageNo);

}
