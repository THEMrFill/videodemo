package com.ostmodern.videodemo.common;

import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by philip.arnold on 14/03/2016.
 */
public class CommonMethods {
    /*
  JSON RETURN FUNCTIONS
*/
    /*
      Find the exact casing of the field name, as some could start lower case, others upper case
    */
    public String getJSONfieldName(JSONObject jsonData, String jsonVar) {
        //start with a blank key name
        String keyName = null;
        //if the sent data is null then return null
        if (jsonData == null) {
            return null;
        }
        //loop through the keys to find the correct spelling
        for (Iterator<String> iter = jsonData.keys(); iter.hasNext(); ) {
            String key = iter.next();
            if (key.equalsIgnoreCase(jsonVar)) {
                keyName = key;
                break;
            }
        }
        return keyName;
    }

    /*
      get an int from the passed JSON object
    */
    public int getJSONint(JSONObject jsonData, String jsonVar) {
        // get the proper name of the field
        String varName = getJSONfieldName(jsonData, jsonVar);
        if (varName == null) {
            return 0;
        }
        // need a try/catch around JSON stuff as the variable could be the wrong type or not exist
        try {
            return jsonData.getInt(varName);
        } catch (Exception e) {
            sLog.w("getJSONint", "failed to getJSONint " + jsonVar + " - " + e.toString());
            // return zero because the "get" failed
            return 0;
        }
    }

    /*
      get a double from the passed JSON object
    */
    public double getJSONdouble(JSONObject jsonData, String jsonVar) {
        //get the proper name of the field
        String varName = getJSONfieldName(jsonData, jsonVar);
        if (varName == null) {
            return 0;
        }
        //need a try/catch around JSON stuff as the variable could be the wrong type or not exist
        try {
            //get the Double value from the data
            return jsonData.getDouble(varName);
        } catch (Exception e) {
            //log the variable & the error
            sLog.w("getJSONdouble", "failed to getJSONdouble " + jsonVar + " - " + e.toString());
            //return zero because the "get" failed
            return 0;
        }
    }

