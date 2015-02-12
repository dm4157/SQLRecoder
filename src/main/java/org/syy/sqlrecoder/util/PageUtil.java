package org.syy.sqlrecoder.util;

import org.syy.sqlrecoder.constant.IndexConstants;

/**
 * 分页相关工具类
 * Created by Administrator on 2015/2/13.
 */
public class PageUtil {

    public static int pageNum(int count) {
        int num = count / IndexConstants.PAGESIZE;
        if (count % IndexConstants.PAGESIZE != 0) {
            num++;
        }
        return num==0?1:num;
    }
}
