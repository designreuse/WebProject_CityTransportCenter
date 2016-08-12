/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Control Security object. Security filter checks for it in session
 * scope.
 */
package security;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmytro Deinichenko
 */
public class UserSessionChecker {
    
    private static Logger log = Logger.getLogger(UserSessionChecker.class.getName());
    private String userName;
    private int checkingKey;
    private int refUsersId;

    public UserSessionChecker() {
        log.log(Level.INFO, "UserSessionChecker created.");
    }
    
    public UserSessionChecker(String userName, int checkingKey, int refUsersId) {
        this.userName = userName;
        this.checkingKey = checkingKey;
        this.refUsersId = refUsersId;
        log.log(Level.INFO, "UserSessionChecker created.");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCheckingKey(int checkingKey) {
        this.checkingKey = checkingKey;
    }

    public void setRefUsersId(int refUsersId) {
        this.refUsersId = refUsersId;
    }

    public String getUserName() {
        return userName;
    }

    public int getCheckingKey() {
        log.log(Level.INFO, "getCheckingKey() passed");
        return checkingKey;
    }

    public int getRefUsersId() {
        return refUsersId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserSessionChecker other = (UserSessionChecker) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (this.checkingKey != other.checkingKey) {
            return false;
        }
        if (this.refUsersId != other.refUsersId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.userName);
        hash = 89 * hash + this.checkingKey;
        hash = 89 * hash + this.refUsersId;
        return hash;
    }

    @Override
    public String toString() {
        return "UserSessionChecker{" + "userName=" + userName + ", checkingKey=" + Integer.toHexString(checkingKey) + ", refUsersId=" + refUsersId + '}';
    }
    
}
