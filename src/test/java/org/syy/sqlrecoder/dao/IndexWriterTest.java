package org.syy.sqlrecoder.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.syy.sqlrecoder.dao.contract.IWriter;
import org.syy.sqlrecoder.entity.SQLRecoder;

/**
 * 很明显在测试写索引
 * 看不懂是猪^(*￣(oo)￣)^
 * Created by Administrator on 2015/2/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class IndexWriterTest {

    @Autowired
    private IWriter writer;

    @Test
    public void doIndex() {
        SQLRecoder recoder = new SQLRecoder("这是一个爱与被爱的故事", "select love from us where forever=1");
        writer.write(recoder);
    }
}
