package uca.desapmov.econic.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import uca.desapmov.econic.helpers.*;
import uca.desapmov.econic.models.*;

public class fundationAssetSource {
    private final Gson parser;
    private final Context mContext;
    private static final String RECYCLE_FILE_NAME = "fundaciones.json";
    private static final String RECYCLE_SERIALIZED_NAME = "fundaciones";

    public fundationAssetSource(@NonNull Context context) {
        mContext = context;
        parser = new Gson();
    }

    public List<fundationModel> getAll() {
        String json = FileHelper.getJsonFromAssets(mContext, RECYCLE_FILE_NAME);
        ListResult listResult = parser.fromJson(json, ListResult.class);
        if(listResult == null) return null;
        return listResult.list;
    }

    static class ListResult {
        @SerializedName(RECYCLE_SERIALIZED_NAME)
        List<fundationModel> list;

        public ListResult(List<fundationModel> list) {
            this.list = list;
        }
    }
}
