package ru.smyt.jitgxt.client.Demo.Treemap;

import com.google.gwt.user.client.Element;
import ru.smyt.jitgxt.client.Color;
import ru.smyt.jitgxt.client.Demo.ExampleBase;
import ru.smyt.jitgxt.client.Treemap.SquarifiedTreemap;
import ru.smyt.jitgxt.client.Treemap.TreemapModel;

/**
 *
 * @author Ildar Musin (c) 2012
 */
public class BasicTreemap extends ExampleBase {
    
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        final SquarifiedTreemap map = new SquarifiedTreemap();
        map.load(createModel());
        map.setSize(500, 500);
        add(map);
    }
    
    private TreemapModel createModel() {
        TreemapModel root = new TreemapModel("world", "World", 100, new TreemapModel[] {
            new TreemapModel("europe", "Europe", 10500, new Color(50, 230, 50)),
            new TreemapModel("asia", "Asia", 43400, new Color(255, 100 , 100)),
            new TreemapModel("africa", "Africa", 29200, new Color(240, 200, 150)),
            new TreemapModel("america", "America", 42480, new Color(150, 100, 230)),
            new TreemapModel("australia", "Australia", 8560, new Color(180, 220, 50)),
            new TreemapModel("antarctica", "Antarctica", 14000, new Color(100, 100, 255))
        });
        return root;
    }
}
