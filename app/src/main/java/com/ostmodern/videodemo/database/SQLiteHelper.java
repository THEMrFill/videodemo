package com.ostmodern.videodemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by philip.arnold on 14/03/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "videoDemo.db";
    private final static int DATABASE_VERSION = 1;

    public final String videoTable = "videos";
    public final String vo_id      = "id";
    public final String vo_uid     = "uid";
    public final String vo_title   = "title";
    public final String vo_body    = "body";
    public final String vo_quote   = "quote";
    public final String vo_quoter  = "quoter";
    public final String vo_summary = "summary";

    private final String CREATE_VIDEOS = new StringBuilder()
            .append("CREATE TABLE ").append(videoTable).append(" (")
            .append(vo_id)     .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
            .append(vo_uid)    .append(" TEXT NOT NULL, ")
            .append(vo_title)  .append(" TEXT NOT NULL, ")
            .append(vo_body)   .append(" TEXT NOT NULL, ")
            .append(vo_quote)  .append(" TEXT NOT NULL, ")
            .append(vo_quoter) .append(" TEXT NOT NULL, ")
            .append(vo_summary).append(" TEXT NOT NULL ")
            .append(");")
            .toString();

    public final String videoImagesTable = "videoImages";
    public final String vi_id    = "id";
    public final String vi_video = "video";
    public final String vi_image = "image";

    private final String CREATE_VIDEO_IMAGES = new StringBuilder()
            .append("CREATE TABLE ").append(videoImagesTable).append(" (")
            .append(vi_id)   .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
            .append(vi_video).append(" INTEGER NOT NULL, ")
            .append(vi_image).append(" TEXT NOT NULL ")
            .append(");")
            .toString();

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VIDEOS);
        db.execSQL(CREATE_VIDEO_IMAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
