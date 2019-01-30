package com.example.lg.tatab01.Comment;

import com.example.lg.tatab01.Task.BoardTaskVO;

import java.io.Serializable;

/**
 * Created by a on 2018-11-21.
 */

public class BoardTaskCommentVO implements Serializable {

    private int comment_no;
    private int task_no;
    private String login_email;
    private String login_name;
    private String task_comment;
    private String reg_date;

    public BoardTaskCommentVO() {
        super();
    }

    public BoardTaskCommentVO(int comment_no, int task_no, String login_email, String login_name, String task_comment, String reg_date) {
        this.comment_no = comment_no;
        this.task_no = task_no;
        this.login_email = login_email;
        this.login_name = login_name;
        this.task_comment = task_comment;
        this.reg_date = reg_date;
    }

    public int getComment_no() {
        return comment_no;
    }

    public void setComment_no(int comment_no) {
        this.comment_no = comment_no;
    }

    public int getTask_no() {
        return task_no;
    }

    public void setTask_no(int task_no) {
        this.task_no = task_no;
    }

    public String getLogin_email() {
        return login_email;
    }

    public void setLogin_email(String login_email) {
        this.login_email = login_email;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getTask_comment() {
        return task_comment;
    }

    public void setTask_comment(String task_comment) {
        this.task_comment = task_comment;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return "BoardTaskCommentVO{" +
                "comment_no=" + comment_no +
                ", task_no=" + task_no +
                ", login_email='" + login_email + '\'' +
                ", login_name='" + login_name + '\'' +
                ", task_comment='" + task_comment + '\'' +
                ", reg_date='" + reg_date + '\'' +
                '}';
    }
}
