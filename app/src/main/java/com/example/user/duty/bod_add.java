package com.example.user.duty;
/*
import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;*/

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class bod_add extends AppCompatActivity {


    int coin;
    int coinck=0;
    int a=0;
    private String mCurrentPhotoPath;

    Random random = new Random();


    String count = String.valueOf(0);

    ListView listView;
    Button camera_btn;
    ImageView bod_cma_view;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bod_add);



        SharedPreferences akey_save = getSharedPreferences("akey11",0);
        SharedPreferences bod_num = getSharedPreferences("bod_num1",0);
        SharedPreferences.Editor edit_bod_num = bod_num.edit();

        ImageButton addbutton = (ImageButton)findViewById(R.id.addbutton);
        final EditText firedit = (EditText)findViewById(R.id.firstedit);
        final EditText secondedit = (EditText)findViewById(R.id.secondedit);
        TextView textView = (TextView)findViewById(R.id.textbutton);
        Button camera_btn = (Button)findViewById(R.id.bod_canera_btn);


        a = akey_save.getInt("akey11",1);

        Intent intent = getIntent();
        final String getintent = intent.getStringExtra("login_id");





        //카메라 버튼 클릭해서 권한있으면 사진을 찍는다.

        camera_btn.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(bod_add.this,"권한이 없습니다 권한을 받아주세요",Toast.LENGTH_LONG).show();
                }



            }
        });


        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences put_coin = getSharedPreferences("get_coin1",0);
                int coin = put_coin.getInt(getintent,0);


                SharedPreferences title = getSharedPreferences("title1",0);
                SharedPreferences contents = getSharedPreferences("contents1",0);
                SharedPreferences bod_num = getSharedPreferences("bod_num1",0);
                SharedPreferences get_id = getSharedPreferences("get_id1",0);

                SharedPreferences.Editor edit_bod_num = bod_num.edit();
                SharedPreferences.Editor edit_title = title.edit();
                SharedPreferences.Editor edit_contents = contents.edit();
                SharedPreferences.Editor editor_id = get_id.edit();


                String contents1 = firedit.getText().toString();
                String title1 = secondedit.getText().toString();
                String id = getintent;

                edit_title.putString(String.valueOf(a),title1).commit();
                edit_contents.putString(String.valueOf(a), contents1).commit();
                editor_id.putString(String.valueOf(a),id).commit();

                SharedPreferences akey_save = getSharedPreferences("akey11",0);
                a++;

                SharedPreferences.Editor akey_editor = akey_save.edit();
                SharedPreferences.Editor bkey_editor = akey_save.edit();

                SharedPreferences img = getSharedPreferences("img2",0);
                SharedPreferences.Editor edit_img = img.edit();

                String urs = mCurrentPhotoPath;

                akey_editor.putInt("akey11",a).commit();
                bkey_editor.putInt("bkey11",a).commit();
                edit_img.putString(String.valueOf(a),urs).commit();
                edit_bod_num.putString(String.valueOf(a),String.valueOf(a)).commit();

             /*   if(coinck==random.nextInt(4)){

                    coin++;

                    SharedPreferences.Editor put_coin1 = put_coin.edit();

                    put_coin1.putInt(getintent,coin).commit();

                    Toast.makeText(add.this,"코인을 획득하였습니다.",Toast.LENGTH_SHORT).show();

                }*/



                Intent intent = new Intent();
                setResult(100,intent);
                finish();
            }
        });





        //투데이 액티비티로 뒤로 돌아간다.
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bod_add.this,today.class);
                setResult(10000000,intent);
                finish();
            }
        });





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

            ImageView bod_cma_view = (ImageView)findViewById(R.id.bod_camera_view);
            bod_cma_view.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        }

    }

}





