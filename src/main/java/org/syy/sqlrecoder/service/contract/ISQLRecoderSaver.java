package org.syy.sqlrecoder.service.contract;

import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.exception.SaveSQLRecoderException;

/**
 * SQL记录存储契约
 * 所有可以存储SQL记录的类都应该遵循此契约
 * 违者斩立决
 * Created by Administrator on 2015/2/9.
 */
public interface ISQLRecoderSaver {

    /**
     * 保存SQL记录
     * @param recoder   记录实体
     * @throws SaveSQLRecoderException  保存是的异常信息
     */
    public void saveOne(SQLRecoder recoder) throws SaveSQLRecoderException;
}
