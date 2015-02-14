package org.syy.sqlrecoder.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Administrator on 2015/2/14.
 */
public class AboutPane extends BorderPane {

    public AboutPane() {
        createUserInterface();
    }

    private void createUserInterface() {
        GridPane messagePane = new GridPane();
        Label title = new Label("SQLRecoder");
        Label version = new Label("version:0.1 for xms");
        Label remark = new Label("<谨希望能帮到小萌神>");
        Label date = new Label("公元后2015年圣瓦伦丁殉难日");
        GridPane.setConstraints(title, 0, 0);
        GridPane.setConstraints(version, 0, 1);
        GridPane.setConstraints(remark, 0, 2);
        GridPane.setConstraints(date, 0, 3);
        messagePane.getChildren().addAll(title, version, remark, date);
        this.setCenter(messagePane);
    }

    public static void createAbout() {
        Stage stage = new Stage();
        stage.setTitle("与你有关");
        stage.setScene(new Scene(new AboutPane(), 300, 200));
        stage.show();
    }
}
