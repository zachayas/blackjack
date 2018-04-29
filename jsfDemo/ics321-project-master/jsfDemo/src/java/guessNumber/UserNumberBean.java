package guessNumber;

import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author nbuser
 */
@ManagedBean(name = "UserNumberBean")
@SessionScoped
public class UserNumberBean implements Serializable {

    Integer randomInt;
    String  response;
    private static String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static String tableName = "FUNDS";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    private static void createConnection()
    {
        try
        {
           Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "zac", "ayy"); 
            
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    
    public static void insertCash(Integer cash)
    { 
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Funds" 
                    + "(cash) VALUES"
                    + "(?)");
          
            preparedStatement.setInt(1, cash);
            preparedStatement.execute();    
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public static Integer getCash()
    {
        Integer mon = null;
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM FUNDS");
           do{  
               if (!results.next()) {
         insertCash(50);
        } else {
          
            mon = results.getInt(1);
            System.out.println(mon);
            } 
           }while(results.next());
           
            results.close();
            stmt.close();
            return mon;
        }
    
        catch (SQLException sqlExcept)
        {
        }
        return null;
    }
    private static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL);
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
    
    /** Creates a new instance of UserNumberBean */
    public UserNumberBean() {
        createConnection();
        Random randomGR = new Random();
        randomInt = new Integer(randomGR.nextInt(10));
        System.out.println("Duke's number: " + randomInt);
    }

    public boolean active(Integer i){
        if(i == 1){
        return false;
        }
        return true;
    }
}

