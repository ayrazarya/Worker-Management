/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopdictionary;

/**
 *
 * @author azka
 */
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManajemenKaryawan {
    private final Map<Integer, Karyawan> daftarKaryawan;

    public ManajemenKaryawan() {
        daftarKaryawan = new HashMap<>();
    }

    public void tambahKaryawanTetap(String nama, int idKaryawan, String email, String nomerTelepon, double gajiPokok, String jenisKaryawan, double tunjangan) {
        daftarKaryawan.put(idKaryawan, new KaryawanTetap(nama, idKaryawan, email,nomerTelepon, gajiPokok, jenisKaryawan, tunjangan));
    }

    public void tambahKaryawanKontrak(String nama, int idKaryawan, String email, String nomerTelpon, double gajiPokok, String jenisKaryawan) {
        daftarKaryawan.put(idKaryawan, new KaryawanKontrak(nama, idKaryawan,email,nomerTelpon ,gajiPokok, jenisKaryawan));
    }

    public void hapusKaryawan(int idKaryawan) {
        daftarKaryawan.remove(idKaryawan);
    }

    public void ubahGajiKaryawan(int idKaryawan, double gajiBaru) {
        if (daftarKaryawan.containsKey(idKaryawan)) {
            Karyawan karyawan = daftarKaryawan.get(idKaryawan);
            karyawan.setGajiPokok(gajiBaru);
        } else {
            System.out.println("Karyawan dengan ID " + idKaryawan + " tidak ditemukan.");
        }
    }


    public Map<Integer, String> getDaftarKaryawan() {
        Map<Integer, String> daftarNamaKaryawan = new HashMap<>();
        for (Map.Entry<Integer, Karyawan> entry : daftarKaryawan.entrySet()) {
            Karyawan karyawan = entry.getValue();
            daftarNamaKaryawan.put(karyawan.getIdKaryawan(), karyawan.getNama());
        }
        return daftarNamaKaryawan;
    }

    public void lihatDataKaryawan() {
        System.out.println("\n===== DATA KARYAWAN =====");
        for (Karyawan karyawan : daftarKaryawan.values()) {
            System.out.println("Nama: " + karyawan.getNama());
            System.out.println("ID: " + karyawan.getIdKaryawan());
            System.out.println("Email: " + karyawan.getEmail());
            System.out.println("Nomer Telepon: " + karyawan.getNomerTelpon());
            System.out.println("Gaji Pokok: " + karyawan.getGajiPokok());
            System.out.println("Jenis karyawan: " + karyawan.getJenisKaryawan());
            if (karyawan instanceof KaryawanTetap) {
                KaryawanTetap tetap = (KaryawanTetap) karyawan;
                System.out.println("Tunjangan: " + tetap.getTunjangan());
                System.out.println("Gaji Total: " + tetap.hitungGajiTotal());
            }
            System.out.println("------------------------");
        }
    }

    public void simpanDataKaryawan(String namaFile) {
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(namaFile));
            for (Karyawan karyawan : daftarKaryawan.values()) {
                if (karyawan instanceof KaryawanTetap) {
                    KaryawanTetap tetap = (KaryawanTetap) karyawan;
                    writer.write(tetap.getNama() + ";" + tetap.getIdKaryawan() + ";" + tetap.getEmail() + ";" + tetap.getNomerTelpon() + ";" + tetap.getGajiPokok() + ";" + tetap.getJenisKaryawan() + ";" + tetap.getTunjangan() + "\n");
                } else if (karyawan instanceof KaryawanKontrak) {
                    KaryawanKontrak kontrak = (KaryawanKontrak) karyawan;
                    writer.write(kontrak.getNama() + ";" + kontrak.getIdKaryawan() + ";" + kontrak.getEmail() + ";" + kontrak.getNomerTelpon() + ";" + kontrak.getGajiPokok() + ";" + kontrak.getJenisKaryawan() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Gagal menulis ke file.");
        }
    }


    // Inner class KaryawanTetap
    private class KaryawanTetap extends Karyawan {
        private final double tunjangan;

        public KaryawanTetap(String nama, int idKaryawan,  String email, String nomerTelpon ,double gajiPokok, String jenisKaryawan, double tunjangan) {
            super(nama, idKaryawan, email,nomerTelpon ,gajiPokok, jenisKaryawan);
            this.tunjangan = tunjangan;
        }

        public double hitungGajiTotal() {
            return getGajiPokok() + tunjangan;
        }

        public double getTunjangan() {
            return tunjangan;
        }
    }

    // Inner class KaryawanKontrak
    private class KaryawanKontrak extends Karyawan {
        public KaryawanKontrak(String nama, int idKaryawan, String email, String nomerTelpon, double gajiPokok, String jenisKaryawan) {
            super(nama, idKaryawan, email, nomerTelpon, gajiPokok, jenisKaryawan);
        }
    }

    public void filterNamaKaryawan(String nama) {

    }


    public void filterKaryawanEmail (String email){
        String awalanEmail = email;
        boolean found = false;

        // Mencari karyawan berdasarkan email dalam file menggunakan regex
        try (BufferedReader reader = new BufferedReader(new FileReader("data_karyawan_dict.txt"))) {
            String line;
            Pattern pattern = Pattern.compile("^." + Pattern.quote(awalanEmail) + ".$", Pattern.CASE_INSENSITIVE);

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                String emailKaryawan = data[2]; // Email karyawan berada di indeks 4

                // Cocokkan email input dengan email karyawan menggunakan regex
                Matcher matcher = pattern.matcher(emailKaryawan);
                if (matcher.matches()) {
                    found = true;
                    // Tampilkan info karyawan
                    System.out.println("\n===== INFO KARYAWAN =====");
                    System.out.println("Nama: " + data[0]);
                    System.out.println("ID: " + data[1]);
                    System.out.println("Email : " + emailKaryawan);
                    System.out.println("Nomor Telepon: " + data[3]);
                    System.out.println("Gaji Pokok: " + data[4]);
                    System.out.println("Jenis karyawan: " + data[5]);
                    if (data.length > 6) { // Jika karyawan adalah karyawan tetap
                        System.out.println("Tunjangan: " + data[6]);
                        double gajiTotal = Double.parseDouble(data[2]) + Double.parseDouble(data[6]);
                        System.out.println("Gaji Total: " + gajiTotal);
                    }
                    System.out.println("==========================");

                }




            }
            if (!found) {
                System.out.println("Karyawan dengan email '" + awalanEmail + "' tidak ditemukan.");
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file.");
        }
    }


    public void filterKaryawanNOtelp (String nomerTelpon){
        for (Karyawan karyawan : daftarKaryawan.values()) {
            if (karyawan.getNomerTelpon() == nomerTelpon) {
                //
                System.out.println("Karyawan dengan nomer telepon " + nomerTelpon + "\n" + "dengan nama " + karyawan.getNama()+" ditemukan");

            }

        }
    }

    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method untuk validasi nomor telepon
    public boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^08\\d{8,10}$"; // Nomor telepon diawali '08' dan memiliki 10-12 digit angka
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}


