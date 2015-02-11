package org.syy.sqlrecoder.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.gui.components.ModalDialog;
import org.syy.sqlrecoder.gui.components.RootBorderPane;
import org.syy.sqlrecoder.service.SQLRecoderService;

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
        cancelButton.setOnAction(event -> {
            rootBorderPane.showSQLSquare();
        });
        titlePane.setLeft(cancelButton);

        doneButton = new Button("记下来");
        doneButton.setOnAction(event -> {
            addActionPerformed(event);
        });
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

    /**
     * 处理添加的事件
     * @param event
     */
    private void addActionPerformed(ActionEvent event) {
//        String sqlStr = sqlArea.getText();
//        String descriptionStr = descriptionArea.getText();
//
//        if (StringUtils.isBlank(sqlStr)) {
//
//        }
        ModalDialog dialog = new ModalDialog(rootBorderPane.getPrimaryStage(), "添加信息", "SQL为空还记录什么啊，二货");
    }
}
