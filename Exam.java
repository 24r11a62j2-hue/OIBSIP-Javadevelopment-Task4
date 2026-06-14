import java.sql.*;
import java.util.Scanner;

public class Exam {

    public static void startExam(int userId) {

        Scanner sc = new Scanner(System.in);

        int score = 0;

        // 60-second exam timer
        long startTime = System.currentTimeMillis();
        long examDuration = 60000;

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM questions");

            while (rs.next()) {

                long currentTime =
                        System.currentTimeMillis();

                // Auto submit if time is over
                if (currentTime - startTime >= examDuration) {

                    System.out.println(
                            "\nTime Up! Exam Submitted Automatically");

                    break;
                }

                long remainingTime =
                        (examDuration -
                                (currentTime - startTime)) / 1000;

                System.out.println(
                        "\nTime Remaining: "
                                + remainingTime
                                + " seconds");

                System.out.println(
                        "\n--------------------------------");

                System.out.println(
                        rs.getInt("qid") + ". "
                                + rs.getString("question"));

                System.out.println(
                        "A. " + rs.getString("optionA"));

                System.out.println(
                        "B. " + rs.getString("optionB"));

                System.out.println(
                        "C. " + rs.getString("optionC"));

                System.out.println(
                        "D. " + rs.getString("optionD"));

                System.out.print("Answer: ");

                String answer =
                        sc.nextLine().toUpperCase();

                if (answer.equals(
                        rs.getString("answer"))) {

                    score++;
                }
            }

            System.out.println(
                    "\n====================");

            System.out.println(
                    "Exam Completed");

            System.out.println(
                    "Score = " + score);

            System.out.println(
                    "====================");

            PreparedStatement ps =
                    con.prepareStatement(
                            "INSERT INTO results(userid,score) VALUES(?,?)");

            ps.setInt(1, userId);
            ps.setInt(2, score);

            ps.executeUpdate();

            System.out.println(
                    "Result Saved Successfully");

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}