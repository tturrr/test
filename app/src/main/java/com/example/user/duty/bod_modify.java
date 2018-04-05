package com.example.user.duty;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class bod_modify extends AppCompatActivity {

    private String mCurrentPhotoPath;
    TextView back_btn;
    ImageButton delet_btn;
    ImageButton modify_btn;
    EditText title_view;
    EditText contents_view;
    ImageView chat_view;
    TextView chat_count;
    ImageView modify_camera_view;

    ArrayList<list_item> list_itemArrayList;
    ArrayList<Integer> list_num = new ArrayList<>();
    ListView listView;
    MyListAdpater myListAdpater;

    SharedPreferences title;
    SharedPreferences contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bod_modify);

        final String getintent2 = getIntent().getStringExtra("login_id");

        final Intent intent = getIntent();

        final int chk_position = intent.getIntExtra("position",0);
        chat_view = (ImageView)findViewById(R.id.chat_view);
        back_btn = (TextView) findViewById(R.id.back_button);
        delet_btn = (ImageButton) findViewById(R.id.delet_btn);
        modify_btn = (ImageButton) findViewById(R.id.modify_btn);
        title_view = (EditText) findViewById(R.id.title_edview);
        contents_view = (EditText) findViewById(R.id.contents_edview);
        modify_camera_view = (ImageView)findViewById(R.id.bod_modify_camera_view);

        title = getSharedPreferences("title1", 0);
        contents = getSharedPreferences("contents1", 0);
        SharedPreferences img = getSharedPreferences("img2",0);


        listView = (ListView) findViewById(R.id.listview1);
        list_itemArrayList = new ArrayList<>();
        chat_count = (TextView)findViewById(R.id.chat_count_view);

        SharedPreferences chat_number = getSharedPreferences("chat_number",0);

        String chat_num = chat_number.getString(String.valueOf(chk_position), "");
        chat_count.setText(chat_num);

        String get_img = img.getString(String.valueOf(chk_position),"모디파이 디폴트");

        final String geturi = get_img;

        title_view.setText(title.getString(String.valueOf(chk_position),"no_title"));
        contents_view.setText(contents.getString(String.valueOf(chk_position),"pp"));


        modify_camera_view.setImageBitmap(BitmapFactory.decodeFile(geturi));


        modify_camera_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requirePermission();

                boolean camera =  ContextCompat.checkSelfPermission
                        (v.getContext(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;


                boolean write = ContextCompat.checkSelfPermission(v.getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

                if (camera && write) {
                    //사진찍는 인텐트 코드 넣기.

                    takePicture();

                }else {
                    Toast.makeText(bod_modify.this,"권한이 없습니다 권한을 받아주세요",Toast.LENGTH_LONG).show();
                }
            }
        });


        //삭제 버튼 클릭해서 투데이 액티비티로 전환. 포지션위치의  내용을 지운다.
        delet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences title = getSharedPreferences("title1", 0);
                SharedPreferences contents = getSharedPreferences("contents1", 0);

                SharedPreferences.Editor edit_title = title.edit();
                SharedPreferences.Editor edit_contents = contents.edit();

                edit_title.remove(String.valueOf(chk_position)).commit();
                edit_contents.remove(String.valueOf(chk_position)).commit();

                Intent intent1 = new Intent(bod_modify.this, today.class);

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


                SharedPreferences title = getSharedPreferences("title1", 0);
                SharedPreferences contents = getSharedPreferences("contents1", 0);

                SharedPreferences.Editor edit_title = title.edit();
                SharedPreferences.Editor edit_contents = contents.edit();

                edit_title.putString(String.valueOf(chk_position),get_title).commit();
                edit_contents.putString(String.valueOf(chk_position),get_contents).commit();

                SharedPreferences img = getSharedPreferences("img2",0);
                SharedPreferences.Editor edit_img = img.edit();

                String urs = mCurrentPhotoPath;
                edit_img.putString(String.valueOf(chk_position),urs ).commit();

                Intent intent = new Intent(bod_modify.this,today.class);

                setResult(300,intent);
                finish();
            }
        });



        //채팅 액티비티 .
        chat_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bod_modify.this,chat_Activity.class);
                intent.putExtra("login_id",getintent2);
                intent.putExtra("position",chk_position);

                startActivityForResult(intent,10);


            }
        });

        //뒤로가기 버튼
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bod_modify.this, today.class);
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

        if (requestCode == 20) {

            final ImageView modify_camera_view = (ImageView)findViewById(R.id.bod_modify_camera_view);
            modify_camera_view.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));





        }

    }



    void requirePermission() {
        String [] permissions = new String [] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ArrayList<String> ListPermissionNeeded = new ArrayList<>();

        for(String permission : permissions){


            //권한이 허가가 안됬을 경우 요청할 권한을 모집하는 부분.
            if(ContextCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_DENIED) {
                ListPermissionNeeded.add(permission);
            }
        }

        //데이터가 없을경우 권한 요청하는 부분
        if(!ListPermissionNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,ListPermissionNeeded.toArray(new String[ListPermissionNeeded.size()]),1);

        }

    }


    void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            File phtoFile = createImageFile();
            Uri photoUri = FileProvider.getUriForFile(this,"com.example.user.duty.fileprovider",phtoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
            startActivityForResult(intent, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;

    }

    public String getBase64String(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }




}