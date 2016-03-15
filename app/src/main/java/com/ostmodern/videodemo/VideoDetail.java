package com.ostmodern.videodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ostmodern.videodemo.data.Video;
import com.ostmodern.videodemo.data.VideoParcel;

/**
 * Created by philip.arnold on 15/03/2016.
 */
public class VideoDetail extends AppCompatActivity {
    private Video video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VideoParcel parcel = getIntent().getExtras().getParcelable("video");
        video = parcel.getVideo();

        setContentView(R.layout.video_detail);

    }
}
