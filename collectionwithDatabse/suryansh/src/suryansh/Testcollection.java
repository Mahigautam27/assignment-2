package suryansh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Testcollection {
	
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        // Prompt user for database details
        System.out.print("Enter your MySQL username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your MySQL password: ");
        String password = scanner.nextLine();
      
        // Connect to the database
        Connection con = null;
        try {
          
        	// Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
          
            // Establish the connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/suryansh", username, password);
            System.out.println("Connection created");
           
            // Prompt user for the number of students
            System.out.print("Enter the number of students: ");
            int studentCount = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Statement stmt = con.createStatement();
           
            // Loop to insert student details
            for (int i = 1; i <= studentCount; i++) {
                System.out.println("\nEnter details for student " + i + ":");
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter SYS_ID (System ID): ");
                long sysId = scanner.nextLong();
                System.out.print("Enter Mobile Number: ");
                long mobile = scanner.nextLong();
                scanner.nextLine(); // Consume newline

                // Create SQL query
                String query = "INSERT INTO K (NAME, SYS_ID, MOB) VALUES ('" + name + "', " + sysId + ", " + mobile + ")";

                // Execute the query
                stmt.execute(query);
                System.out.println("Record inserted successfully for student " + i);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found. Please ensure the connector is in the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
            scanner.close();
        }
    }
}