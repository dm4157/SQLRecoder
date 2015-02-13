package org.syy.sqlrecoder.entity;

/**
 * 记录的实体
 * Created by Administrator on 2015/2/8.
 */
public class SQLRecoder {
    private String uuid;
    private String description;
    private String sql;
    private long timeToken;

    public SQLRecoder() {
    }

    public SQLRecoder(String description, String sql) {
        this.description = description;
        this.sql = sql;
    }

    public SQLRecoder(String description, String sql, long timeToken) {
        this.description = description;
        this.sql = sql;
        this.timeToken = timeToken;
    }


    public SQLRecoder(String uuid, String description, String sql, long timeToken) {
        this.uuid = uuid;
        this.description = description;
        this.sql = sql;
        this.timeToken = timeToken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public long getTimeToken() {
        return timeToken;
    }

    public void setTimeToken(long timeToken) {
        this.timeToken = timeToken;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
