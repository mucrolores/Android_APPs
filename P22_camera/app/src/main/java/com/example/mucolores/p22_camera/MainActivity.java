package com.example.mucolores.p22_camera;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PERMISSION_CAMERA = 100;

    private boolean mbFaceDetAvailable;
    private int miMaxFaceCount = 0;
    private int miFaceDetMode;

    private TextureView mTextureView = null;

    private Size mPreviewSize = null;
    private CameraDevice mCameraDevice = null;
    private CaptureRequest.Builder mPreviewBuilder = null;
    private CameraCaptureSession mCameraPreviewCaptureSession = null,
            mCameraTakePicCaptureSession = null;

    // ???UI???TextureView?????????????????????onSurfaceTextureAvailable()
    private TextureView.SurfaceTextureListener mSurfaceTextureListener =
            new TextureView.SurfaceTextureListener() {
                @Override
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture,
                                                      int width, int height) {
                    // ????????????????????????camera?????????
                    if (askForPermissions())
                        openCamera();   // ??????camera.
                }

                @Override
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture,
                                                        int width, int height) {

                }

                @Override
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    return false;
                }

                @Override
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextureView = (TextureView) findViewById(R.id.TextureView1);

        Button btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askForPermissions();
                takePicture();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mCameraDevice != null)
        {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openCamera();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private  boolean askForPermissions() {
        // App??????????????????????????????
        String[] permissions= new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        // ??????????????????????????????
        final List<String> listPermissionsNeeded = new ArrayList<>();
        boolean bShowPermissionRationale = false;

        for (String p: permissions) {
            int result = ContextCompat.checkSelfPermission(MainActivity.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);

                // ??????????????????????????????
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        MainActivity.this, p))
                    bShowPermissionRationale = true;
            }
        }

        // ??????????????????????????????????????????
        if (!listPermissionsNeeded.isEmpty()) {
            if (bShowPermissionRationale) {
                AlertDialog.Builder altDlgBuilder =
                        new AlertDialog.Builder(MainActivity.this);
                altDlgBuilder.setTitle("??????");
                altDlgBuilder.setMessage("App?????????????????????????????????");
                altDlgBuilder.setIcon(android.R.drawable.ic_dialog_info);
                altDlgBuilder.setCancelable(false);
                altDlgBuilder.setPositiveButton("??????",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                                        REQUEST_PERMISSION_CAMERA);
                            }
                        });
                altDlgBuilder.show();
            } else
                ActivityCompat.requestPermissions(MainActivity.this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        REQUEST_PERMISSION_CAMERA);

            return false;
        }

        return true;
    }

    private void openCamera() {
        // ?????? CameraManager
        CameraManager camMgr = (CameraManager) getSystemService(CAMERA_SERVICE);

        try{
            // ????????????????????? camera
            String cameraId = camMgr.getCameraIdList()[0];
            CameraCharacteristics camChar = camMgr.getCameraCharacteristics(cameraId);

            // ???????????????
            StreamConfigurationMap map = camChar.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            mPreviewSize = map.getOutputSizes(SurfaceTexture.class)[0];

            // ?????????????????????????????????
            int[] iFaceDetModes = camChar.get(
                    CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES);
            if (iFaceDetModes == null) {
                mbFaceDetAvailable = false;
                Toast.makeText(MainActivity.this, "?????????????????????", Toast.LENGTH_LONG)
                        .show();
            } else {
                mbFaceDetAvailable = false;
                for (int mode : iFaceDetModes) {
                    if (mode == CameraMetadata.STATISTICS_FACE_DETECT_MODE_SIMPLE) {
                        mbFaceDetAvailable = true;
                        miFaceDetMode = CameraMetadata.STATISTICS_FACE_DETECT_MODE_SIMPLE;
                        break;   // Find the desired mode, so stop searching.
                    } else if (mode == CameraMetadata.STATISTICS_FACE_DETECT_MODE_FULL) {
                        // This is a candidate mode, keep searching.
                        mbFaceDetAvailable = true;
                        miFaceDetMode = CameraMetadata.STATISTICS_FACE_DETECT_MODE_FULL;
                    }
                }
            }

            if (mbFaceDetAvailable) {
                miMaxFaceCount = camChar.get(
                        CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT);

                Toast.makeText(MainActivity.this, "??????????????????: " + String.valueOf(miFaceDetMode) +
                        "\n??????????????????: " + String.valueOf(miMaxFaceCount), Toast.LENGTH_LONG)
                        .show();
            }

            // ?????? camera
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                camMgr.openCamera(cameraId, mCameraStateCallback, null);
        }
        catch(CameraAccessException e)
        {
            e.printStackTrace();
        }
    }

    private CameraDevice.StateCallback mCameraStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            startPreview();
        }

        @Override
        public void onDisconnected(CameraDevice cameraDevice) {
            Toast.makeText(MainActivity.this, "????????????camera", Toast.LENGTH_LONG)
                    .show();
        }

        @Override
        public void onError(CameraDevice cameraDevice, int error) {
            Toast.makeText(MainActivity.this, "Camera????????????", Toast.LENGTH_LONG)
                    .show();
        }
    };

    // Camera???CaptureSession?????????????????????
    private CameraCaptureSession.StateCallback mCameraCaptureSessionCallback =
            new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                    closeAllCameraCaptureSession();

                    // ????????????capture session????????????????????????
                    mCameraPreviewCaptureSession = cameraCaptureSession;

                    mPreviewBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
                    mPreviewBuilder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, miFaceDetMode);

                    HandlerThread backgroundThread = new HandlerThread("CameraPreview");
                    backgroundThread.start();
                    Handler backgroundHandler = new Handler(backgroundThread.getLooper());

                    try {
                        mCameraPreviewCaptureSession.setRepeatingRequest(mPreviewBuilder.build(), null, backgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(MainActivity.this, "Camera????????????", Toast.LENGTH_LONG)
                            .show();
                }
            };

    private void startPreview() {
        // ???UI?????????TextureView??????SurfaceTexture
        // ?????? camera?????????????????????TextureView????????????
        SurfaceTexture surfaceTexture = mTextureView.getSurfaceTexture();
        surfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());

        // ??????TextureView???????????????????????? surface ???camera??????
        Surface surface = new Surface(surfaceTexture);

        // ??????camera???CaptureRequest???CaptureSession
        try {
            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        } catch (CameraAccessException e){
            e.printStackTrace();
        }

        mPreviewBuilder.addTarget(surface);

        try {
            mCameraDevice.createCaptureSession(Arrays.asList(surface), mCameraCaptureSessionCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    // ????????????Camera Capture Session??????
    // ?????????????????????????????????Camera Capture Session
    private void closeAllCameraCaptureSession() {
        if (mCameraPreviewCaptureSession != null) {
            mCameraPreviewCaptureSession.close();
            mCameraPreviewCaptureSession = null;
        }

        if (mCameraTakePicCaptureSession != null) {
            mCameraTakePicCaptureSession.close();
            mCameraTakePicCaptureSession = null;
        }
    }

    private void takePicture() {
        if(mCameraDevice == null) {
            Toast.makeText(MainActivity.this, "Camera??????", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // ???????????????
        final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath(), "photo.jpg");

        // ??????OnImageAvailableListener
        ImageReader.OnImageAvailableListener imgReaderOnImageAvailable =
                new ImageReader.OnImageAvailableListener() {
                    @Override
                    public void onImageAvailable(ImageReader imageReader) {
                        // ???????????????????????????
                        Image image = null;
                        try {
                            image = imageReader.acquireLatestImage();
                            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                            byte[] bytes = new byte[buffer.capacity()];
                            buffer.get(bytes);

                            OutputStream output = null;
                            try {
                                output = new FileOutputStream(file);
                                output.write(bytes);
                            } finally {
                                if (null != output)
                                    output.close();
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (image != null)
                                image.close();
                        }
                    }
                };

        // ?????? CameraManager
        CameraManager camMgr = (CameraManager) getSystemService(CAMERA_SERVICE);

        try {
            CameraCharacteristics camChar = camMgr.getCameraCharacteristics(mCameraDevice.getId());

            // ????????????????????????
            Size[] jpegSizes = null;
            if (camChar != null)
                jpegSizes = camChar.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                        .getOutputSizes(ImageFormat.JPEG);

            int picWidth = 640;
            int picHeight = 480;
            if (jpegSizes != null && jpegSizes.length > 0) {
                picWidth = jpegSizes[0].getWidth();
                picHeight = jpegSizes[0].getHeight();
            }

            // ???????????????????????????
            // 1. ????????????????????? 2. ?????????UI???TextureView??????
            ImageReader imgReader = ImageReader.newInstance(picWidth, picHeight, ImageFormat.JPEG, 1);

            // ??????????????????thread
            HandlerThread thread = new HandlerThread("CameraTakePicture");
            thread.start();
            final Handler backgroudHandler = new Handler(thread.getLooper());

            // ???OnImageAvailableListener???thread?????????ImageReader
            imgReader.setOnImageAvailableListener(imgReaderOnImageAvailable, backgroudHandler);

            List<Surface> outputSurfaces = new ArrayList<Surface>(2);
            outputSurfaces.add(imgReader.getSurface());
            outputSurfaces.add(new Surface(mTextureView.getSurfaceTexture()));

            final CaptureRequest.Builder captureBuilder =
                    mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(imgReader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);

            // ??????????????????????????????????????????
            SparseIntArray PICTURE_ORIENTATIONS = new SparseIntArray();
            PICTURE_ORIENTATIONS.append(Surface.ROTATION_0, 90);
            PICTURE_ORIENTATIONS.append(Surface.ROTATION_90, 0);
            PICTURE_ORIENTATIONS.append(Surface.ROTATION_180, 270);
            PICTURE_ORIENTATIONS.append(Surface.ROTATION_270, 180);

            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, PICTURE_ORIENTATIONS.get(rotation));

            // ???????????????callback
            final CameraCaptureSession.CaptureCallback camCaptureCallback =
                    new CameraCaptureSession.CaptureCallback() {
                        @Override
                        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                            super.onCaptureCompleted(session, request, result);

                            Integer mode = result.get(CaptureResult.STATISTICS_FACE_DETECT_MODE);
                            Face[] faces = result.get(CaptureResult.STATISTICS_FACES);
                            if(faces != null && mode != null)
                                Toast.makeText(MainActivity.this, "??????: " + faces.length, Toast.LENGTH_SHORT).show();

                            // ?????????????????????
                            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" );
                            MediaPlayer mp = MediaPlayer.create(MainActivity.this, uri);
                            mp.start();

                            Toast.makeText(MainActivity.this, "????????????\n?????????: " + file, Toast.LENGTH_SHORT).show();
                            startPreview();
                        }

                        @Override
                        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
                        }
                    };

            // ????????????????????????Capture Session
            // ??????????????????
            mCameraDevice.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
                        @Override
                        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                            try {
                                closeAllCameraCaptureSession();

                                // ????????????capture session????????????????????????
                                mCameraTakePicCaptureSession = cameraCaptureSession;

                                cameraCaptureSession.capture(captureBuilder.build(), camCaptureCallback, backgroudHandler);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {

                        }
                    },
                    backgroudHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}