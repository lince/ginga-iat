package POJOTVDI;
// Generated 27/03/2010 09:17:04 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Log generated by hbm2java
 */
public class Log  implements java.io.Serializable {


     private Integer idLog;
     private int watchTvIdWatchTv;
     private Date logDate;
     private String ip;
     private Double size;

    public Log() {
    }

	
    public Log(int watchTvIdWatchTv) {
        this.watchTvIdWatchTv = watchTvIdWatchTv;
    }
    public Log(int watchTvIdWatchTv, Date logDate, String ip, Double size) {
       this.watchTvIdWatchTv = watchTvIdWatchTv;
       this.logDate = logDate;
       this.ip = ip;
       this.size = size;
    }
   
    public Integer getIdLog() {
        return this.idLog;
    }
    
    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }
    public int getWatchTvIdWatchTv() {
        return this.watchTvIdWatchTv;
    }
    
    public void setWatchTvIdWatchTv(int watchTvIdWatchTv) {
        this.watchTvIdWatchTv = watchTvIdWatchTv;
    }
    public Date getLogDate() {
        return this.logDate;
    }
    
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    public Double getSize() {
        return this.size;
    }
    
    public void setSize(Double size) {
        this.size = size;
    }




}

