package com.utilities.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vendor {
    private int id;
    private String name;
    @JsonProperty("self_link")
    private String selfLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", selfLink='" + selfLink + '\'' +
                '}';
    }
}
