package blackjack;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;

/**
 *
 * @author zachayas
 */
@ManagedBean(name = "Money")
@SessionScoped
public class Money implements Serializable {
    
    String response;
    private static String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static String tableName = "FUNDS";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    private static void createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL, "zac", "ayy");

        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    public static void addCash(Integer cash) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE funds SET cash = ?");
          
            preparedStatement.setInt(1, cash);
            preparedStatement.executeUpdate();
            preparedStatement.close();
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
}
