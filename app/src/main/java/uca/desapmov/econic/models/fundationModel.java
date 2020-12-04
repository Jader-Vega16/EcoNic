package uca.desapmov.econic.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class fundationModel implements Parcelable {

    private String ImgUrl;
    private String nombre;
    private String descripcion;
    private String mission;
    private String vision ;
    private String direccion;
    private String telefono;
    private String paginaWeb;

    public fundationModel(String imgUrl, String nombre, String descripcion, String mission, String vision, String direccion, String telefono, String paginaWeb) {
        ImgUrl = imgUrl;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.mission = mission;
        this.vision = vision;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }


    protected fundationModel(Parcel in) {
        ImgUrl = in.readString();
        nombre = in.readString();
        descripcion = in.readString();
        mission = in.readString();
        vision  = in.readString();
        direccion = in.readString();
        telefono = in.readString();
        paginaWeb = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ImgUrl);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(mission);
        dest.writeString(vision);
        dest.writeString(direccion);
        dest.writeString(telefono);
        dest.writeString(paginaWeb);
    }

    public static final Parcelable.Creator<fundationModel> CREATOR = new Parcelable.Creator<fundationModel>() {
        @Override
        public fundationModel createFromParcel(Parcel in) {
            return new fundationModel(in);
        }

        @Override
        public fundationModel[] newArray(int size) {
            return new fundationModel[size];
        }
    };
}
