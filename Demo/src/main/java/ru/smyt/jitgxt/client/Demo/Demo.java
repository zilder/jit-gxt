package ru.smyt.jitgxt.client.Demo;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.core.FastMap;
import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import ru.smyt.jitgxt.client.Demo.Treemap.BasicTreemap;
import ru.smyt.jitgxt.client.Demo.Treemap.TreemapEvents;
import ru.smyt.jitgxt.client.Demo.Treemap.TreemapTips;


/**
 * Demo application
 * @author Ildar Musin (c) 2012
 */
public class Demo extends LayoutContainer {
    private FastMap<ExamplePage> openedExamples = new FastMap<ExamplePage>();
    
    class ExampleItem extends BaseTreeModel {
        public ExampleItem(String name, ExampleItem[] children) {
            set("name", name);
            for(ExampleItem e: children)
                add(e);
        }
        public ExampleItem(String name, String source, IExampleCreator creator) {
            set("name", name);
            set("creator", creator);
            set("source", source);
        }
        public String getName() {
            return (String) get("name");
        }
        public String getSource() {
            return (String) get("source");
        }
        public IExampleCreator getCreator() {
            return (IExampleCreator) get("creator");
        }
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setLayout(new BorderLayout());

        HorizontalPanel panel = new HorizontalPanel();
        final TabPanel tabPanel = new TabPanel();
        
        TreeStore<ModelData> store = new TreeStore<ModelData>();
        store.add(createMenu().getChildren(), true);
        
        TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
        tree.setDisplayProperty("name");

        tree.addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>() {

            public void handleEvent(TreePanelEvent<ModelData> be) {
                ExampleItem item = (ExampleItem) be.getItem();
                
                // Check if example is already opened
                if( openedExamples.containsKey(item.getName()) ) {
                    // Select existing page
                    tabPanel.setSelection( openedExamples.get(item.getName()) );
                }
                // Else open new example page
                else {
                    IExampleCreator creator = item.getCreator();
                    if(creator != null) {
                        ExampleBase e = creator.create();
                        ExamplePage page = new ExamplePage(item.getName(), e, item.getSource());
                        tabPanel.add(page);
                        tabPanel.setSelection(page);

                        openedExamples.put(item.getName(), page);

                        // Catch the close event
                        page.addListener(Events.Close, new Listener<TabPanelEvent>() {
                            public void handleEvent(TabPanelEvent be) {
                                // Remove this page instance from openedExamples map
                                ExamplePage p = (ExamplePage) be.getItem();
                                openedExamples.remove(p.getText());
                                int i = 5;
                            }
                        }); // On tab close listener

                    } // if(creator != null)
                } // else
            } // handleEvent
        }); // On menu item click listener

        ContentPanel westPanel = new ContentPanel();
        ToolBar toolBar = new ToolBar();
        westPanel.setTopComponent(toolBar);
        westPanel.setLayout(new FitLayout());
        westPanel.setHeading("Navigation");
        westPanel.add(tree, new FitData(3));

        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(5, 5, 5, 0));
        add(tabPanel, centerData);
        
        BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 250, 150, 300);
        westData.setMargins(new Margins(5));
        add(westPanel, westData);
    }
    
    /**
     * Create menu
     * @return root of the menu
     */
    private ExampleItem createMenu() {
        ExampleItem[] examples = new ExampleItem[] {
            new ExampleItem("Treemap", new ExampleItem[]{
                new ExampleItem("Basic Treemap", "Treemap/BasicTreemap.java", new IExampleCreator() {
                    public ExampleBase create() { return new BasicTreemap(); }
                }),
                new ExampleItem("Treemap Events", "Treemap/TreemapEvents.java", new IExampleCreator() {
                    public ExampleBase create() { return new TreemapEvents(); }
                }),
                new ExampleItem("Treemap Tips", "Treemap/TreemapTips.java", new IExampleCreator() {
                    public ExampleBase create() { return new TreemapTips(); }
                }),
            })
        };
        ExampleItem root = new ExampleItem("Root", examples);
        return root;
    }
}
