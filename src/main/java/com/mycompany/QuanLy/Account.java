
package com.mycompany.QuanLy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import java.io.Serializable;
import java.util.Scanner;


public class Account implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String pass;

    public Account() {
    }

    public Account(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public void nhap()
    {   boolean check;    String name,pass; 
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten tai khoan: ");
        do{
        name=sc.nextLine();
        check=name.matches("\\w{4,15}");
        if(!check) System.out.println("Ten dang nhap phai tu 4 va toi da 15 ky tu\n Khong chua ky tu dac biet!!");
        }while(!check);
        setName(name);
        System.out.println("Nhap mat khau: ");
        do{
            pass=sc.nextLine();
            check=pass.matches("\\w{6,15}");
            if(!check) System.out.println("Mat khau phai tu 6 va toi da 15 ky tu\n Khong chua ky tu dac biet!!");
        }while(!check);
        setPass(pass);
    }
    @Override
    public String toString() {
        return String.format("| %-15s | %-12s |",name,pass );
    }
    
}
