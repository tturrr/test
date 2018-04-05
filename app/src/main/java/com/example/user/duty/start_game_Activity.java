package com.example.user.duty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class start_game_Activity extends AppCompatActivity {
    ImageView start_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game_);


        final String getintent = getIntent().getStringExtra("login_id");

        start_btn = (ImageView)findViewById(R.id.start_btn_view);




        //미니게임시작 액티비티에서 스타트버튼을 눌러서 메인 게임 으로 들어가는 버튼.
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences coin = getSharedPreferences("get_coin",0);
                SharedPreferences.Editor save_coin = coin.edit();

                int getcoin = coin.getInt(getintent,0);

                if(getcoin >= 1){
                    getcoin --;
                    save_coin.putInt(getintent,getcoin).commit();
                    Intent start_intent = new Intent(start_game_Activity.this,game_start_Activity.class);
                    start_intent.putExtra("login_id",getintent);
                    startActivity(start_intent);
                }
                else if (getcoin <= 0){
                    Toast.makeText(start_game_Activity.this,"코인이 부족합니다. 코인을 얻어주세요.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
