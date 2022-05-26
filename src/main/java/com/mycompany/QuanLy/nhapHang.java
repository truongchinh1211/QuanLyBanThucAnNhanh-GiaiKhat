package com.mycompany.QuanLy;

import java.util.Scanner;
import java.util.Arrays;

public class nhapHang {
    private String productName;
    private int soLuongNhap;
    private double donGia;
    private static double tiTrongGia=0.6;

    Scanner sc = new Scanner(System.in);

    public nhapHang(){}

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getProductName() {
        return productName;
    }
    public int getSoLuongNhap() {
        return soLuongNhap;
    }
    public double getDonGia() {
        return donGia;
    }

    public void nhap1sp(){
        System.out.println("Moi nhap ten sp:");
        setProductName(sc.nextLine());
        Product productList[] = DSProduct.getProductList();
        boolean flag=false;
        for (int i = 0; i < productList.length; i++) {
            if (productName.equalsIgnoreCase(productList[i].getProductName())){
                flag=true;
                System.out.println("Nhap so luong:");
                setSoLuongNhap(Integer.parseInt(sc.nextLine()));
                setDonGia((productList[i].getDonGia())*tiTrongGia);
                int newsl = productList[i].getSoLuong() + soLuongNhap;
                productList[i].setSoLuong(newsl);
            }
            else continue;
        }
        if (flag==false) {
            System.out.println("San pham ban can nhap khong nam trong danh muc san pham cua cua hang!");
            System.out.println("Tien hanh them moi vao danh muc san pham");
            int n = productList.length;
            Product newProductList[] = Arrays.copyOf(productList, n+1);
            newProductList[n] = new Product();
            newProductList[n].nhap();
            setDonGia((newProductList[n].getDonGia())*tiTrongGia);
            setSoLuongNhap(newProductList[n].getSoLuong());
            DSProduct.setProductList(newProductList);
        }
    }

}
