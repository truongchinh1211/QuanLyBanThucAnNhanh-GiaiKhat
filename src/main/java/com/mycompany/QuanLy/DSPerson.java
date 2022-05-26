package com.mycompany.QuanLy;

import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DSPerson implements QuanLyDS{
    private  Person listPerson[];
    private int n;
    private static double vonTraLuong = 0;

    Scanner sc = new Scanner(System.in);

    public DSPerson(){}
    public void setListPerson(Person[] listPerson) {
        this.listPerson = listPerson;
    }
    public void setN(int n) {
        this.n = n;
    }
    public Person[] getListPerson() {
        return listPerson;
    }
    public  int getN() {
        return n;
    }
    public static double getVonTraLuong() {
        return vonTraLuong;
    }

    public int capNhatSoPerson(){
        int count = 0;
        try {
            BufferedReader rd = new BufferedReader(new FileReader("dsnhanvien.txt"));
            while ((rd.readLine())!=null) {
                count+=1;
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void docFileDsPerson(){
        n = capNhatSoPerson();
        listPerson = new Person[n];
        try {
            BufferedReader r = new BufferedReader(new FileReader("dsnhanvien.txt"));
            String line;
            for (int i = 0; i < listPerson.length; i++) {
                line = r.readLine();
                String split[] = line.split(", ");
                String chucvu = split[4];
                switch (chucvu) {
                    case "phucvu":
                        listPerson[i] = new PhucVu();
                        ((PhucVu) listPerson[i]).setCaPhucVu(split[7]);
                        break;
                    case "ketoan":
                        listPerson[i] = new KeToan();
                        ((KeToan) listPerson[i]).setNamKinhNghiem(Integer.parseInt(split[7]));
                        break;
                    case "quanly":
                        listPerson[i] = new QuanLy();
                        ((QuanLy) listPerson[i]).setBoPhanQl(split[7]);
                        break;
                    default:
                        i-=1; break;
                    }
                listPerson[i].setIDPerson(split[0]);
                listPerson[i].setHoTen(split[1]);
                listPerson[i].setNamSinh(Integer.parseInt(split[2]));
                listPerson[i].setGioiTinh(split[3]);
                listPerson[i].setChucVu(split[4]);
                listPerson[i].setQueQuan(split[5]);
                listPerson[i].setSdt(split[6]);
                vonTraLuong = vonTraLuong + listPerson[i].tinhLuong();
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ghiFileDSPerson(){
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter("dsnhanvien.txt"));
            for (int i = 0; i < listPerson.length; i++) {
                w.write(listPerson[i].getIDPerson() + ", ");
                w.write(listPerson[i].getHoTen() + ", ");
                w.write(Integer.toString(listPerson[i].getNamSinh()) + ", ");
                w.write(listPerson[i].getGioiTinh() + ", ");
                w.write(listPerson[i].getChucVu() + ", ");
                w.write(listPerson[i].getQueQuan() + ", ");
                w.write(listPerson[i].getSdt() + ", ");
                if (listPerson[i] instanceof PhucVu) w.write(((PhucVu) listPerson[i]).getCaPhucVu() + ", ");
                else if (listPerson[i] instanceof QuanLy) w.write(((QuanLy) listPerson[i]).getBoPhanQl());
                else w.write(Integer.toString(((KeToan) listPerson[i]).getNamKinhNghiem()));
                if (i != (listPerson.length-1)) {
                    w.newLine();
                }
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xuatDS(){
        System.out.println("\n\t\t\tDanh sach cac nhan vien:\n");
        System.out.println("---Danh sach cac nhan vien phuc vu---");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("|%-5s | %-20s | %-8s | %-6s | %-11s | %-10s | %-6s |%n",
        "ID", "Hoten","Nam sinh","Gender","Que quan","SDT","Ca lam");
        System.out.println("---------------------------------------------------------------------------------------");
        for (int i = 0; i < listPerson.length; i++) {
            if (listPerson[i] instanceof PhucVu) System.out.print(listPerson[i].toString());
        }
        System.out.println("---------------------------------------------------------------------------------------\n\n");
        System.out.println("---Danh sach cac nhan vien ke toan---");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("|%-5s | %-20s | %-8s | %-6s | %-11s | %-10s | %-16s|%n", 
        "ID", "Hoten","Nam sinh","Gender","Que quan","SDT", "Nam kinh nghiem");
        System.out.println("------------------------------------------------------------------------------------------------");
        for (int i = 0; i < listPerson.length; i++) {
            if (listPerson[i] instanceof KeToan) System.out.print(listPerson[i].toString());
        }
        System.out.println("------------------------------------------------------------------------------------------------\n\n");
        System.out.println("---Danh sach cac nhan vien quan ly---");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("|%-5s | %-20s | %-8s | %-6s | %-11s | %-10s | %-13s|%n", 
        "ID", "Hoten","Nam sinh","Gender","Que quan","SDT", "Bo phan q.ly");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (int i = 0; i < listPerson.length; i++) {
            if (listPerson[i] instanceof QuanLy) System.out.print(listPerson[i].toString());
        }
        System.out.println("---------------------------------------------------------------------------------------------\n\n");
    }
    @Override
    public void themDS(){
        System.out.println("Nhap so luong nham vien muon them:");
        int sl = Integer.parseInt(sc.nextLine());
        while (sl <= 0) {
            System.out.println("So luong them khong the <=0 !, nhap lai:");
            sl = Integer.parseInt(sc.nextLine());
        }
        n = listPerson.length;
        Person newListPerson[] = Arrays.copyOf(listPerson, listPerson.length+sl);
        for (int i = 0; i < sl; i++) {
            System.out.println("Chon chuc vu nhan vien can them: 1)Phuc vu\t2)Quan ly\t3)Ke toan");
            int choice = Integer.parseInt(sc.nextLine());
            while (choice<1 || choice>3) {
                System.out.println("Lua chon khong hop le, chon lai:");
                choice = Integer.parseInt(sc.nextLine());
            }
            switch (choice) {
                case 1:
                    newListPerson[n] = new PhucVu();
                    newListPerson[n].nhap(); break;
                case 2:
                    newListPerson[n] = new QuanLy();
                    newListPerson[n].nhap(); break;
                case 3:
                    newListPerson[n] = new KeToan();
                    newListPerson[n].nhap(); break;
            }
            n += 1;
        }
        setListPerson(newListPerson);
        ghiFileDSPerson();
    }
    @Override
    public void suaDS(){
        System.out.println("Nhap ID cua nhan vien muon sua:");
        String EditID = sc.nextLine();
        boolean flag = false;
        for (int i = 0; i < listPerson.length; i++) {
            if (listPerson[i].getIDPerson().equalsIgnoreCase(EditID)){
                flag = true;
                System.out.println("Chon chuc vu nhan vien: 1)Phuc vu\t2)Quan ly\t3)Ke toan");
                int choice = Integer.parseInt(sc.nextLine());
                while (choice<1 || choice>3) {
                    System.out.println("Lua chon khong hop le, chon lai:");
                    choice = Integer.parseInt(sc.nextLine());
                }
                switch (choice) {
                    case 1:
                        listPerson[i] = new PhucVu();
                        listPerson[i].nhap(); break;
                    case 2:
                        listPerson[i] = new QuanLy();
                        listPerson[i].nhap(); break;
                    case 3:
                        listPerson[i] = new KeToan();
                        listPerson[i].nhap(); break;
                }
            }
        }
        if (flag == false) System.out.println("Khong tim thay ID da nhap!\n");
        ghiFileDSPerson();
    }
    @Override
    public void xoaDS() {
        System.out.println("Nhap so nhan vien muon xoa:");
        int sl = Integer.parseInt(sc.nextLine());
        while (sl <= 0) {
            System.out.println("So luong xoa khong the <=0 !, nhap lai:");
            sl = Integer.parseInt(sc.nextLine());
        }
        int c = 0, notfind = 0;
        while (c < sl) {
            System.out.println("Nhap ID nhan vien muon xoa:");
            String DelID = sc.nextLine();
            Boolean flag = false;
            for (int i = 0; i < listPerson.length; i++) {
                if (listPerson[i].getIDPerson().equalsIgnoreCase(DelID)){
                    flag = true;
                    for (int j = i; j < listPerson.length-1; j++) {
                        listPerson[j] = listPerson[j+1];
                    }
                }
            }
            if (flag == false){
                notfind+=1;
                System.out.println("Khong tim thay ID da nhap!\n");
            }
            c++;
        }
        Person newListPerson[] = Arrays.copyOf(listPerson, listPerson.length-(sl-notfind));
        n = newListPerson.length;
        setListPerson(newListPerson);
        ghiFileDSPerson();
    }
    @Override
    public void searchByID(){
        System.out.println("Nhap ID cua nhan vien tra cuu:");
        String IDSearch = sc.nextLine();
        boolean flag = false;
        for (int i = 0; i < listPerson.length; i++) {
            if (listPerson[i].getIDPerson().equalsIgnoreCase(IDSearch)) {
                flag = true;
                System.out.println("Da tim thay! Thong tin nhan vien:");
                System.out.println(listPerson[i].toString());
            }
        }
        if (flag == false) System.out.println("Khong tim thay nhan vien co ID da nhap!\n");
    }

}