    /*
      get a string from the passed JSON object
     */
    public String getJSONstring(JSONObject jsonData, String jsonVar) {
        //get the proper name of the field
        String varName = getJSONfieldName(jsonData, jsonVar);
        if (varName == null) {
            return "";
        }
        //need a try/catch around JSON stuff as the variable could be the wrong type or not exist
        try {
            return new String(jsonData.getString(varName).getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            //log the variable & the error
            sLog.w("getJSONstring", "failed to getJSONstring " + jsonVar + " - " + e.toString());
            //return an empty string because the "get" failed
            return "";
        }
    }

    /*
      get a boolean from the passed JSON object
     */
    public boolean getJSONboolean(JSONObject jsonData, String jsonVar) {
        //get the proper name of the field
        String varName = getJSONfieldName(jsonData, jsonVar);
        if (varName == null) {
            return false;
        }
        //need a try/catch around JSON stuff as the variable could be the wrong type or not exist
        try {
            //get the Boolean value from the data
            return jsonData.getBoolean(varName);
        } catch (Exception e) {
            //log the variable & the error
            sLog.w("getJSONboolean", "failed to getJSONboolean " + jsonVar + " - " + e.toString());
            //return a false because the "get" failed
            return false;
        }
    }

    /*
      get a JSON object from the passed JSON object
     */
    public JSONObject getJSONobject(JSONObject jsonData, String jsonVar) {
        //get the proper name of the field
        String varName = getJSONfieldName(jsonData, jsonVar);
        if (varName == null) {
            return null;
        }
        //need a try/catch around JSON stuff as the variable could be the wrong type or not exist
        try {
            //get the JSON object from the data
            return jsonData.getJSONObject(varName);
        } catch (Exception e) {
            //log the variable & the error
            sLog.w("getJSONobject", "failed to getJSONobject " + jsonVar + " - " + e.toString());
            //return null because the "get" failed
            return null;
        }
    }

    /*
      get a JSON object from the passed JSON object
     */
    public Object getObject(JSONObject jsonData, String jsonVar) {
        //get the proper name of the field
        String varName = getJSONfieldName(jsonData, jsonVar);
        if (varName == null) {
            return null;
        }
        //need a try/catch around JSON stuff as the variable could be the wrong type or not exist
        try {
            //get the JSON object from the data
            return jsonData.get(varName);
        } catch (Exception e) {
            //log the variable & the error
            sLog.w("getObject", "failed to getObject " + jsonVar + " - " + e.toString());
            //return null because the "get" failed
            return null;
        }
    }

    /*
      get a JSON array from the passed JSON object
     */
    public JSONArray getJSONarray(JSONObject jsonData, String jsonVar) {
        //get the proper name of the field
        String varName = getJSONfieldName(jsonData, jsonVar);
        if (varName == null) {
            return null;
        }
        //need a try/catch around JSON stuff as the variable could be the wrong type or not exist
        try {
            //get the JSON array from the data
            return jsonData.getJSONArray(varName);
        } catch (Exception e) {
            //log the variable & the error
            sLog.w("getJSONarray", "failed to getJSONarray " + jsonVar + " - " + e.toString());
            //return null because the "get" failed
            return null;
        }
    }

    /*
      get a JSON object from the passed JSON object
     */
    public JSONObject getJSONobjectFromArray(JSONArray jsonData, int position) {
        //need a try/catch around JSON stuff as the variable could be the wrong type or not exist
        try {
            //get the JSON object from the data
            return jsonData.getJSONObject(position);
        } catch (Exception e) {
            //log the variable & the error
            sLog.w("getJSONobject", "failed to getJSONobject " + jsonData + " - " + e.toString());
            //return null because the "get" failed
            return null;
        }
    }

    /*
      get a JSON object from the passed JSON object
     */
    public String getJSONstringFromArray(JSONArray jsonData, int position) {
        //need a try/catch around JSON stuff as the variable could be the wrong type or not exist
        try {
            //get the JSON object from the data
            return jsonData.getString(position);
        } catch (Exception e) {
            //log the variable & the error
            sLog.w("getJSONobject", "failed to getJSONobject " + jsonData + " - " + e.toString());
            //return null because the "get" failed
            return null;
        }
    }

    /*
     put something into a JSON object
     */
    public void putJSONobject(JSONObject jsonData, String name, Object object) {
        try {
            jsonData.put(name, object);
        } catch (Exception e) {
            //log the variable & the error
            sLog.w("putJSONobject", "failed to putJSONobject " + jsonData + " - " + e.toString());
        }
    }

    /*
      download a file from a full URL into the app's "image path"
     */
    public void downloadFile(String fileURL, String fileName) {
        //setup the log variable
        String LOG = "downloadFile";
        //block size for downloading the file - 1kb is an acceptable amount
        int aReasonableSize = 1024;
        //start with the URL as null
        URL url = null;
        //get the image path, and make the path
        File file = new File(Constants.IMAGE_PATH);
        file.mkdirs();
        //start an image stream
        InputStream input = null;
        //log the URL & file name
        sLog.w(LOG, "fileURL=" + fileURL + ", fileName=" + fileName);
        //try to open the URL, and the download stream
        try {
            url = new URL(fileURL);
            input = url.openStream();
        } catch (Exception e) {
            //error on the open, so log the message
            e.printStackTrace();
            sLog.w(LOG, "error in creating URL:" + e.toString());
        }
        //if we have a URL and download stream
        if (url != null && input != null) {
            //try to start the download process
            try {
                //start an output stream
                OutputStream output = null;
                //create a new file
                File currentFile = new File(Constants.IMAGE_PATH, fileName);
                try {
                    //create the output file
                    output = new FileOutputStream(currentFile);
                } catch (Exception e) {
                    //catch the error and log the message
                    sLog.w(LOG, "output error:" + e.toString());
                }
                //if the output file isn't null we download the file
                if (output != null) {
                    //try the download process
                    try {
                        //create a byte array the size of the download block
                        byte[] buffer = new byte[aReasonableSize];
                        // number of bytes downloaded
                        int bytesRead = 0;
                        // loop through the file file until there's nothing left to read, writing the bytes to the file
                        while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                            output.write(buffer, 0, bytesRead);
                        }
                    } catch (Exception e) {
                        //log the output error
                        sLog.w(LOG, "stream error:" + e.toString());
                    }
                    //try to close the output file - if it fails, don't do anything with the error
                    try {
                        output.close();
                    } catch (Exception e) {
                        ;
                    }
                }
                //catch on the output process, ignore the error
            } catch (Exception e) {
                ;
            }
            //try to close the input, ignore the error
            try {
                input.close();
            } catch (Exception e) {
                ;
            }
        }
    }

    /*
      SQL Cursor Methods
 */
    /*
      find the correct spelling of the field
     */
    public String getCursorFieldName(Cursor cursor, String field) {
        if (cursor == null) {
            return null;
        }
        String fieldName = null;
        String[] columnNames = cursor.getColumnNames();
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].toLowerCase().equals(field.toLowerCase())) {
                fieldName = columnNames[i];
            }
        }
        return fieldName;
    }

    /*
      get a field from the passed cursor, using the field name
     */
    public String getField(Cursor cursor, String field) {
        // get the actual field name, ignoring case
        String fieldName = getCursorFieldName(cursor, field);
        if (fieldName == null) {
            return "";
        }
        // cursors naturally take fields from an int, so we look-up the field name to make it easier
        return cursor.getString(cursor.getColumnIndex(fieldName));
    }

    /*
      get an int field from the passed cursor, using the field name
     */
    public Integer getFieldInt(Cursor cursor, String field) {
        // get the actual field name, ignoring case
        String fieldName = getCursorFieldName(cursor, field);
        if (fieldName == null) {
            return 0;
        }
        // cursors naturally take fields from an int, so we look-up the field name to make it easier
        return cursor.getInt(cursor.getColumnIndex(fieldName));
    }
}
