package org.syy.sqlrecoder.util;

import java.io.*;

/**
 * 提示信息工具类
 * Created by Administrator on 2015/2/14.
 */
public class MessageUtil {

    private static int STORY = -1;
    private static int INDEX = -1;

    private static String[] storyMessage = null;

    private static String[] addMessage = null;


    /**
     * 获得添加成功的提示
     * @return  添加成功后的提示
     */
    public static String getAddMessage() {
        String path = MessageUtil.class.getResource("").getPath();
        path = path.substring(1, path.length() -25);
        if (STORY == -1) {
            PropertiesHelper propertiesHelper = new PropertiesHelper("/system");
            STORY = propertiesHelper.getIntValue("story");
            INDEX = propertiesHelper.getIntValue("index");
        }

        if (STORY == 1) {
            // 如果还在讲故事
            PropertiesHelper propertiesHelper = new PropertiesHelper("/system");
            if (storyMessage == null) {
                storyMessage = readFileSplit("/story");
                return "第一次用吧，东哥给你讲个故事吧！";
            }
            String message = storyMessage[INDEX++];
            if (INDEX >= storyMessage.length) {
                STORY = 0;
                propertiesHelper.setValue("story", "0");
            } else {
                propertiesHelper.setValue("index", String.valueOf(INDEX));
            }
            propertiesHelper.save();
            return message;
        } else {
            // 不讲故事了就随便吧
            if (addMessage == null) {
                addMessage = readFileSplit("/addtip");
                return "故事讲完咯，下面的提示就普通了呢";
            }
            return addMessage[(int)(Math.random()*addMessage.length)];
        }
    }

    /**
     * 读取文件信息，并根据换行符切割为数组
     * @param fileName  文件名
     * @return          文件内容根据换行符分割后的数组
     */
    private static String[] readFileSplit(String fileName) {
        File storyFile = new File(fileName);
        try {
            InputStream is = MessageUtil.class.getResourceAsStream(fileName);
            byte[] buff = new byte[1024];
            int len = is.read(buff);
            String ss = new String(buff, 0, len, "utf-8");
            return ss.split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
