package org.syy.sqlrecoder.gui.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.syy.sqlrecoder.entity.SQLRecoder;

/**
 * SQL文件展示面板
 * Created by Administrator on 2015/2/8.
 */
public class SQLShowerPane extends VBox{

    /**记录内容*/
    private SQLRecoder recoder;

    public SQLShowerPane(SQLRecoder recoder) {
        this.recoder = recoder;
        createUserInterface();
    }

    private void createUserInterface() {
        this.setPrefSize(150, 50);
        this.setSpacing(5);

        Label descriptionLabel = new Label(recoder.getDescription());
        Label sqlLabel = new Label(recoder.getSql());
        this.getChildren().addAll(descriptionLabel, sqlLabel);
    }
}
