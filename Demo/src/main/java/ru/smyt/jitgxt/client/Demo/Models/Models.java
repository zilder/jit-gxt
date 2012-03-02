package ru.smyt.jitgxt.client.Demo.Models;

/**
 *
 * @author Ildar Musin (c) 2012
 */
public class Models {

    public static FileSystemModel createFileSystemModel() {
        FileSystemModel root = new FileSystemModel("Jit", 1718883, new FileSystemModel[]{
            new FileSystemModel("Examples", 1024596, new FileSystemModel[] {
                new FileSystemModel("AreaChart", 5865, new FileSystemModel[] {
                    //new FileSystemModel("example1.html", 1785),
                    //new FileSystemModel("example1.js", 4080)
                }),
                new FileSystemModel("BarChart", 10824, new FileSystemModel[] {
                    //new FileSystemModel("example1.html", 1577),
                    //new FileSystemModel("example1.js", 3870),
                    //new FileSystemModel("example2.html", 1581),
                    //new FileSystemModel("example2.js", 3796)
                }),
                new FileSystemModel("css", 41322, FileType.FOLDER),
                new FileSystemModel("ForceDirection", 35135, FileType.FOLDER),
                new FileSystemModel("Hypertree", 51179, FileType.FOLDER),
                new FileSystemModel("Icicle", 499407, FileType.FOLDER),
                new FileSystemModel("Other", 37930, FileType.FOLDER),
                new FileSystemModel("PieChart", 5307, FileType.FOLDER),
                new FileSystemModel("RGraph", 84098, FileType.FOLDER),
                new FileSystemModel("Spacetree", 127397, FileType.FOLDER),
                new FileSystemModel("Sunburst", 40406, FileType.FOLDER),
                new FileSystemModel("Treemap", 85726, FileType.FOLDER)
            }),
            new FileSystemModel("Extras", 41209, new FileSystemModel[]{
                new FileSystemModel("excanvas.js", 41209, FileType.JS)
            }),
            new FileSystemModel("jit-yc.js", 162463, FileType.JS),
            new FileSystemModel("jit.js", 490615, FileType.JS)
        });

        return root;
    }
}
