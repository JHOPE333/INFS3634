package com.example.jianshi.mydatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//this place is where table metadata is held

@Entity
public class ModuleChecker {

    @PrimaryKey (autoGenerate = true)
    private int id;

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "module")
    private String module;

    @ColumnInfo (name = "complete")
    private Boolean complete;

    public ModuleChecker(String module) {
        this.module = module;
        this.complete = Boolean.FALSE;

    }


    public String getModule() {
        return module;
    }

    public void setModule(String Module) {
        this.module = module;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete= complete;
    }


}