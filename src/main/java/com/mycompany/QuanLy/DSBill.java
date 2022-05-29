package com.mycompany.QuanLy;

import java.time.format.DateTimeFormatter;
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
    public void show()
    {
        
        if(BillList!=null){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(" \"---- Danh sach bill ----\"");
        System.out.println("-------------------------------------");
        System.out.printf("| %-3s | %-4s | %-20s |%n","stt","Id","Date" );
        System.out.println("-------------------------------------");
            for(int i=0;i<BillList.length;i++)
            System.out.println(String.format("| %-3s | %-4s | %-20s |",(i+1),BillList[i].getBillID(),dtf.format(BillList[i].getBilldate())));
            System.out.println("-------------------------------------");
            System.out.println("Nhap stt bill muon xem: ");
            int n = Integer.parseInt(sc.nextLine());
            n-=1;
            BillList[n].printBill();
    }
        else System.out.println("Khong co bill nao trong danh sach");
}
}