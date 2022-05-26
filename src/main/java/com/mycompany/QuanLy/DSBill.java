package com.mycompany.QuanLy;

import java.util.Scanner;

public class DSBill {

    private Bill[] BillList;
    private static double doanhThu = 0;

    Scanner sc  = new Scanner(System.in);

    public DSBill(){}
    
    public static double getDoanhThu() {
        return doanhThu;
    }

    public void makeBillList(){
        System.out.println("Nhap so luong hoa don muon tao:");
        int sl = Integer.parseInt(sc.nextLine());
        while (sl <= 0) {
            System.out.println("So luong ban nhap dang <=0 ! Moi nhap lai:");
            sl = Integer.parseInt(sc.nextLine());
        }
        BillList = new Bill[sl];
        for (int i = 0; i < BillList.length; i++) {
            System.out.println("== Nhap hoa don thu "+(i+1)+" ==");
            BillList[i] = new Bill();
            BillList[i].makeBill();
            BillList[i].printBill();
            doanhThu += BillList[i].getTongTienBill();
        }
    }

}