package utilities;

import java.sql.*;

public class Printer
{
    public static void printTable(ResultSet rset, ResultSetMetaData mdata)
        throws SQLException
    {
        System.out.println(mdata.getTableName(1));

        for (
            int indexColA = 1; 
            indexColA <= mdata.getColumnCount();
            ++indexColA)
        {
            System.out.print(mdata.getColumnName(indexColA) + "\t");
        }

        System.out.println();

        while( rset.next( ) ) 
        {   
           for (
                int indexColB = 1; 
                indexColB <= mdata.getColumnCount();
                ++indexColB)
            {
                System.out.print(rset.getObject(indexColB) + "\t");
            }

            System.out.println();
        } 
    }    
}
