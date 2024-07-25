package com.yulia.informasikehamilan.MODEL;

import java.util.ArrayList;

public class Model_berat extends ArrayList<Model_berat> {
    private String judul;
    private String keterangan;
    private String gejala;
    private String penyebab;
    private String penanganan;

    public Model_berat(String judul, String keterangan) {
        this.judul = judul;
        this.keterangan = keterangan;
        this.gejala = gejala;
        this.penyebab = penyebab;
        this.penanganan = penanganan;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getGejala() {
        return gejala;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

    public String getPenyebab() {
        return penyebab;
    }

    public void setPenyebab(String penyebab) {
        this.penyebab = penyebab;
    }

    public String getPenanganan() {
        return penanganan;
    }

    public void setPenanganan(String penanganan) {
        this.penanganan = penanganan;
    }


}
