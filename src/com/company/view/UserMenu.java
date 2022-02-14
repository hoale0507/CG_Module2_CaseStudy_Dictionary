package com.company.view;

import com.company.controller.DictionaryForUser;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMenu {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[32m";
    Scanner input = new Scanner(System.in);
    DictionaryForUser dictionaryForUser = new DictionaryForUser();

    public void run() {
        try {
            dictionaryForUser.readFile("dictionary.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int choice = -1;
        do{
            System.out.println(ANSI_BLUE +  "---Menu---\n" + ANSI_RESET +
                    "1.\tHiển thị danh sách từ theo Alphabet\n" +
                    "2.\tTra từ\n" +
                    "3.\tTra cụm từ hoặc câu\n" +
                    "0.\tĐăng xuất\n" +
                    ANSI_BLUE + "Nhập lựa chọn của bạn\n" + ANSI_RESET);
            try {
                choice = input.nextInt();
            } catch (InputMismatchException inputMismatchException){
                System.err.println("Lưu ý chỉ nhập chữ số từ 0 đến 3!");
            }
            input.nextLine();
            switch (choice){
                case 1: {
                    System.out.println(ANSI_BLUE +  "Danh sách từ Tiếng Anh xếp theo Alphabet: " + ANSI_RESET);
                    dictionaryForUser.printWordList();
                    break;
                }

                case 2: {
                    System.out.println(ANSI_BLUE +  "Vui lòng nhập từ cần tra:" + ANSI_RESET);
                    String word = (input.nextLine()).toLowerCase();
                    if(dictionaryForUser.isExitedWord(word)){
                        System.out.println(dictionaryForUser.searchWord(word));
                    } else {
                        System.err.println("Không tim thấy từ " + "\'" + word + "\'!");
                    }
                    break;
                }

                case 3: {
                    System.out.println(ANSI_BLUE +  "Nhập vào cụm từ hoặc câu cần dịch nghĩa: " + ANSI_RESET);
                    String phrase = (input.nextLine()).toLowerCase();
                    System.out.println(ANSI_PURPLE +  dictionaryForUser.searchPhrase(phrase) + ANSI_RESET);
                    break;
                }
            }
        } while(choice != 0);
    }
}
