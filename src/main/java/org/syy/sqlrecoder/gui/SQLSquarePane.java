package org.syy.sqlrecoder.gui;

import javafx.scene.layout.FlowPane;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.gui.components.SQLShowerPane;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL广场
 * 用来展示SQL文件
 * Created by Administrator on 2015/2/8.
 */
@Component
public class SQLSquarePane extends FlowPane{

    private List<SQLRecoder> recoders;

    public SQLSquarePane() {
        recoders = new ArrayList<>();
    }

    public SQLSquarePane(List<SQLRecoder> recoders) {
        this();
        appendSQLFlow(recoders);
    }

    /**
     * 追加新的SQL展示标签
     * @param newData
     */
    public void appendSQLFlow(List<SQLRecoder> newData) {
        recoders.addAll(newData);
        for (SQLRecoder recoder : newData) {
            this.getChildren().add(new SQLShowerPane(recoder));
        }
    }
}
