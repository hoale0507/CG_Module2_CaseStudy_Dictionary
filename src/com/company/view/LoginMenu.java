package com.company.view;

import com.company.controller.AccountManagement;
import com.company.controller.AccountRegexPattern;
import com.company.model.Account;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginMenu {
     Scanner input = new Scanner(System.in);
     AccountManagement accountManagement = new AccountManagement();
     AccountRegexPattern accountRegexPattern = new AccountRegexPattern();
     AdminMenu adminMenu = new AdminMenu();
     UserMenu userMenu = new UserMenu();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    public void run() {
        int choice = -1;
        do{
            menuLogin();
            try {
                choice = input.nextInt();
            } catch (InputMismatchException inputMismatchException){
                System.err.println("Lưu ý chỉ nhập số từ 0 đến 2!");
            }
            input.nextLine();

            switch (choice) {
                case 1: {
                    doLogin();
                    break;
                }
                case 2: {
                    doRegistration ();
                    break;
                }
                case 3: {
                    for (int i = 0; i < accountManagement.getAccounts().size(); i++) {
                        System.out.println(accountManagement.getAccounts().get(i));
                    }
                }
            }
        } while (choice != 0);
    }

    private void doLogin() {
        String id, password;
        do {
            System.out.println("Vui lòng nhập ID để đăng nhập:");
            id = input.nextLine();
            System.out.println("Vui lòng nhập password");
            password = input.nextLine();
            if(accountManagement.isSuccessfulLogin(id,password)){
                System.out.println("Đăng nhập thành công.");
                if(accountManagement.roleGetById(id).equals("admin")){
                    System.out.println("Xin chào admin "+ id + "!");
                    adminMenu.run();
                } else {
                    System.out.println("Xin chào user " + id + "!");
                    userMenu.run();
                }
            } else if(accountManagement.isExistedID(id) && !accountManagement.passwordGetById(id).equals(password)){
                System.err.println("Password bạn nhập bị sai!");
            } else {
                System.out.println(ANSI_RED + "ID bạn nhập chưa được đăng ký!!" + ANSI_RESET);
                System.out.println("Bạn có muốn đăng ký tài khoản mới không? \n" +
                        "Chọn " + ANSI_BLUE + "Có " + ANSI_RESET + "để đăng ký mới tài khoản hoặc chọn" +
                        ANSI_BLUE + " Không " + ANSI_RESET + "để quay lại phần đăng nhập");
                String choice1 = input.nextLine();
                if(choice1.equals("Có")){
                    doRegistration();
                    System.out.println("Mời bạn tiếp tục đăng nhập để sử dụng hệ thống! \n");
                } else {
                    System.out.println("Quay lại phần đăng nhập");
                }
            }
        } while (!accountManagement.isSuccessfulLogin(id, password));
    }

    private void doRegistration() {
        String id = "";
        do {
            System.out.println(ANSI_BLUE + "Vui lòng nhập ID:\n" + ANSI_RESET +
                    "Lưu ý: Một ID hợp lệ cần đạt các yêu cầu sau:\n" +
                    "•\tKhông chứa các ký tự đặc biệt, chỉ chứa chữ thường hoặc số\n" +
                    "•\tÍt nhất là 6 ký tự, tối đa là 12 ký tự\n");
            id = input.nextLine();
            if(!accountRegexPattern.validateID(id)) {
                System.err.println("Rất tiếc! ID không đúng định dạng!");
            } else if(accountManagement.isExistedID(id)){
                System.err.println("Rất tiếc! ID đã tồn tại!");
            } else {
                System.out.println("Id hợp lệ!");
            }
        } while (!accountRegexPattern.validateID(id) || accountManagement.isExistedID(id));

        String password = "";
        do {
            System.out.println(ANSI_BLUE + "Vui lòng nhập password:\n" + ANSI_RESET +
                    "Lưu ý: Một password hợp lệ cần đạt các yêu cầu sau:\n" +
                    "•\tCó thể chứa các ký tự tuỳ ý\n" +
                    "•\tÍt nhất là 6 ký tự, tối đa là 12 ký tự\n");
            password = input.nextLine();
        } while (!accountRegexPattern.validateID(password));

        String role = "";
        do{
            System.out.println(ANSI_BLUE + "Vui lòng nhập role của bạn ("  +
                    "Lưu ý: Chỉ nhập \'user\' hoặc \'admin\') \n" + ANSI_RESET);
            role = input.nextLine();
        } while (!role.equals("admin") && !role.equals("user"));
        accountManagement.register(id, password, role);
        System.out.println("Đăng ký tài khoản thành công!");
    }

    private static void menuLogin() {
        System.out.println("-------------------------------------------");
        System.out.println(ANSI_YELLOW + "Dictionary Ver 1.0 - Learn English Smartly " + ANSI_RESET);
        System.out.println(ANSI_BLUE +  "---Menu---" + ANSI_RESET);
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("0. Thoát");
        System.out.println(ANSI_BLUE + "Nhập lựa chọn của bạn" + ANSI_RESET);
    }
}
