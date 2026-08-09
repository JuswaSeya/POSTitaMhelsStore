package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class barcode extends AppCompatActivity {

    private PreviewView previewView;
    private TextView txtResult;

    private ExecutorService cameraExecutor;

    private BarcodeScanner scanner;

    private boolean barcodeDetected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_barcode);

        previewView = findViewById(R.id.previewView);
        txtResult = findViewById(R.id.txtResult);

        cameraExecutor = Executors.newSingleThreadExecutor();

        scanner = BarcodeScanning.getClient();

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED) {

            startCamera();

        } else {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    100
            );
        }
    }

    private void startCamera() {

        ListenableFuture<ProcessCameraProvider> future =
                ProcessCameraProvider.getInstance(this);

        future.addListener(() -> {

            try {

                ProcessCameraProvider cameraProvider =
                        future.get();

                Preview preview =
                        new Preview.Builder().build();

                preview.setSurfaceProvider(
                        previewView.getSurfaceProvider()
                );

                ImageAnalysis analysis =
                        new ImageAnalysis.Builder()
                                .setBackpressureStrategy(
                                        ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
                                )
                                .build();

                analysis.setAnalyzer(
                        cameraExecutor,
                        imageProxy -> processImage(imageProxy)
                );

                CameraSelector selector =
                        CameraSelector.DEFAULT_BACK_CAMERA;

                cameraProvider.unbindAll();

                cameraProvider.bindToLifecycle(
                        this,
                        selector,
                        preview,
                        analysis
                );

            } catch (Exception e) {

                e.printStackTrace();

            }

        }, ContextCompat.getMainExecutor(this));
    }

    private void processImage(ImageProxy imageProxy) {

        if (barcodeDetected) {
            imageProxy.close();
            return;
        }

        @SuppressLint("UnsafeOptInUsageError")
        Image mediaImage = imageProxy.getImage();

        if (mediaImage == null) {
            imageProxy.close();
            return;
        }

        InputImage image =
                InputImage.fromMediaImage(
                        mediaImage,
                        imageProxy
                                .getImageInfo()
                                .getRotationDegrees()
                );

        scanner.process(image)

                .addOnSuccessListener(barcodes -> {

                    for (Barcode barcode : barcodes) {

                        String value =
                                barcode.getRawValue();

                        if (value != null && !value.isEmpty()) {

                            barcodeDetected = true;

                            runOnUiThread(() -> {

                                txtResult.setText(value);

                                Intent intent =
                                        new Intent();

                                intent.putExtra(
                                        "barcode",
                                        value
                                );

                                setResult(
                                        RESULT_OK,
                                        intent
                                );

                                finish();
                            });

                            break;
                        }
                    }
                })

                .addOnCompleteListener(task ->
                        imageProxy.close()
                );
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if (scanner != null) {
            scanner.close();
        }

        if (cameraExecutor != null) {
            cameraExecutor.shutdown();
        }
    }
}