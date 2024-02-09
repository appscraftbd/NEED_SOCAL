package com.appscraftbd.needasocal.Model;

public class ProfilePostModel {

    private String post_id;
    private String username;
    private String full_name;
    private String post_date;
    private String post_time;
    private String post_body;


    public ProfilePostModel (String post_id, String username, String full_name, String post_date, String post_time, String post_body) {
        this.post_id = post_id;
        this.username = username;
        this.full_name = full_name;
        this.post_date = post_date;
        this.post_time = post_time;
        this.post_body = post_body;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getPost_body() {
        return post_body;
    }

    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }
}
