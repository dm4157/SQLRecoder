package org.syy.sqlrecoder.gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.bus.SpringContainer;

/**
 * Created by Administrator on 2015/2/8.
 */
@Component
public class MainFrame extends Application {

    private SearchPane searchPane;
    private SQLSquarePane sqlSquarePane;
    private AddRecoderPane addRecoderPane;

    private BorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        searchPane = SpringContainer.me().getBean(SearchPane.class);
        sqlSquarePane = SpringContainer.me().getBean(SQLSquarePane.class);
        addRecoderPane = SpringContainer.me().getBean(AddRecoderPane.class);

        root.setTop(searchPane);
        root.setCenter(sqlSquarePane);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void showAddPane() {
        root.setCenter(addRecoderPane);
    }

    public void showSQLSquare() {
        root.setCenter(sqlSquarePane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
