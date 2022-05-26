package com.mycompany.QuanLy;

import java.util.Scanner;
import java.util.Calendar;

public abstract class Person {
    private String IDPerson;
    private String hoTen;
    private int namSinh;
    private String gioiTinh;
    private String chucVu;
    private String queQuan;
    private String sdt;

    Scanner sc = new Scanner(System.in);

    public Person(){}

    public void setIDPerson(String iDPerson) {
        IDPerson = iDPerson;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getIDPerson() {
        return IDPerson;
    }
    public String getHoTen() {
        return hoTen;
    }
    public int getNamSinh() {
        return namSinh;
    }
    public String getGioiTinh() {
        return gioiTinh;
    }
    public String getChucVu() {
        return chucVu;
    }
    public String getQueQuan() {
        return queQuan;
    }
    public String getSdt() {
        return sdt;
    }

    public abstract double tinhLuong();

    public void nhap(){
        System.out.println("Nhap ID nhan vien:");
        setIDPerson(sc.nextLine());
        System.out.println("Nhap ho ten:");
        setHoTen(sc.nextLine());
        System.out.println("Nhap nam sinh:");
        int birthYear = Integer.parseInt(sc.nextLine());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        while ((currentYear-birthYear)<18) {
            System.out.println("Tuoi nhan vien phai >= 18!");
            birthYear = Integer.parseInt(sc.nextLine());
        }
        setNamSinh(birthYear);
        System.out.println("Nhap gioi tinh:");
        String gender = sc.nextLine();
        while (gender.equalsIgnoreCase("nam")==false && gender.equalsIgnoreCase("nu")==false) {
            System.out.println("Gioi tinh phai la 'nam' hoac 'nu', nhap lai:");
            gender = sc.nextLine();
        }
        setGioiTinh(gender);
        // set chuc vu o cac class inherit tuong ung//
        System.out.println("Nhap que quan:");
        setQueQuan(sc.nextLine());
        System.out.println("Nhap SDT:");
        boolean check;String phoneNumber;
        do{
        phoneNumber = sc.nextLine();
        check = phoneNumber.matches("0\\d{9}");
        if(!check) System.out.println("Dinh dang sdt khong dung (10 chu so), nhap lai:");
        }while (!check);
        setSdt(phoneNumber);
    }

    @Override
    public String toString(){
        return String.format("|%-5s | %-20s | %-8s | %-6s | %-11s | %-10s ",
        IDPerson, hoTen, namSinh, gioiTinh, queQuan, sdt);
    };
}