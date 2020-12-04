package uca.desapmov.econic.helpers;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileHelper {
    public static String getJsonFromAssets(Context context, String fileName) {
        try {
            String jsonString;
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                jsonString = new String(buffer, StandardCharsets.UTF_8);
            } else {
                jsonString = new String(buffer, "UTF-8");
            }

            return jsonString;
        } catch (IOException e) {
            Log.e("FileHelper",
                    "Ocurrió un error al procesar el archivo. " + e.getMessage()
            );
            return null;
        }
    }
}
