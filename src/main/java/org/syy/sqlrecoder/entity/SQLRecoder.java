package org.syy.sqlrecoder.entity;

/**
 * 记录的实体
 * Created by Administrator on 2015/2/8.
 */
public class SQLRecoder {
    private String desciption;
    private String sql;

    public SQLRecoder() {
    }

    public SQLRecoder(String desciption, String sql) {
        this.desciption = desciption;
        this.sql = sql;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
