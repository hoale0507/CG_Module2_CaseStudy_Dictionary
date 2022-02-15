package com.company.view;

import com.company.controller.DictionaryForAdmin;
import com.company.model.Explanation;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
     Scanner input = new Scanner(System.in);
     DictionaryForAdmin dictionaryForAdmin = new DictionaryForAdmin();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public void run()  {
        try {
            dictionaryForAdmin.readFile("dictionary.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int choice = -1;
        do{
            adminMenu();
            try {
                choice = input.nextInt();
            } catch (InputMismatchException inputMismatchException){
                System.err.println("Lưu ý chỉ nhập chữ số từ 0 đến 5!");
            }
            input.nextLine();
            switch (choice) {
                case 1: {
                    if(dictionaryForAdmin.numberOfWord() == 0){
                        System.out.println(ANSI_YELLOW +  "Danh sách từ đang trống" + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_BLUE +  "Danh sách từ tiếng Anh xếp theo Alphabet: " + ANSI_RESET);
                        dictionaryForAdmin.printWordList();}
                    break;
                }

                case 2:
                {
                    System.out.println("Nhập từ cần thêm:");
                    String word = (input.nextLine()).toLowerCase();
                    if(dictionaryForAdmin.isExitedWord(word)){
                        System.out.println("Từ " + "\'"+word+"\'"+ " đã tồn tại!");
                    } else {
                        Explanation explanation = getExplanation();
                        dictionaryForAdmin.addWord(word, explanation);
                        System.out.println("Thêm từ thành công!");
                    }
                    break;
                }

                case 3: {
                    System.out.println("Nhập từ cần xoá:");
                    String word = (input.nextLine()).toLowerCase();
                    if(dictionaryForAdmin.isExitedWord(word)){
                        dictionaryForAdmin.deleteWord(word);
                        System.out.println("Đã xoá từ " + word + "khỏi danh sách!");
                    } else {
                        System.err.println("Không tìm thấy từ cần xoá!");
                    }
                    break;
                }

                case 4: {
                    System.out.println("Nhập từ cần tra:");
                    String word = (input.nextLine()).toLowerCase();
                    if(dictionaryForAdmin.isExitedWord(word)){
                        System.out.println(dictionaryForAdmin.searchWord(word));
                    } else {
                        System.err.println("Không tìm thấy từ " + "\'" + word + "\'!");
                    }
                    break;
                }

                case 5: {
                    System.out.println("Nhập từ cần sửa: ");
                    String word = (input.nextLine()).toLowerCase();
                    if(dictionaryForAdmin.isExitedWord(word)){
                        int choiceUpdate = -1;
                        do {
                            updateMenu();

                            try {
                                choiceUpdate = input.nextInt();
                            } catch (InputMismatchException inputMismatchException){
                                System.err.println("Lưu ý chỉ nhập chữ số từ 0 đến 4!");
                            }
                            input.nextLine();

                            switch (choiceUpdate){
                                case 1: {
                                    System.out.println("Nhập nghĩa mới của từ: ");
                                    String meaning = input.nextLine();
                                    dictionaryForAdmin.updateMeaning(word, meaning);
                                    System.out.println("Sửa nghĩa thành công!");
                                    break;
                                }

                                case 2: {
                                    System.out.println("Nhập phát âm mới của từ: ");
                                    String pronunciation = input.nextLine();
                                    dictionaryForAdmin.updatePronunciation(word, pronunciation);
                                    System.out.println("Sửa phát âm thành công!");
                                    break;
                                }

                                case 3: {
                                    System.out.println("Nhập từ loại mới của từ: ");
                                    String wordType = input.nextLine();
                                    dictionaryForAdmin.updateWordType(word, wordType);
                                    System.out.println("Sửa từ loại thành công!");
                                    break;
                                }

                                case 4: {
                                    System.out.println("Nhập ví dụ mới của từ: ");
                                    String example = input.nextLine();
                                    dictionaryForAdmin.updateExample(word, example);
                                    System.out.println("Sửa ví dụ thành công");
                                    break;
                                }
                            }
                        } while (choiceUpdate != 0);
                    } else {
                        System.err.println("Từ bạn nhập không tồn tại!");
                    }
                }
            }

            try {
                dictionaryForAdmin.writeToFile("dictionary.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (choice != 0);
    }

    private Explanation getExplanation() {
        System.out.println("Nhập ý nghĩa của từ:");
        String meaning = (input.nextLine()).toLowerCase();
        System.out.println("Nhập phiên âm của từ: ");
        String pronunciation = (input.nextLine()).toLowerCase();
        System.out.println("Nhập từ loại:");
        String wordType = (input.nextLine()).toLowerCase();
        System.out.println("Nhập ví dụ về cách sử dụng của từ:");
        String exampleInUse = (input.nextLine()).toLowerCase();
        Explanation explanation = new Explanation(meaning, pronunciation, wordType, exampleInUse);
        return explanation;
    }

    private void updateMenu() {
        System.out.println(ANSI_BLUE + "---Menu---\n" + ANSI_RESET +
                "1.\tSửa nghĩa \n" +
                "2.\tSửa phát âm \n" +
                "3.\tSửa từ loại\n" +
                "4.\tSửa ví dụ\n" +
                "0.\tQuay lại\n" +
                ANSI_BLUE +  "Nhập lựa chọn của bạn.\n" + ANSI_RESET);
    }

    private void adminMenu() {
        System.out.println(ANSI_BLUE +  "---Menu---\n" + ANSI_RESET +
                "1.\tHiển thị danh sách từ\n" +
                "2.\tThêm từ mới\n" +
                "3.\tXoá từ\n" +
                "4.\tTra từ\n" +
                "5.\tSửa từ\n" +
                "0.\tĐăng xuất");
        System.out.println(ANSI_BLUE + "Vui lòng nhập lựa chọn của bạn" + ANSI_RESET);
    }
}
