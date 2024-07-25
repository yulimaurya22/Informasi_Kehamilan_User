package com.yulia.informasikehamilan.MODEL;

// model ini digunakan untuk menempatkan data user saat login, data nya dari JSON
public class Model_user {
    private String id;
    private String username;
    private String password;
    private String namabunda;
    private String usiakehamilan;
    private String beratbadanbunda;
    private String tinggibadanbunda;
    private String tekanandarah;
    private String beratjanin;
    private String panjangjanin;
    private String detakjantungjanin;
    private String obatyangdikonsumsi;

    public Model_user(String newName, String newUsername, String newPassword) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamabunda() {
        return namabunda;
    }

    public void setNamabunda(String namabunda) {
        this.namabunda = namabunda;
    }

    public String getUsiakehamilan() {
        return usiakehamilan;
    }

    public void setUsiakehamilan(String usiakehamilan) {
        this.usiakehamilan = usiakehamilan;
    }

    public String getBeratbadanbunda() {
        return beratbadanbunda;
    }

    public void setBeratbadanbunda(String beratbadanbunda) {
        this.beratbadanbunda = beratbadanbunda;
    }

    public String getTinggibadanbunda() {
        return tinggibadanbunda;
    }

    public void setTinggibadanbunda(String tinggibadanbunda) {
        this.tinggibadanbunda = tinggibadanbunda;
    }

    public String getTekanandarah() {
        return tekanandarah;
    }

    public void setTekanandarah(String tekanandarah) {
        this.tekanandarah = tekanandarah;
    }

    public String getBeratjanin() {
        return beratjanin;
    }

    public void setBeratjanin(String beratjanin) {
        this.beratjanin = beratjanin;
    }

    public String getPanjangjanin() {
        return panjangjanin;
    }

    public void setPanjangjanin(String panjangjanin) {
        this.panjangjanin = panjangjanin;
    }

    public String getDetakjantungjanin() {
        return detakjantungjanin;
    }

    public void setDetakjantungjanin(String detakjantungjanin) {
        this.detakjantungjanin = detakjantungjanin;
    }

    public String getObatyangdikonsumsi() {
        return obatyangdikonsumsi;
    }

    public void setObatyangdikonsumsi(String obatyangdikonsumsi) {
        this.obatyangdikonsumsi = obatyangdikonsumsi;
    }
}
