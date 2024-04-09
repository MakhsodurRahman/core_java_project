package Library;

import java.util.Scanner;

public class Main {
    static Scanner s;
    static Database database;
    public static void main(String[] args) {

        database = new Database();
        while (true){
            System.out.println("Welcome to Library Management System ");
            System.out.println("1. Login");
            System.out.println("2. New User");
            System.out.println("3. Exit");
            s = new Scanner(System.in);
            int n = s.nextInt();

            switch (n){
                case 1:login(); break;
                case 2: newUser(); break;
                case 3: break;
                default:
                    System.out.println("error");
            }
            if(n == 3)
                break;
        }
    }



    private static void login() {
        System.out.println("Enter your Phone number");
        String phoneNumber = s.next();
        System.out.println("Enter your email");
        String email = s.next();
        int n = database.login(email,phoneNumber);
        if(n != -1){
            User user = database.getUser(n);
            user.menu();
        }else {
            System.out.println("User not found in database");;
        }
    }

    private static void newUser() {
        System.out.println("Enter your name");
        String name = s.next();
        System.out.println("Enter your Phone number");
        String phoneNumber = s.next();
        System.out.println("Enter your email");
        String email = s.next();

        System.out.println("1. Admin\n2. Normal User");
        int n2 = s.nextInt();
        User user;
        if(n2 ==1){
             user = new Admin(name,email,phoneNumber);
        }else {
             user = new NormalUser(name,email,phoneNumber);
        }
        database.addUser(user);
        user.menu();
    }
}