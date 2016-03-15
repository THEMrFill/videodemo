package com.ostmodern.videodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ostmodern.videodemo.common.CommonMethods;
import com.ostmodern.videodemo.common.sLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import static com.ostmodern.videodemo.common.Constants.MAIN_URL;

/**
 * Created by philip.arnold on 14/03/2016.
 */
public class Splash extends Activity {
    private String LOG = getClass().getSimpleName();
    private CommonMethods commonMethods = new CommonMethods();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        progress.setIndeterminate(true);

        StringRequest request = new StringRequest(MAIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    JSONObject responseNew = null;
                    try {
                        byte[] u = response.toString().getBytes("ISO-8859-1");
                        response = new String(u, "UTF-8");
                        responseNew = new JSONObject(response);
                        sLog.w(LOG, "responseNew=" + responseNew.length());
                        workOver(responseNew);
                    } catch (Exception e) {
                        e.printStackTrace();
                        pushToMain();
                    }
                } else {
                    pushToMain();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pushToMain();
            }
        })
        {
            public Map<String, String> getHeaders() {
                Map<String, String> header = new ArrayMap<String, String>();
                header.put("Content-Type", "application/json");
                return header;
            }
        }
        ;
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void workOver(JSONObject response) {
        JSONArray array = commonMethods.getJSONarray(response, "objects");
        if (array != null) {
            Application.datasource.deleteVideos();
            for (int i = 0; i < array.length(); i++) {
                Application.datasource.addVideo(commonMethods.getJSONobjectFromArray(array, i));
            }
        }
        pushToMain();
    }

    private void pushToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
