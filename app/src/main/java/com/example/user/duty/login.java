package com.example.user.duty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    SharedPreferences idshared;
    SharedPreferences password ;

    String id_text;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView textView = (TextView)findViewById(R.id.textbutton);
        Button button = (Button)findViewById(R.id.loginbuttton);
//        final CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        final EditText id_edit = (EditText)findViewById(R.id.id_edit);
        final EditText pass_edit = (EditText)findViewById(R.id.pass_edit);



        idshared  = getSharedPreferences("id",0);
        password =getSharedPreferences("password",0);
























        //로그인 버튼을 누를시 id의 키값에 있는 정보를 today 액티비티 로 전달한다.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id_text = id_edit.getText().toString();
                String idcheck = idshared.getString(id_text,"");
                String passchedk = password.getString(id_text,"");

                //발표전에 주설을 풀자.
//                if (id_text.equals(idcheck) && pass_edit.getText().toString().equals(passchedk)) {
////
////                    if (id_edit.getText().toString().length() == 0 ) {
////                        Toast.makeText(login.this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
////                        id_edit.requestFocus();
////                        return;
////                    }

                    Intent intent = new Intent(login.this, today.class);
                    intent.putExtra("id",id_text);

                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(login.this,"이메일 또는 비밀번호가 틀립니다.",Toast.LENGTH_SHORT).show();
//                }
            }


        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

}
