package com.mycompany.QuanLy;

import java.util.Scanner;

public class KeToan extends Person{
    private int namKinhNghiem;
    
    Scanner sc = new Scanner(System.in);

    public KeToan(){}

    public void setNamKinhNghiem(int namKinhNghiem) {
        this.namKinhNghiem = namKinhNghiem;
    }
    public int getNamKinhNghiem() {
        return namKinhNghiem;
    }

    @Override
    public void nhap(){
        super.nhap();
        setChucVu("ketoan");
        System.out.println("Nhap nam kinh nghiem");
        int expYear = Integer.parseInt(sc.nextLine());
        while (expYear < 0) {
            System.out.println("So nam kinh nghiem khong duoc nho hon 0!, nhap lai:");
            expYear = Integer.parseInt(sc.nextLine());
        }
        setNamKinhNghiem(expYear);
    }

    @Override
    public double tinhLuong() {
        return 350000 + (namKinhNghiem*40000);
    }

    @Override
    public String toString(){
        return super.toString() + String.format("| %-16s|%n", namKinhNghiem);
    }
}
