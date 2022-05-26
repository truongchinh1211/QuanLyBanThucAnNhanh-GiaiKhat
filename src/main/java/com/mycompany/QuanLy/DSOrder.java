
package com.mycompany.QuanLy;
import java.util.Scanner;

public class DSOrder {
    private int n;
    private static Order Orderlist[];
    
    Scanner sc = new Scanner(System.in);
    
    public DSOrder(){}
    public void setN(int n){
        this.n=n;
    }
    public static void setOrderlist(Order orderlist[]){
        Orderlist = orderlist;
    }
    public int getN(){
        return n;
    }
    public static Order[] getOrderlist(){
        return Orderlist;
    }
    
    public void makeOrderList(){
        Product p[] = DSProduct.getProductList();
        int countAmountProductLeft[] = new int[p.length];
        for (int i = 0; i < p.length; i++) {
            countAmountProductLeft[i] = p[i].getSoLuong();
        }
        System.out.println("Nhap so luong don order:");
        int sl = Integer.parseInt(sc.nextLine());
        while (sl <= 0) {
            System.out.println("So luong ban nhap dang <=0 ! Moi nhap lai:");
            sl = Integer.parseInt(sc.nextLine());
        }
        Orderlist = new Order[sl];
        for(int i=0; i<sl; i++){
            System.out.println("== Nhap order thu "+(i+1)+" ==");
            Orderlist[i] = new Order();
            Orderlist[i].makeOrder(countAmountProductLeft);
        }
        
    }
}