package org.syy.sqlrecoder.gui.components;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * Created by Administrator on 2015/2/13.
 */
public class ResizeFlowPane extends FlowPane{

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        ObservableList<Node> children = this.getChildren();
        for (Node node : children) {
            if (node instanceof SQLShowerPane) {
                ((SQLShowerPane) node).setPrefWidth((width-31)/2);
            }
        }
    }
}
