package com.dinusha.soft.model;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jayas on 3/15/2020
 * @project utility-monitor
 */


public class SonarQube {

    private String project;

    private String author;
    private String date;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date parsed = format.parse(date);
        this.date = parsed.toString();
    }
}
