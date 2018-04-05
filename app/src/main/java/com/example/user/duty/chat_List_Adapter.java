package com.example.user.duty;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class chat_List_Adapter extends BaseAdapter
{

    Context context;

    ArrayList<chat_list_item> chat_list_itemArrayList = new ArrayList<chat_list_item>();
    ViewHolder viewholder;


    public chat_List_Adapter(Context context, ArrayList<chat_list_item> chat_list_itemArrayList) {
        this.context = context;
        this.chat_list_itemArrayList = chat_list_itemArrayList;
    }

    @Override
    public int getCount() {
        return chat_list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return chat_list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.chat_item,null);
            viewholder = new ViewHolder();
            viewholder.chat_text_view = (TextView)convertView.findViewById(R.id.content_textview);
            viewholder.id_text_today = (TextView)convertView.findViewById(R.id.id_text_today);
            viewholder.camera = (ImageView)convertView.findViewById(R.id.camera_image_view);

            convertView.setTag(viewholder);

        }else{
            viewholder = (chat_List_Adapter.ViewHolder)convertView.getTag();
        }
        viewholder.chat_text_view.setText(chat_list_itemArrayList.get(position).getChat_Edit());
        viewholder.id_text_today.setText(chat_list_itemArrayList.get(position).getId_text());
        viewholder.camera.setImageURI(chat_list_itemArrayList.get(position).getCamera());


        return convertView;
    }



    class ViewHolder{
        TextView chat_text_view;
        TextView id_text_today;
        ImageView camera;


    }
}
