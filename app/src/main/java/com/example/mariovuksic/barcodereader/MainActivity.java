package com.example.mariovuksic.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_SCANNER = 1;
    public final static String FORMAT = "format";
    public final static String CONTENT = "content";

    private TextView content;
    private TextView format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        View btnScan = findViewById(R.id.btn_scan);
        format = (TextView) findViewById(R.id.format);
        content = (TextView) findViewById(R.id.content);

        setSupportActionBar(toolbar);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivityForResult(intent, REQUEST_SCANNER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_OK) {
            content.setText(data.getStringExtra(CONTENT));
            format.setText(data.getStringExtra(FORMAT));
        }
    }
}