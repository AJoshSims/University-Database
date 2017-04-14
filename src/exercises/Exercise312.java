package exercises;

import utilities.Printer;

import java.sql.*;

import java.util.Scanner;

public class Exercise312 
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
        PreparedStatement pstmt = null;	
        Statement qstmt = null;
        Statement ustmt = null; 
        ResultSet rset = null;
        ResultSetMetaData mdata = null;
        String cs = "jdbc:postgresql://" + 
        serverIp + "/" + databasename + "?user=" + username 
        + "&password=" + password; 
        try 
        { 
            conn = DriverManager.getConnection(cs); 
            qstmt = conn.createStatement(); 

            rset = qstmt.executeQuery(
                "SELECT * " +
                "FROM course");
            mdata = rset.getMetaData();

            System.out.println("Before");
            Printer.printTable(rset, mdata);

            rset.close();

            ustmt = conn.createStatement();
            ustmt.executeUpdate(
                "INSERT INTO course(course_id, title, credits) " +
                "VALUES('CS-001', 'Weekly Seminar', 1)");

            ustmt.close();

            System.out.println();
            System.out.println("After");

            rset = qstmt.executeQuery(
                "SELECT * " +
                "FROM course");
            mdata = rset.getMetaData();
            
            Printer.printTable(rset, mdata);

            rset.close();

            rset = qstmt.executeQuery(
                "SELECT * " +
                "FROM section");
            mdata = rset.getMetaData();

            System.out.println();
            System.out.println("Before");
            Printer.printTable(rset, mdata);

            rset.close();

            pstmt = conn.prepareStatement( 
                "INSERT INTO section(course_id, semester, year, sec_id) " +
                "VALUES(?, ?, ?, ?)"); 
            pstmt.setString(1, "CS-001"); 
            pstmt.setString(2, "Fall"); 
            pstmt.setInt(3, 2009); 
            pstmt.setString(4, "1"); 
            pstmt.execute();
             
            pstmt.close(); 

            System.out.println();
            System.out.println("After");

            rset = qstmt.executeQuery(
                "SELECT * " +
                "FROM section");
            mdata = rset.getMetaData();
            
            Printer.printTable(rset, mdata);

            rset.close();

            rset = qstmt.executeQuery(
                "SELECT * " +
                "FROM takes");
            mdata = rset.getMetaData();

            System.out.println();
            System.out.println("Before");
            Printer.printTable(rset, mdata);

            rset.close();

            ustmt = conn.createStatement();
            ustmt.executeUpdate(
                "INSERT INTO takes(ID, course_id, sec_id, semester, year) " +
                "SELECT ID, 'CS-001', '1', 'Fall', '2009' " +
                "FROM student " +
                "WHERE dept_name = 'Comp. Sci.'");

            ustmt.close();

            System.out.println();
            System.out.println("After");

            rset = qstmt.executeQuery(
                "SELECT * " +
                "FROM takes");
            mdata = rset.getMetaData();
            
            Printer.printTable(rset, mdata);

            rset.close();

             rset = qstmt.executeQuery(
                "SELECT * " +
                "FROM takes");
            mdata = rset.getMetaData();

            System.out.println();
            System.out.println("Before");
            Printer.printTable(rset, mdata);

            rset.close();

            ustmt = conn.createStatement();
            ustmt.executeUpdate(
                "DELETE FROM takes " +
                "USING student " +
                "WHERE" +
                " student.ID = takes.ID AND student.name = 'Chavez'" +
                " AND takes.course_id = 'CS-001' AND takes.sec_id = '1'" +
                " AND takes.semester = 'Fall' AND takes.year = '2009'");

            ustmt.close();

            System.out.println();
            System.out.println("After");

            rset = qstmt.executeQuery(
                "SELECT * " +
                "FROM takes");
            mdata = rset.getMetaData();
            
            Printer.printTable(rset, mdata);

            rset.close();

            qstmt.close();
        } 
        catch (SQLException e) 
        { 
            System.err.println(e.getMessage()); 
            System.exit(1); 
        }
    } // end of main 
}
