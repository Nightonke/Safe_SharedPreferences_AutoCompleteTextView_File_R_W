package com.nightonke.ex_09;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Weiping on 2015/11/27.
 */

public class FileUtils {

    private static FileUtils ourInstance = new FileUtils();

    public static FileUtils getInstance() {
        return ourInstance;
    }

    private FileUtils() {

    }

    public void save(Context context, String fileName, String content) {
        if ("".equals(fileName)) {
            Toast.makeText(context, "Empty file name!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            FileOutputStream fileOutputStream
                    = context.openFileOutput(fileName, Context.MODE_PRIVATE);

            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();

            Toast.makeText(context, "Save successfully!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Save failed!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public String read(Context context, String fileName) {
        File file = new File(context.getFilesDir() + "/" + fileName);

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();

            return text.toString();
        }
        catch (IOException e) {
            Toast.makeText(context, "Read failed!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return new String("");
        }
    }

    public Boolean delete(Context context, String fileName) {
        if (context.deleteFile(fileName)) {
            Toast.makeText(context, "Delete successfully!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "Delete failed!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
