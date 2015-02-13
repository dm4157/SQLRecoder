package org.syy.sqlrecoder.util;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * BorderPane
 * 在传入的node外围包一个BorderPane
 * Created by Administrator on 2015/2/14.
 */
public class BorderLayoutUtil {

    /**
     * 在传入的node外围包一个BorderPane
     * @param node
     * @return
     */
    public static BorderPane warpWithBorderPane(Node node) {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: red");
        pane.setCenter(node);
        return pane;
    }
}
