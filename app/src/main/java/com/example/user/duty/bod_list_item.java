package com.example.user.duty;


import android.net.Uri;

public class bod_list_item {


    public bod_list_item(String content, String title, String id, Uri bod_img) {
        this.content = content;
        this.title = title;
        this.id = id;
        this.bod_img = bod_img;

    }

    private String content;

    private String title;

    private String id;

    private Uri bod_img;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }






    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Uri getBod_img() {
        return bod_img;
    }

    public void setBod_img(Uri bod_img) {
        this.bod_img = bod_img;
    }

}
