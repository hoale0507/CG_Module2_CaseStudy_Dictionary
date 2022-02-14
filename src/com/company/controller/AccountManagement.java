package com.company.controller;

import com.company.model.Account;

import java.io.*;
import java.util.ArrayList;

public class AccountManagement implements WriteToFile, ReadFile{
    ArrayList<Account> accounts = new ArrayList<>();


    public AccountManagement() {
        File file = new File("account.txt");
        if(file.exists()){
            try {
                readFile("account.txt");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public AccountManagement(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void register(String id, String password, String role){
        Account account = new Account(id, password, role);
        accounts.add(account);
        try {
            writeToFile("account.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isExistedID(String id){
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean isSuccessfulLogin(String id, String password){
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getId().equals(id) && accounts.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public String roleGetById(String id){
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getId().equals(id)){
                return accounts.get(i).getRole();
            }
        }
        return null;
    }

    public String passwordGetById(String id){
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getId().equals(id)){
                return accounts.get(i).getPassword();
            }
        }
        return null;
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        accounts = (ArrayList<Account>)ois.readObject();
        is.close();
        ois.close();
    }

    @Override
    public void writeToFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(accounts);
        os.close();
        oos.close();
    }
}
