package guessNumber;

import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;


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
    
    private static void addCash(Integer cash)
    { 
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE funds SET cash = ?" );
          
            preparedStatement.setInt(1, cash);
            preparedStatement.executeUpdate();      
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
            while(results.next()){
            mon = results.getInt(1);
            }
            results.close();
            stmt.close();
            return mon;
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
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
    }

    public boolean active(Integer i){
        if(i == 1){
        return false;
        }
        return true;
    }
}

