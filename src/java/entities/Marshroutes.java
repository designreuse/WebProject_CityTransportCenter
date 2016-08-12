/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Entity for Marshroutes.
 * 
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Dmytro Deinichenko
 */


public class Marshroutes implements Serializable {
    
    private static final Logger log = Logger.getLogger(Marshroutes.class.getName());
    
    private static final long serialVersionUID = 1L;
   
    private Integer idmarshroutes;

    private String routename;

    private String description;

    public Marshroutes() {
        log.log(Level.INFO, "Marshroutes created");
    }

    public Marshroutes(Integer idmarshroutes) {
        this.idmarshroutes = idmarshroutes;
        log.log(Level.INFO, "Marshroutes created");
    }

    public Marshroutes(Integer idmarshroutes, String routename) {
        this.idmarshroutes = idmarshroutes;
        this.routename = routename;
        log.log(Level.INFO, "Marshroutes created");
    }
    
     public Marshroutes(String routename, String description) {

        this.routename = routename;
        this.description = description;
        log.log(Level.INFO, "Marshroutes created");
    }

    public Integer getIdmarshroutes() {
        return idmarshroutes;
    }

    public void setIdmarshroutes(Integer idmarshroutes) {
        this.idmarshroutes = idmarshroutes;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idmarshroutes);
        hash = 17 * hash + Objects.hashCode(this.routename);
        hash = 17 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Marshroutes)) {
            return false;
        }
        Marshroutes other = (Marshroutes) object;
        if ((this.idmarshroutes == null && other.idmarshroutes != null) || (this.idmarshroutes != null && !this.idmarshroutes.equals(other.idmarshroutes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Marshroutes{" + "idmarshroutes=" + idmarshroutes + ", routename=" + routename + ", description=" + description + '}';
    }
    
}
