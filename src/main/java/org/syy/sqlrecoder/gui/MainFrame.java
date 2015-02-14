package org.syy.sqlrecoder.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.syy.sqlrecoder.bus.SpringContainer;
import org.syy.sqlrecoder.gui.components.RootBorderPane;

/**
 * 主界面,程序入口
 * Created by syy on 2015/2/8.
 */
public class MainFrame extends Application {

    private Stage stage;
    private double initX;
    private double initY;

    @Override
    public void start(Stage primaryStage) throws Exception {
        RootBorderPane root =  SpringContainer.me().getBean(RootBorderPane.class);
        root.setPrimaryStage(primaryStage);

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.getScene().getStylesheets().add("/css/main.css");
        primaryStage.setTitle("SQLRecoder 小萌神特别版");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
