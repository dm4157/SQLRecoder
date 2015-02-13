package org.syy.sqlrecoder.service.contract;

import org.syy.sqlrecoder.entity.SQLRecoder;

/**
 * 索引编辑
 * Created by Administrator on 2015/2/14.
 */
public interface ISQLRecoderEditor {

    public void update(SQLRecoder recoder);

    public void delete(SQLRecoder recoder);
}
