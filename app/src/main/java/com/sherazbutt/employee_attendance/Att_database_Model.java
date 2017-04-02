package com.sherazbutt.employee_attendance;

/**
 * Created by Butt on 3/28/2017.
 */
public class Att_database_Model {
    private String id;
    private String name;
    private String date;
    private String t_in;
    private String t_out;
    private String w_hours;
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getT_in() {
        return t_in;
    }

    public void setT_in(String t_in) {
        this.t_in = t_in;
    }

    public String getT_out() {
        return t_out;
    }

    public void setT_out(String t_out) {
        this.t_out = t_out;
    }

    public String getW_hours() {
        return w_hours;
    }

    public void setW_hours(String w_hours) {
        this.w_hours = w_hours;
    }
}
