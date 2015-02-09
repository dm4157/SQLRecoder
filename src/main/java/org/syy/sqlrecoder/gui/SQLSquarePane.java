package org.syy.sqlrecoder.gui;

import javafx.scene.layout.FlowPane;
import org.syy.sqlrecoder.entity.SQLRecoder;

import java.util.List;

/**
 * SQL广场
 * 用来展示SQL文件
 * Created by Administrator on 2015/2/8.
 */
public class SQLSquarePane extends FlowPane{

    private List<SQLRecoder> recoders;

    public SQLSquarePane() {
        this(null);
    }

    public SQLSquarePane(List<SQLRecoder> recoders) {
        this.recoders = recoders;
    }


}
