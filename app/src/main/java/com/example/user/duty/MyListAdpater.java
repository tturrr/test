package com.example.user.duty;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdpater extends BaseAdapter{



    Context context;

    ArrayList<list_item> list_itemArrayList;
    ViewHolder viewholder;


    public MyListAdpater(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }


    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.today_item,null);
            viewholder = new ViewHolder();

           viewholder.content_textView = (TextView)convertView.findViewById(R.id.content_textview);
            viewholder.title_textView = (TextView)convertView.findViewById(R.id.title_textview);
            viewholder.id = (TextView)convertView.findViewById(R.id.id_text_today) ;
            viewholder.today_img = (ImageView)convertView.findViewById(R.id.today_img);

            convertView.setTag(viewholder);

        }else{
            viewholder = (ViewHolder)convertView.getTag();
        }

        viewholder.content_textView.setText(list_itemArrayList.get(position).getContent());
        viewholder.title_textView.setText(list_itemArrayList.get(position).getTitle());
        viewholder.id.setText(list_itemArrayList.get(position).getId());
        viewholder.today_img.setImageURI(list_itemArrayList.get(position).getBod_img());






        return convertView;
    }

    class ViewHolder{
        TextView content_textView;
        TextView title_textView;
        TextView id;
        ImageView today_img;


    }


}
