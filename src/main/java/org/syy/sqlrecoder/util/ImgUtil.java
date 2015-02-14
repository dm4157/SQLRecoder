package org.syy.sqlrecoder.util;

import javafx.scene.image.Image;

import java.net.URISyntaxException;

/**
 * 图片工具类
 * Created by Administrator on 2015/2/14.
 */
public class ImgUtil {

    /**
     * 获得图片对象
     * @param path
     * @return
     */
    public static Image getImage(String path) {
        return new Image("/img/" + path);
    }
}
