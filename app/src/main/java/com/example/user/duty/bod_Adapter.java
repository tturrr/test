package com.example.user.duty;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class bod_Adapter extends BaseAdapter{



    Context context;

    ArrayList<bod_list_item> bod_list_itemArrayList;
    ViewHolder viewholder;


    public bod_Adapter(Context context, ArrayList<bod_list_item> list_itemArrayList) {
        this.context = context;
        this.bod_list_itemArrayList = list_itemArrayList;
    }


    @Override
    public int getCount() {
        return this.bod_list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.bod_list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bod1_item,null);
            viewholder = new ViewHolder();

            viewholder.content_textView = (TextView)convertView.findViewById(R.id.bod_content_textview);
            viewholder.title_textView = (TextView)convertView.findViewById(R.id.bod_title_textview);
            viewholder.id = (TextView)convertView.findViewById(R.id.bod_id_text_today) ;
            viewholder.bod_img = (ImageView)convertView.findViewById(R.id.bod_img);

            convertView.setTag(viewholder);

        }else{
            viewholder = (ViewHolder)convertView.getTag();
        }

        viewholder.content_textView.setText(bod_list_itemArrayList.get(position).getContent());
        viewholder.title_textView.setText(bod_list_itemArrayList.get(position).getTitle());
        viewholder.id.setText(bod_list_itemArrayList.get(position).getId());
        viewholder.bod_img.setImageURI(bod_list_itemArrayList.get(position).getBod_img());





        return convertView;
    }

    class ViewHolder{
        TextView content_textView;
        TextView title_textView;
        TextView id;
        ImageView bod_img;



    }


}
