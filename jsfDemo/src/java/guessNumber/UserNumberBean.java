/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zacha
 */
@ManagedBean(name = "UserNumberBean")
@SessionScoped
public class UserNumberBean implements Serializable {
        
       Integer randomInt;
       Integer userNumber;
       String response;
       
    public String getResponse() {
    if((userNumber !=null) && (userNumber.compareTo(randomInt) == 0)) {
        //invalidates user session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        
        return "Yay! You got it!";
    } else {
        return "<p>Sorry, " + userNumber + " isn't it.</p>" 
                + "<p>Guesss again...</p>";
    }
}
       
       
       
    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
       
       
    /** Creates a new instance of UserNumberBean*/
    public UserNumberBean() {
        Random randomGR = new Random();
        randomInt = new Integer(randomGR.nextInt(10));
        System.out.println("Duke's number: " + randomInt);
    }
    
}
