/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Entity for time Intervals.
 * 
 */
package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author Dmytro Deinichenko
 */

public class Timetable implements Serializable {
    
    private static final long serialVersionUID = 1L;
   
    private int ttid;

    private String direction;

    private String startPoint;

    private String endPoint;

    private Date interval;
    
    private String intervalStr;

    private int routeRef;

    private String routename;

    public Timetable() {
    }

    public Timetable(int idInterval) {
        
        this.ttid = idInterval;
    }
    
//    public Timetable(String direction, String startPoint, String endPoint, String intervalStr, int routeRef) {
//        this.direction = direction;
//        this.startPoint = startPoint;
//        this.endPoint = endPoint;
//        this.intervalStr = intervalStr;
//        this.routeRef = routeRef;
//    }
    
    public Timetable(String direction, String startPoint, String endPoint, Date interval, int routeRef) {
        
        this.direction = direction;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.interval = interval;
        this.routeRef = routeRef;
    }
    
    public int getTtid() {
        return ttid;
    }

    public void setTtid(int ttid) {
        this.ttid = ttid;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Date getInterval() {
        return interval;
    }

    public void setInterval(Date interval) {
        this.interval = interval;
    }

    public void setIntervalStr(String intervalStr) {
        this.intervalStr = intervalStr;
    }

    public String getIntervalStr() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String result = dateFormat.format(interval);
        return result;
    }

    public int getRouteRef() {
        return routeRef;
    }

    public void setRouteRef(int routeRef) {
        this.routeRef = routeRef;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    @Override
    public String toString() {
        return "Timetable{" + "ttid=" + ttid + ", direction=" + direction + ", startPoint=" + startPoint + ", endPoint=" + endPoint + ", interval=" + interval + ", intervalStr=" + intervalStr + ", routeRef=" + routeRef + ", routename=" + routename + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.ttid;
        hash = 83 * hash + Objects.hashCode(this.direction);
        hash = 83 * hash + Objects.hashCode(this.startPoint);
        hash = 83 * hash + Objects.hashCode(this.endPoint);
        hash = 83 * hash + Objects.hashCode(this.interval);
        hash = 83 * hash + Objects.hashCode(this.intervalStr);
        hash = 83 * hash + this.routeRef;
        hash = 83 * hash + Objects.hashCode(this.routename);
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
        final Timetable other = (Timetable) obj;
        if (this.ttid != other.ttid) {
            return false;
        }
        if (!Objects.equals(this.direction, other.direction)) {
            return false;
        }
        if (!Objects.equals(this.startPoint, other.startPoint)) {
            return false;
        }
        if (!Objects.equals(this.endPoint, other.endPoint)) {
            return false;
        }
        if (!Objects.equals(this.interval, other.interval)) {
            return false;
        }
        if (!Objects.equals(this.intervalStr, other.intervalStr)) {
            return false;
        }
        if (this.routeRef != other.routeRef) {
            return false;
        }
        if (!Objects.equals(this.routename, other.routename)) {
            return false;
        }
        return true;
    }
    
}
