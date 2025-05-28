/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kredit.model;

/**
 *
 * @author PC PRAKTIKUM
 */
public class Kredit {
    private int id;
    private String namaCustomer, barang;
    private int harga, tenor;
    private double bungaPerBulan, angsuran, totalPembayaran;
    
    public Kredit(int id, String namaCustomer, String barang, int harga, int tenor, double bungaPerBulan, double angsuran, double totalPembayaran){
        this.id = id;
        this.namaCustomer = namaCustomer;
        this.barang = barang;
        this.harga = harga;
        this.tenor = tenor;
        this.bungaPerBulan = bungaPerBulan;
        this.angsuran = angsuran;
        this.totalPembayaran = totalPembayaran;
    }
    
    public Kredit(String namaCustomer,String barang, int harga, int tenor, double bungaPerBulan, double Angsuran, double totalPembayaran){
        this.namaCustomer = namaCustomer;
        this.barang = barang;
        this.harga = harga;
        this.tenor = tenor;
        this.bungaPerBulan = bungaPerBulan;
        this.angsuran = angsuran;
        this.totalPembayaran = totalPembayaran;
    }
    
    public int getId(){return id;}
    public String getNamaCustomer(){return namaCustomer;}
    public String getBarang(){return barang;}
    public int getHarga(){return harga;}
    public int getTenor(){return tenor;}
    public double getBungaPerBulan(){return bungaPerBulan;}
    public double getAngsuran(){return angsuran;}
    public double getTotalPembayaran(){return totalPembayaran;}
}
