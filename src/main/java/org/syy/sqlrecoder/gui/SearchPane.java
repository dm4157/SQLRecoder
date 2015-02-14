package org.syy.sqlrecoder.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.gui.components.RootBorderPane;
import org.syy.sqlrecoder.service.SQLRecoderService;

import java.util.List;

/**
 * 搜索面板
 * 1.搜索条和确定按钮
 * 2.常用搜索项
 * Created by Administrator on 2015/2/8.
 */
@Component("searchPane")
public class SearchPane extends BorderPane {

    private RootBorderPane rootBorderPane;
    @Autowired
    private SQLRecoderService sqlRecoderService;

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
        /********中间内容面板***********/
        // 搜索框
        searchTextField = new TextField();
        searchTextField.setPrefHeight(30);
        searchTextField.setOnAction(event -> {
            newSearch();
        });
        BorderPane.setMargin(searchTextField, new Insets(0, 10, 0, 10));

        // 搜索按钮
        searchButton = new Button("快找啊");
        searchButton.setPrefHeight(30);
        searchButton.setOnAction(event -> {
            newSearch();
        });

        BorderPane inputSearchPane = new BorderPane();
        inputSearchPane.setCenter(searchTextField);
        inputSearchPane.setRight(searchButton);
        this.setCenter(inputSearchPane);

        /*********左侧按钮********/
        addRecoderButton = new Button("新增");
        addRecoderButton.setPrefHeight(30);
        addRecoderButton.setOnAction(event -> {
            rootBorderPane.showAddPane();
        });
        this.setLeft(addRecoderButton);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setStyle("-fx-background-color: deepskyblue");
        this.setPrefHeight(50);
    }

    /**
     * 新的搜索
     */
    private void newSearch() {
        rootBorderPane.createNewPagination();
    }

    /**
     * 根据条件确定如何去查找
     * 并给出结果
     * @return
     */
    public List<SQLRecoder> findHowToSearch() {
        String key = searchTextField.getText().trim();
        List<SQLRecoder>  data;
        if (StringUtils.isBlank(key)) {
            data = sqlRecoderService.searchDescriptionOrderByTime(rootBorderPane.getCurrentPage());
        } else if (key.equals("XMSDDG2015214") || key.equals("CUBE2015214")) {
            data = sqlRecoderService.searchDescriptionOrderByTime(rootBorderPane.getCurrentPage());
            CubePane.createAbout();
        }else if (key.contains("*") || key.contains("?")) {
            data = sqlRecoderService.wildcardQuery(key, rootBorderPane.getCurrentPage());
        } else {
            data = sqlRecoderService.searchAllFieldWithCondition(key, rootBorderPane.getCurrentPage());
        }

        return data;
    }

    /**
     * 根据关键字确定如何计算总条数
     * @return
     */
    public int findHowToCount() {
        String key = searchTextField.getText().trim();
        if (StringUtils.isBlank(key)) {
            return sqlRecoderService.numDocsWithoutCondition();
        } else if (key.contains("*") || key.contains("?")) {
            return sqlRecoderService.numDocsWildcardQuery(key);
        } else {
            return sqlRecoderService.numDocsForAllField(key);
        }
    }

    public RootBorderPane getRootBorderPane() {
        return rootBorderPane;
    }

    public void setRootBorderPane(RootBorderPane rootBorderPane) {
        this.rootBorderPane = rootBorderPane;
    }
}
