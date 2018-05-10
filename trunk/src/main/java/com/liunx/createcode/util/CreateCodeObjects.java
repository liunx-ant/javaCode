package com.liunx.createcode.util;

import java.util.ArrayList;
import java.util.List;

public class CreateCodeObjects {
    private String author;
    private String version;
    private String copyright;
    private CreateCodeObject mainObject;
    private List<CreateCodeObject> objects=new ArrayList<CreateCodeObject>();
    private List<CreateCodeObject> relObjects=new ArrayList<CreateCodeObject>();
    
    
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getCopyright() {
        return copyright;
    }
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    public List<CreateCodeObject> getObjects() {
        return objects;
    }
    public void setObjects(List<CreateCodeObject> objects) {
        this.objects = objects;
    }
    public CreateCodeObject getMainObject() {
        for(CreateCodeObject object:objects){
            if(object.getIsMain()){
                mainObject=object;
                break;
            }
        }
        return mainObject;
    }
    public void setMainObject(CreateCodeObject mainObject) {
        this.mainObject = mainObject;
    }
    public List<CreateCodeObject> getRelObjects() {
        
        for(CreateCodeObject object:objects){
            if(!object.getIsMain()){
                relObjects.add(object);
            }
        }
        return relObjects;
    }
    public void setRelObjects(List<CreateCodeObject> relObjects) {
        this.relObjects = relObjects;
    }
    
}
