package com.tactc.galeri;

public class MediaFile {
    private String path;
    private int type;

    public static final int TYPE_IMAGE=0;
    public static final int TYPE_VIDEO=1;

    public MediaFile(String path, int type){
        this.path = path;
        this.type = type;
    }
    public String getPath(){
        return path;
    }
    public int getType(){
        return type;
    }
}