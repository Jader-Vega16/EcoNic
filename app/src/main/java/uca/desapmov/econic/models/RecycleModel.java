package uca.desapmov.econic.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecycleModel implements Parcelable {

    private String areaImg;
    private String ImgUrl;
    private String nombre;
    private String categoria;
    private String superficie;
    private String descripcion;
    private String fauna;
    private String region;

    public RecycleModel(String areaImg, String ImgUrl, String nombre, String categoria, String superficie, String descripcion, String fauna, String region) {
        this.areaImg = areaImg;
        this.ImgUrl = ImgUrl;
        this.nombre = nombre;
        this.categoria = categoria;
        this.superficie = superficie;
        this.descripcion = descripcion;
        this.fauna = fauna;
        this.region = region;
    }

    public String getAreaImg() {
        return areaImg;
    }

    public void setAreaImg(String areaImg) {
        this.areaImg = areaImg;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFauna() {
        return fauna;
    }

    public void setFauna(String fauna) {
        this.fauna = fauna;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    protected RecycleModel(Parcel in) {
         areaImg = in.readString();
         ImgUrl = in.readString();
         nombre = in.readString();
         categoria = in.readString();
         superficie = in.readString();
         descripcion = in.readString();
         fauna = in.readString();
         region = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(areaImg);
        dest.writeString(ImgUrl);
        dest.writeString(nombre);
        dest.writeString(categoria);
        dest.writeString(superficie);
        dest.writeString(descripcion);
        dest.writeString(fauna);
        dest.writeString(region);
    }

    public static final Parcelable.Creator<RecycleModel> CREATOR = new Parcelable.Creator<RecycleModel>() {
        @Override
        public RecycleModel createFromParcel(Parcel in) {
            return new RecycleModel(in);
        }

        @Override
        public RecycleModel[] newArray(int size) {
            return new RecycleModel[size];
        }
    };
}
