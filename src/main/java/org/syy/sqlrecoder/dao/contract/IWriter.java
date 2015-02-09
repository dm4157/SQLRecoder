package org.syy.sqlrecoder.dao.contract;

import org.syy.sqlrecoder.entity.SQLRecoder;

/**
 * SQL记录写入本地的契约
 * Created by Administrator on 2015/2/9.
 */
public interface IWriter {

    /**
     * 保存到本地
     * 可以使数据库、索引、文件等
     * 契约中不作要求
     * @param recoder
     */
    public void write(SQLRecoder recoder);
}
