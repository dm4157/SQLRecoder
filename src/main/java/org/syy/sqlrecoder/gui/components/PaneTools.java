package org.syy.sqlrecoder.gui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

/**
 * 用来创建一些常用的面板
 * Created by Administrator on 2015/2/11.
 */
public class PaneTools {

    /**
     * 创建组件居中的flowPane
     * @param insets
     * @return
     */
    public static FlowPane createCenterFlowPane(Insets insets) {
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(insets);
        flowPane.setPrefSize(50, 50);
        return flowPane;
    }
}
