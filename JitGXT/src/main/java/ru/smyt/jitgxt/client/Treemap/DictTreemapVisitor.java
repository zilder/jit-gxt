package ru.smyt.jitgxt.client.Treemap;

import com.extjs.gxt.ui.client.core.FastMap;
import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Dictionary treemap visitor
 * @author Ildar Musin (c) 2012
 */
public class DictTreemapVisitor implements ITreemapVisitor {
    //private HashMap<String, TreemapNode> map = new HashMap<String, TreemapNode>();
    private FastMap<TreemapModel> map = new FastMap<TreemapModel>();
    private String prefix;

    public DictTreemapVisitor(TreemapModel node) {
        this(node, "");
    }
    
    public DictTreemapVisitor(TreemapModel node, String prefix) {
        this.prefix = prefix;
        map.clear();
        node.acceptVisitor(this);
    }

    public void visit(TreemapModel node) {
        map.put(prefix + node.getId(), node);

        for(ModelData n: node.getChildren())
            ((TreemapModel)n).acceptVisitor(this);
    }
    
    public FastMap<TreemapModel> getDict() {
        return this.map;
    }

}
