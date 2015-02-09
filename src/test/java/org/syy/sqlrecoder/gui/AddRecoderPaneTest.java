package org.syy.sqlrecoder.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Administrator on 2015/2/9.
 */
public class AddRecoderPaneTest extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        AddRecoderPane addPane = new AddRecoderPane();
        primaryStage.setScene(new Scene(addPane, 450, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
