package java_exceptions_final;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserInformationApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");

        String input = scanner.nextLine();
        String[] data = input.split(" ");

        if (data.length != 6) {
            System.out.println("Ошибка: введено неверное количество данных.");
            return;
        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String dateOfBirth = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        try {
            validateData(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);
            writeToFile(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);
            System.out.println("Данные успешно записаны в файл.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validateData(String lastName, String firstName, String middleName, String dateOfBirth,
            String phoneNumber, String gender) throws Exception {
        if (!gender.equals("f") && !gender.equals("m")) {
            throw new Exception("Неверный формат поля пол.");
        }

        String[] dateParts = dateOfBirth.split("\\.");
        if (dateParts.length != 3) {
            throw new Exception("Неверный формат даты рождения.");
        }

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || year > 2022) {
            throw new Exception("Неверная дата рождения.");
        }

        if (!phoneNumber.matches("\\d+")) {
            throw new Exception("Неверный формат номера телефона.");
        }
    }

    private static void writeToFile(String lastName, String firstName, String middleName, String dateOfBirth,
            String phoneNumber, String gender) throws IOException {
        String filename = lastName + ".txt";
        FileWriter writer = new FileWriter(filename, true);

        String dataLine = "<" + lastName + ">" + "<" + firstName + ">" + "<" + middleName + ">" + "<" + dateOfBirth
                + ">" +
                "<" + phoneNumber + ">" + "<" + gender + ">" + "\n";
        writer.write(dataLine);

        writer.close();
    }
}