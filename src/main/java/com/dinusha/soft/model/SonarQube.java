package com.dinusha.soft.model;

/**
 * @author Dinusha Jayasekara on 3/15/2020
 * @project utility-monitor
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SonarQube {

    private String project;

    private String author;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        this.date = format.parse(date);
    }

    @Override
    public String toString() {
        return "SonarQube{" +
                "project='" + project + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }
}
