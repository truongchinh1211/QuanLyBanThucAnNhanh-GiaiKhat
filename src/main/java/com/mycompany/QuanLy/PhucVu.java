package com.mycompany.QuanLy;

import java.util.Scanner;

public class PhucVu extends Person {
    private String caPhucVu;

    Scanner sc = new Scanner(System.in);

    public PhucVu(){}

    public void setCaPhucVu(String caPhucVu) {
        this.caPhucVu = caPhucVu;
    }
    public String getCaPhucVu() {
        return caPhucVu;
    }

    @Override
    public void nhap(){
        super.nhap();
        setChucVu("phucvu");
        System.out.println("Nhap ca phuc vu:");
        String ca = sc.nextLine();
        while (ca.equalsIgnoreCase("sang")==false&&ca.equalsIgnoreCase("toi")==false) {
            System.out.println("Ca lam la 'sang' hoac 'toi', nhap lai:" );
            ca = sc.nextLine();
        }
        setCaPhucVu(ca);
    }

    @Override
    public double tinhLuong() {
        return 200000;
    }
    
    @Override
    public String toString(){
        return super.toString() + String.format("| %-6s |%n", caPhucVu);
    }
}
