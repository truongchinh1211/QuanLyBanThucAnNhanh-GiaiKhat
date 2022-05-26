package com.mycompany.QuanLy;

import java.util.Scanner;

public class QuanLy extends Person{
    private String boPhanQl;

    Scanner sc = new Scanner(System.in);

    public QuanLy(){}

    public void setBoPhanQl(String boPhanQl) {
        this.boPhanQl = boPhanQl;
    }
    public String getBoPhanQl() {
        return boPhanQl;
    }

    @Override
    public void nhap(){
        super.nhap();
        setChucVu("quanly");
        System.out.println("Chon bo phan quan ly 1)Nhan su\t2)Kho hang");
        int choice = Integer.parseInt(sc.nextLine());
        while (choice !=1 && choice !=2) {
            System.out.println("Lua chon khong hop le, chon lai:");
            choice = Integer.parseInt(sc.nextLine());
        }
        switch (choice) {
            case 1:
                setBoPhanQl("Nhan su"); break;
            case 2:
                setBoPhanQl("Kho hang"); break;
        }
    }

    @Override
    public double tinhLuong() {
        if (boPhanQl.equalsIgnoreCase("Nhan su")) return 500000;
        else if (boPhanQl.equalsIgnoreCase("Kho hang")) return 450000;
        else return 0;
    }

    @Override
    public String toString(){
        return super.toString() + String.format("| %-13s|%n", boPhanQl);
    }
}
