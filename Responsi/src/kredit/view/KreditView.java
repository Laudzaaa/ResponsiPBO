/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kredit.view;

import kredit.controller.KreditController;
import kredit.model.Kredit;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import kredit.controller.KreditController;
import kredit.model.Kredit;


/**
 *
 * @author PC PRAKTIKUM
 */
public class KreditView extends JFrame{
    private JTextField txtNama, txtHarga;
    private JComboBox<String> cmbBarang;
    private JRadioButton rb3, rb6, rb12;
    private JButton btnCreate, btnEdit, btnDelete, btnReset;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private KreditController controller;
    
    public KreditView(){
        setTitle("TOKO BELA NEGARA");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        try {
                controller = new KreditController();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal koneksi database: " + e.getMessage());
                System.exit(1);
            }
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtNama = new JTextField();
        txtHarga = new JTextField();
        cmbBarang = new JComboBox<>(new String[]{"TV", "Kulkas", "Mesin Cuci"});
        rb3 = new JRadioButton("3 Bulan");
        rb6 = new JRadioButton("6 Bulan");
        rb12 = new JRadioButton("12 Bulan");
        ButtonGroup bgTenor = new ButtonGroup();
        bgTenor.add(rb3);
        bgTenor.add(rb6);
        bgTenor.add(rb12);
        rb3.setSelected(true);
        JPanel panelTenor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTenor.add(rb3);
        panelTenor.add(rb6);
        panelTenor.add(rb12);
        panelInput.add(new JLabel("Nama Customer:"));
        panelInput.add(txtNama);
        panelInput.add(new JLabel("Barang:"));
        panelInput.add(cmbBarang);
        panelInput.add(new JLabel("Harga Barang:"));
        panelInput.add(txtHarga);
        panelInput.add(new JLabel("Cicilan:"));
        panelInput.add(panelTenor);
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnCreate = new JButton("Create");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnReset = new JButton("Reset");
        panelButton.add(btnCreate);
        panelButton.add(btnEdit);
        panelButton.add(btnDelete);
        panelButton.add(btnReset);
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(panelInput, BorderLayout.CENTER);
        panelTop.add(panelButton, BorderLayout.SOUTH);
        String[] kolom = {"ID", "Nama Customer", "Barang", "Harga", "Tenor", "Bunga / Bulan", "Angsuran /Bulan", "Total"};
        tableModel = new DefaultTableModel(kolom, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        btnCreate.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> editData());
        btnDelete.addActionListener(e -> hapusData());
        btnReset.addActionListener(e -> resetForm());
        table.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());
        tampilkanData();
        }
    private int getTenor() {
        if (rb3.isSelected()) return 3;
        if (rb6.isSelected()) return 6;
        return 12;
    }
    private void tambahData() {
        try {
            String nama = txtNama.getText();
            String barang = cmbBarang.getSelectedItem().toString();
            int harga = Integer.parseInt(txtHarga.getText());
            int tenor = getTenor();
            double bunga = (1.5 / 100) * harga;
            double angsuran = (harga / (double)tenor) + bunga;
            double total = angsuran * tenor;
            Kredit k = new Kredit(nama, barang, harga, tenor, bunga, angsuran, total);
            controller.addKredit(k);
            tampilkanData();
            resetForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
        }
        }
    private void editData() {
            int row = table.getSelectedRow();
            if (row >= 0) {
            try {
            int id = (int) tableModel.getValueAt(row, 0);
            String nama = txtNama.getText();
            String barang = cmbBarang.getSelectedItem().toString();
            int harga = Integer.parseInt(txtHarga.getText());
            int tenor = getTenor();
            double bunga = (1.5 / 100) * harga;
            double angsuran = (harga / (double)tenor) + bunga;
            double total = angsuran * tenor;
            Kredit k = new Kredit(id, nama, barang, harga, tenor, bunga, angsuran, total);
            controller.updateKredit(k);
            tampilkanData();
            resetForm();
            } catch (Exception ex) {
         JOptionPane.showMessageDialog(this, "Gagal update: " + ex.getMessage());
            }
         } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diedit");
            }
        }
    private void hapusData() {
            int row = table.getSelectedRow();
            if (row >= 0) {
            int id = (int) tableModel.getValueAt(row, 0);
            try {
            controller.deleteKredit(id);
            tampilkanData();
            resetForm();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal hapus: " + ex.getMessage());
            }
            } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus");
            }
        }
    private void tampilkanData() {
            try {
            List<Kredit> list = controller.getAllKredit();
            tableModel.setRowCount(0);
            for (Kredit k : list) {
            tableModel.addRow(new Object[]{
            k.getId(), k.getNamaCustomer(), k.getBarang(), k.getHarga(),
            k.getTenor(), format(k.getBungaPerBulan()),
            format(k.getAngsuran()), format(k.getTotalPembayaran())
            });
            }
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal ambil data: " + ex.getMessage());
        }
    }
    private void isiFormDariTabel() {
        int row = table.getSelectedRow();
        if (row >= 0) {
        txtNama.setText(tableModel.getValueAt(row, 1).toString());
        cmbBarang.setSelectedItem(tableModel.getValueAt(row, 2).toString());
        txtHarga.setText(tableModel.getValueAt(row, 3).toString());
        int tenor = Integer.parseInt(tableModel.getValueAt(row, 4).toString());
        if (tenor == 3) rb3.setSelected(true);
        else if (tenor == 6) rb6.setSelected(true);
        else rb12.setSelected(true);
        }
    }
    private void resetForm() {
        txtNama.setText("");
        txtHarga.setText("");
        cmbBarang.setSelectedIndex(0);
        rb3.setSelected(true);
        table.clearSelection();
     }
    private String format(double angka) {
        return String.format("%.0f", angka);
    }
    } 

