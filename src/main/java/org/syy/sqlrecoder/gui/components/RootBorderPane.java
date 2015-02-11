package org.syy.sqlrecoder.gui.components;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.gui.AddRecoderPane;
import org.syy.sqlrecoder.gui.SQLSquarePane;
import org.syy.sqlrecoder.gui.SearchPane;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2015/2/11.
 */
//@DependsOn({"addRecoderPane", "searchPane", "sqlSquarePane"})
@Component
public class RootBorderPane extends BorderPane {

    /**真正的内容面板*/
    private BorderPane contentPane;
    /**主舞台*/
    private Stage primaryStage;

    @Autowired
    private SearchPane searchPane;
    @Autowired
    private SQLSquarePane sqlSquarePane;
    @Autowired
    private AddRecoderPane addRecoderPane;

    @PostConstruct
    public void createUserInterface() {
        contentPane = new BorderPane();
        contentPane.setTop(searchPane);
        contentPane.setCenter(sqlSquarePane);

        this.setCenter(contentPane);
    }

    public void showAddPane() {
        setCenter(addRecoderPane);
    }

    public void showSQLSquare() {
        setCenter(contentPane);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
