package ru.smyt.jitgxt.client.Events;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import ru.smyt.jitgxt.client.Treemap.TreemapModel;

/**
 *
 * @author Ildar Musin (c) 2012
 */
public class NodeEvent extends BaseEvent {
    private TreemapModel node;

    public NodeEvent(TreemapModel node, EventType type) {
        super(type);
        this.node = node;
    }

    public TreemapModel getNode() {
        return node;
    }
}
