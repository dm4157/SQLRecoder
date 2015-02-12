package org.syy.sqlrecoder.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
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
@Component("sqlSquarePane")
public class SQLSquarePane extends ScrollPane {

    /**真正的内容面板*/
    private FlowPane contentPane;
    private List<SQLRecoder> recoders;

    public SQLSquarePane() {
        recoders = new ArrayList<>();
        contentPane = new FlowPane();
        contentPane.setHgap(10);
        contentPane.setVgap(6);
        contentPane.setPadding(new Insets(10));

        this.setContent(contentPane);
        this.setFitToHeight(true);
        this.setFitToWidth(true);
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
        if (null == newData) {
            contentPane.setAlignment(Pos.CENTER);
            Label messageLabel = new Label("没有搜到相关结果呢，我太笨了Ｏ(≧口≦)Ｏ， 不要嫌弃我");
            contentPane.getChildren().add(messageLabel);
        } else {
            contentPane.setAlignment(Pos.TOP_LEFT);
            recoders.addAll(newData);
            for (SQLRecoder recoder : newData) {
                contentPane.getChildren().add(new SQLShowerPane(recoder));
            }
        }
    }

    /**
     * 重新装载数据
     * @param newData
     */
    public void reload(List<SQLRecoder> newData) {
        // 清空数据和显示
        recoders = new ArrayList<>();
        this.getChildren().remove(0, this.getChildren().size());

        appendSQLFlow(newData);
    }
}
