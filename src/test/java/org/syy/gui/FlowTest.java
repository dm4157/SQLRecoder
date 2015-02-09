package org.syy.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Created by Administrator on 2015/2/8.
 */
public class FlowTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane root = new FlowPane();
        primaryStage.setScene(new Scene(root, 400, 400));

        Button b1 = new Button("sdfsd");
        b1.setPrefSize(200, 100);

        Button b2 = new Button("sdfsd");
        b2.setPrefSize(100, 200);

        Button b3 = new Button("sdfsd");
        b3.setPrefSize(150, 100);

        Button b4 = new Button("sdfsd");
        b4.setPrefSize(200, 300);

        root.getChildren().addAll(b1, b2, b3, b4);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
