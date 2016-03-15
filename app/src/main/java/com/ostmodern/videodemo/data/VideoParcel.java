package com.ostmodern.videodemo.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by philip.arnold on 15/03/2016.
 */
public class VideoParcel implements Parcelable {
    public Video video;
    public VideoParcel(Video video) {
        this.video = video;
    }
    protected VideoParcel(Parcel in) {
    }

    public Video getVideo() {
        return video;
    }

    public static final Creator<VideoParcel> CREATOR = new Creator<VideoParcel>() {
        @Override
        public VideoParcel createFromParcel(Parcel in) {
            return new VideoParcel(in);
        }

        @Override
        public VideoParcel[] newArray(int size) {
            return new VideoParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
