package ru.smyt.jitgxt.client.Treemap;

import ru.smyt.jitgxt.client.Treemap.TreemapModel;

/**
 * Standart title tip. Used by default.
 * @author Ildar Musin (c) 2012
 */
public class SimpleTipTemplate implements ITipTemplate {

    public String getHtml(TreemapModel node) {
        //return "<div style=\"width: 100px; border: 1px solid #000; background-color: #FFF;\">" + node.getTitle() + "</div>";
        return node.getTitle();
    }

}
