package com.example.user.duty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class modify extends AppCompatActivity {

    TextView back_btn;
    ImageButton delet_btn;
    ImageButton modify_btn;
    EditText title_view;
    EditText contents_view;
    ImageView chat_view;
    TextView chat_count;



    ArrayList<list_item> list_itemArrayList;
    ArrayList<Integer> list_num = new ArrayList<>();
    ListView listView;
    MyListAdpater myListAdpater;

    SharedPreferences title;
    SharedPreferences contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        final String getintent2 = getIntent().getStringExtra("login_id");

        final Intent intent = getIntent();

        final int chk_position = intent.getIntExtra("position",0);
        chat_view = (ImageView)findViewById(R.id.chat_view);
        back_btn = (TextView) findViewById(R.id.back_button);
        delet_btn = (ImageButton) findViewById(R.id.delet_btn);
        modify_btn = (ImageButton) findViewById(R.id.modify_btn);
        title_view = (EditText) findViewById(R.id.title_edview);
        contents_view = (EditText) findViewById(R.id.contents_edview);

        title = getSharedPreferences("title", 0);
        contents = getSharedPreferences("contents", 0);

        listView = (ListView) findViewById(R.id.listview1);
        list_itemArrayList = new ArrayList<>();
        chat_count = (TextView)findViewById(R.id.chat_count_view);

        SharedPreferences chat_number = getSharedPreferences("chat_number",0);

        String chat_num = chat_number.getString(String.valueOf(chk_position), "");
        chat_count.setText(chat_num);



        title_view.setText(title.getString(String.valueOf(chk_position),"no_title"));
        contents_view.setText(contents.getString(String.valueOf(chk_position),"pp"));


        //삭제 버튼 클릭해서 투데이 액티비티로 전환. 포지션위치의  내용을 지운다.
        delet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences title = getSharedPreferences("title", 0);
                SharedPreferences contents = getSharedPreferences("contents", 0);

                SharedPreferences.Editor edit_title = title.edit();
                SharedPreferences.Editor edit_contents = contents.edit();

                edit_title.remove(String.valueOf(chk_position)).commit();
                edit_contents.remove(String.valueOf(chk_position)).commit();

                Intent intent1 = new Intent(modify.this, today.class);

                setResult(200, intent1);
                finish();
            }
        });


        //수정 버튼 클릭.
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String get_title = title_view.getText().toString();
                String get_contents = contents_view.getText().toString();

                SharedPreferences title = getSharedPreferences("title", 0);
                SharedPreferences contents = getSharedPreferences("contents", 0);

                SharedPreferences.Editor edit_title = title.edit();
                SharedPreferences.Editor edit_contents = contents.edit();

                edit_title.putString(String.valueOf(chk_position),get_title).commit();
                edit_contents.putString(String.valueOf(chk_position),get_contents).commit();


                Intent intent = new Intent(modify.this,today.class);

                setResult(300,intent);
                finish();
            }
        });



        //채팅 액티비티 .
        chat_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(modify.this,chat_Activity.class);
                intent.putExtra("login_id",getintent2);
                intent.putExtra("position",chk_position);

                startActivityForResult(intent,10);




            }
        });

        //뒤로가기 버튼
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(modify.this, today.class);
                setResult(1000000,intent);
                finish();
            }
        });


    }
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (requestCode == 10 && resultCode == 500) {
            if (data != null) {

                final Intent intent = getIntent();

                final int chk_position = intent.getIntExtra("position",0);
                chat_count = (TextView)findViewById(R.id.chat_count_view);

                SharedPreferences chat_number = getSharedPreferences("chat_number",0);

                String chat_num = chat_number.getString(String.valueOf(chk_position), "");
                chat_count.setText(chat_num);


            }
        }
    }
}
