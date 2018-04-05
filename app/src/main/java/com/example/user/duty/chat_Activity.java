package com.example.user.duty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class chat_Activity extends AppCompatActivity {


    ListView listView;
    Button chat_btn;
    TextView id_text_view;
    ImageView id_image_view;
    EditText chat_edit_text;
    SharedPreferences get_chat;
    SharedPreferences get_id;
    SharedPreferences get_img;
    TextView back_btn;

    ArrayList<chat_list_item> chat_list_itemArrayList;
    chat_List_Adapter chat_list_adapter;

    ArrayList<Integer> Stringpath = new ArrayList();

    int n = 0;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        id_image_view = (ImageView)findViewById(R.id.id_image_view);

        final String getintent2 = getIntent().getStringExtra("login_id");

        final int getintent1 = getIntent().getIntExtra("position", 0);


        SharedPreferences img = getSharedPreferences("img",0);
        String getimg = img.getString(getintent2,"디폴트 이미지");

        String geturi = getimg;


        id_image_view.setImageBitmap(BitmapFactory.decodeFile(geturi));

        back_btn = (TextView)findViewById(R.id.back_button);
        listView = (ListView) findViewById(R.id.listview);
        chat_btn = (Button) findViewById(R.id.chat_ok_btn);
        id_text_view = (TextView) findViewById(R.id.id_text_view);
        id_image_view = (ImageView) findViewById(R.id.id_image_view);
        chat_edit_text = (EditText) findViewById(R.id.chat_text_view);

        final String chat = chat_edit_text.getText().toString();


        chat_list_itemArrayList = new ArrayList<>();
        chat_list_adapter = new chat_List_Adapter(chat_Activity.this, chat_list_itemArrayList);


        listView.setAdapter(chat_list_adapter);


        get_chat = getSharedPreferences("get_chat" + getintent1, 0);

        get_id = getSharedPreferences("get_id" + getintent1, 0);

        get_img = getSharedPreferences("img",0);

        SharedPreferences nkey_save = getSharedPreferences("nkey" + getintent1, 0);

        n = nkey_save.getInt("akey1", 0);



        chat_list_adapter.notifyDataSetChanged();



        for (int i = 0; i <= n - 1; i++) {
            String id_add = get_id.getString(String.valueOf(i), "no_title");
            String chat_add = get_chat.getString(String.valueOf(i), "no_contents");
            String img1 = get_img.getString(String.valueOf(i),"no_chat");

            Uri get_uri = Uri.parse(img1);

            chat_list_item chat_list_item = new chat_list_item(chat_add, id_add, get_uri);
            Stringpath.add(i);
            chat_list_itemArrayList.add(chat_list_item);
        }

        //리스트 뷰 아이템 전체삭제.
        SharedPreferences chat_num = getSharedPreferences("chat+num", 0);
        SharedPreferences.Editor edit_chat_num = chat_num.edit();

/*

        Stringpath.clear();
        chat_list_itemArrayList.clear();
        SharedPreferences.Editor id_edi = get_id.edit();
        SharedPreferences.Editor editor = get_chat.edit();
        SharedPreferences.Editor nkey_edi = nkey_save.edit();
        nkey_edi.clear().commit();
        editor.clear().commit();
        id_edi.clear().commit();
        edit_chat_num.clear().commit();
*/

        chat_list_adapter.notifyDataSetChanged();


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences nkey_save = getSharedPreferences("nkey" + getintent1, 0);

                n = nkey_save.getInt("akey1", 0);



                chat_list_adapter.notifyDataSetChanged();

                SharedPreferences chat_number = getSharedPreferences("chat_number",0);
                SharedPreferences.Editor get_chat_number = chat_number.edit();
                get_chat_number.putString(String.valueOf(getintent1), String.valueOf(n)).commit();
                Intent intent = new Intent(chat_Activity.this,modify.class);
                setResult(500,intent);
                finish();

        }
        });

        //댓글 달기 버튼. 쉐어드에 저장과 리스트 뷰 에 추가를 한다
        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chat_edit_text.getText().toString().length()<=2){
                    Toast.makeText(chat_Activity.this, "두자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    get_id = getSharedPreferences("get_id" + getintent1, 0);

                    get_chat = getSharedPreferences("get_chat" + getintent1, 0);

                    SharedPreferences.Editor save_chat = get_chat.edit();
                    SharedPreferences.Editor save_id = get_id.edit();
                    SharedPreferences.Editor save_img = get_img.edit();


                    String img = get_img.getString(getintent2,"no_chat");

                    Uri get_uri = Uri.parse(img);


                    String chat = chat_edit_text.getText().toString();

                    chat_list_itemArrayList.add(new chat_list_item(chat, getintent2,get_uri));


                    save_id.putString(String.valueOf(n), getintent2).commit();
                    save_chat.putString(String.valueOf(n), chat).commit();
                    save_img.putString(String.valueOf(n), String.valueOf(get_uri)).commit();


                    SharedPreferences nkey_save = getSharedPreferences("nkey" + getintent1, 0);
                    n++;

                    SharedPreferences.Editor nkey_editor = nkey_save.edit();


                    nkey_editor.putInt("akey1", n).commit();


                    chat_edit_text.setText("");
                    chat_list_adapter.notifyDataSetChanged();

                    SharedPreferences chat_number = getSharedPreferences("chat_number",0);
                    SharedPreferences.Editor get_chat_number = chat_number.edit();
                    get_chat_number.putString(String.valueOf(getintent1), String.valueOf(n)).commit();
                }
            }
        });


        //아이디를 텍스트뷰에 셋팅.
        id_text_view.setText(getintent2);


    }


}
