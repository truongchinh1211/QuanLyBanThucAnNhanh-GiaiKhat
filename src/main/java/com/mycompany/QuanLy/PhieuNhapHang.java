
package com.mycompany.QuanLy;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;

public class PhieuNhapHang {
    private nhapHang phieuNhap[];
    private LocalDateTime date ;
    private double tongTienNhapHang;
    private static int n;
    private static double vonMuaHang = 0;

    Scanner sc = new Scanner(System.in);

    public PhieuNhapHang(){}

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public void setTongTienNhapHang(double tongTienNhapHang) {
        this.tongTienNhapHang = tongTienNhapHang;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public double getTongTienNhapHang() {
        return tongTienNhapHang;
    }
    public static double getVonMuaHang() {
        return vonMuaHang;
    }
    public double tinhTongTienNhapHang(){
        double tongTien = 0;
        for (int i = 0; i < phieuNhap.length; i++) {
            tongTien += phieuNhap[i].getDonGia()*phieuNhap[i].getSoLuongNhap();
        }
        return tongTien;
    }

    public void makePhieunhap(){
        setDate(LocalDateTime.now());
        System.out.println("Moi nhap so loai hang can nhap:");
        n = Integer.parseInt(sc.nextLine());
        phieuNhap = new nhapHang[n];
        for (int i = 0; i < phieuNhap.length; i++) {
            System.out.println("--- Nhap hang san pham thu "+(i+1)+" ---");
            phieuNhap[i] = new nhapHang();
            phieuNhap[i].nhap1sp();
        }
        setTongTienNhapHang(tinhTongTienNhapHang());
        vonMuaHang = vonMuaHang + tongTienNhapHang;
    }

    public void inPhieuNhapHang(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("\n\t\t  =====================================================");
        System.out.printf("\t\t| %-40s |%n" ,"\t\tPHIEU NHAP HANG");
        System.out.printf("\t\t| %-40s |%n", "\t\tNgay nhap: " + dtf.format(date));
        System.out.printf("\t\t| %-52s |%n","");
        System.out.printf("\t\t| %-40s |%n", "\t\tDanh sach san pham duoc nhap");
        System.out.printf("\t\t| %-52s |%n","");
        System.out.printf("\t\t| %-18s\t%-9s\t%-12s   |%n", "Ten san pham","So luong","Don gia(VND)");
        for (int i = 0; i < phieuNhap.length; i++) {
            System.out.printf("\t\t| %-18s\t%-9s\t%-12s   |%n", phieuNhap[i].getProductName(),
            phieuNhap[i].getSoLuongNhap(), phieuNhap[i].getDonGia());
        }
        System.out.printf("\t\t| %-47s |%n", "\t-------------------------------------");
        tinhTongTienNhapHang();
        System.out.printf("\t\t| %-50s   |%n", "Tong gia tri phieu nhap hang: " + String.format("%.2f VND", tongTienNhapHang));
        System.out.print("\t\t ======================================================\n\n");
    }
}
