package com.mycompany.QuanLy;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Bill {
    private String CustomerName;
    private LocalDateTime Billdate;
    private String BillID;
    private String DSSPBill[];
    private int SoluongSpBill[];
    private double giaSpBill[];
    private double TongTienBill;
    private static double Thue = 0.1;

    Scanner sc = new Scanner(System.in);

    public Bill(){}

    public void setBillID(String billID) {
        BillID = billID;
    }
    public void setBilldate(LocalDateTime billdate) {
        Billdate = billdate;
    }
    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
    public void setDSSPBill(String[] dSSPBill) {
        DSSPBill = dSSPBill;
    }
    public void setSoluongSpBill(int[] soluongSpBill) {
        SoluongSpBill = soluongSpBill;
    }
    public void setGiaSpBill(double[] giaSpBill) {
        this.giaSpBill = giaSpBill;
    }
    public void setTongTienBill(double tongTienBill) {
        TongTienBill = tongTienBill;
    }
    public String getBillID() {
        return BillID;
    }
    public LocalDateTime getBilldate() {
        return Billdate;
    }
    public String getCustomerName() {
        return CustomerName;
    }
    public double getTongTienBill() {
        return TongTienBill;
    }

    public void makeBill(){
        setBilldate(LocalDateTime.now());
        System.out.println("Nhap id cua bill:");
        setBillID(sc.nextLine());
        System.out.println("Nhap id cua don Order:");
        // tim trong order list //
        Order Orderlist[] = DSOrder.getOrderlist();
        boolean flag1 = false;
        
        do{
            String orID = sc.nextLine();
            for(int i=0; i<Orderlist.length; i++){
                if(Orderlist[i].getOrderID().equalsIgnoreCase(orID)){
                    setCustomerName(Orderlist[i].getCustomerName());
                    setDSSPBill(Orderlist[i].getOrderNameproductList());
                    setSoluongSpBill(Orderlist[i].getSoluongOrder());
                    flag1 = true;
                    break;
                }
            }
            if(flag1==false) System.out.println("Khong tim thay Id order! Moi nhap lai:");
        } while(flag1==false);
        
        giaSpBill = new double[DSSPBill.length]; 
        Product dsProduct[] = DSProduct.getProductList();
        double tongtien = 0;
        for (int i = 0; i < DSSPBill.length; i++) {
            boolean flag = false;
            for (int j = 0; j < dsProduct.length; j++) {
                if (DSSPBill[i].equalsIgnoreCase(dsProduct[j].getProductName()) && SoluongSpBill[i]<=dsProduct[j].getSoLuong()) {
                    flag = true;
                    giaSpBill[i] = dsProduct[j].getDonGia();
                    tongtien += giaSpBill[i]*SoluongSpBill[i];
                    dsProduct[j].setSoLuong(dsProduct[j].getSoLuong() - SoluongSpBill[i]);
                }
            }
            if (flag == false) {
                System.out.println("Xin loi, san pham '"+ DSSPBill[i] +"' da het hang!");
                SoluongSpBill[i] = 0;
            }
        }
        TongTienBill = tongtien;
    }

    public void printBill(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("\n\t\t  =====================================================");
        System.out.printf("\t\t| %-40s |%n" ,"\t\tHOA DON");
        System.out.printf("\t\t| %-40s |%n", "\t\tNgay nhap: " + dtf.format(Billdate));
        System.out.printf("\t\t| %-40s |%n", "\t\tId Hoa don: "+BillID);
        System.out.printf("\t\t| %-40s |%n", "\t\tTen KH: "+CustomerName);
        System.out.printf("\t\t| %-52s |%n","");
        System.out.printf("\t\t| %-40s |%n", "\t\tDanh sach mua hang");
        System.out.printf("\t\t| %-52s |%n","");
        System.out.printf("\t\t|   %-18s\t%-12s\t%-9s      |%n", "Ten san pham","Don gia(VND)","Soluong");
        for (int i = 0; i < DSSPBill.length; i++) {
            if (SoluongSpBill[i] != 0 && DSSPBill[i]!=null) {
                System.out.printf("\t\t|   %-18s\t%-12s\t%-9s      |%n", DSSPBill[i], giaSpBill[i], SoluongSpBill[i]);
            }
        }
        System.out.printf("\t\t| %-47s |%n", "\t-------------------------------------");
        System.out.printf("\t\t| %-52s |%n", "Tong tien: " + String.format("%.2f VND", TongTienBill));
        System.out.printf("\t\t| %-52s |%n", "So tien can thanh toan (+10% thue): " + String.format("%.2f VND", (TongTienBill+TongTienBill*Thue)));
        System.out.print("\t\t ======================================================\n\n");
    }

}