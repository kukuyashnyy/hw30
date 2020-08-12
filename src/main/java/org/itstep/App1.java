package org.itstep;


import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class App1
{
    public static void main( String[] args ) throws FileNotFoundException {
        final String fileName = "taskDB.dat";

        List<User> userList = null;

        User user = null;

        File file = new File(fileName);
        if (file.exists()) {
            userList = readObject(fileName);
        } else {
            userList = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);

        boolean exit = true;

        do {
            printMenu();
            switch (scanner.nextInt()) {
                case 1:
                    //Регистрация нового пользователя
                    addUser(userList);
                    break;
                case 2:
                    //Авторизация пользователя
                    user = auth(userList);
                    break;
                case 3:
                    //Отображение списка всех не выполненых задач (отсортированные по важности и дате)
                    userList.get(userList.indexOf(user)).getTaskList().stream()
                            .filter(p -> !p.isComplete())
                            .sorted((p1, p2) -> p2.getPriority() - p1.getPriority())
                            .forEach(System.out::println);
                    break;
                case 4:
                    //Отображение списка всех выполненных задач (отсортированные по дате в обратном порядке)
                    userList.get(userList.indexOf(user)).getTaskList().stream()
                            .filter(Task::isComplete)
                            .sorted((p1, p2) -> p1.getPriority() - p2.getPriority())
                            .forEach(System.out::println);
                    break;
                case 5:
                    //Добавление новой задачи
                    if (user == null) {
                        System.out.println("Пользователь не авторизирован!");
                    } else {
                        userList.get(userList.indexOf(user)).add(new Task(reqInfo("Введите название задачи:"),
                                Integer.parseInt(reqInfo("Введите приоритет от 1 до 5:"))));

                    }
                    break;
                case 6:
                    //Отметка про выполнение задания
                    if (user == null) {
                        System.out.println("Пользователь не авторизирован!");
                    } else {
                        Task task = userList.get(userList.indexOf(user))
                                .del(new Task(reqInfo("Введите название задачи:"), Integer.parseInt(reqInfo("Введите приоритет:"))));
                        task.setComplete();
                        userList.get(userList.indexOf(user))
                                .add(task);
                    }
                    break;
                case 7:
                    //Удаление задачи
                    if (user == null) {
                        System.out.println("Пользователь не авторизирован!");
                    } else {
                        userList.get(userList.indexOf(user))
                                .del(new Task(reqInfo("Введите название задачи:"), Integer.parseInt(reqInfo("Введите приоритет:"))));
                    }
                    break;
                case 8:
                    //Архивирование выполненных задач (после архивирования исчезают из списка)
                    break;
                case 9:
                    //Exit
                    exit = false;
                    break;
            }
        } while (exit);

        saveObject(userList, fileName);

    }



    private static User auth(List<User> users) {
        User user = new User(reqInfo("Введите имя:"), reqInfo("Введите пароль:"));

        if (users.contains(user)) {
            System.out.println("Авторизированно.");
            return user;
        } else {
            System.out.println("Пользователь не найден.");
            return null;
        }


    }

    private static void addUser(List<User> users) {
        User user = new User(reqInfo("Введите имя:"), reqInfo("Введите пароль:"));
        if (users.contains(user)) {
            System.out.println("Пользователь с таким именем существует.");
        } else {
            users.add(user);
            System.out.println("Пользователь добавлен.");
        }
    }

    private static String reqInfo(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
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

    private static void printMenu(){
        System.out.println("1. Регистрация нового пользователя\n" +
                "2. Авторизация пользователя\n" +
                "3. Отображение списка всех не выполненых задач (отсортированные по важности и дате)\n" +
                "4. Отображение списка всех выполненных задач (отсортированные по дате в обратном порядке)\n" +
                "5. Добавление новой задачи\n" +
                "6. Отметка про выполнение задания\n" +
                "7. Удаление задачи\n" +
                "8. Архивирование выполненных задач (после архивирования исчезают из списка)\n" +
                "9. Выход");
    }
}
