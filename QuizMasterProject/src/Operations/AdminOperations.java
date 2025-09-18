//package Operations;
//
//public class AdminOperations {
//
//}

package Operations;

import java.sql.*;
import Util.DBConnection;
import java.util.Scanner;

public class AdminOperations {

    static Scanner sc = new Scanner(System.in);

    // === View All Scores (Tabular Form) ===
    public static void viewAllScores() {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT s.id, CONCAT(s.first_name,' ',s.last_name) AS name, sc.total_score, sc.grade " +
                           "FROM student s JOIN score sc ON s.id = sc.student_id " +
                           "ORDER BY sc.total_score ASC";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("**** All Student Scores (Sorted in Ascending Order) ****");
            System.out.println("+------------+-------------------------+-------+-------+");
            System.out.printf("| %-10s | %-23s | %-5s | %-5s |%n", "StudentID", "Name", "Score", "Grade");
            System.out.println("+------------+-------------------------+-------+-------+");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int score = rs.getInt("total_score");
                String grade = rs.getString("grade");
                System.out.printf("| %-10d | %-23s | %-5d | %-5s |%n", id, name, score, grade);
            }

            System.out.println("+------------+-------------------------+-------+-------+");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // === Fetch Score by Student ID ===
    public static void fetchScoreById() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM score WHERE student_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Score: " + rs.getInt("total_score") + " | Grade: " + rs.getString("grade"));
            } else {
                System.out.println("No record found!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // === View Top Scorer ===
    public static void viewTopScorer() {
        try (Connection con = DBConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT s.first_name, s.last_name, sc.total_score, sc.grade " +
                "FROM student s JOIN score sc ON s.id=sc.student_id " +
                "ORDER BY sc.total_score DESC LIMIT 1");

            if (rs.next()) {
                System.out.println("Top Scorer: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                                   " | Score: " + rs.getInt("total_score") + " | Grade: " + rs.getString("grade"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
