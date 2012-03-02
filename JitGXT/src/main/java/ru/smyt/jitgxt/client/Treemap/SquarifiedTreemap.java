package ru.smyt.jitgxt.client.Treemap;

import com.extjs.gxt.ui.client.core.FastMap;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import ru.smyt.jitgxt.client.Events.JitEvents;
import ru.smyt.jitgxt.client.Events.NodeEvent;

/**
 *
 * @author Ildar Musin (c) 2012
 */
public class SquarifiedTreemap extends BoxComponent {

    /** Treemap identifier */
    private String id;
    
    /** The root of the Treemap data model */
    private TreemapModel root;
    
    /** Whether to animate transitions */
    private boolean animationEnabled = true;

    /** Key-value map containing Treemap node objects */
    //private HashMap<String, TreemapNode> dict = new FastMap<TreemapNode>();
    private FastMap<TreemapModel> dict = new FastMap<TreemapModel>();
    
    /** JavaScript Treemap object */
    private JavaScriptObject tm;
    
    /** Template to be used for tips displaying */
    private ITipTemplate tip;
    
    /** Number of levels to show */
    private int levelsToShow = 3;
    
    /** Parents box title heights */
    private int titleHeight = 15;

    private boolean firstRender = false;

    /**
     * Constructor
     */
    public SquarifiedTreemap() {
        id = HTMLPanel.createUniqueId();
        this.root = new TreemapModel(id);
        this.tip = new SimpleTipTemplate();
    }

    /**
     * Load nodes to treemap
     * @param root - the root containing rest nodes
     */
    public void load(TreemapModel root) {
        this.root = root;
        updateDictionary(root);
    }

    /**
     * Update hashmap containing key-value entry for each node. Dictionary
     * allows quick search for a node by its identifier
     */
    private void updateDictionary(TreemapModel root) {
        String prefix = id + "_";
        DictTreemapVisitor visitor = new DictTreemapVisitor(root, id + "_");
        dict = visitor.getDict();
    }

    @Override
    protected void onResize(int width, int height) {
        super.onResize(width, height);
        if(!firstRender)
            resize(tm, width, height);
    }
    
    @Override
    protected void onRender(Element target, int index) {
        if(el() ==null) {
            firstRender = true;
            Element x = DOM.createDiv();
            x.setAttribute("height", "100px");
            x.setAttribute("width", "100px");
            x.setAttribute("style", "width: 200px; height: 200px; border: 1px solid #000;");
            setElement(x);
            el().insertInto(target, index);
            getElement().setAttribute("id", id);
        }
        super.onRender(target, index);
    }

    @Override
    protected void afterRender() {
        super.afterRender();
        if(firstRender) {
            start();
            firstRender = false;
        }
    }

    /**
     * On node click event handler This method is invoked
     * from inside of javascript.
     * @param node_id String identifier of clicked node
     */
    private void onClick(String node_id) {
        TreemapModel node = dict.get(node_id);
        for(Listener<? extends BaseEvent> event: getListeners(JitEvents.OnNodeClick)) {
            Listener<NodeEvent> e = (Listener<NodeEvent>) event;
            e.handleEvent(new NodeEvent(node, JitEvents.OnNodeClick));
        }
    }
    
    /* Not implemented yet */
    private void onMouseEnter(String node_id) {
        TreemapModel node = dict.get(node_id);
        for(Listener<? extends BaseEvent> event: getListeners(JitEvents.OnMouseEnter)) {
            Listener<NodeEvent> e = (Listener<NodeEvent>) event;
            e.handleEvent(new NodeEvent(node, JitEvents.OnMouseEnter));
        }
    }
    private void onMouseLeave(String node_id) {
        TreemapModel node = dict.get(node_id);
        for(Listener<? extends BaseEvent> event: getListeners(JitEvents.OnMouseLeave)) {
            Listener<NodeEvent> e = (Listener<NodeEvent>) event;
            e.handleEvent(new NodeEvent(node, JitEvents.OnMouseLeave));
        }
    }

    private void onRightClick() {
        for(Listener<? extends BaseEvent> event: getListeners(JitEvents.OnMouseRightClick)) {
            Listener<BaseEvent> e = (Listener<BaseEvent>) event;
           e.handleEvent(new BaseEvent(JitEvents.OnMouseRightClick));
        }
    }
    
    /**
     * On tip show event handler This method is invoked
     * from inside of javascript.
     * @param node_id String identifier of clicked node
     */
    private String onShowTip(String node_id) {
        //Window.alert("onShowTip(" + node_id + ")");
        TreemapModel node = dict.get(node_id);
        if(node == null)
            Window.alert("Node is null");
        return tip.getHtml(node);
    }

    private native void refresh(JavaScriptObject tm) /*-{
      tm.refresh();
    }-*/;

