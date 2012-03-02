package ru.smyt.jitgxt.client.Demo.Treemap;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import ru.smyt.jitgxt.client.Demo.Models.FileSystemModel;
import ru.smyt.jitgxt.client.Demo.Models.Models;
import ru.smyt.jitgxt.client.Events.JitEvents;
import ru.smyt.jitgxt.client.Events.NodeEvent;
import ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap;
import ru.smyt.jitgxt.client.Treemap.ITipTemplate;
import ru.smyt.jitgxt.client.Treemap.TreemapModel;
import com.google.gwt.user.client.Element;
import ru.smyt.jitgxt.client.Demo.ExampleBase;

/**
 *
 * @author Ildar Musin (c) 2012
 */
public class TreemapTips extends ExampleBase {
    
    class FileSystemTip implements ITipTemplate {
        public String getHtml(TreemapModel node) {
            FileSystemModel n = (FileSystemModel)node;
            String ret = "<img src='resources/images/";
            switch(n.getFileType()) {
                case FOLDER: ret += "icon_folder.png"; break;
                case JS: ret += "icon_js.png"; break;
                case HTML: ret += "icon_html.png"; break;
                default: ret += "icon_unknown.png"; break;
            }
            ret += "' /><br/>" + node.getTitle() + "<br/>File size: " + node.getArea();
            return ret;
        }
    }
    
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        final SquarifiedTreemap map = new SquarifiedTreemap();
        final TreemapModel data = Models.createFileSystemModel();
        map.load(data);
        map.setSize(500, 500);
        
        map.addListener(JitEvents.OnNodeClick, new Listener<NodeEvent>() {
            public void handleEvent(NodeEvent be) {
                map.enter(be.getNode());
            }
        });
        map.addListener(JitEvents.OnMouseRightClick, new Listener<BaseEvent>() {
            public void handleEvent(BaseEvent be) {
                map.out();
            }
        });

        map.setTip(new FileSystemTip());
        add(map);
    }
}
