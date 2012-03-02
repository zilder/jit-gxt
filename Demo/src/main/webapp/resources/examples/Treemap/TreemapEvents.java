package ru.smyt.jitgxt.client.Demo.Treemap;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.Element;
import ru.smyt.jitgxt.client.Demo.ExampleBase;
import ru.smyt.jitgxt.client.Demo.Models.FileSystemModel;
import ru.smyt.jitgxt.client.Demo.Models.Models;
import ru.smyt.jitgxt.client.Events.JitEvents;
import ru.smyt.jitgxt.client.Events.NodeEvent;
import ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap;

/**
 *
 * @author Ildar Musin (c) 2012
 */
public class TreemapEvents extends ExampleBase {
    
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setLayout(new HBoxLayout());
        final Html info_html = new Html();
        
        final SquarifiedTreemap map = new SquarifiedTreemap();
        map.load(Models.createFileSystemModel());
        map.setSize(500, 500);

        map.addListener(JitEvents.OnNodeClick, new Listener<NodeEvent>() {
            public void handleEvent(NodeEvent be) {
                FileSystemModel node = (FileSystemModel) be.getNode();
                map.enter(node);
            }
        });
        map.addListener(JitEvents.OnMouseRightClick, new Listener<BaseEvent>() {
            public void handleEvent(BaseEvent be) {
                map.out();
            }
        });
        
        map.addListener(JitEvents.OnMouseEnter, new Listener<NodeEvent>() {
            public void handleEvent(NodeEvent be) {
                info_html.setHtml(formatFileInfo( (FileSystemModel) be.getNode()));
            }
        });
        
        map.addListener(JitEvents.OnMouseLeave, new Listener<NodeEvent>() {
            public void handleEvent(NodeEvent be) {
                info_html.setHtml("Select treemap node");
            }
        });

        add(map);
        add(info_html, new HBoxLayoutData(0, 0, 0, 5));
    }
    
    private String formatFileInfo(FileSystemModel model) {
        String info = "<div class='file_info'><img src='resources/images/";
        switch(model.getFileType()) {
            case FOLDER: info += "icon_folder.png"; break;
            case JS:     info += "icon_js.png"; break;
            case HTML:   info += "icon_html.png"; break;
            default:     info += "icon_unknown.png"; break;
        }
        info += "'/><br/>";
        info += model.getFilename() + "<br/>";
        info += "(" + model.getSize() + " bytes)</div>";
        
        return info;
    }
}