    private native void resize(JavaScriptObject tm, int width, int height) /*-{
      tm.canvas.resize(width, height);
    }-*/;
    
    private native void plot(JavaScriptObject tm) /*-{
      tm.plot();
    }-*/;
    
    public void start() {
        // generate json data string
        ITreemapVisitor visitor = new JsonTreemapVisitor(id + "_");
        root.acceptVisitor(visitor);
        String json = "var data = " + visitor.toString();

        tm = start(id, json);
    }

    /**
     * Creates Jit Treemap object and links it with this.getElement() by its
     * identifier
     * @param div_id identifier of this components DIV
     * @param json_data JSON data as string
     * @return Method returns JavaScript Treemap object. It's used for further calls
     */
    private native JavaScriptObject start(String div_id, String json_data) /*-{
        eval(json_data);
        var thiz = this;
        var tm = new $wnd.$jit.TM.Squarified({
            injectInto: div_id,
            //levelsToShow: thiz.@com.newlookphoto.app.client.jit.SquarifiedTreemap::levelsToShow,
            levelsToShow: thiz.@ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap::levelsToShow,
            titleHeight: 15,
            offset: 1,
            Label: {
              type: 'Native',
              size: 9,
              family: 'Tahoma, Verdana, Arial'
            },
            //enable specific canvas styles
            //when rendering nodes
            Node: {
              CanvasStyles: {
                shadowBlur: 0,
                shadowColor: '#000'
              }
            },
            //Node.type: 'circle',
            Events: {
                    enable: true,
                    onClick: function(node) {
                        if(node) {
                            //tm.enter(node);
                            thiz.@ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap::onClick(Ljava/lang/String;)(node.id);
                        }
                    },
                    onRightClick: function() {
                            //tm.out();
                            thiz.@ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap::onRightClick()();
                    },
                    onMouseEnter: function(node, eventInfo) {  
                        //alert('in');
                        if(node) {
                          thiz.@ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap::onMouseEnter(Ljava/lang/String;)(node.id);
                        }
                    },  
                    onMouseLeave: function(node) {
                        //alert('out');
                        if(node)
                          thiz.@ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap::onMouseLeave(Ljava/lang/String;)(node.id); 
                    } 
            },
            Tips: {
                enable: true,
                type: 'Native',
                offsetX: 20,
                offsetY: 20,
                onShow: function(tip, node, isLeaf, domElement) {
                    tip.innerHTML = thiz.@ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap::onShowTip(Ljava/lang/String;)(node.id);
                }
            },
            duration: 100,
            animate: thiz.@ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap::animationEnabled,
            onCreateLabel: function(domElement, node){
                domElement.innerHTML = node.name;
                var style = domElement.style;
                style.display = '';
                style.border = '1px solid transparent';
                domElement.onmouseover = function() {
                  style.border = '1px solid #9FD4FF';
                };
                domElement.onmouseout = function() {
                  style.border = '1px solid transparent';
                };
            }
	});
	tm.loadJSON(data);
	tm.refresh();
        return tm;
    }-*/;

    /**
     * Sets template for tips display. Use ITipTemplate as an interface
     * or DataTipTemplate as a superclass for writing your own template.
     * @param tip Tips template. Default is SimpleTipTemplate
     * @see ITipTemplate
     * @see DataTipTemplate
     * @see SimpleTipTemplate
     */
    public void setTip(ITipTemplate tip) {
        this.tip = tip;
    }

    /**
     * @return Returns the number of levels to show
     */
    public int getLevelsToShow() {
        return levelsToShow;
    }

    /**
     * The number of levels to show for a subtree
     * @param levelsToShow   the number of levels to show. Default is 3
     */
    public void setLevelsToShow(int levelsToShow) {
        this.levelsToShow = levelsToShow;
    }
    
    /**
     * Enter to the nodes level
     * @param node 
     */
    public void enter(TreemapModel node) {
        String node_id = id + '_' + node.getId();
        enter(tm, node_id);
        //plot(tm);
    }
    
    private native void enter(JavaScriptObject tm, String node_id) /*-{
        tm.enter(tm.graph.getNode(node_id));
    }-*/;
    
    /**
     * Go out to the upper level
     */
    public void out() {
        out(tm);
    }
    
    private native void out(JavaScriptObject tm) /*-{
        tm.out();
    }-*/;

    public boolean isAnimationEnabled() {
        return animationEnabled;
    }

    public void setAnimationEnabled(boolean animationEnabled) {
        this.animationEnabled = animationEnabled;
        setAnimationEnabledJS(tm, animationEnabled);
        
    }
    
    private native void setAnimationEnabledJS(JavaScriptObject tm, boolean flag) /*-{
        tm.animate = flag;
    }-*/;
}
