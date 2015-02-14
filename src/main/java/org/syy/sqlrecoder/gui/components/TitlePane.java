package org.syy.sqlrecoder.gui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.springframework.stereotype.Component;
import org.syy.sqlrecoder.util.ImgUtil;

/**
 * 标题栏
 * Created by Administrator on 2015/2/14.
 */
public class TitlePane extends BorderPane{

    public TitlePane() {
        createUserInterface();
    }

    private void createUserInterface() {
        /*****标题栏****/
        // 标题
        Label titleLabel = new Label("SQLRecoder 萌神特别版");
        titleLabel.setAlignment(Pos.CENTER);

        // 窗口按钮
        ImageView miniView = new ImageView(ImgUtil.getImage("window/min_16.png"));
        miniView.setFitHeight(30);
        miniView.setFitWidth(30);
        Button miniButton = new Button("", miniView);
        ImageView maxView = new ImageView(ImgUtil.getImage("window/max_16.png"));
        Button maxButton = new Button("", maxView);
        ImageView closeView = new ImageView(ImgUtil.getImage("window/close_16.ico"));
        Button closeButton = new Button("", closeView);

        FlowPane buttonFlowPane = new FlowPane();
        buttonFlowPane.setAlignment(Pos.CENTER_RIGHT);
        buttonFlowPane.setPadding(new Insets(5));
        buttonFlowPane.getChildren().addAll(miniView, miniButton, maxButton, closeButton);

        this.setLeft(titleLabel);
        this.setRight(buttonFlowPane);
        this.setStyle("-fx-background-color: #1877ff");
    }
}
