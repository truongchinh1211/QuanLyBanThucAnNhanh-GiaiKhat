
package com.mycompany.QuanLy;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Bum
 */
public class AccountList {
    private int n;
    Account[] acc;
    
    public AccountList()
    {
        ReadObject();
    }


    public int getN() {
        return n;
    }

    public Account[] getAcc() {
        return acc;
    }
    
    public void createAcc()
    {
        System.out.println("Tien hanh tao tai khoan......");
        System.out.println("_____Tao tai khoan______");
        n++;
        Account temp = new Account();
        temp.nhap();
        int flag = 1;
        for(int i=0;i<acc.length;i++)
            if(acc[i]!=null)
                if(temp.getName().equals(acc[i].getName())){
                    System.out.println("Ten tai khoan da ton tai, tao tk that bai!!!");
                    flag = 0;
                    break;}
        if(flag ==1){
            acc=Arrays.copyOf(acc,acc.length+1);
            acc[n-1] = new Account();
            acc[n-1] = temp;
            WriteObject();
        }
    }
    public void changePass()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap ten dang nhap can chinh sua: ");
        String update = sc.nextLine();
        int flag=0;
        for (Account acc1 : acc) {
            if (update.equals(acc1.getName())) {
                flag=1;
                System.out.println("Nhap mat khau moi: ");
                acc1.setPass(sc.nextLine());
                WriteObject();
                break;
            }
        }
        if(flag==0) System.out.println("khong tim thay ten dang nhap!!");
    }
    public void deleteAcc()
    {
        Scanner sc =new Scanner(System.in);
        int flag=0;
        System.out.println("Nhap ten dang nhap cua tai khoan can xoa: ");
        String delete = sc.nextLine();
        for(int i=0;i<acc.length;i++)       
            if(delete.equals(acc[i].getName())){
                flag=1;
                for(int j=i;i<acc.length-1;i++)
                acc[i]=acc[i+1];
                acc=Arrays.copyOf(acc,acc.length-1);
                WriteObject();
                break;
            }
        if(flag==0) System.out.println("Khong tim thay ten dang nhap!!");
    }
    public void forgotPass()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap ten dang nhap ban quen mat khau: ");
        String find = sc.nextLine();
        int flag=0;
        for (Account acc1 : acc) {
            if (find.equals(acc1.getName())) {
                flag=1;
                System.out.println("Mat khau cua ban la:  "+ acc1.getPass());
                break;
            }
        }
        if(flag==0) System.out.println("khong tim thay ten dang nhap!!");
        
    }
    public void initAccounts()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap n:");
        n = Integer.parseInt(sc.nextLine());
        acc = new Account[10];
        for(int i=0;i<n;i++){
          acc[i] = new Account();
          acc[i].nhap();}
        WriteObject();
    }
    public void WriteObject()
    {        
        try{
        FileOutputStream fos = new FileOutputStream("log.txt");
        ObjectOutputStream oos = new ObjectOutputStream((fos));
        oos.writeInt(n);
        oos.writeObject(acc);
        fos.close();
        oos.close();
        System.out.println("done");
        } catch (IOException ex){ System.out.println("Error");;}
    }
    public void ReadObject()
    {
        Account acc[]=null; int n = 0;
        try{
        FileInputStream fis = new FileInputStream("log.txt");
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
        n = (int)ois.readInt();
        acc = new Account[10];
        acc = (Account[])ois.readObject();
        ois.close();
        fis.close();
    } catch (IOException ex){ System.out.println("error");}
      catch (ClassNotFoundException e) {
            System.out.println("Class not found");
}        
        this.acc= acc;this.n= n;
    }
    public void DisplayAcc ()
    {
        System.out.println(" \"---- Danh sach tai khoan ----\"");
        System.out.println("----------------------------------");
        System.out.printf("| %-15s | %-12s |%n","Ten dang nhap","Mat khau" );
        System.out.println("----------------------------------");
        for (Account Acc1: acc)
            if(Acc1!=null) System.out.println(Acc1.toString());
        System.out.println("----------------------------------");
    }
    public int login()
    {
        Scanner sc = new Scanner(System.in);
        Account login = new Account();
        int flag=0;
        int function=0;
        System.out.println("De dung chuc nang nay ban can phai dang nhap!");

        do {
            if(choice()==2) break;
            login.nhap();
            if(login.getName().equals("admin") && login.getPass().equals("123456"))
                flag=2;
            else
            for (int i = 0; i<acc.length; i++) {
                if(acc[i]!=null)
                    if (login.getName().equals(acc[i].getName())) {
                        if (login.getPass().equals(acc[i].getPass())) {
                            flag = 1;
                            break;
                        }
                    }
            }
            if (flag == 0) {
                System.out.println("Ten dang nhap hoac mat khau khong dung!!!");
                System.out.println("Moi nhap lai!");
            }
        } while (flag == 0);
        if(flag == 1)return 1; if(flag==2)return 2;return 0;
    }
    public int choice()
    {
        Scanner sc = new Scanner(System.in);
        int function=0;
        do{
            try{
            System.out.println("1)Dang nhap\n2)Quay lai");
            function=Integer.parseInt(sc.nextLine());
            if(function>2) System.out.println("Moi nhap dung chuc nang!!");
            } catch(NumberFormatException E) {System.out.println("Gia tri can nhap la so!!");}
        }while(function!=2 && function>2);
        return function;
    }
    

  }

