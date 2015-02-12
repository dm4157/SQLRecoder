package org.syy.sqlrecoder.dao;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Created by Administrator on 2015/2/13.
 */
public class IKtest {

    public static void main(String[] args) throws ParseException {
        IKAnalyzer analyzer = new IKAnalyzer(true);
        QueryParser parser = new QueryParser("ff", analyzer);
        System.out.println(parser.parse("中国天安门广场"));
    }
}
