package com.example.user.duty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class today extends AppCompatActivity {

    ListView listView;
    MyListAdpater myListAdpater;
    ArrayList<list_item> list_itemArrayList;
    ArrayList<Integer> list_num = new ArrayList<>();
    ImageButton infobtn;
    ImageView spt_view;
    ImageView view;
    ImageView anim_view;
    ImageView today_img;
    ImageView duty_img;


    ImageView info_btn;
    ImageView game_btn;

    int check_position;
    Handler handler;
    Thread thread;

    Random random = new Random();
    int coinck=0;
    int check;
    int r = 0;
    int t = 0;
    int h = 0;
    SharedPreferences count_shared;


    ImageView menu_btn;
    ImageView game__btn;
    ImageView info__btn;
    TextView today_view;
    TextView duty_view;


    int a = 0;
    SharedPreferences idshared;
    SharedPreferences akey_save;
    SharedPreferences bkey_save;
    SharedPreferences title;
    SharedPreferences contents;
    SharedPreferences get_id;

    long mNow;
    Date mDate;
    SimpleDateFormat mFomat = new SimpleDateFormat("yyyy-MM-dd");

    TextView mTextview;
    Button calbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);


        final String getintent2 = getIntent().getStringExtra("id");


        menu_btn = (ImageView)findViewById(R.id.menu_btn);
        today_view = (TextView)findViewById(R.id.today1_bod);
        duty_view = (TextView)findViewById(R.id.duty1_bod);
        game__btn = (ImageView) findViewById(R.id.game_btn);
        info__btn = (ImageView) findViewById(R.id.info_btn);
        duty_img = (ImageView)findViewById(R.id.duty_img1);
        today_img = (ImageView)findViewById(R.id.today_img);


        SharedPreferences img = getSharedPreferences("img",0);


        SharedPreferences.Editor edit_img = img.edit();




        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(today.this,R.anim.slide);
                Animation animation1 = AnimationUtils.loadAnimation(today.this,R.anim.slide_today);
                duty_view.startAnimation(animation);
                today_view.startAnimation(animation1);
                duty_view.setText("계획 게시판");
                today_view.setText("일기 게시판");
            }
        });


        today_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(today.this,today.class);
                intent.putExtra("login_id", getintent2);
                startActivity(intent);

            }
        });

        duty_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(today.this,bod_1_Activity.class);
                intent.putExtra("login_id", getintent2);
                startActivity(intent);
                finish();

            }
        });






        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);

        animation.setDuration(300);
        set.addAnimation(animation);

        animation = new ScaleAnimation(
                0,1,0,1
        );
        animation.setDuration(1000);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);


        animation.setDuration(300);

        set.addAnimation(animation);

        LayoutAnimationController controller =
                new LayoutAnimationController(set, 0.5f);



        spt_view = (ImageView) findViewById(R.id.img_view);
        listView = (ListView) findViewById(R.id.listview1);

        listView.setLayoutAnimation(controller);

        list_itemArrayList = new ArrayList<list_item>();




        myListAdpater = new MyListAdpater(today.this, list_itemArrayList);
        listView.setAdapter(myListAdpater);

        idshared = getSharedPreferences("id", 0);

        akey_save = getSharedPreferences("akey", 0);
        bkey_save = getSharedPreferences("bkey", 0);
        title = getSharedPreferences("title", 0);
        contents = getSharedPreferences("contents", 0);
        count_shared = getSharedPreferences("count_key_box", 0);
        get_id = getSharedPreferences("get_id",0);
        SharedPreferences title = getSharedPreferences("title", 0);
        SharedPreferences contents = getSharedPreferences("contents", 0);
        SharedPreferences bod_num = getSharedPreferences("bod_num", 0);

        SharedPreferences.Editor edit_bod_num = bod_num.edit();


        SharedPreferences.Editor edit_akey = akey_save.edit();
        SharedPreferences.Editor edit_bkey = bkey_save.edit();
        SharedPreferences.Editor edit_title = title.edit();
        SharedPreferences.Editor edit_contents = contents.edit();
        SharedPreferences.Editor edit_count = count_shared.edit();

      /*  edit_bod_num.clear().commit();
        edit_akey.clear().commit();
        edit_bkey.clear().commit();
        edit_title.clear().commit();
        edit_contents.clear().commit();
        edit_count.clear().commit();*/


        a = akey_save.getInt("akey1", 1);

        SharedPreferences get_img = getSharedPreferences("img1",0);
        myListAdpater.notifyDataSetChanged();


        for (int i = 0; i <= a - 1; i++) {
            SharedPreferences get_count_key = getSharedPreferences("get_count", 0);

            String title_main = title.getString(String.valueOf(i), "no_title");
            String contents_main = contents.getString(String.valueOf(i), "no_contents");
            String id = get_id.getString(String.valueOf(i),"no");

            String img1 = get_img.getString(String.valueOf(i),"no_chat");
            get_img = getSharedPreferences("img",0);
            Uri get_uri = Uri.parse(img1);




            list_item list_item = new list_item(title_main, contents_main,id,get_uri);
            list_num.add(i);
            list_itemArrayList.add(list_item);

        }
        for (int i = a - 1; i >= 0; i--) {
            String title_main = list_itemArrayList.get(i).getTitle();
            if (title_main == "no_contents") {
                list_num.remove(i);
                list_itemArrayList.remove(i);
            }
        }

        myListAdpater.notifyDataSetChanged();


        mTextview = (TextView) findViewById(R.id.timetext);
        calbutton = (Button) findViewById(R.id.calbutton);

        //택스트뷰에 날짜 정보 받아오는 메소드
        mTextview.setText(getTime());


        //일정추가 객채 생성
        TextView textView = (TextView) findViewById(R.id.addtext);




        //투데이 액티비티에서 add 화면으로 액티비티 전환
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(today.this, add.class);


                Intent intent1 = intent.putExtra("key", a);
                intent.putExtra("login_id", getintent2);

                startActivityForResult(intent, 1001);
            }
        });


        //투데이 액티비티에서 캘린더 액티비티로 전환

       /* calbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(today.this, calendar.class);
                intent.putExtra("login_id", getintent2);
                startActivity(intent);

            }
        });*/




       //게임 액티비티로 이동하는 버튼 id의 정보를 가지고 간다.
       game__btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(today.this, start_game_Activity.class);
               intent.putExtra("login_id", getintent2);
               startActivity(intent);
           }
       });

       //프로필 액티비티로 이동하는 버튼
        info__btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(today.this, profile.class);
                intent.putExtra("login_id", getintent2);
                startActivity(intent);

            }
        });






        //아이템 클릭시 modify 액티비티가 나타나며, 아이템의 정보를 넘겨준다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long ld) {

                check_position = listView.getCheckedItemPosition();
                int chk = list_num.get(check_position);
                Intent intent = new Intent(today.this, modify.class);
                intent.putExtra("position", chk);
                intent.putExtra("login_id",getintent2);
                startActivityForResult(intent, 2002);
            }
        });

        //아이템 수정 .
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long ld) {
                check_position = listView.getCheckedItemPosition();
                int chk = list_num.get(check_position);
                Intent intent = new Intent(today.this, modify.class);

                intent.putExtra("list_position", chk);
                startActivityForResult(intent, 1);
            }
        });
