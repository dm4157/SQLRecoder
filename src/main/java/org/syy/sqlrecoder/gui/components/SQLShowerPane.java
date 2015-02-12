package org.syy.sqlrecoder.gui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;
import org.syy.sqlrecoder.entity.SQLRecoder;

import javax.swing.border.LineBorder;

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
        this.setPrefSize(250, 50);
        this.setSpacing(5);

        String description = recoder.getDescription();
        if (StringUtils.isBlank(description)) {
            description = "没提示呢";
        }
        Label descriptionLabel = new Label(description);
        Label sqlLabel = new Label(recoder.getSql());
        this.getChildren().addAll(descriptionLabel, sqlLabel);

        // 线性边框
        this.setPadding(new Insets(2, 4, 2, 4));
        this.setId("sqlShowerPane");
    }
}
