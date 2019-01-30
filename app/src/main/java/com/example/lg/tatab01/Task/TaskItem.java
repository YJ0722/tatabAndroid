package com.example.lg.tatab01.Task;

import android.graphics.drawable.Drawable;

/**
 * Created by LG on 2018-11-19.
 */

public class TaskItem {

    private int task_no;
    private Drawable icon;
    private String name;

    public int getTask_no() {
        return task_no;
    }

    public void setTask_no(int task_no) {
        this.task_no = task_no;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
