package ru.smyt.jitgxt.client.Treemap;

import com.extjs.gxt.ui.client.data.ModelData;
import java.util.ListIterator;
import ru.smyt.jitgxt.client.Color;

/**
 * JSON format visitor for tree map model
 * @author Ildar Musin (c) 2012
 */
public class JsonTreemapVisitor implements ITreemapVisitor {
    private int currentLevel = 0;
    private String prefix = "";
    private StringBuilder sb = new StringBuilder();
    
    public JsonTreemapVisitor() {}
    public JsonTreemapVisitor(String prefix) {
        this.prefix = prefix;
    }

    public void visit(TreemapModel node) {
        String indent = "";
        for(int i=0; i<currentLevel; ++i)
            indent += '\t';

        sb.append(indent).append("{\n");
        
        sb.append(indent).append("'id': '").append(prefix).append(node.getId()).append("',\n");
        sb.append(indent).append("'name': '").append(node.getTitle()).append("',\n");
        
        // children node
        sb.append(indent).append("'children': ");
        if(node.getChildren().size() > 0) {
            sb.append("[\n");

            // increase indentation level
            currentLevel++;

            // going through child nodes
            ListIterator<ModelData> it = node.getChildren().listIterator();
            while(it.hasNext()) {
                TreemapModel n = (TreemapModel) it.next();
                n.acceptVisitor(this);
                if(it.hasNext())
                    sb.append(",\n");
            }
            sb.append("\n");

            // decrease indentation level back
            currentLevel--;

            sb.append(indent).append("],\n");
        } else {
            sb.append("[],\n");
        }
        
        // data node
        sb.append(indent).append("'data': {\n");
        if(node.getColor() != null) {
            Color c = node.getColor();
            sb.append(indent).append("\t'$color': 'rgb(" +
                    c.r() + ',' +
                    c.g() + ',' +
                    c.b() + ")',\n");
        }
        sb.append(indent).append("\t'$area': ").append(node.getArea() + "\n");
        sb.append(indent).append("}\n");

        sb.append(indent).append("}");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
    
}
