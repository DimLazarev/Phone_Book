package phone_book_ex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    private static HashMap<String, String> phoneBookList = new HashMap<String, String>();
    private static void addPhoneNumber(String phone, String name) {
        phoneBookList.put(phone, name);
    }
    private static void delPhoneNumber(String phone) {
        phoneBookList.remove(phone);
    }
    private static void savePhoneNumber() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("phoneBookList.txt")));
        for (Map.Entry<String, String> k : phoneBookList.entrySet()) {
            writer.write(k.getKey() + " " + k.getValue() + System.lineSeparator());
        }
        writer.close();
    }
    public static void loadPhoneNumber() throws IOException {
        File file = new File("phoneBookList.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(new File("phoneBookList.txt")));
            String work;
            while ((work = reader.readLine()) != null) {
                String[] dat = work.split(" ");
                phoneBookList.put(dat[0], dat[1]);
            }
            reader.close();
        }
    }
    public static String FindSurname(String number) {
        String result = phoneBookList.get(number);
        if (result == null) return "абонент с таким номером не найдей";
        return result;
    }
    public static String[] FindNumberPhone(String surname) {
        List<String> result = new ArrayList<String>();
        for (Map.Entry entry : phoneBookList.entrySet()) {
            if (surname.equalsIgnoreCase((String) entry.getValue())) {
                result.add((String) entry.getKey());
            }
        }
        if (result.size() == 0) result.add("абонент с такой фамилией не найден");
        return result.toArray(new String[0]);
    }
    public static void main(String[] args) throws IOException {

        String work;
        loadPhoneNumber();
        
        System.out.println("Телефонный справочник:");
        System.out.println("выбор действия: (add)добавить данные, (del)удалить данные, (num) найти номера по фамилии,(all) вывести весь список справочника, " + "(save)сохранить, (exit)выход");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        work = bf.readLine();
        while (!work.equals("exit")) {
            if (work.equals("all")) {
                System.out.println("Телефонный справочник: ");
                for (Map.Entry<String, String> k : phoneBookList.entrySet()) {
                    System.out.println(k.getValue() + ": " + k.getKey());
                }
            }
            if (work.equals("add")) {
                System.out.println("Введите фамилию:");
                String name = bf.readLine();
                System.out.println("Введите телефон:");
                String phone = bf.readLine();
                addPhoneNumber(phone, name);
            } else {
                if (work.equals("del")) {
                    System.out.println("Введите телефон:");
                    String phone = bf.readLine();
                    delPhoneNumber(phone);
                } else {
                    if (work.equals("num")) {
                        System.out.println("Введите фамилию:");
                        String surname = bf.readLine();
                        String[] numbers = FindNumberPhone(surname);
                        for (String number : numbers) {
                            System.out.println(number);
                        }
                    } else {
                        if (work.equals("save")) {
                        savePhoneNumber();
                        }
                    }
                }
            }
            System.out.println("выбор действия: (add)добавить данные, (del)удалить данные, (num) найти номера по фамилии, (all) вывести весь список справочника, (save)сохранить, (exit)выход");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            work = bf.readLine();
        }
    }
}