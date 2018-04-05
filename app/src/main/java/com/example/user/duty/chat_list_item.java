package com.example.user.duty;


import android.net.Uri;

public class chat_list_item {




    private String chat_Edit;

    private String id_text;

    private Uri camera;


    public chat_list_item(String chat_Edit , String id_text,Uri camera) {



        this.chat_Edit = chat_Edit;
        this.id_text = id_text;
        this.camera = camera;
    }



    public String getChat_Edit() {
        return chat_Edit;
    }

    public void setChat_Edit(String chat_Edit) {
        this.chat_Edit = chat_Edit;
    }

    public String getId_text() {
        return id_text;
    }

    public void setId_text(String id_text) {
        this.id_text = id_text;
    }

    public Uri getCamera() {
        return camera;
    }

    public void setCamera(Uri camera) {
        this.camera = camera;
    }

}
