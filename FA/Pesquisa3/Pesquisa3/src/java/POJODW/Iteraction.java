package POJODW;
// Generated 19/04/2010 22:24:40 by Hibernate Tools 3.2.1.GA



/**
 * Iteraction generated by hbm2java
 */
public class Iteraction  implements java.io.Serializable {


     private Integer idIteraction;
     private String type;
     private Integer volumeLevel;
     private Boolean volumeMute;
     private String keyCode;
     private String keyAction;

    public Iteraction() {
    }

    public Iteraction(String type, Integer volumeLevel, Boolean volumeMute, String keyCode, String keyAction) {
       this.type = type;
       this.volumeLevel = volumeLevel;
       this.volumeMute = volumeMute;
       this.keyCode = keyCode;
       this.keyAction = keyAction;
    }
   
    public Integer getIdIteraction() {
        return this.idIteraction;
    }
    
    public void setIdIteraction(Integer idIteraction) {
        this.idIteraction = idIteraction;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public Integer getVolumeLevel() {
        return this.volumeLevel;
    }
    
    public void setVolumeLevel(Integer volumeLevel) {
        this.volumeLevel = volumeLevel;
    }
    public Boolean getVolumeMute() {
        return this.volumeMute;
    }
    
    public void setVolumeMute(Boolean volumeMute) {
        this.volumeMute = volumeMute;
    }
    public String getKeyCode() {
        return this.keyCode;
    }
    
    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }
    public String getKeyAction() {
        return this.keyAction;
    }
    
    public void setKeyAction(String keyAction) {
        this.keyAction = keyAction;
    }




}


