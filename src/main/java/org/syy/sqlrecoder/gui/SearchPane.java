package org.syy.sqlrecoder.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * 搜索面板
 * 1.搜索条和确定按钮
 * 2.常用搜索项
 * Created by Administrator on 2015/2/8.
 */
public class SearchPane extends BorderPane {

    private TextField searchTextField;
    private Button searchButton;

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
        inputSearchPane.setLeft(searchButton);
        this.setBottom(inputSearchPane);
    }
}
