package org.syy.sqlrecoder.dao;

import org.syy.sqlrecoder.dao.contract.IWriter;
import org.syy.sqlrecoder.entity.SQLRecoder;

/**
 * 默认的写入者
 * 写入的媒介是本地文件索引
 * Created by Administrator on 2015/2/9.
 */
public class DefaultIndexWriter implements IWriter{

    /**
     * 将SQL记录保存到本地索引中
     * Field选项是索引、存储、高亮
     * @param recoder   SQL记录
     */
    @Override
    public void write(SQLRecoder recoder) {
        //TODO 实现这个lucene的写入操作
    }
}
