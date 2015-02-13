package org.syy.sqlrecoder.gui.components;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;
import org.syy.sqlrecoder.entity.SQLRecoder;

/**
 * SQL文件展示面板
 * Created by Administrator on 2015/2/8.
 */
public class SQLShowerPane extends VBox{

    /**记录内容*/
    private SQLRecoder recoder;

    private Timeline showTime;
    private Timeline Time;

    public SQLShowerPane(SQLRecoder recoder) {
        this.recoder = recoder;
        createUserInterface();
    }

    private void createUserInterface() {
        this.setPrefSize(250, 50);
        this.setSpacing(5);

        String description = recoder.getDescription();
        if (StringUtils.isBlank(description)) {
            description = "没提示呢";
        }
        Label descriptionLabel = new Label(description);
        Label sqlLabel = new Label(recoder.getSql());
        this.getChildren().addAll(descriptionLabel, sqlLabel);

        // 线性边框
        this.setPadding(new Insets(2, 4, 2, 4));
        this.setId("sqlShowerPane");

        showTime = new Timeline();
        showTime.setAutoReverse(true);
        KeyValue keyValueX = new KeyValue(this.prefWidthProperty(), 400);
        KeyValue keyValueY = new KeyValue(this.prefHeightProperty(), 200);
        Duration duration = Duration.seconds(1);
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                SQLShowerPane.this.setPrefHeight(200);
                SQLShowerPane.this.setPrefWidth(400);
            }
        };
        KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);
        showTime.getKeyFrames().add(keyFrame);

        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showTime.play();
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showTime.stop();
            }
        });
    }

    private void createShowTime() {
        showTime = new Timeline();
        showTime.setAutoReverse(true);
        KeyValue keyValueX = new KeyValue(this.prefWidthProperty(), 400);
        KeyValue keyValueY = new KeyValue(this.prefHeightProperty(), 200);
        Duration duration = Duration.seconds(1);
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                SQLShowerPane.this.setPrefHeight(200);
                SQLShowerPane.this.setPrefWidth(400);
            }
        };
        KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);
        showTime.getKeyFrames().add(keyFrame);
    }
}
