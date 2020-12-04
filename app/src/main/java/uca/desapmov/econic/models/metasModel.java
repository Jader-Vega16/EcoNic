package uca.desapmov.econic.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class metasModel implements Parcelable {

    private String ImgUrl;
    private String nombre;
    private String meta;

    public metasModel(String ImgUrl, String nombre, String meta) {
        ImgUrl = ImgUrl;
        this.nombre = nombre;
        this.meta = meta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        ImgUrl = ImgUrl;
    }

    public String getmeta() {
        return meta;
    }

    public void setmeta(String meta) {
        this.meta = meta;
    }

    protected metasModel(Parcel in) {
        nombre = in.readString();
        meta = in.readString();
        ImgUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ImgUrl);
        dest.writeString(nombre);
        dest.writeString(meta);
    }

    public static final Parcelable.Creator<metasModel> CREATOR = new Parcelable.Creator<metasModel>() {
        @Override
        public metasModel createFromParcel(Parcel in) {
            return new metasModel(in);
        }

        @Override
        public metasModel[] newArray(int size) {
            return new metasModel[size];
        }
    };
}
