package com.inhatc.welko;

public class Travel {
    private String type;
    private String name;
    private String location;
    private String intro;
    private String description;
    private String address;
    private String transportation;
    private String thumbnail;

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getIntro() {
        return intro;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getTransportation() {
        return transportation;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
