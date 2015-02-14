package org.syy.sqlrecoder.gui;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.syy.sqlrecoder.gui.components.CubeSystemSample;

/**
 * 这是个隐藏页面
 * Created by Administrator on 2015/2/14.
 */
public class CubePane extends BorderPane{

    public CubePane() {
        createUserInterface();
    }

    private void createUserInterface() {

        BorderPane contentPane = new BorderPane();
        CubeSystemSample cube = new CubeSystemSample();
        contentPane.setCenter(cube.create3dContent());
        this.setBottom(contentPane);

        GridPane messagePane = new GridPane();
        Text sample = new Text(0,100,"SQLRecoder");
        sample.setFont(Font.font("Arial Black", 80));
        sample.setFill(Color.web("#BBBBBB"));
        final InnerShadow innerShadow = new InnerShadow();
        innerShadow.setRadius(5d);
        innerShadow.setOffsetX(2);
        innerShadow.setOffsetY(2);
        sample.setEffect(innerShadow);

        Label version = new Label("version:0.1 for xms");
        Label remark = new Label("<谨希望能帮到小萌神>");
        Label date = new Label("公元后2015年圣瓦伦丁殉难日");
        GridPane.setConstraints(sample, 0, 0);
        GridPane.setConstraints(version, 0, 1);
        GridPane.setConstraints(remark, 0, 2);
        GridPane.setConstraints(date, 0, 3);
        messagePane.getChildren().addAll(sample, version, remark, date);
        this.setCenter(messagePane);
    }

    public static void createAbout() {
        Stage stage = new Stage();
        stage.setTitle("与你有关");
        CubePane about = new CubePane();
        stage.setScene(new Scene(about, 600, 500));
        stage.getScene().setCamera(new PerspectiveCamera());
        stage.show();
    }
}
