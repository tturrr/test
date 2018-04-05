package com.example.user.duty;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class profile extends AppCompatActivity {

   private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Button camera_btn = (Button)findViewById(R.id.profile_camera_btn);
        ImageView prople_img = (ImageView)findViewById(R.id.profile_image);

        TextView textbtn = (TextView)findViewById(R.id.textbutton);
        TextView nametext = (TextView) findViewById(R.id.name_text);
        TextView idtext = (TextView) findViewById(R.id.id_text);
        TextView identt = (TextView)findViewById(R.id.identt_text);
        TextView number = (TextView)findViewById(R.id.number_text);
        TextView count_text = (TextView)findViewById(R.id.count_text);
        TextView coin = (TextView)findViewById(R.id.coin_count_view);


        //카메라 버튼 눌러서 동작시키는 부분
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requirePermission();
                boolean camera =  ContextCompat.checkSelfPermission
                        (view.getContext(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;


                boolean write = ContextCompat.checkSelfPermission(view.getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

                if (camera && write) {
                    //사진찍는 인텐트 코드 넣기.

                  takePicture();

                }else {
                    Toast.makeText(profile.this,"권한이 없습니다 권한을 받아주세요",Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });


        final String getintent = getIntent().getStringExtra("login_id");
        int getintent1 = getIntent().getIntExtra("coin_ck",0);


        SharedPreferences nameshared = getSharedPreferences("name",0);
        SharedPreferences idshared = getSharedPreferences("id",0);
        SharedPreferences identtshared = getSharedPreferences("idnett",0);
        SharedPreferences numbershared = getSharedPreferences("number",0);
        SharedPreferences get_count = getSharedPreferences("get_count_file",0);
        SharedPreferences get_coin = getSharedPreferences("get_coin",0);
        SharedPreferences img = getSharedPreferences("img",0);


        SharedPreferences.Editor edit_img = img.edit();


        String name = nameshared.getString(getintent,"어려웡");
        String id = idshared.getString(getintent, "엄청어려웡");
        String iden = identtshared.getString(getintent,"개어려웡");
        String num = numbershared.getString(getintent,"무쟈게! 개 어려웡");
        String getimg = img.getString(getintent,"디폴트 이미지");
        int count_info = get_count.getInt(getintent,0);
        int coin_count = get_coin.getInt(getintent,0);

        String geturi = getimg;

        prople_img.setImageBitmap(BitmapFactory.decodeFile(geturi));

        nametext.setText(name);
        idtext.setText(id);
        identt.setText(iden);
        number.setText(num);
        count_text.setText(""+count_info);
        coin.setText(""+coin_count);


        textbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,today.class);
                startActivity(intent);
                finish();
            }
        });

        if (getintent1==1) {
            final ImageView plus = (ImageView)findViewById(R.id.plus_one);
            plus.setImageResource(R.drawable.one);

            Animation animation = AnimationUtils.loadAnimation(profile.this, R.anim.coin_up);

            final Animation animation1 = AnimationUtils.loadAnimation(profile.this,R.anim.coin_down);
            plus.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                   plus.startAnimation(animation1);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    plus.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });



        }






        else if (getintent1==0){

        }

    }


    //카메라 권한을 얻어오는 부분
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
            startActivityForResult(intent, 10);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            ImageView prople_img = (ImageView)findViewById(R.id.profile_image);
            prople_img.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        }

    }
}
