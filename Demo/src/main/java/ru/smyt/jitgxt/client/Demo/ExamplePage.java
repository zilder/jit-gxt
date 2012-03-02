package ru.smyt.jitgxt.client.Demo;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ScrollPanel;


/**
 *
 * @author Ildar Musin (c) 2012
 */
public class ExamplePage extends TabItem {
    private Component example;
    private String srcFilename;
    private Window srcWindow;
    private String title;
    
    /**
     * Creates tab items containing example widget and source code
     * @param example
     * @param srcFilename 
     */
    public ExamplePage(String title, Component example, final String srcFilename) {
        super(title);
        this.example = example;
        this.srcFilename = srcFilename;
        this.title = title;
        
        setClosable(true);
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        LayoutContainer panel = new LayoutContainer();
        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);
        
        // add sample widget
        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(5));
        panel.add(example, centerData);

        Button sourceBtn = new Button("Source", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if(srcWindow == null)
                    srcWindow = createSourceWindow(srcFilename);
                srcWindow.show();
            }
        });

        BorderLayoutData eastData = new BorderLayoutData(LayoutRegion.EAST);
        eastData.setMargins(new Margins(5));
        panel.add(sourceBtn, eastData);
        
        this.add(panel);
    }
    
    /**
     * Window apearing on "Source" button click
     * @param srcFilename Source file name
     * @return window object
     */
    private Window createSourceWindow(String srcFilename) {
        final Html html = new Html();
        srcFilename = GWT.getHostPageBaseURL() + "resources/examples/" + srcFilename;
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(srcFilename));
        
        try {
          Request request = builder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
               
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode()) {
                  String code = response.getText();
                  code = code.replace("<", "&lt;").replace(">", "&gt;");
                  String s = "<pre class='prettyprint linenums:1' style='background-color: #fff;'><code>";
                  s += code;
                  s += "</code></pre>";
                  html.setHtml( prettyPrint(s) );
                  html.setSize("100%", "100%");
              } else {
                  html.setHtml(response.getStatusText());
              }
            }       
          });
        } catch (RequestException e) {
            
        }
        
        Window wnd = new Window();
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setHeight("100%");
        scrollPanel.add(html);
        wnd.add(scrollPanel);
        wnd.setSize(500, 500);
        return wnd;
    }

    /**
     * Call the JavaScript library for syntax highlighting
     * @param code Java code string
     * @return 
     */
    private native String prettyPrint(String code) /*-{
        return $wnd.prettyPrintOne(code, "", true);
    }-*/;
}
