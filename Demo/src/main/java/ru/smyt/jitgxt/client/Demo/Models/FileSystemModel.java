package ru.smyt.jitgxt.client.Demo.Models;

import ru.smyt.jitgxt.client.Treemap.TreemapModel;

/**
 * File system model for Treemap demonstration
 * @author Ildar Musin (c) 2012
 */
public class FileSystemModel extends TreemapModel {

    public FileSystemModel(String filename, int size) {
        this(filename, size, FileType.UNKNOWN);
    }
    public FileSystemModel(String filename, int size, FileType type) {
        super(filename, filename, size);
        set("filetype", type);
    }
    public FileSystemModel(String filename, int size, FileSystemModel[] subfolders) {
        this(filename, size, FileType.FOLDER);
        for(FileSystemModel f: subfolders)
            add(f);
    }
    public int getSize() {
        return (Integer)get("area");
    }
    public String getFilename() {
        return getId();
    }
    public FileType getFileType() {
        return get("filetype");
    }

    @Override
    public FileSystemModel getParent() {
        return (FileSystemModel)super.getParent();
    }
    
}
