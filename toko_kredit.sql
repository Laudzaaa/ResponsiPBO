CREATE DATABASE IF NOT EXISTS toko_kredit;

USE toko_kredit;

CREATE TABLE kredit(
 id INT AUTO_INCREMENT PRIMARY KEY,
 nama_customer VARCHAR(100) NOT NULL,
 barang VARCHAR(50) NOT NULL,
 harga_barang DOUBLE NOT NULL,
 tenor INT NOT NULL,
 bunga_per_bulan DOUBLE NOT NULL,
 angsuran_per_bulan DOUBLE NOT NULL,
 total_pembayaran DOUBLE NOT NULL
);