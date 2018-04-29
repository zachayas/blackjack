/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author tylerchong
 */
@Entity
public class Attempt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Timestamp atime;
    public Integer usernum;
    public Integer actualnum;
    
    Attempt(Timestamp atime, Integer usernum, Integer actualnum) {
        this.atime = atime;
        this.usernum = usernum;
        this.actualnum = actualnum;
    }

    public Timestamp getAtime() {
        return atime;
    }

    public Integer getUsernum() {
        return usernum;
    }

    public Integer getActualnum() {
        return actualnum;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attempt)) {
            return false;
        }
        Attempt other = (Attempt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "guessNumber.Attempt[ id=" + id + " ]";
    }
    
}
