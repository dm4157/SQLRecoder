package org.syy.sqlrecoder.util;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 * BorderPane
 * 在传入的node外围包一个BorderPane
 * Created by Administrator on 2015/2/14.
 */
public class LayoutUtil {

    /**
     * 在传入的node外围包一个BorderPane
     * @param node
     * @return
     */
    public static BorderPane warpWithBorderPane(Node node) {
        BorderPane pane = new BorderPane();
        pane.setCenter(node);
        return pane;
    }

    public static FlowPane warpWithFlowPane(Node node) {
        FlowPane pane = new FlowPane();
        pane.getChildren().add(node);
        return pane;
    }
}
