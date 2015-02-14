package org.syy.sqlrecoder.gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.exception.SaveSQLRecoderException;
import org.syy.sqlrecoder.gui.components.Dialog;
import org.syy.sqlrecoder.gui.components.RootBorderPane;
import org.syy.sqlrecoder.service.SQLRecoderService;
import org.syy.sqlrecoder.util.MessageUtil;

import java.util.UUID;

/**
 * 添加SQL记录的界面
 * Created by Administrator on 2015/2/9.
 */
@Component("addRecoderPane")
public class AddRecoderPane extends BorderPane{

    @Lazy
    @Autowired
    private RootBorderPane rootBorderPane;

    @Autowired
    private SQLRecoderService sqlRecoderService;

    private TextArea sqlArea;
    private TextArea descriptionArea;
    private Button cancelButton;
    private Button doneButton;

    public AddRecoderPane() {
        createUserInterface();
    }

    private void createUserInterface() {
        // 标题栏，放两个按钮就算了
        BorderPane titlePane = new BorderPane();

        cancelButton = new Button("返回");
        cancelButton.setPrefHeight(30);
        cancelButton.setOnAction(event -> {
            rootBorderPane.showSQLSquare();
        });
        titlePane.setLeft(cancelButton);

        doneButton = new Button("记下来");
        doneButton.setPrefHeight(30);
        doneButton.setOnAction(event -> {
            addActionPerformed(event);
        });
        titlePane.setRight(doneButton);
        titlePane.setPadding(new Insets(10));
        titlePane.setStyle("-fx-background-color: deepskyblue");
        this.setTop(titlePane);

        // spiltPane
        sqlArea = new TextArea();
        sqlArea.setPromptText("快把SQL复制过来...");

        descriptionArea = new TextArea();
        descriptionArea.setPromptText("写点描述吧，好看也好查...");

        SplitPane contentPane = new SplitPane();
        contentPane.setPadding(new Insets(10));
        contentPane.setOrientation(Orientation.VERTICAL);
        contentPane.getItems().addAll(sqlArea, descriptionArea);
        this.setCenter(contentPane);
        this.setPrefHeight(50);

    }

    /**
     * 处理添加的事件
     * @param event
     */
    private void addActionPerformed(ActionEvent event) {
        String sqlStr = sqlArea.getText();
        String descriptionStr = descriptionArea.getText();

        if (StringUtils.isBlank(sqlStr)) {
            Dialog.showMessageDialog(rootBorderPane.getPrimaryStage(), "添加信息", "SQL为空还记录什么啊，二货");
        } else {
            SQLRecoder sqlRecoder = new SQLRecoder(UUID.randomUUID().toString(),descriptionStr, sqlStr, System.currentTimeMillis());
            try {
                sqlRecoderService.saveOne(sqlRecoder);
                sqlArea.setText("");
                descriptionArea.setText("");
                Dialog.showMessageDialog(rootBorderPane.getPrimaryStage(), "添加成功", MessageUtil.getAddMessage());
            } catch (SaveSQLRecoderException e) {
                Dialog.showMessageDialog(rootBorderPane.getPrimaryStage(), "添加失败", e.getMessage());
            } catch (IllegalArgumentException e) {
                Dialog.showMessageDialog(rootBorderPane.getPrimaryStage(), "添加失败", e.getMessage());
            }
        }
    }
}
