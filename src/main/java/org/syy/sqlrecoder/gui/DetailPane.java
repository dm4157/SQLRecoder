package org.syy.sqlrecoder.gui;

import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.gui.components.Dialog;
import org.syy.sqlrecoder.service.SQLRecoderService;

/**
 * 详细面板
 * Created by Administrator on 2015/2/14.
 */
@Scope("prototype")
@Component
public class DetailPane extends BorderPane{

    private SQLRecoder recoder;
    private Stage rootStage;

    private TextArea descriptionArea;
    private TextArea sqlArea;

    @Autowired
    private SQLRecoderService sqlRecoderService;

    public DetailPane() {
    }

    public DetailPane(SQLRecoder recoder) {
        setRecoder(recoder);
    }

    private void createUserInterface() {
        /***** 设置标题栏，包括记录时间， 点击次数等****/
        BorderPane titlePane = new BorderPane();
        titlePane.setPadding(new Insets(5));

        // 修改和删除
        FlowPane buttonPane = new FlowPane();
        buttonPane.setHgap(10);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        Button updateButton = new Button("修改");
        updateButton.setOnAction(event -> {
            if (StringUtils.isBlank(sqlArea.getText())) {
                Dialog.showMessageDialog(rootStage, "修改记录", "SQL为空了还记录什么啊");
            } else {
                recoder.setDescription(descriptionArea.getText().trim());
                recoder.setSql(sqlArea.getText().trim());
                recoder.setTimeToken(System.currentTimeMillis());
                sqlRecoderService.update(recoder);
                Dialog.showMessageDialog(rootStage, "修改记录", "修改成功");
            }
        });
        Button deleteButton = new Button("删除");
        deleteButton.setOnAction(event -> {
            sqlRecoderService.delete(recoder);
            Dialog.showMessageDialog(rootStage, "删除记录", "删除成功");
            rootStage.close();
        });
        buttonPane.getChildren().addAll(updateButton, deleteButton);

        FlowPane labelPane = new FlowPane();
        labelPane.setHgap(10);
        labelPane.setAlignment(Pos.CENTER_LEFT);
        // 时间标签
        DateTime dateTime = new DateTime(recoder.getTimeToken());
        Label timeLabel = new Label();
        timeLabel.setText(dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS"));
        labelPane.getChildren().add(timeLabel);

        titlePane.setCenter(labelPane);
        titlePane.setRight(buttonPane);
        /******内容面板***********/
        String description = recoder.getDescription();
        if (StringUtils.isBlank(description)) {
            description = "<没提示呢>";
        }
        descriptionArea = new TextArea(description);
        sqlArea = new TextArea(recoder.getSql());

        SplitPane contentPane = new SplitPane();
        contentPane.setOrientation(Orientation.HORIZONTAL);
        contentPane.getItems().addAll(descriptionArea, sqlArea);
        /*************容器本身设置**************/
        // 面板布局
        this.setTop(titlePane);
        this.setCenter(contentPane);

    }

    public SQLRecoder getRecoder() {
        return recoder;
    }

    public void setRecoder(SQLRecoder recoder) {
        this.recoder = recoder;
        createUserInterface();
    }

    public Stage getRootStage() {
        return rootStage;
    }

    public void setRootStage(Stage rootStage) {
        this.rootStage = rootStage;
    }
}
