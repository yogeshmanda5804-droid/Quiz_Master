package Main_Menu;

import java.sql.*;
import java.util.*;
import java.util.regex.*;
import Util.DBConnection;
import Operations.AdminOperations;
//import Models.Question;

public class QuizApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("=== Welcome to Quiz Application ===");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: registerStudent(); break;
                case 2: loginStudent(); break;
                case 3: adminMenu(); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid Choice!");
            }
            if (!askContinue()) break;
        }
    }
    // Student Registration
    static void registerStudent() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter First Name: "); String fn = sc.nextLine();
            System.out.print("Enter Last Name: "); String ln = sc.nextLine();
            System.out.print("Enter Username: "); String un = sc.nextLine();

            // Check unique username
            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM student WHERE username=?");
            ps1.setString(1, un);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                System.out.println("Username already exists!");
                return;
            }

            System.out.print("Enter Password: "); String pw = sc.nextLine();
            System.out.print("Enter City: "); String city = sc.nextLine();

            // Email Validation
            System.out.print("Enter Email: "); String email = sc.nextLine();
            if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
                System.out.println("Invalid Email!");
                return;
            }

            // Mobile Validation
            System.out.print("Enter Mobile (10 digits): "); String mobile = sc.nextLine();
            if (!Pattern.matches("^[6-9][0-9]{9}$", mobile)) {
                System.out.println("Invalid Mobile Number!");
                return;
            }

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO student(first_name,last_name,username,password,city,email,mobile) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, fn);
            ps.setString(2, ln);
            ps.setString(3, un);
            ps.setString(4, pw);
            ps.setString(5, city);
            ps.setString(6, email);
            ps.setString(7, mobile);

            ps.executeUpdate();
            System.out.println("Registration Successful!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Student Login
    static void loginStudent() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Username: "); String un = sc.nextLine();
            System.out.print("Enter Password: "); String pw = sc.nextLine();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM student WHERE username=? AND password=?");
            ps.setString(1, un);
            ps.setString(2, pw);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful! Welcome " + rs.getString("first_name"));
                studentMenu(rs.getInt("id"));
            } else {
                System.out.println("Invalid Credentials!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
 // Student Menu
    static void studentMenu(int studentId) {
        while (true) {
            System.out.println("1. Attempt Quiz");
            System.out.println("2. View My Score");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1: attemptQuiz(studentId); break;
                case 2: viewScore(studentId); break;
                case 3: return;
                default: System.out.println("Invalid!");
            }
            if (!askContinue()) break;
        }
    }

    // Attempt Quiz
    static void attemptQuiz(int studentId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM question");
            ResultSet rs = ps.executeQuery();

            int score = 0;
            List<Question> questions = new ArrayList<>();

            while (rs.next()) {
                String[] options = {rs.getString("option1"), rs.getString("option2"), rs.getString("option3"), rs.getString("option4")};
                questions.add(new Question(rs.getInt("id"), rs.getString("question_text"), options, rs.getInt("correct_option")));
            }

            for (Question q : questions) {
                System.out.println(q.questionText);
                for (int i = 0; i < 4; i++) {
                    System.out.println((i + 1) + ". " + q.options[i]);
                }
                System.out.print("Your Answer (1-4): ");
                int ans = sc.nextInt(); sc.nextLine();
                if (ans == q.correctOption) score++;
            }

            String grade = (score >= 8) ? "A" : (score >= 5) ? "B" : "C";

            PreparedStatement ps2 = con.prepareStatement("INSERT INTO score(student_id,total_score,grade) VALUES(?,?,?) ON DUPLICATE KEY UPDATE total_score=?, grade=?");
            ps2.setInt(1, studentId);
            ps2.setInt(2, score);
            ps2.setString(3, grade);
            ps2.setInt(4, score);
            ps2.setString(5, grade);
            ps2.executeUpdate();

            System.out.println("Quiz Completed! Score: " + score + "/" + questions.size() + " Grade: " + grade);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // View Score
    static void viewScore(int studentId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM score WHERE student_id=?");
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Your Score: " + rs.getInt("total_score") + " Grade: " + rs.getString("grade"));
            } else {
                System.out.println("No score found!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // ✅ Student registration, login, attemptQuiz, viewScore methods
    // (हे तू आधी लिहिलेले बरोबर आहेत, फक्त तसेच ठेव)

    // Admin Menu
    static void adminMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View All Student Scores");
            System.out.println("2. Fetch Score by Student ID");
            System.out.println("3. View Top Scorer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: AdminOperations.viewAllScores(); break;
                case 2: AdminOperations.fetchScoreById(); break;
                case 3: AdminOperations.viewTopScorer(); break;
                case 4: return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    // Exit/Retry Option
    static boolean askContinue() {
        System.out.print("Do you want to continue? (Y/N): ");
        String ans = sc.nextLine();
        return ans.equalsIgnoreCase("Y");
    }
}
