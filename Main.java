import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n====================");
            System.out.println("ONLINE EXAM SYSTEM");
            System.out.println("====================");

            System.out.println("1. Login");
            System.out.println("2. Exit");

            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    int userId = Login.login();

                    if (userId != -1) {

                        while (true) {

                            System.out.println(
                                    "\n===== MAIN MENU =====");

                            System.out.println(
                                    "1. Update Profile");

                            System.out.println(
                                    "2. Start Exam");

                            System.out.println(
                                    "3. Logout");

                            System.out.print(
                                    "Choice: ");

                            int ch = sc.nextInt();
                            sc.nextLine();

                            switch (ch) {

                                case 1:
                                    Profile.updateProfile(userId);
                                    break;

                                case 2:
                                    Exam.startExam(userId);
                                    break;

                                case 3:
                                    System.out.println(
                                            "Logged Out Successfully");
                                    break;

                                default:
                                    System.out.println(
                                            "Invalid Choice");
                            }

                            if (ch == 3)
                                break;
                        }
                    }

                    break;

                case 2:

                    System.out.println(
                            "Thank You");

                    System.exit(0);

                default:

                    System.out.println(
                            "Invalid Choice");
            }
        }
    }
}
