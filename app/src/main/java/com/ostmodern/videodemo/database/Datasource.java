package com.ostmodern.videodemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ostmodern.videodemo.common.CommonMethods;
import com.ostmodern.videodemo.common.sLog;
import com.ostmodern.videodemo.data.Video;
import com.ostmodern.videodemo.data.VideoImage;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by philip.arnold on 14/03/2016.
 */
public class Datasource {
    private String LOG = getClass().getSimpleName();
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private Context context;
    private CommonMethods commonMethods = new CommonMethods();

    public Datasource(Context context) {
        this.context = context;
        dbHelper = new SQLiteHelper(context);
        this.context = context;
    }

    // open & close methods - note, open() MUST be called after instantiation for it to be usable
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        //sLog.w("database", "database.getPath()=" + database.getPath() + ", database_name=" + SQLiteHelper.DATABASE_NAME);
    }

    public void deleteVideos() {
        database.delete(dbHelper.videoTable, null, null);
        database.delete(dbHelper.videoImagesTable, null, null);
    }

    public void addVideo(JSONObject object) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.vo_title,   commonMethods.getJSONstring(object, "title"));
        values.put(dbHelper.vo_summary, commonMethods.getJSONstring(object, "summary"));
        values.put(dbHelper.vo_body,    commonMethods.getJSONstring(object, "formatted_body"));
        values.put(dbHelper.vo_quote,   commonMethods.getJSONstring(object, "quote"));
        values.put(dbHelper.vo_quoter,  commonMethods.getJSONstring(object, "quoter"));
        values.put(dbHelper.vo_uid, commonMethods.getJSONstring(object, "uid"));
        int videoId = (int) database.insert(dbHelper.videoTable, null, values);

        JSONArray images = commonMethods.getJSONarray(object, "image_urls");
        if (images != null) {
            ContentValues values2 = null;
            if (images.length() > 0) {
                for (int i = 0; i < images.length(); i++) {
                    values2 = new ContentValues();
                    values2.put(dbHelper.vi_video, videoId);
                    values2.put(dbHelper.vi_image, commonMethods.getJSONstringFromArray(images, i).toString());
                    database.insert(dbHelper.videoImagesTable, null, values2);
                }
            }
        }
    }

    public Video[] getVideos() {
        Video[] videos = {};
        Cursor cursor = database.query(dbHelper.videoTable, new String[] {dbHelper.vo_id, dbHelper.vo_title, dbHelper.vo_summary, dbHelper.vo_body, dbHelper.vo_quote, dbHelper.vo_quoter, dbHelper.vo_uid}, null, null, null, null, null);
        if (cursor != null) {
            int counter = 0;
            videos = new Video[cursor.getCount()];
            VideoImage[] images;
            Video video;
            Cursor cursorImages = null;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int vId = commonMethods.getFieldInt(cursor, dbHelper.vo_id);
                video = new Video();
                video.setId(vId);
                video.setTitle(commonMethods.getField(cursor, dbHelper.vo_title));
                video.setSummary(commonMethods.getField(cursor, dbHelper.vo_summary));
                video.setBody(commonMethods.getField(cursor, dbHelper.vo_body));
                video.setQuote(commonMethods.getField(cursor, dbHelper.vo_quote));
                video.setQuoter(commonMethods.getField(cursor, dbHelper.vo_quoter));
                video.setUid(commonMethods.getField(cursor, dbHelper.vo_uid));

                images = new VideoImage[] {};
                cursorImages = database.query(dbHelper.videoImagesTable, new String[] {dbHelper.vi_id, dbHelper.vi_video, dbHelper.vi_image}, dbHelper.vi_video + "=?", new String[] {"" + vId}, null, null, null);
                if (cursorImages != null) {
                    int iCounter = 0;
                    images = new VideoImage[cursorImages.getCount()];
                    cursorImages.moveToFirst();
                    while (!cursorImages.isAfterLast()) {
                        VideoImage image = new VideoImage();
                        image.setId(commonMethods.getFieldInt(cursor, dbHelper.vi_id));
                        image.setVideo(commonMethods.getFieldInt(cursor, dbHelper.vi_video));
                        image.setImage(commonMethods.getField(cursor, dbHelper.vi_image));
                        images[iCounter++] = image;
                        cursorImages.moveToNext();
                    }
                    cursorImages.close();
                }
                video.setImages(images);
                videos[counter++] = video;
                cursor.moveToNext();
            }

            if (cursorImages != null) {
                cursorImages.close();
            }
            cursor.close();
        }
        sLog.w(LOG, "videos="+videos.length);
        return videos;
    }
}
