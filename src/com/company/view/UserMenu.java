package com.company.view;

import com.company.controller.DictionaryForUser;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
            userMenu();
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

                case 4: {
                    overview();
                    break;
                }
            }
        } while(choice != 0);
    }

    private void overview() {
        ArrayList<String> overviewList = dictionaryForUser.overviewList();
        System.out.println("Cho danh sách 10 từ sau: ");
        System.out.println(ANSI_GREEN + overviewList + ANSI_RESET);
        System.out.println("Bạn hãy nhập nghĩa của các từ theo thứ tự:");
        int score = 0;
        ArrayList<String> wrongWords = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String word = input.nextLine().toLowerCase();
            String targetMeaning = dictionaryForUser.meaningFoundByWord(overviewList.get(i));
            if(word.equals(targetMeaning)){
                score++;
            } else {
                wrongWords.add(overviewList.get(i));
            }
        }
        System.out.println("Số điểm của bạn là: " + score + "/10");
        if(wrongWords.size() != 0) {
            System.out.println("Các từ bị sai: " + wrongWords);
        }
        if(score <= 5){
            System.out.println("Kết quả chưa tốt. Bạn hãy cố gắng ôn tập thêm nhé!");
        } else if (score > 6 && score <=8){
            System.out.println("Kết quả trung bình!");
        } else {
            System.out.println("Kết quả tốt!");
        }
    }

    private void userMenu() {
        System.out.println(ANSI_BLUE +  "---Menu---\n" + ANSI_RESET +
                "1.\tHiển thị danh sách từ theo Alphabet\n" +
                "2.\tTra từ\n" +
                "3.\tTra cụm từ hoặc câu\n" +
                "4.\tOverview\n" +
                "0.\tĐăng xuất\n" +
                ANSI_BLUE + "Nhập lựa chọn của bạn\n" + ANSI_RESET);
    }
}
