package org.itstep;


import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class App1
{
    public static void main( String[] args )
    {
        final String fileName = "taskDB.dat";

        List<User> userList = null;

        File file = new File(fileName);
        if (file.exists()) {
            userList = readObject(fileName);
        } else {
            userList = new ArrayList<>();
        }

        Menu menu = new Menu("Главное меню");

        Scanner scanner = new Scanner(System.in);

        menu.add(new Menu("Регистрация нового пользователя", () -> {
            //не работает с userList
        }));
        System.out.println( "Hello World!" );
        System.out.println(LocalDate.parse("2020-12-02").toEpochDay());
        System.out.println(LocalDate.parse("2020-12-01").toEpochDay());
    }

    private static String reqInfo(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void addUser(List<User> users) {
        User user = new User(reqInfo("Введите имя:"), reqInfo("Введите пароль:"));
        if (users.contains(user)) {
            System.out.println("Пользователь с таким именем существует. ");
        } else {
            users.add(user);
        }
    }

    private static void saveObject(List<User> list, String filename) {
        try (ObjectOutput output = new ObjectOutputStream(new FileOutputStream(filename))) {
            output.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<User> readObject(String filename) {
        List<User> list = null;
        try (ObjectInput input = new ObjectInputStream(new FileInputStream(filename))) {
            list = (List<User>) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
