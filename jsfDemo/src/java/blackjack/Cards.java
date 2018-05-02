package blackjack;

import static blackjack.Money.addCash;
import static blackjack.Money.getCash;
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
@ManagedBean(name = "Cards")
@SessionScoped
public class Cards implements Serializable {

    private static String[] card = null;
    private static Integer[] val = null;
    private static Integer value1 = null;
    private static Integer value2 = null;
    private static Integer value3 = null;
    private static Integer value4 = null;
    private static Integer value5 = null;
    private static Integer valueDeal = null;

    static String card1 = null;
    static String card2 = null;
    static String card3 = null;
    static String card4 = null;
    static String card5 = null;

    static Boolean hit1 = true;
    static Boolean hit2 = true;
    static Boolean hit3 = true;
    static Boolean hit4 = true;
    static Boolean hit5 = true;

    static Boolean show1 = false;
    static Boolean show2 = false;
    static Boolean show3 = false;
    static Boolean show4 = false;
    static Boolean show5 = false;

    static String cardDeal = null;

    static Random rand = new Random();

    private static String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";

// jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    /**
     * Creates a new instance of Cards
     */
    public Cards() {
        createConnection();
        card = new String[52];
        val = new Integer[56];
        for (int i = 0; i < 52; i++) {
            card[i] = "PNG/" + (i + 1) + ".png";
        }
        for (int i = 0; i < 56; i++) {
            if (i < 4) {
                val[i] = 2;
            } else if (i < 8) {
                val[i] = 3;
            } else if (i < 12) {
                val[i] = 4;
            } else if (i < 16) {
                val[i] = 5;
            } else if (i < 20) {
                val[i] = 6;
            } else if (i < 24) {
                val[i] = 7;
            } else if (i < 28) {
                val[i] = 8;
            } else if (i < 32) {
                val[i] = 9;
            } else if (i < 48) {
                val[i] = 10;
            } else if (i < 52) {
                val[i] = 1;
            } else if (i < 56) {
                val[i] = 11;
            }
        }
    }

    private static void createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL, "zac", "ayy");

        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    public static void addCard(String cardi) {
        int n;
        n = rand.nextInt(51);       
        if (card[n] != null) {
            if (cardi.equals("card1")) {
                card1 = card[n];
                value1 = n;
                card[n] = null;               
            }
            if (cardi.equals("card2")) {
                card2 = card[n];
                value2 = n;
                card[n] = null;
            }
            if (cardi.equals("card3")) {
                card3 = card[n];
                value3 = n;
                card[n] = null;
            }
            if (cardi.equals("card4")) {
                card4 = card[n];
                value4 = n;
                card[n] = null;
                
            }
            if (cardi.equals("card5")) {
                card5 = card[n];
                value5 = n;
                card[n] = null;
                
            }
            if (cardi.equals("cardDeal") && (cardDeal == null)) {
                cardDeal = card[n];
                valueDeal = n;
                card[n] = null;               
            }
        }
    }

    public static void reshuffle() {
        card1 = null;
        card2 = null;
        card3 = null;
        card4 = null;
        card5 = null;
        cardDeal = null;

        hit1 = true;
        hit2 = true;
        hit3 = true;
        hit4 = true;
        hit5 = true;

        show1 = false;
        show2 = false;
        show3 = false;
        show4 = false;
        show5 = false;

        for (int i = 0; i < 52; i++) {
            card[i] = "PNG/" + (i + 1) + ".png";
        }
    }

    public void active(String boo) {
        if (boo.equals("show1")) {
            if (show1 == false) {
                show1 = true;
                hit1 = false;
            }
        }
        if (boo.equals("show2")) {
            if (show2 == false) {
                show2 = true;
                hit2 = false;
            }
        }
        if (boo.equals("show3")) {
            if (show3 == false) {
                show3 = true;
                hit3 = false;
            }
        }
        if (boo.equals("show4")) {
            if (show4 == false) {
                show4 = true;
                hit4 = false;
            }
        }
        if (boo.equals("show5")) {
            if (show5 == false) {
                show5 = true;
                hit5 = false;
            }
        }
    }

    public void deal() {
        int p = 0;
        int d = 0;
        int m = getCash();
        StringBuilder card = new StringBuilder();

        if (card1 != null) {
            p = p + val[value1];
            card.append(val[value1]).append(", ");
            card1 = null;
            show1 = false;
            hit1 = true;
        }
        if (card2 != null) {
            p = p + val[value2];
            card.append(val[value2]).append(", ");
            card2 = null;
            show2 = false;
            hit2 = true;
        }
        if (card3 != null) {
            p = p + val[value3];
            card.append(val[value3]).append(", ");
            card3 = null;
            show3 = false;
            hit3 = true;
        }
        if (card4 != null) {
            p = p + val[value4];
            card.append(val[value4]).append(", ");
            card4 = null;
            show4 = false;
            hit4 = true;
        }
        if (card5 != null) {
            p = p + val[value5];
            card.append(val[value5]).append(", ");
            card5 = null;
            show5 = false;
            hit5 = true;
        }
        while (cardDeal != null) {
            d = d + val[valueDeal];
            if (d < 16) {
                cardDeal = null;
                addCard("cardDeal");
            } else {
                cardDeal = null;
            }
        }
        if (p <= 21) {
            if (p > d || d > 21) {
                addCash(m * 2);
                status("Win", p, card.toString());
                
            } else if (p == d) {
                status("Push", p, card.toString());
            } else {
                 addCash(m - (m/2));
                status("Lost", p, card.toString());
            }
        } else {
             addCash(m - (m/2));
            status("Lost", p, card.toString());
        }
        reshuffle();
    }

    private void status(String str, Integer val, String card) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO STATUS"
                    + "(WLP, VALUE, CARDS, TIMES) VALUES"
                    + "(?,?,?,?)");

            preparedStatement.setString(1, str);
            preparedStatement.setInt(2, val);
            preparedStatement.setString(3, card);
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()) );
            preparedStatement.execute();

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public static List<Status> getStatus() {

        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM (SELECT * FROM STATUS ORDER BY TIMES DESC) WHERE rownum <= 10");
            
              List<Status> list = new ArrayList<Status>();
              
            while (results.next()) {
               String status = results.getString(1);
               Integer value = results.getInt(2);
               String cards = results.getString(3);
               list.add(new Status(status, value, cards));
            }
            results.close();
            stmt.close();
            return list;
        } 
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return null;
    }

    public static String getCard1() {
        return card1;
    }

    public static String getCard2() {
        return card2;
    }

    public static String getCard3() {
        return card3;
    }

    public static String getCard4() {
        return card4;
    }

    public static String getCard5() {
        return card5;
    }

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

    public static Boolean getHit1() {
        return hit1;
    }

    public static Boolean getHit2() {
        return hit2;
    }

    public static Boolean getHit3() {
        return hit3;
    }

    public static Boolean getHit4() {
        return hit4;
    }

    public static Boolean getHit5() {
        return hit5;
    }

    public static String getCardDeal() {
        return cardDeal;
    }

}
