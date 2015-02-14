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
import org.syy.sqlrecoder.util.ImgUtil;

import java.net.URISyntaxException;

/**
 * 对话框，你信么
 */
public class Dialog {
    private Button okButton;

    public Dialog(final Stage stg, String title, String message) {
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
        Image image = ImgUtil.getImage("dialog/info.png");
        ImageView imageView = new ImageView(image);
        FlowPane imagePane = PaneTools.createCenterFlowPane(new Insets(10, 0, 0, 10));
        imagePane.getChildren().add(imageView);
        root.setLeft(imagePane);

        // 信息
        Label messageLabel = new Label(message);
//        messageLabel.setPadding(new Insets(10));
        messageLabel.setAlignment(Pos.CENTER_LEFT);
        root.setCenter(messageLabel);

        // 设置按钮
        okButton.setOnAction(event -> stage.close());
        okButton.setText("知道了");
        FlowPane buttonPane = PaneTools.createCenterFlowPane(new Insets(0, 10, 10, 15));
        buttonPane.getChildren().add(okButton);

        root.setBottom(buttonPane);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * 展示一个信息提示对话框
     * @param stg       是否模态，null为非模态，非null即模态的对象
     * @param title     标题
     * @param message   信息内容
     */
    public static void showMessageDialog(final Stage stg, String title, String message) {
        new Dialog(stg, title, message);
    }

    public static void main(String[] args) throws URISyntaxException {
        System.out.println(Dialog.class.getResource("/").toURI());
    }
}