*/

        //이미지 뷰 핸들러 사용 하는 공간. 투데이 액티비티 메인화면에 배너 광고를 띄운다.

        handler = new Handler();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (t == 0) {
                                spt_view.setImageResource(R.drawable.support);
                                spt_view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                });
                                t++;
                            } else if (t == 1) {
                                spt_view.setImageResource(R.drawable.support1);
                                spt_view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                });
                                t = 0;
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }

    //날짜 정보를 받아오는 메소드.
    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFomat.format(mDate);

    }

    // 리스트뷰 추가 하는 부분
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (requestCode == 1001 && resultCode == 100) {
            if (data != null) {


                final String getintent2 = getIntent().getStringExtra("id");

                a = akey_save.getInt("akey1", 0);
                SharedPreferences get_img = getSharedPreferences("img1",0);
                SharedPreferences put_coin = getSharedPreferences("get_coin",0);
                SharedPreferences contents = getSharedPreferences("contents", 0);
                SharedPreferences title = getSharedPreferences("title", 0);
                SharedPreferences get_count_key = getSharedPreferences("get_count", 0);
                SharedPreferences get_id = getSharedPreferences("get_id",0);

                String tit = title.getString(String.valueOf(a - 1), "무쟈게! 개 어려2웡");
                String cont = contents.getString(String.valueOf(a - 1), "무쟈게! 개 어려2웡");
                String id = get_id.getString(String.valueOf(a - 1),"아이디 추가 어려워");


                String img1 = get_img.getString(String.valueOf(a-1),"no_chat");

                Uri get_uri = Uri.parse(img1);


                SharedPreferences.Editor editor = get_count_key.edit();
                int get_coin = put_coin.getInt(getintent2,0);


                editor.commit();
                list_num.add(a - 1);
                list_itemArrayList.add(new list_item(tit, cont,id,get_uri));


                if(coinck==random.nextInt(1)) {




                    SharedPreferences.Editor put_coin1 = put_coin.edit();

                    get_coin++;
                    put_coin1.putInt(getintent2, get_coin).commit();

                    Toast.makeText(today.this, "코인을 획득하였습니다.하단 캐릭터를 클릭해 보세요.", Toast.LENGTH_SHORT).show();

                    view = (ImageView)findViewById( R.id.anim_img );
                    view.setBackgroundResource(R.drawable.animation);
                    AnimationDrawable anim = (AnimationDrawable)view.getBackground();
                    anim.setOneShot(true);
                    anim.start();
                    //애니메이션 생성되면 클릭가능함.
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            view.setBackgroundResource(0);
                            Intent intent = new Intent(today.this,profile.class);
                            intent.putExtra("login_id",getintent2);
                            intent.putExtra("coin_ck",1);
                            startActivity(intent);



                        }
                    });


                }
                myListAdpater.notifyDataSetChanged();
            }
        }

        //리스트 뷰 삭제 하는 부분
        if (requestCode == 2002 && resultCode == 200) {
            if (data != null) {
                a = akey_save.getInt("akey1", 0);

                list_itemArrayList.clear();
                list_num.clear();
                for (int i = 0; i <= a - 1; i++) {

                    String title_main = title.getString(String.valueOf(i), "no_title");
                    String contents_main = contents.getString(String.valueOf(i), "no_contents");
                    String id = get_id.getString(String.valueOf(i),"no");

                    SharedPreferences get_img = getSharedPreferences("img1",0);
                    String img1 = get_img.getString(String.valueOf(i),"no_chat");
                    Uri get_uri = Uri.parse(img1);

                    list_item list_item = new list_item(title_main, contents_main,id,get_uri);
                    list_num.add(i);
                    list_itemArrayList.add(list_item);
                }
                for (int i = a - 1; i >= 0; i--) {
                    String title_main = list_itemArrayList.get(i).getTitle();

                    if (title_main == "no_contents") {
                        list_num.remove(i);
                        list_itemArrayList.remove(i);
                    }
                }
                myListAdpater.notifyDataSetChanged();

            }
        }
/*

        //리스트 뷰 수정 하는 부분
     */


        if (requestCode == 2002 && resultCode == 300) {
            if (data != null) {
                a = akey_save.getInt("akey1", 0);

                list_itemArrayList.clear();
                list_num.clear();
                for (int i = 0; i <= a - 1; i++) {

                    String title_main = title.getString(String.valueOf(i), "no_title");
                    String contents_main = contents.getString(String.valueOf(i), "no_contents");
                    String id = get_id.getString(String.valueOf(i),"no");
                    SharedPreferences get_img = getSharedPreferences("img1",0);
                    String img1 = get_img.getString(String.valueOf(a-1),"no_chat");

                    Uri get_uri = Uri.parse(img1);

                    list_item list_item = new list_item(title_main, contents_main,id,get_uri);
                    list_num.add(i);
                    list_itemArrayList.add(list_item);


                }
                for (int i = a - 1; i >= 0; i--) {
                    String title_main = list_itemArrayList.get(i).getTitle();
                    if (title_main == "no_contents") {
                        list_num.remove(i);
                        list_itemArrayList.remove(i);
                    }
                }
                }
            myListAdpater.notifyDataSetChanged();
            }
        }
    }








