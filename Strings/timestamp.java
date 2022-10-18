import java.sql.Date; 
import java.sql.Timestamp; 
public class GFG { 
    public static void main(String[] args) 
    { 
        String empOrStuAvailableDate = "2021-01-01"; 
        Date utilAvailabilityDate 
            = new SimpleDateFormat("yyyy-MM-dd") 
                  .parse(empOrStuAvailableDate); 
        
        // jdbc connectivity expects java.sql.Date 
        java.sql.Date sqlAvailabilityDate 
            = new java.sql.Date( 
                utilAvailabilityDate.getTime()); 
  
        // create a java timestamp object that represents 
        // the current time (i.e., a "current timestamp") 
        Calendar calendarInstance = Calendar.getInstance(); 
        
        java.sql.Timestamp sampleJavaTimestampObject 
            = new java.sql.Timestamp( 
                calendarInstance.getTime().getTime()); 
        try { 
            Class.forName("com.mysql.jdbc.Driver"); 
            
            // Here test is the databasename 
            // username is root and password is root 
            Connection con = DriverManager.getConnection( 
                "jdbc:mysql://localhost:3306/test", "root", 
                "root"); 
  
            PreparedStatement pStmt = con.prepareStatement( 
                "insert into empOrStuInformation1 (empOrStuName,empOrStuAvailability,empOrStuLogEntry) values(?,?,?)"); 
              
            // As we are using Date datatype, we are using 
            // setDate only... 
            pStmt.setString(1, "AAAA"); 
            
            // The setDate() method is used to set date 
            pStmt.setDate(2, sqlAvailabilityDate); 
            
            // setTimestamp() is used to set time. 
            pStmt.setTimestamp(3, 
                               sampleJavaTimestampObject); 
            pStmt.executeUpdate(); 
            pStmt.close(); 
            con.close(); 
        } 
        catch (Exception ex) { 
            System.out.println(ex); 
        } 
    } 
}
