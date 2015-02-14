package org.syy.sqlrecoder.gui.components;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.syy.sqlrecoder.bus.SpringContainer;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.syy.sqlrecoder.gui.DetailPane;

/**
 * SQL文件展示面板
 * Created by Administrator on 2015/2/8.
 */
public class SQLShowerPane extends BorderPane{

    private final int defaultWidth = 300;
    private final int defaultHeight = 100;
    private final int expandWidth = 300;
    private final int expandHeight = 300;

    /**记录内容*/
    private SQLRecoder recoder;

    private Timeline showTime;
    private Timeline hideTime;


    public SQLShowerPane(SQLRecoder recoder) {
        this.recoder = recoder;
        createUserInterface();
    }

    private void createUserInterface() {
        this.setPrefSize(defaultWidth, defaultHeight);

        /***** 设置标题栏，包括记录时间， 点击次数等****/
        FlowPane titlePane = new FlowPane();
        titlePane.setHgap(10);
        titlePane.setPadding(new Insets(3));

        // 时间标签
        DateTime dateTime = new DateTime(recoder.getTimeToken());
        Label timeLabel = new Label();
        timeLabel.setText(dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS"));
        titlePane.getChildren().add(timeLabel);

        // 详情按钮
        Button button = new Button("详细");
        button.setOnAction(event -> {
            final Stage stage = new Stage();
            DetailPane detailPane = SpringContainer.me().getBean(DetailPane.class);
            detailPane.setRecoder(recoder);
            detailPane.setRootStage(stage);
            Scene scene = new Scene(detailPane, 900, 500, Color.WHITESMOKE);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        });
        titlePane.getChildren().add(button);

        /******内容面板***********/
        String description = recoder.getDescription();
        if (StringUtils.isBlank(description)) {
            description = "<没提示呢>";
        }
        TextArea descriptionArea = new TextArea(description);
        descriptionArea.setWrapText(true);
        descriptionArea.setEditable(false);
        TextArea sqlArea = new TextArea(recoder.getSql());
        sqlArea.setWrapText(true);
        sqlArea.setEditable(false);

        GridPane contentPane = new GridPane();
        contentPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        GridPane.setConstraints(descriptionArea, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(sqlArea, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        contentPane.getChildren().addAll(descriptionArea, sqlArea);
        contentPane.setPadding(new Insets(0, 0, 5, 0));

        /*************容器本身设置**************/
        // 面板布局
        this.setTop(titlePane);
        this.setCenter(contentPane);

        // 线性边框
        this.setPadding(new Insets(2, 4, 2, 4));
        this.setId("sqlShowerPane");

       // 创建展开和收起的动画
        createShowTime();
        createHideTime();

        // 展开
        this.setOnMouseEntered(event -> {
            hideTime.stop();
            showTime.play();
        });

        // 收起
        this.setOnMouseExited( event -> {
            showTime.stop();
            hideTime.play();
        });
    }

    /**
     * 设置展示动画
     */
    private void createShowTime() {
        showTime = new Timeline();
        showTime.setAutoReverse(true);
        KeyValue keyValueX = new KeyValue(this.prefWidthProperty(), this.getPrefWidth());
        KeyValue keyValueY = new KeyValue(this.prefHeightProperty(), expandHeight);
        Duration duration = Duration.millis(400);
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                SQLShowerPane.this.setPrefWidth(SQLShowerPane.this.getPrefWidth());
                SQLShowerPane.this.setPrefHeight(expandHeight);
            }
        };
        KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);
        showTime.getKeyFrames().add(keyFrame);
    }

    /**
     * 收起的动画
     */
    private void createHideTime() {
        hideTime = new Timeline();
        hideTime.setAutoReverse(true);
        KeyValue keyValueX = new KeyValue(this.prefWidthProperty(), this.getPrefWidth());
        KeyValue keyValueY = new KeyValue(this.prefHeightProperty(), defaultHeight);
        Duration duration = Duration.millis(200);
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                SQLShowerPane.this.setPrefWidth(SQLShowerPane.this.getPrefWidth());
                SQLShowerPane.this.setPrefHeight(defaultHeight);
            }
        };
        KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);
        hideTime.getKeyFrames().addAll(keyFrame);
    }
}
