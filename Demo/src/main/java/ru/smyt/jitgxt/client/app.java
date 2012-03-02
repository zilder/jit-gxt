package ru.smyt.jitgxt.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import ru.smyt.jitgxt.client.Demo.Demo;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class app implements EntryPoint {

    //private final MyServiceAsync testService = GWT.create(MyService.class);
  
    @Override public void onModuleLoad() {
        
        Viewport viewport = new Viewport();
        viewport.setLayout(new BorderLayout());
        
        Demo demo = new Demo();

        viewport.add(demo, new BorderLayoutData(LayoutRegion.CENTER));
        RootPanel.get().add(viewport);
        
        
        //RootPanel.get().add(new Html("Hello world"));
        
        /*Button btn = new Button("Test", new ClickHandler() {

            public void onClick(ClickEvent event) {
                //test_jit();
                System.out.print("test");
            }
        });
        
        final SquarifiedTreemap map = new SquarifiedTreemap();

        FormPanel form = new FormPanel();

        final TextField<String> idField = new  TextField<String>();
        idField.setTitle("Id");
        form.add(idField);

        final TextField<String> nameField = new TextField<String>();
        nameField.setTitle("Name");
        form.add(nameField);

        Window wnd = new Window();
        wnd.setHeading("Squarified Treemap");
        wnd.setLayout(new FitLayout());
        wnd.setSize(500, 300);
        
        TreemapNode root = new TreemapNode("root", "Root");
        TreemapNode node1;
        root.append(node1 = new TreemapNode("node1", "Node 1"));
        node1.append(new TreemapNode("node1_1", "Node 1.1", new Color(210, 210, 230)));
        root.append(new TreemapNode("node2", "Node 2", new Color(210, 230, 210)));
        root.setData(new Color(255, 255, 0));

        ITreemapVisitor visitor = new JsonTreemapVisitor();
        root.acceptVisitor(visitor);
        
        TextArea area = new TextArea();
        area.setSize("500px", "300px");
        area.setValue(visitor.toString());
        
        map.load(root);
        map.addListener(Events.Select, new Listener<NodeSelectionEvent>() {
            public void handleEvent(NodeSelectionEvent be) {
                idField.setValue(be.getNode().getId());
                nameField.setValue(be.getNode().getTitle());
            }
        });

        map.setSize(500, 300);
        map.setTip(new DataTipTemplate() {
            @Override
            public String getDataHtml(TreemapNode node) {
                Object obj = node.getData();
                if(obj != null) {
                    Color c = (Color)obj;
                    return "<div style=\"background-color: rgb(" + c.r() + ',' + c.g() + ',' + c.b() + ")\">color</div>";
                } else
                    return "";
            }
        });
        RootPanel.get().add(map);
        //wnd.add(map);
        //wnd.show();

        /*TreemapNode root2 = new TreemapNode("root2", "ident");
        root2.append(new TreemapNode("c1", "c1_id"));
        root2.append(new TreemapNode("c2", "c2_id"));
        SquarifiedTreemap map2 = new SquarifiedTreemap();
        map2.load(root2);
        
        RootPanel.get().add(map2);*/
        /*RootPanel.get().add(form);
        RootPanel.get().add(area);
        RootPanel.get().add(btn);*/
        
        //map.start();

        //map.init();

        /*MyServiceAsync.Util.getInstance().myMethod("test", new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                MessageBox.info("Message", "Failure! " + caught.getMessage(), null);
            }
            public void onSuccess(String result) {
                MessageBox.info("Message", "The message is: " + result, null);
            }
        });*/

        //Viewport viewPort = new Viewport();
        //viewPort.setLayout(new VBoxLayout());
        
        //final SquarifiedTreemap map = new SquarifiedTreemap();
        //viewPort.add(map);
        
        /*Button button = new Button("Test", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                //MessageBox.prompt("test", "test");
                map.init();
                //MessageBox.prompt("test", "test");
            }
        }) ;
        viewPort.add(button);*/
        
        /*FormPanel formPanel = new FormPanel();
        
        formPanel.addButton(new Button("Test", new SelectionListener<ButtonEvent>(){
            public void componentSelected(ButtonEvent ce) {
                Window.alert("Test");
            }}));
        viewPort.add(formPanel);*/
        //RootPanel.get().add(viewPort);
    }
}


