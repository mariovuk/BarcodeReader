package com.example.mariovuksic.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scanner);
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);

        scannerView = new ZXingScannerView(this);
        contentFrame.addView(scannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        //Call back data to main activity
        Intent intent = new Intent();
        intent.putExtra(MainActivity.FORMAT, rawResult.getBarcodeFormat().toString());
        intent.putExtra(MainActivity.CONTENT, rawResult.getText());

        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}