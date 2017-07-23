package com.assessment.videoplayerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.http).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveScreen(VideoLinks.HTTP);
            }
        });


        findViewById(R.id.rtmp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RTMPActivity.class);
                intent.putExtra(VideoLinks.VIDEOLINK, VideoLinks.RTMP);
                startActivity(intent);
            }
        });

        findViewById(R.id.hls).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveScreen(VideoLinks.HLS);
            }
        });

        findViewById(R.id.https).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveScreen(VideoLinks.HTTPS);
            }
        });
    }

    private void moveScreen(String link){
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(VideoLinks.VIDEOLINK, link);
        startActivity(intent);
    }
}
