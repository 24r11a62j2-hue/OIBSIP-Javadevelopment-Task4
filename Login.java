import java.sql.*;
import java.util.Scanner;

public class Login {

    public static int login() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Login Successful");

                return rs.getInt("userid");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Invalid Login");
        return -1;
    }
}
