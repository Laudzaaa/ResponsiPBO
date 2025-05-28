/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kredit.controller;

import kredit.model.Kredit;
import kredit.kon.Koneksi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author PC PRAKTIKUM
 */
public class KreditController {
    private Connection conn;
    
    public KreditController()throws SQLException{
        conn = Koneksi.getKoneksi();
    }
    public void addKredit(Kredit k) throws SQLException{
        String sql = "INSERT INTO kredit(nama_customer,barang,harga_barang,tenor,bunga_perbulan,angsuran_perbulan,total_pembayaran)VALUES(?,?,?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, k.getNamaCustomer());
            stmt.setString(2, k.getBarang());
            stmt.setInt(3, k.getHarga());
            stmt.setInt(4, k.getTenor());
            stmt.setDouble(5, k.getBungaPerBulan());
            stmt.setDouble(6, k.getAngsuran());
            stmt.setDouble(7, k.getTotalPembayaran());
            stmt.executeUpdate();
        }
    }
    
    public void updateKredit(Kredit k) throws SQLException{
        String sql = "UPDATE kredit SET nama_customer=?,barang=?,harga_barang=?,tenor=?,bunga_perbulan=?,angsuran_perbulan=?,total_pembayaran=? WHERE id=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, k.getNamaCustomer());
            stmt.setString(2, k.getBarang());
            stmt.setInt(3, k.getHarga());
            stmt.setInt(4, k.getTenor());
            stmt.setDouble(5, k.getBungaPerBulan());
            stmt.setDouble(6, k.getAngsuran());
            stmt.setDouble(7, k.getTotalPembayaran());
            stmt.setInt(8, k.getId());
            stmt.executeUpdate();
        }
    }
    
    public void deleteKredit(int id) throws SQLException{
        String sql = "DELETE FROM kredit WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }
    
    public List<Kredit>getAllKredit() throws SQLException {
        List<Kredit>list = new ArrayList<>();
        String sql = "SELECT * FROM kredit";
        try(Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                Kredit k = new Kredit(
                 rs.getInt("id"),
                 rs.getString("nama_customer"),
                 rs.getString("barang"),
                 rs.getInt("harga_barang"),
                 rs.getInt("tenor"),
                 rs.getDouble("bunga_perbulan"),
                 rs.getDouble("angsuran_perbulan"),
                 rs.getDouble("total_pembayaran")
                );
                list.add(k);
            }
        }
                return list;
    }
}
