package core;

import core.ArrayDataError;
import core.PhoneNumberError;
import core.RewriteDB;

import java.io.IOException;
 import java.time.format.DateTimeParseException;
 import java.util.Scanner;

 public class App {
    public App() {
        String pathToFile = "C:\\Users\\User\\Desktop\\ExceptionsCertification\\src\\";
        RewriteDB users = new RewriteDB();

        System.err.println("Формат ввода данных: Фамилия Имя Отчество 10.01.1983 79995556532 m\n (вводится через пробел, " +
                "где 10.01.1983 - дата рождения, 79222232123 - номер телефона, m или f - пол)\n для завершения введите q");
        while (true) {

            Scanner in = new Scanner(System.in);
            System.out.print("Введите данные пользователя: ");
            String userInformation = in.nextLine();

            if (userInformation.equalsIgnoreCase("q")) {
                System.out.println("Работа с программой завершена");
                break;
            }

            try {
                users.parseString(userInformation);
            } catch (ArrayDataError e) {
                System.out.println(e.getMessage());
                continue;
            }
            try {
                users.convertString();
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты рождения. Пример: 10.10.2000");
                continue;
            } catch (PhoneNumberError e) {
                System.out.println("Неверный формат телефонного номера");
                continue;
            }

            users.setFile(pathToFile, users.getUser()[0]);
            try {
                users.createDbFile(users.getFile());
                users.addUser(users.getFile());
                System.out.print("Данные пользователя записаны в файл: " + users.getFile() + "\n");

            } catch (IOException e) {
                System.out.println("Файл с данными не найден");
                break;
            } catch (Exception e) {
                System.out.println("Проверьте правильность пути -  " + pathToFile + "и, после внесения изменений, " +
                        "перезапустите программу");
                break;
            }

        }
    }
 }



