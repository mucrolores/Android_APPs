package com.example.mucolores.p32_camera_2;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Camera camera;
    FrameLayout frameLayout;
    ShowCamera showCamera;
    String mCurrentPhotoPath;
    String pictureName;
    int currentCameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.FrameLayout_ID);

        //open the camera

        camera = Camera.open();

        showCamera = new ShowCamera(this,camera);

        frameLayout.addView(showCamera);

        currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;

    }

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            File picture_file = getOutputMediaFiles();
            mCurrentPhotoPath = picture_file.getAbsolutePath();
            if(picture_file == null){
                return;
            }
            else{
                try{
                    FileOutputStream fos = new FileOutputStream(picture_file);
                    fos.write(bytes);
                    fos.close();

                    camera.startPreview();

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            galleryAddPic();
        }
    };

    private File getOutputMediaFiles(){
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED))
        {
            return null;
        }
        else{
            File folder_gui = new File(Environment.getExternalStorageDirectory() + File.separator + "GUI");

            if(!folder_gui.exists()){
                folder_gui.mkdirs();
            }

            Calendar take_pic_time = Calendar.getInstance();
            int year = take_pic_time.get(Calendar.YEAR);
            int month = take_pic_time.get(Calendar.MONTH);
            int day = take_pic_time.get(Calendar.DAY_OF_MONTH);
            int hour = take_pic_time.get(Calendar.HOUR_OF_DAY);
            int minute = take_pic_time.get(Calendar.MINUTE);
            int second = take_pic_time.get(Calendar.SECOND);
            pictureName =
                    Integer.toString(year) + "_" + Integer.toString(month) + "_" +
                    Integer.toString(day) + "_" + Integer.toString(hour) + "_" +
                    Integer.toString(minute) + "_" + Integer.toString(second) + "_" +
                    ".jpg";

            File outputFiles = new File(folder_gui,pictureName);
            return outputFiles;
        }
    }

    public void captureImage(View v){
        if(camera != null){
            camera.takePicture(null,null,mPictureCallback);
            Toast.makeText(this,R.string.picture_capture_success_string,Toast.LENGTH_LONG).show();

        }

    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void switchFrontBack(View v){
//NB: if you don't release the current camera before switching, you app will crash
        camera.release();

        frameLayout.removeAllViewsInLayout();
        camera = null;

//swap the id of the camera to be used

        if(currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK){
            currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
        }
        else if(currentCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT){
            currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        }

        camera = Camera.open(currentCameraId);

        showCamera = new ShowCamera(this,camera);

        frameLayout.addView(showCamera);



        camera.startPreview();

    }

}

