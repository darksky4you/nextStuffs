package com.android.nextos;

public class UserModel {
    String name , photo , summery , link;

    public UserModel(String name, String photo, String summery, String link) {
        this.name = name;
        this.photo = photo;
        this.summery = summery;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String name) {
        this.link = link;
    }
}
