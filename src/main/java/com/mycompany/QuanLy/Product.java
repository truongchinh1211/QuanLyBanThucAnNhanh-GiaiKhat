package com.mycompany.QuanLy;

import java.util.Scanner;

public class Product {
    private String productName;
    private String productID;
    private int soLuong;
    private double donGia;

    Scanner sc = new Scanner(System.in);

    public Product(){}

    public Product(String name, String id, int sl, double dg){
        this.productName = name;
        this.productID = id;
        this.soLuong = sl;
        this.donGia = dg;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductID() {
        return productID;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public double getDonGia() {
        return donGia;
    }

    public void nhap(){
        System.out.println("Nhap ID san pham:");
        setProductID(sc.nextLine());
        System.out.println("Nhap ten san pham:");
        setProductName(sc.nextLine());
        System.out.println("Nhap don gia san pham:");
        double gia = Double.parseDouble(sc.nextLine());
        while (gia<0) {
            System.out.println("Gia tien san pham khong duoc nho hon 0!");
            gia = Double.parseDouble(sc.nextLine());
        }
        setDonGia(gia);
        System.out.println("Nhap so luong:");
        int sl = Integer.parseInt(sc.nextLine());
        while (sl<0) {
            System.out.println("so luong khong duoc nho hon 0!");
            sl = Integer.parseInt(sc.nextLine());
        }
        setSoLuong(sl);
    }

    @Override
    public String toString(){
        return String.format("|%-5s | %-17s | %-12s | %-9s|%n", productID, productName, donGia, soLuong);
    }
}