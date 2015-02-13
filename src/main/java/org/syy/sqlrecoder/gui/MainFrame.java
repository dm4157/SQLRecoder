package org.syy.sqlrecoder.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.syy.sqlrecoder.bus.SpringContainer;
import org.syy.sqlrecoder.gui.components.RootBorderPane;

/**
 * 主界面,程序入口
 * Created by syy on 2015/2/8.
 */
public class MainFrame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        RootBorderPane root =  SpringContainer.me().getBean(RootBorderPane.class);
        root.setPrimaryStage(primaryStage);
        primaryStage.setTitle("SQLRecoder");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.getScene().getStylesheets().add(getClass().getResource("/").toString() + "/css/main.css");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
