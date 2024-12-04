/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopdictionary;

/**
 *
 * @author azka
 */
public abstract class Karyawan {
    private String nama;
    private int idKaryawan;

    private String email;

    private String nomerTelpon;
    private double gajiPokok;
    private String jenisKaryawan;


    public Karyawan(String nama, int idKaryawan, String email, String nomerTelpon ,double gajiPokok, String jenisKaryawan) {
        this.nama = nama;
        this.idKaryawan = idKaryawan;
        this.gajiPokok = gajiPokok;
        this.jenisKaryawan = jenisKaryawan;
        this.email = email;
        this.nomerTelpon = nomerTelpon;
    }

    public String getJenisKaryawan() {
        return jenisKaryawan;
    }

    public void setJenisKaryawan(String jenisKaryawan) {
        this.jenisKaryawan = jenisKaryawan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(int idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomerTelpon() {
        return nomerTelpon;
    }

    public void setNomerTelpon(String nomerTelpon) {
        this.nomerTelpon = nomerTelpon;
    }

    public double getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(double gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

}

