package uca.desapmov.econic.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import uca.desapmov.econic.helpers.*;
import uca.desapmov.econic.models.*;

public class metasAssetSource {
    private final Gson parser;
    private final Context mContext;
    private static final String RECYCLE_FILE_NAME = "metas.json";
    private static final String RECYCLE_SERIALIZED_NAME = "metas";

    public metasAssetSource(@NonNull Context context) {
        mContext = context;
        parser = new Gson();
    }

    public List<metasModel> getAll() {
        String json = FileHelper.getJsonFromAssets(mContext, RECYCLE_FILE_NAME);
        ListResult listResult = parser.fromJson(json, ListResult.class);
        if(listResult == null) return null;
        return listResult.list;
    }

    static class ListResult {
        @SerializedName(RECYCLE_SERIALIZED_NAME)
        List<metasModel> list;

        public ListResult(List<metasModel> list) {
            this.list = list;
        }
    }
}
