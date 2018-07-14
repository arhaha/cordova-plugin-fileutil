package com.matrixgz.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class echoes a string called from JavaScript.
 */
public class FileUtil extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("readFileAsBytes")) {
            String filePath = args.getString(0);
            this.readFileAsBytes(filePath, callbackContext);
            return true;
        }
        return false;
    }

    private void readFileAsBytes(String filePath, CallbackContext callbackContext) {
        if (!filePath.isEmpty()) {
            byte[] result = readBytesFromFile(filePath);
            callbackContext.success(result);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {
            File file = new File(stripFileProtocol(filePath));
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }

    public static String stripFileProtocol(String uriString) {
        if (uriString.startsWith("file://")) {
            uriString = uriString.substring(7);
        }
        return uriString;
    }
}
