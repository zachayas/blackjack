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
@ManagedBean(name = "Money")
@SessionScoped
public class Money implements Serializable {

    public Boolean getShow1() {
        return show1;
    }

    public Boolean getShow2() {
        return show2;
    }

    public Boolean getShow3() {
        return show3;
    }

    public Boolean getShow4() {
        return show4;
    }

    public Boolean getShow5() {
        return show5;
    }

    Integer randomInt;
    String response;
     static Boolean show1 = false;
    Boolean show2 = false;
    Boolean show3 = false;
    Boolean show4 = false;
    Boolean show5 = false;
    private static String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static String tableName = "FUNDS";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    private static void createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "zac", "ayy");

        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    private static void addCash(Integer cash) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE funds SET cash = ?");

            preparedStatement.setInt(1, cash);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public static Integer getCash() {
        Integer mon = null;
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM FUNDS");
            while (results.next()) {
                mon = results.getInt(1);
            }
            results.close();
            stmt.close();
            return mon;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return null;
    }

    private static void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL);
                conn.close();
            }
        } catch (SQLException sqlExcept) {

        }

    }

    /**
     * Creates a new instance of UserNumberBean
     */
    public Money() {
        createConnection();
    }
    
    public boolean active(String boo) {
        if(boo.equals("show1")){
            if(show1 == false){
                return show1 = true;
            } else
                return show1 = false;
        }
         if(boo.equals("show2")){
            if(show2 == false){
                return show2 = true;
            } else
                return show2 = false;
            }
        if(boo.equals("show3")){
            if(show3 == false){
                return show3 = true;
            } else
                return show3 = false;
            }
         if(boo.equals("show4")){
            if(show4 == false){
                return show4 = true;
            } else
                return show4 = false;
            }
           if(boo.equals("show5")){
            if(show5 == false){
                return show5 = true;
            } else
                return show5 = false;
            }
        return false; 
        }
}
