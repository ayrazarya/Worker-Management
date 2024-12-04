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
import java.util.*;

public class ProgramUtama {
    public static void main(String[] args) {
        // Membuat objek ManajemenKaryawan
        ManajemenKaryawan manajemen = new ManajemenKaryawan();
        Scanner scanner;
        scanner = new Scanner(System.in);

        try {
            // Membaca data karyawan dari file
            String namaFile = "data_karyawan_dict.txt";
            File file = new File(namaFile);
            if (!file.exists()) {
                System.out.println("File " + namaFile + " tidak ditemukan.");
                System.out.println("Membuat file baru...");
                file.createNewFile();
            } else {
                BufferedReader reader;
                reader = new BufferedReader(new FileReader(namaFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] dataKaryawan = line.split(";");
                    String nama = dataKaryawan[0];
                    int id = Integer.parseInt(dataKaryawan[1]);
                    String email = dataKaryawan[2];
                    String nomerTelepon = (dataKaryawan[3]);
                    double gajiPokok = Double.parseDouble(dataKaryawan[4]);
                    String tipe = dataKaryawan[5];
                    if (tipe.equalsIgnoreCase("Tetap") && dataKaryawan.length >= 7) {
                        double tunjangan = Double.parseDouble(dataKaryawan[6]);
                        manajemen.tambahKaryawanTetap(nama, id, email, nomerTelepon, gajiPokok, tipe, tunjangan);
                    } else if (tipe.equalsIgnoreCase("Kontrak")) {
                        manajemen.tambahKaryawanKontrak(nama, id, email, nomerTelepon, gajiPokok, tipe);
                    }
                }


                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file.");
        }

        // Menampilkan menu konsol
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Lihat Data Karyawan");
            System.out.println("2. Tambah Karyawan Tetap");
            System.out.println("3. Tambah Karyawan Kontrak");
            System.out.println("4. Hapus Karyawan");
            System.out.println("5. Ubah Gaji Karyawan");
            System.out.println("6 Filter Karyawan");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    manajemen.lihatDataKaryawan();
                    break;
                case 2:
                    tambahKaryawanTetap(scanner, manajemen);
                    break;
                case 3:
                    tambahKaryawanKontrak(scanner, manajemen);
                    break;
                case 4:
                    hapusKaryawan(scanner, manajemen);
                    break;
                case 5:
                    ubahGajiKaryawan(scanner, manajemen);
                case 6 :
                    filterKaryawan(scanner, manajemen);
                    break;
                case 7:
                    exit = true;
                    manajemen.simpanDataKaryawan("data_karyawan_dict.txt");
                    break;
                default:
                    System.out.println("Menu tidak valid.");
            }
        }

        // Menutup scanner
        scanner.close();
    }

    public static void filterKaryawan(Scanner scanner, ManajemenKaryawan manajemen) {
        System.out.println("Filter Berdasarkan: ");
        System.out.println("1. ID");
        System.out.println("2. Email");
        System.out.println("3. Nomer Telepon");

        int pilihan = scanner.nextInt();
        switch (pilihan) {
            case 1:


                break;

            case 2:
                filterKaryawanEmail(scanner, manajemen);

                break;

            case 3:
                break;

            default:
                System.out.println("Pilihan tidak valid");
        }
    }

    public static void tambahKaryawanTetap(Scanner scanner, ManajemenKaryawan manajemen) {
        System.out.print("Nama: ");
        String namaTetap = scanner.next();
        System.out.print("ID Karyawan: ");
        int idTetap = scanner.nextInt();

        System.out.println("Email: ");
        String email = scanner.next();

        System.out.println("Nomer Telepon ");
        String nomerTelepon = scanner.next();

        System.out.print("Gaji Pokok: ");
        double gajiPokokTetap = scanner.nextDouble();

        System.out.print("Tunjangan: ");
        double tunjangan = scanner.nextDouble();

        manajemen.tambahKaryawanTetap(namaTetap, idTetap, email , nomerTelepon, gajiPokokTetap, "Tetap", tunjangan);
        System.out.println("Karyawan tetap berhasil ditambahkan.");
    }

    public static void tambahKaryawanKontrak(Scanner scanner, ManajemenKaryawan manajemen) {
        System.out.print("Nama: ");
        String namaKontrak = scanner.next();
        System.out.print("ID Karyawan: ");
        System.out.println("Email: ");
        String email = scanner.next();
        System.out.println("Nomer Telepon ");
        String nomerTelepon = scanner.next();
        int idKontrak = scanner.nextInt();
        System.out.print("Gaji Pokok: ");
        double gajiPokokKontrak = scanner.nextDouble();
        manajemen.tambahKaryawanKontrak(namaKontrak, idKontrak,email, nomerTelepon  ,gajiPokokKontrak, "Kontrak");
        System.out.println("Karyawan kontrak berhasil ditambahkan.");
    }

    public static void hapusKaryawan(Scanner scanner, ManajemenKaryawan manajemen) {
        System.out.print("ID Karyawan yang akan dihapus: ");
        int idHapus = scanner.nextInt();
        manajemen.hapusKaryawan(idHapus);
        System.out.println("Karyawan berhasil dihapus.");
    }

    public static void ubahGajiKaryawan(Scanner scanner, ManajemenKaryawan manajemen) {
        System.out.print("ID Karyawan yang gajinya akan diubah: ");
        int idUbah = scanner.nextInt();
        System.out.print("Gaji Baru: ");
        double gajiBaru = scanner.nextDouble();
        manajemen.ubahGajiKaryawan(idUbah, gajiBaru);
        System.out.println("Gaji karyawan berhasil diubah.");
    }

    public static void filterKaryawanEmail(Scanner scanner, ManajemenKaryawan manajemen){
        System.out.println("Masukan email Karyawan : ");
        String email = scanner.next();
        manajemen.filterKaryawanEmail(email);

    }
    public static void filterKaryawanNotelp(Scanner scanner, ManajemenKaryawan manajemen){
        System.out.println("Masukan Nama Karyawan : ");
        String nomor = scanner.next();
        manajemen.filterKaryawanNOtelp(nomor);

    }



}