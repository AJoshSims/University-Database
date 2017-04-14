package exercises;

import java.sql.*;

public class PostgresqlJDBCTest 
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
        
        String serverIp = "localhost"; 
        String databasename = "jnsims2";
        // you need to change this (mh) 
        String username = "jnsims2";
        // you need to change this (mh) 
        String password = "peopaj99eoo0";
        // you need to change this (mh) 
        String tablename = "customers"; // Make sure this table exists
                                        // in your database
        Connection conn = null; 	
        Statement stmt = null; 
        ResultSet rset = null;
        String cs = "jdbc:postgresql://" + 
        serverIp + "/" + databasename + "?user=" + username 
        + "&password=" + password; 
        try 
        { 
            conn = DriverManager.getConnection(cs); 
            stmt = conn.createStatement( ); 
            rset = stmt.executeQuery(
                "select * from " + tablename ); 
            while( rset.next( ) ) 
            { 
                System.out.print( rset.getString( 1 ) + "\t"); 
                System.out.print( rset.getString( 2 ) + "\t"); 
                System.out.print( rset.getString( 3 ) + "\t"); 
                System.out.println( rset.getDouble( 4 ) ); 
            } 
            stmt.close(); 
            rset.close(); 
            PreparedStatement pstmt = 
                conn.prepareStatement( 
                "Insert into customers values (?, ?, ?, ?)"); 
            pstmt.setString(1, "c009"); 
            pstmt.setString(2, "United"); 
            pstmt.setString(3, "Sylva"); 
            pstmt.setDouble(4, 20.00); 
            pstmt.execute(); 
            pstmt.close(); 
            stmt = conn.createStatement( ); 
            rset = stmt.executeQuery(
                "select * from " + tablename ); 
            while( rset.next( ) ) 
            { 
                System.out.print( rset.getString( 1 ) + "\t"); 
                System.out.print( rset.getString( 2 ) + "\t"); 
                System.out.print( rset.getString( 3 ) + "\t"); 
                System.out.println( rset.getDouble( 4 ) ); 
            } 
	        stmt.close(); 
	        rset.close(); 
        } 
        catch (SQLException e) 
        { 
            System.err.println(e.getMessage()); 
            System.exit(1); 
        }
    } // end of main 
}
