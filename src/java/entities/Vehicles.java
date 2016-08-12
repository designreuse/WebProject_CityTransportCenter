/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Entity for Vehicles.
 * 
 */
package entities;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author Dmytro Deinichenko
 */

public class Vehicles implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer vid;

    private String vmark;

    private String vmodel;

    private String status;
    
    private int marshroutes;

    private int vehiclestypes;

    // added manually;
    private String vehiclestypesname;
    // added manually;
    private String vehiclestypesdescription;
    // added manually;
    private int assignedto;
    // added manually;
    private String assignedtoname;

    public Vehicles() {
    }

    public Vehicles(Integer vid) {
        this.vid = vid;
    }

    public Vehicles(Integer vid, String vmark, String vmodel, String status, int vehiclestypes) {
        this.vid = vid;
        this.vmark = vmark;
        this.vmodel = vmodel;
        this.status = status;
        this.vehiclestypes = vehiclestypes;
    }
    
    public Vehicles(String mark, String model, String status, String vehicleType) {
        this.vmark = mark;
        this.vmodel = model;
        this.status = status;
        switch (vehicleType) {
            case "Autobus":
                this.vehiclestypes = 1;
                break;
            case "Trolleybus":
                this.vehiclestypes = 2;
                break;
            case "Tramway":
            default:
                this.vehiclestypes = 3;
        }
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVmark() {
        return vmark;
    }

    public void setVmark(String vmark) {
        this.vmark = vmark;
    }

    public String getVmodel() {
        return vmodel;
    }

    public void setVmodel(String vmodel) {
        this.vmodel = vmodel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMarshroutes() {
        return marshroutes;
    }

    public void setMarshroutes(int marshroutes) {
        this.marshroutes = marshroutes;
    }

    public int getVehiclestypes() {
        return vehiclestypes;
    }

    public void setVehiclestypes(int vehiclestypes) {
        this.vehiclestypes = vehiclestypes;
    }

    public void setVehiclestypesname(String vehiclestypesname) {
        this.vehiclestypesname = vehiclestypesname;
    }

    public String getVehiclestypesname() {
        return vehiclestypesname;
    }

    public void setVehiclestypesdescription(String vehiclestypesdescription) {
        this.vehiclestypesdescription = vehiclestypesdescription;
    }

    public String getVehiclestypesdescription() {
        return vehiclestypesdescription;
    }

    public void setAssignedto(int assignedto) {
        this.assignedto = assignedto;
    }

    public int getAssignedto() {
        return assignedto;
    }

    public void setAssignedtoname(String assignedtoname) {
        this.assignedtoname = assignedtoname;
    }

    public String getAssignedtoname() {
        return assignedtoname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.vid);
        hash = 83 * hash + Objects.hashCode(this.vmark);
        hash = 83 * hash + Objects.hashCode(this.vmodel);
        hash = 83 * hash + Objects.hashCode(this.status);
        hash = 83 * hash + this.marshroutes;
        hash = 83 * hash + this.vehiclestypes;
        hash = 83 * hash + Objects.hashCode(this.vehiclestypesname);
        hash = 83 * hash + Objects.hashCode(this.vehiclestypesdescription);
        hash = 83 * hash + this.assignedto;
        hash = 83 * hash + Objects.hashCode(this.assignedtoname);
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
        final Vehicles other = (Vehicles) obj;
        if (!Objects.equals(this.vid, other.vid)) {
            return false;
        }
        if (!Objects.equals(this.vmark, other.vmark)) {
            return false;
        }
        if (!Objects.equals(this.vmodel, other.vmodel)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (this.marshroutes != other.marshroutes) {
            return false;
        }
        if (this.vehiclestypes != other.vehiclestypes) {
            return false;
        }
        if (!Objects.equals(this.vehiclestypesname, other.vehiclestypesname)) {
            return false;
        }
        if (!Objects.equals(this.vehiclestypesdescription, other.vehiclestypesdescription)) {
            return false;
        }
        if (this.assignedto != other.assignedto) {
            return false;
        }
        if (!Objects.equals(this.assignedtoname, other.assignedtoname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehicles{" + "vid=" + vid + ", vmark=" + vmark + ", vmodel=" + vmodel + ", status=" + status + ", marshroutes=" + marshroutes + ", vehiclestypes=" + vehiclestypes + ", vehiclestypesname=" + vehiclestypesname + ", vehiclestypesdescription=" + vehiclestypesdescription + ", assignedto=" + assignedto + ", assignedtoname=" + assignedtoname + '}';
    }
}
