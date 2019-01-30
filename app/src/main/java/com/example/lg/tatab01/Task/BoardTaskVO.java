package com.example.lg.tatab01.Task;

import java.io.Serializable;

/**
 * Created by a on 2018-11-20.
 */

public class BoardTaskVO implements Serializable {

    private int project_no;
    private int col_no;
    private int task_no;
    private int task_index;
    private String task_name;
    private String task_content;
    private String status;
    private String d_day;
    private String reg_date;
    private String update_date;

    public BoardTaskVO(int project_no, int col_no, int task_no, int task_index, String task_name, String task_content, String status, String d_day, String reg_date, String update_date) {
        this.project_no = project_no;
        this.col_no = col_no;
        this.task_no = task_no;
        this.task_index = task_index;
        this.task_name = task_name;
        this.task_content = task_content;
        this.status = status;
        this.d_day = d_day;
        this.reg_date = reg_date;
        this.update_date = update_date;
    }

    public int getProject_no() {
        return project_no;
    }

    public void setProject_no(int project_no) {
        this.project_no = project_no;
    }

    public int getCol_no() {
        return col_no;
    }

    public void setCol_no(int col_no) {
        this.col_no = col_no;
    }

    public int getTask_no() {
        return task_no;
    }

    public void setTask_no(int task_no) {
        this.task_no = task_no;
    }

    public int getTask_index() {
        return task_index;
    }

    public void setTask_index(int task_index) {
        this.task_index = task_index;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_content() {
        return task_content;
    }

    public void setTask_content(String task_content) {
        this.task_content = task_content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getD_day() {
        return d_day;
    }

    public void setD_day(String d_day) {
        this.d_day = d_day;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    @Override
    public String toString() {
        return "BoardTaskVO{" +
                "project_no=" + project_no +
                ", col_no=" + col_no +
                ", task_no=" + task_no +
                ", task_index=" + task_index +
                ", task_name='" + task_name + '\'' +
                ", task_content='" + task_content + '\'' +
                ", status='" + status + '\'' +
                ", d_day='" + d_day + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", update_date='" + update_date + '\'' +
                '}';
    }
}
