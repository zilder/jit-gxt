/*
 * Treemap Node class
 */
package ru.smyt.jitgxt.client.Treemap;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import ru.smyt.jitgxt.client.Color;

/**
 * Treemap node class
 * @author Ildar Musin (c) 2012
 */
public class TreemapModel extends BaseTreeModel {

    /**
     * Constructor
     * @param id - string identifier
     */
    public TreemapModel(String id) {
        set("id", id);
        set("area", 100);
    }
    
    /**
     * Constructor
     * @param id - string identifier
     * @param title - node title
     */
    public TreemapModel(String id, String title) {
        this(id);
        set("title", title);
    }
    
    /**
     * Constructor
     * @param id - string identifier
     * @param title - node title
     * @param area - relative square of the node
     */
    public TreemapModel(String id, String title, int area) {
        this(id, title);
        set("area", area);
    }
    
    /**
     * Constructor
     * @param id - string identifier
     * @param title - node title
     * @param area - relative square of the node
     * @param color - color object
     */
    public TreemapModel(String id, String title, int area, Color color) {
        this(id, title, area);
        set("color", color);
    }
    
    /**
     * Constructor
     * @param id - string identifier
     * @param title - node title
     * @param color - color object
     */
    public TreemapModel(String id, String title, Color color) {
        this(id, title);
        //this.color = color;
        set("color", color);
    }
    
    /**
     * Constructor
     * @param id - string identifier
     * @param title - node title
     * @param area - relative square of the node
     * @param children - children array
     */
    public TreemapModel(String id, String title, int area, TreemapModel[] children) {
        this(id, title, area);
        addChildren(children);
    }
    
    private void addChildren(TreemapModel[] children) {
        for(TreemapModel n: children)
            add(n);
    }

    /**
     * Append node to the children collection
     * @param node - the node to add
     */
    //public void append(TreemapModel node) {
    //    add(node);
    //}
    
    /**
     * Remove node from children collection
     * @param node 
     */
    //public void remove(TreemapModel node) {
    //    remove(node);
    //}

    /**
     * Get nodes string identifier
     * @return string identifier
     */
    public String getId() {
        //return id;
        return get("id");
    }

    /**
     * Get color object
     * @return color object
     */
    public Color getColor() {
        return get("color");
    }

    public void setColor(Color color) {
        set("color", color);
    }

    public String getTitle() {
        return get("title");
    }

    public void setTitle(String title) {
        set("title", title);
    }

    public int getArea() {
        return (Integer)get("area");
    }

    /**
     * Set the relative square of this node
     * @param area - relative square (default is 100)
     */
    public void setArea(int area) {
        set("area", area);
    }

    /**
     * Gets node data object
     */
    public Object getData() {
        return get("data");
    }

    /**
     * Set node data object
     * @param data - any object
     */
    public void setData(Object data) {
        set("data", data);
    }

    /**
     * Visitor acceptor. The implementation of the visitor pattern
     * @param visitor - visitor object of ITreemapVisitor type
     */
    public void acceptVisitor(ITreemapVisitor visitor) {
        visitor.visit(this);
    }
}
