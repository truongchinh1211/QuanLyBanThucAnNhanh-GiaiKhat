package com.mycompany.QuanLy;

import java.util.Scanner;

public class Order {
    private String OrderID;
    private  String OrderNameproductList[];
    private int soluongOrder[];

    Scanner sc = new Scanner(System.in);

    public Order(){}

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }
    public void setOrderNameproductList(String[] orderList) {
        OrderNameproductList = orderList;
    }
    public void setSoluongOrder(int[] soluong) {
        soluongOrder = soluong;
    }
    public String getOrderID() {
        return OrderID;
    }
    public  String[] getOrderNameproductList() {
        return OrderNameproductList;
    }
    public  int[] getSoluongOrder() {
        return soluongOrder;
    }

    public void makeOrder(int countslproduct[]){
        // cap nhat soluong san pham con lai vao 1 ban nhap moi khi order 1 sp, sau khi thanh toan (tinh Bill) thi moi tru vao sl that.
        
        System.out.println("Nhap Id order:");
        setOrderID(sc.nextLine());
        System.out.println("Nhap so luong mon an:");
        int n = Integer.parseInt(sc.nextLine());
        while(n<=0){
            System.out.println("so luong mon order khong duoc <= 0!");
            n = Integer.parseInt(sc.nextLine());
        }
        OrderNameproductList = new String[n];
        soluongOrder = new int[n];
        Product pd[] = DSProduct.getProductList();
        
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap ten mon:");
            boolean flag = false;
            do{
                OrderNameproductList[i] = sc.nextLine();
                for (int j=0; j<pd.length; j++){
                    if(pd[j].getProductName().equalsIgnoreCase(OrderNameproductList[i])){
                        flag = true;
                        break;
                    }
                }
                if(flag==false) System.out.println("Khong ton tai ten mon an vua nhap! nhap lai:");
            } while(flag==false);
            System.out.println("Nhap so luong:");
            int sl =Integer.parseInt(sc.nextLine());
            while (sl<=0) {
                System.out.println("So luong khong duoc nho hon hoac bang 0!:");
                sl = Integer.parseInt(sc.nextLine());
            }
            for (int j = 0; j < pd.length; j++) {
                if (pd[j].getProductName().equalsIgnoreCase(OrderNameproductList[i]) && sl>countslproduct[j]) {
                    if (countslproduct[j]>0) {
                        do {
                            System.out.println("San pham khong con du so luong! (con lai "+countslproduct[j]+")");
                            System.out.println("Nhap lai so luong (>0 va nho hon so luong con lai):");
                            sl = Integer.parseInt(sc.nextLine());
                        } while (sl>countslproduct[j] || sl<=0);
                        soluongOrder[i] = sl;
                    }
                    else {
                        System.out.println("Xin loi! san pham hien tai da het hang.");
                        soluongOrder[i] = 0;
                    }
                    countslproduct[j] = countslproduct[j] - soluongOrder[i]; // 0-0=0 (sl ko dc am du chi la data test)
                    continue; 
                }
                if (pd[j].getProductName().equalsIgnoreCase(OrderNameproductList[i]) && sl<=countslproduct[j]) {
                    soluongOrder[i] = sl;
                    countslproduct[j] = countslproduct[j] - sl;
                    continue;
                }
            }
        } 
    }
    

}