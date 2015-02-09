package org.syy.sqlrecoder.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Administrator on 2015/2/8.
 */
public class MainFrame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        primaryStage.setScene(new Scene(root, 600, 400));



        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
