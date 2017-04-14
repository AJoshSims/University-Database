package exercises;

import utilities.Printer;

import java.sql.*;

import java.util.Scanner;

public class Exercise311
{ 
    public static void main( String args[] ) 
    { 
        try 
        { 
        Class.forName(
            "org.postgresql.Driver").newInstance(); 
        } 
        catch (
        	LinkageError | ClassNotFoundException
        	| IllegalAccessException | InstantiationException 
        	| SecurityException e) 
        { 
            System.err.println(e.getMessage()); 
            System.exit(1); 
        }
        
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter db name");
        String databasename = userInput.next();
        
        System.out.println("Enter username");
        String username = userInput.next();

        System.out.println("Enter password");
        String password = userInput.next();

        String serverIp = "localhost"; 

        Connection conn = null; 	
        Statement stmt = null; 
        ResultSet rset = null;
        ResultSetMetaData mdata = null;
        String cs = "jdbc:postgresql://" + 
        serverIp + "/" + databasename + "?user=" + username 
        + "&password=" + password; 
        try 
        { 
            conn = DriverManager.getConnection(cs); 

            stmt = conn.createStatement(); 

            rset = stmt.executeQuery(
                "SELECT DISTINCT name " +
                "FROM student NATURAL JOIN takes NATURAL JOIN course " +
                "WHERE dept_name = 'Comp. Sci.'"); 
            mdata = rset.getMetaData();

            Printer.printTable(rset, mdata);

            rset.close();  

            rset = stmt.executeQuery(
                "SELECT ID, name " +
                "FROM student " + 
                "EXCEPT " +
                "SELECT ID, name " +
                "FROM student NATURAL JOIN takes " +
                "WHERE year < '2009'");
            mdata = rset.getMetaData();

            Printer.printTable(rset, mdata);

            rset.close();

            rset = stmt.executeQuery(
                "SELECT dept_name, MAX(salary) " +
                "FROM instructor " +
                "GROUP BY dept_name");
            mdata = rset.getMetaData();

            Printer.printTable(rset, mdata);

            rset.close();

            rset = stmt.executeQuery(
                "WITH max_salaries_per_dept AS " +
                "( " +
                " SELECT MAX(salary) AS max_salary" +
                " FROM instructor" +
                " GROUP BY dept_name" +
                ") " +
                "SELECT MIN(max_salary) AS min_salary_of_max_salaries " +
                "FROM max_salaries_per_dept");

            Printer.printTable(rset, mdata);

            rset.close();

            stmt.close();
        } 
        catch (SQLException e) 
        { 
            System.err.println(e.getMessage()); 
            System.exit(1); 
        }
    } // end of main  
}
