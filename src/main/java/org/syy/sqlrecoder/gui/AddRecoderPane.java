package org.syy.sqlrecoder.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * 添加SQL记录的界面
 * Created by Administrator on 2015/2/9.
 */
public class AddRecoderPane extends BorderPane{

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
        titlePane.setLeft(cancelButton);

        doneButton = new Button("记下来");
        titlePane.setRight(doneButton);

        this.setTop(titlePane);

        // 中间内容，先放两个文本域
        VBox contentBox = new VBox(5);

        sqlArea = new TextArea();
        sqlArea.setPromptText("快把SQL复制过来...");

        descriptionArea = new TextArea();
        descriptionArea.setPromptText("写点描述吧，好看也好查...");

        contentBox.getChildren().addAll(sqlArea, descriptionArea);
        this.setCenter(contentBox);
    }
}
