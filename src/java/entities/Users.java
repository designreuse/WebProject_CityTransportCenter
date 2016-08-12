/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Entity for Users.
 * 
 */
package entities;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author Dmytro Deinichenko
 */

public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer uid;
   
    private String loginName;

    private String mail;

    private String password;

    private String role;

    private String status;

    public Users() {
    }

    public Users(Integer uid) {
        this.uid = uid;
    }
    
    public Users(String loginName, String mail, String password) {
        this.loginName = loginName;
        this.mail = mail;
        this.password = password;
    }

    public Users(Integer uid, String loginName, String mail, String password) {
        this.uid = uid;
        this.loginName = loginName;
        this.mail = mail;
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.uid);
        hash = 53 * hash + Objects.hashCode(this.loginName);
        hash = 53 * hash + Objects.hashCode(this.mail);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + Objects.hashCode(this.role);
        hash = 53 * hash + Objects.hashCode(this.status);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        if (!Objects.equals(this.uid, other.uid)) {
            return false;
        }
        if (!Objects.equals(this.loginName, other.loginName)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users{" + "uid=" + uid + ", loginName=" + loginName + ", mail=" + mail + ", password=" + password + ", role=" + role + ", status=" + status + '}';
    }
}
