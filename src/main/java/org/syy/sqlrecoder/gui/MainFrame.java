package org.syy.sqlrecoder.gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.bus.SpringContainer;
import org.syy.sqlrecoder.gui.components.RootBorderPane;

/**
 * Created by Administrator on 2015/2/8.
 */
public class MainFrame extends Application {

    private RootBorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root =  SpringContainer.me().getBean(RootBorderPane.class);
        root.setPrimaryStage(primaryStage);
        primaryStage.setTitle("SQLRecoder");
        primaryStage.setScene(new Scene(root, 600, 400));

        primaryStage.getScene().getStylesheets().add(getClass().getResource("/").toString() + "/css/main.css");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
