package com.example.app_samsat;

public class AdapterLokasi {

    private String Nama;
    private String Alamat;
    private String Info;
    private String Nomor;
    private String Latitude;
    private String Longitude;
    private String ImageUrl;
    private String Video;

    public AdapterLokasi(String nama, String alamat, String info, String nomor, String latitude, String longitude, String imageUrl, String video) {
        Nama = nama;
        Alamat = alamat;
        Info = info;
        Nomor = nomor;
        Latitude = latitude;
        Longitude = longitude;
        ImageUrl = imageUrl;
        Video = video;
    }

    public AdapterLokasi() {
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getNomor() {
        return Nomor;
    }
    //create and develop by yuvi sandika
    public void setNomor(String nomor) {
        Nomor = nomor;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }
}