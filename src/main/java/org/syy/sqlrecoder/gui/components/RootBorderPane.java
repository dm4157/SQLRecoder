package org.syy.sqlrecoder.gui.components;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.gui.AddRecoderPane;
import org.syy.sqlrecoder.gui.SQLSquarePane;
import org.syy.sqlrecoder.gui.SearchPane;
import org.syy.sqlrecoder.service.SQLRecoderService;
import org.syy.sqlrecoder.util.PageUtil;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2015/2/11.
 */
//@DependsOn({"addRecoderPane", "searchPane", "sqlSquarePane"})
@Component("rootBorderPane")
public class RootBorderPane extends BorderPane {

    /**真正的内容面板*/
    private BorderPane contentPane;
    /**主舞台*/
    private Stage primaryStage;
    private Pagination pagination = null;
    private int currentPage = 1;

    @Autowired
    private SearchPane searchPane;
    @Autowired
    private SQLSquarePane sqlSquarePane;
    @Autowired
    private AddRecoderPane addRecoderPane;

//    @Autowired
//    private TitlePane titlePane;

    @Autowired
    private SQLRecoderService sqlRecoderService;

    @PostConstruct
    public void createUserInterface() {

//        /*****标题栏****/
//        this.setTop(titlePane);

        /******中部内容******/
        contentPane = new BorderPane();
        searchPane.setRootBorderPane(this);
        contentPane.setTop(searchPane);
        //分页
        createNewPagination();

        // 设置布局
        this.setCenter(contentPane);
    }

    /**
     * 创建一个分页器
     */
    public void createNewPagination() {
        pagination = new Pagination();
        // 最大页码
        pagination.setPageCount(PageUtil.pageNum(searchPane.findHowToCount()));
        // 第一页从0开始
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override public Node call(Integer pageIndex) {
                // lucene查询是计算页码是从1开始的，所以+1
                currentPage = pageIndex+1;
                // 搜索结果
                List<SQLRecoder> data = searchPane.findHowToSearch();
                sqlSquarePane =  new SQLSquarePane(data);
                return sqlSquarePane;
            }
        });
        contentPane.setCenter(pagination);
    }

    public void showAddPane() {
        setCenter(addRecoderPane);
    }

    public void showSQLSquare() {
        setCenter(contentPane);
        createNewPagination();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

}
