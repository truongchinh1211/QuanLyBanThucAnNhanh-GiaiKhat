package com.mycompany.QuanLy;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DSProduct implements QuanLyDS{
    private int N;
    private static Product ProductList[];

    Scanner sc = new Scanner(System.in);

    public DSProduct(){}

    public void setN(int n) {
        N = n;
    }
    public static void setProductList(Product[] proList) {
        ProductList = proList;
    }
    public int getN() {
        return N;
    }
    public static Product[] getProductList() {
        return ProductList;
    }

    public int capnhatSLProduct(){
        int count = 0;
        try {
            BufferedReader r = new BufferedReader(new FileReader("dsproduct.txt"));
            while (r.readLine() != null) {
                count+=1;
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void docFileDSProduct(){
        N = capnhatSLProduct();
        ProductList = new Product[N];
        try {
            BufferedReader r = new BufferedReader(new FileReader("dsproduct.txt"));
            String line;
            for (int i = 0; i < ProductList.length; i++) {
                line = r.readLine();
                String split[] = line.split(", ");
                ProductList[i] = new Product();
                ProductList[i].setProductID(split[0]);
                ProductList[i].setProductName(split[1]);
                ProductList[i].setDonGia(Double.parseDouble(split[2]));
                ProductList[i].setSoLuong(Integer.parseInt(split[3]));
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ghiFileDSProduct(){
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter("dsproduct.txt"));
            for (int i = 0; i < ProductList.length; i++) {
                w.write(ProductList[i].getProductID() + ", ");
                w.write(ProductList[i].getProductName() + ", ");
                w.write(Double.toString(ProductList[i].getDonGia()) + ", ");
                w.write(Integer.toString(ProductList[i].getSoLuong()));
                if (i != (ProductList.length-1)) w.newLine();
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void xuatDS(){
        System.out.println("\t---- Danh sach cac san pham ----");
        System.out.println("------------------------------------------------------");
        System.out.printf("|%-5s | %-17s | %-12s | %-9s|%n", "ID", "Ten san pham", "Don gia(VND)", "So luong");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < ProductList.length; i++) {
            System.out.printf(ProductList[i].toString());
        }
        System.out.println("------------------------------------------------------\n");
    }
    @Override
    public void themDS(){
        System.out.println("Nhap so luong san pham muon them:");
        int sl = Integer.parseInt(sc.nextLine());
        while (sl <= 0) {
            System.out.println("So luong them khong duoc <=0 !, nhap lai:");
            sl = Integer.parseInt(sc.nextLine());
        }
        N = ProductList.length;
        Product newProList[] = Arrays.copyOf(ProductList, ProductList.length+sl);
        for (int i = 0; i < sl; i++) {
            newProList[N] = new Product();
            newProList[N].nhap();
            N += 1;
        }
        setProductList(newProList);
        ghiFileDSProduct();
    }
    @Override
    public void suaDS(){
        System.out.println("Nhap ID cua san pham muon sua:");
        String IdEdit = sc.nextLine();
        boolean flag = false;
        for (int i = 0; i < ProductList.length; i++) {
            if (ProductList[i].getProductID().equalsIgnoreCase(IdEdit)) {
                flag = true;
                ProductList[i].nhap();
            }
        }
        if(flag == false) System.out.println("Khong tim thay ID da nhap!");
        ghiFileDSProduct();
    }
    @Override
    public void xoaDS(){
        System.out.println("Nhap so luong san pham muon xoa:");
        int sl = Integer.parseInt(sc.nextLine());
        while (sl <=0) {
            System.out.println("So luong xoa khong duoc <=0 !, nhap lai:");
            sl = Integer.parseInt(sc.nextLine());
        }
        int c = 0, notfind = 0;
        while (c < sl) {
            System.out.println("Nhap ID san pham muon xoa:");
            String IDXoa = sc.nextLine();
            boolean flag = false;
            for (int i = 0; i < ProductList.length; i++) {
                if (ProductList[i].getProductID().equalsIgnoreCase(IDXoa)) {
                    flag = true;
                    for (int j = i; j < ProductList.length-1; j++) {
                        ProductList[j] = ProductList[j+1];
                    }
                }
            }
            if(flag == false){
                notfind +=1;
                System.out.println("Khong tim thay ID da nhap!");
            }
            c++;
        }
        Product newProList[] = Arrays.copyOf(ProductList, ProductList.length-(sl-notfind));
        N = newProList.length;
        setProductList(newProList);
        ghiFileDSProduct();
    }
    @Override
    public void searchByID(){
        System.out.println("Nhap ID san pham can tim:");
        String SearchID = sc.nextLine();
        boolean flag = false;
        for (int i = 0; i < ProductList.length; i++) {
            if (ProductList[i].getProductID().equalsIgnoreCase(SearchID)) {
                flag = true;
                System.out.println("Da tim thay, thong tin san pham:");
                System.out.println(ProductList[i].toString());
            }

            if(flag == false) System.out.println("Khong tim thay ID da nhap!");
        }
    }
}