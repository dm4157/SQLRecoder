package org.syy.sqlrecoder.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.gui.components.RootBorderPane;

/**
 * 搜索面板
 * 1.搜索条和确定按钮
 * 2.常用搜索项
 * Created by Administrator on 2015/2/8.
 */
@Component("searchPane")
public class SearchPane extends BorderPane {

    @Lazy
    @Autowired
    private RootBorderPane rootBorderPane;

    private TextField searchTextField;
    private Button searchButton;
    private Button addRecoderButton;

    public SearchPane() {
        createUserInterface();
    }

    /**
     * 创建用户界面
     */
    private void createUserInterface() {
        BorderPane inputSearchPane = new BorderPane();
        // 搜索框
        searchTextField = new TextField();
        inputSearchPane.setCenter(searchTextField);

        // 搜索按钮
        searchButton = new Button("快找啊");
        inputSearchPane.setRight(searchButton);
        this.setBottom(inputSearchPane);

        addRecoderButton = new Button("新增");
        addRecoderButton.setOnAction(event -> {
            rootBorderPane.showAddPane();
        });
        this.setLeft(addRecoderButton);
    }
}
