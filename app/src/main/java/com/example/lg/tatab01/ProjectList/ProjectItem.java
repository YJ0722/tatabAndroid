package com.example.lg.tatab01.ProjectList;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by LG on 2018-11-19.
 */

public class ProjectItem implements Serializable {

    private Drawable icon;
    private int project_no;
    private String name;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getProject_no() {
        return project_no;
    }

    public void setProject_no(int project_no) {
        this.project_no = project_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
