import java.sql.*;
import java.util.Scanner;

public class Profile {

    public static void updateProfile(int userId) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("New Username: ");
            String username = sc.nextLine();

            System.out.print("New Password: ");
            String password = sc.nextLine();

            Connection con = DBConnection.getConnection();

            String sql =
                    "UPDATE users SET username=?, password=? WHERE userid=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, userId);

            ps.executeUpdate();

            System.out.println("Profile Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
