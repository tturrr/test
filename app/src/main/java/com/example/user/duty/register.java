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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class register extends AppCompatActivity {
String id_text;
private String mCurrentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        Button camera_btn = (Button)findViewById(R.id.register_camera_btn);
        ImageView prople_img = (ImageView)findViewById(R.id.register_image);


        final EditText nametext = (EditText)findViewById(R.id.name_text);
        final Button over_btn = (Button)findViewById(R.id.overlap_btn);
        Button button = (Button)findViewById(R.id.button1);
        final TextView textView = (TextView)findViewById(R.id.textbutton);

        final EditText pass_edit1 = (EditText)findViewById(R.id.password_edit_text1);
        final EditText pass_edit2 = (EditText)findViewById(R.id.password_edit_text2);


        final EditText number_text = (EditText)findViewById(R.id.number_edit);

        final EditText identt_text = (EditText)findViewById(R.id.identt_edit);
        final EditText name_edit = (EditText)findViewById(R.id.name_text);



        //카메라 버튼을 눌럿을떄 권한이 나오며 권한을 승인한경우  카메라 앱이 켜진다.
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
                   Toast.makeText(register.this,"권한이 없습니다 권한을 받아주세요",Toast.LENGTH_LONG).show();
               }

            }
        });



        over_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                EditText id_edit = (EditText)findViewById(R.id.id_edit_text);
                SharedPreferences id = getSharedPreferences("id", 0);

                id_text = id_edit.getText().toString();

                String idcheck = id.getString(id_text,"");
                    if (id_text.equals(idcheck)){

                    Toast.makeText(register.this,"중복된 아이디 입니다.",Toast.LENGTH_SHORT).show();
                    }

                else {
                    Toast.makeText(register.this,"사용가능한 아이디 입니다.",Toast.LENGTH_SHORT).show();
                }

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (nametext.getText().toString().length() == 0) {
                    Toast.makeText(register.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                    nametext.requestFocus();
                    return;
                }




                if (pass_edit1.getText().toString().length() == 0) {
                    Toast.makeText(register.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    pass_edit1.requestFocus();
                    return;
                }
                if (pass_edit2.getText().toString().length() == 0) {
                    Toast.makeText(register.this, "비밀번호 확인란을 입력하세요", Toast.LENGTH_SHORT).show();
                    pass_edit2.requestFocus();
                    return;
                }
                if (!pass_edit1.getText().toString().equals(pass_edit2.getText().toString())) {
                    Toast.makeText(register.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    pass_edit1.setText("");
                    pass_edit2.setText("");
                    pass_edit1.requestFocus();
                    return;
                }

                SharedPreferences id = getSharedPreferences("id", 0);
                SharedPreferences password = getSharedPreferences("password", 0);
                SharedPreferences name = getSharedPreferences("name", 0);
                SharedPreferences identt = getSharedPreferences("idnett", 0);
                SharedPreferences number = getSharedPreferences("number", 0);
                SharedPreferences img = getSharedPreferences("img",0);


                SharedPreferences.Editor edit_id = id.edit();
                SharedPreferences.Editor edit_pass = password.edit();
                SharedPreferences.Editor edit_name = name.edit();
                SharedPreferences.Editor edit_identt = identt.edit();
                SharedPreferences.Editor edit_number = number.edit();
                SharedPreferences.Editor edit_img = img.edit();



                EditText id_edit = (EditText) findViewById(R.id.id_edit_text);
                id_text = id_edit.getText().toString();
                String pass_text = pass_edit1.getText().toString();
                String numbertext = number_text.getText().toString();
                String identttext = identt_text.getText().toString();
                String name_text = name_edit.getText().toString();





                if (id_edit.getText().toString().length() == 0 ) {
                    Toast.makeText(register.this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                    id_edit.requestFocus();
                    return;
                }

                else {

                    Intent intent = new Intent(register.this, MainActivity.class);
                    intent.putExtra("id", id_text);


                    String urs = mCurrentPhotoPath;


                    edit_id.putString(id_text, id_text).commit();
                    edit_pass.putString(id_text, pass_text).commit();
                    edit_name.putString(id_text, name_text).commit();
                    edit_identt.putString(id_text, identttext).commit();
                    edit_number.putString(id_text, numbertext).commit();
                    edit_img.putString(id_text,urs).commit();









                    startActivity(intent);
                }

            }

        });








        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(register.this,MainActivity.class);



                startActivity(intent);

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

            ImageView register_img = (ImageView)findViewById(R.id.register_image);
            register_img.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        }

    }

}
