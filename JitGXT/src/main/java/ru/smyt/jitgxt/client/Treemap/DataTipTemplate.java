package ru.smyt.jitgxt.client.Treemap;

import ru.smyt.jitgxt.client.Treemap.TreemapModel;

/**
 * Tip template with data section. Allows to redefine the way in which the node 
 * data will be presented
 * @author Ildar Musin (c) 2012
 */
public abstract class DataTipTemplate implements ITipTemplate {

    /**
     * Return HTML formatted data. Define your formatting code here
     * @param node - treemap node
     * @return HTML formatted data
     */
    public abstract String getDataHtml(TreemapModel node);
    
    public String getHtml(TreemapModel node) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div style=\"width: 100px; border: 1px solid black; background-color: #FFF;\">");
        sb.append(node.getTitle()).append("<br>");
        sb.append(getDataHtml(node));
        sb.append("</div>");

        return sb.toString();
    }
    
}
