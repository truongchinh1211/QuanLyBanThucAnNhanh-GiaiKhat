
package com.mycompany.QuanLy;

import java.util.Scanner;

public class Console {
    Scanner sc = new Scanner(System.in);
    public void MainConsole()
    {
        DSProduct dssp = new DSProduct();
        dssp.docFileDSProduct();
        DSPerson dsP = new DSPerson();
        dsP.docFileDsPerson();
        AccountList acc = new AccountList();
        int function=0;
        do{
            
            System.out.println("==============Quan Ly cua hang==============");
            System.out.printf("%-22s%s\n%-22s%-22s\n%s","1.Quan ly san pham","2.Thong ke tai chinh","3.Quan ly nhan vien","4.Quan ly tai khoan","5.Thoat\n");
           try{    
               function = Integer.parseInt(sc.nextLine());
                switch(function){
                    case 1: SanPhamConsole(dssp);break;
                    case 2:
                        System.out.printf("Doanh thu tu ban hang la: %.2f VND%n", DSBill.getDoanhThu());
                        System.out.printf("Von chi cho nhap hang: %.2f VND%n", PhieuNhapHang.getVonMuaHang());
                        System.out.printf("Von chi de tra luong nhan vien: %.2f VND%n", DSPerson.getVonTraLuong());
                        System.out.printf("Loi nhuan hom nay cua cua hang: %.2f VND%n", DSBill.getDoanhThu()-(PhieuNhapHang.getVonMuaHang()+DSPerson.getVonTraLuong()));
                        break;
                    case 3: int n;
                        do  {
                            n=acc.login();
                            if(n==2)System.out.println("ban phai nhap tai khoan danh cho quan li!!!");
                        }while(n==2); 
                        if(n==1){System.out.println("Dang nhap thanh cong!!"); QuanLyPermission(dsP);} break;
                    case 4: do {
                            n=acc.login();
                            if(n==1)System.out.println("Ban phai nhap tai khoan danh cho admin!!!");
                        }while(n==1);
                        if(n==2){System.out.println("Dang nhap thanh cong!!!"); AdminPermission(acc);} break;
                    case 5: break;
                default: System.out.println("Vui long nhap dung chuc nang!!");
            }
           }catch(NumberFormatException E) {
               System.out.println("Gia tri can nhap la so!!");
           }
        }while(function!=5); System.out.println("Cam on ban da su dung chuong trinh ^^");
        
    }
    public void SanPhamConsole(DSProduct dssp)
    {
        int choice=0;
        do  {
            System.out.println("\t•~•San pham•~•");
            System.out.printf("%-18s%-18s\n%-18s%-18s\n%-18s%-18s\n%-18s%-18s\n","1.Hien thi sp","2.Them sp","3.Chinh sua sp","4.Xoa sp","5.Nhap hang","6.Order mon","7.Tinh bill","8.Quay lai");          
            try{
                choice =  Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: dssp.xuatDS(); break;
                    case 2: dssp.themDS(); break;
                    case 3: dssp.suaDS(); break;
                    case 4: dssp.xoaDS(); break;
                    case 5:
                        System.out.println("==Nhap hang==");
                        PhieuNhapHang p = new PhieuNhapHang();
                        p.makePhieunhap();
                        p.inPhieuNhapHang();
                        dssp.ghiFileDSProduct(); 
                        break;
                    case 6:
                        DSOrder o = new DSOrder();
                        System.out.println("==Order mon==");
                        o.makeOrderList();
                        break;
                    case 7:
                        DSBill b = new DSBill();
                        b.makeBillList();
                        dssp.ghiFileDSProduct();
                        break;                         
                    case 8: break;
                    default: System.out.println("Vui long nhap dung chuc nang!!");
            }
            } catch (NumberFormatException E){
                System.out.println("Gia tri can nhap la so!!!");
            }
        } while (choice != 8);
        }
    public void QuanLyPermission(DSPerson dsP)
    {
        
        int function = 0;
        do{
            System.out.println("\t•~•quan ly nhan vien•~•");
            System.out.println(" 1.Hien thi danh sach nhan vien\n 2.them nhan vien\n 3.Chinh sua\n 4.Xoa\n 5.Quay lai");
            try{
            function=Integer.parseInt(sc.nextLine());
            switch(function)
            {
                case 1:dsP.xuatDS();break;
                case 2:dsP.themDS();break;
                case 3:dsP.suaDS();break;
                case 4:dsP.xoaDS();break;
                case 5:break;
                default: System.out.println("Vui long nhap dung chuc nang!!");break;
            }
            }catch(NumberFormatException E)
            {
                System.out.println("Gia tri can nhap la so!!");
            }
        }while(function!=5);
    }
    public void AdminPermission(AccountList acc)
    {   
        int function=0;
        do{
            System.out.println("\t•~•quan ly tai khoan•~•");
            System.out.printf("%-22s%-22s\n%-22s%-22s\n%-22s%-22s\n","1.Hien thi danh sach","2.them tai khoan","3.thay doi mat khau","4.xoa tai khoan","5.Quen mat khau","6.Quay lai");
            try{
                function = Integer.parseInt(sc.nextLine());
                switch(function){
                    case 1: acc.DisplayAcc(); break;
                    case 2: acc.createAcc(); break;
                    case 3: acc.changePass(); break;
                    case 4: acc.deleteAcc(); break;
                    case 5: acc.forgotPass();break;
                    case 6: break;
                    default: System.out.println("Vui long nhap dung chuc nang!!"); break;
                }
            }catch(NumberFormatException E)
                {
                System.out.println("Gia tri can nhap la so!!");
                }
        }while(function!=6);
    }
    public static void main(String[] args)
    {
        Console cs = new Console();
        cs.MainConsole();
    }
}
