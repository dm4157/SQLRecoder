package org.syy.sqlrecoder.gui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class ModalDialog {
    private Button okButton;

    public ModalDialog(final Stage stg, String title, String message) {
        okButton = new Button();

        final Stage stage = new Stage();

        // 是否模态
        if (null != stg) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stg);
        }

        stage.setTitle(title);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 270, 100, Color.GRAY);

        // 图标
        ModalDialog.class.getResource("/").getPath();
        Image image = null;
        try {
            image = new Image(ModalDialog.class.getResource("/").toURI() + "img/dialog/info.png");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
        FlowPane imagePane = PaneTools.createCenterFlowPane(new Insets(5));
        imagePane.getChildren().add(imageView);
        root.setLeft(imagePane);

        // 信息
        Label messageLabel = new Label(message);
        messageLabel.setPadding(new Insets(10));
        messageLabel.setAlignment(Pos.CENTER_LEFT);
        root.setCenter(messageLabel);

        // 设置按钮
        okButton.setOnAction(event -> stage.hide());
        okButton.setText("知道了");
        FlowPane buttonPane = PaneTools.createCenterFlowPane(new Insets(10, 10, 10, 15));
        buttonPane.getChildren().add(okButton);

        root.setBottom(buttonPane);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws URISyntaxException {
        System.out.println(ModalDialog.class.getResource("/").toURI());
    }
}