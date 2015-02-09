package org.syy.sqlrecoder.service;

import org.springframework.stereotype.Service;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.exception.SaveSQLRecoderException;
import org.syy.sqlrecoder.service.contract.ISQLRecoderSaver;

/**
 * 对SQL记录的各种业务逻辑
 * Created by Administrator on 2015/2/9.
 */
@Service
public class SQLRecoderService implements ISQLRecoderSaver{

    /**
     * 将SQL记录保存的本地索引中
     * @param recoder   记录实体
     * @throws SaveSQLRecoderException
     */
    @Override
    public void saveOne(SQLRecoder recoder) throws SaveSQLRecoderException {
        //TODO 保存的业务逻辑
    }
}
