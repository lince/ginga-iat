package POJOTVDI;
// Generated 27/03/2010 09:17:04 by Hibernate Tools 3.2.1.GA



/**
 * Volume generated by hbm2java
 */
public class Volume  implements java.io.Serializable {


     private Integer idVolume;
     private Integer level;
     private Boolean mute;

    public Volume() {
    }

    public Volume(Integer level, Boolean mute) {
       this.level = level;
       this.mute = mute;
    }
   
    public Integer getIdVolume() {
        return this.idVolume;
    }
    
    public void setIdVolume(Integer idVolume) {
        this.idVolume = idVolume;
    }
    public Integer getLevel() {
        return this.level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    public Boolean getMute() {
        return this.mute;
    }
    
    public void setMute(Boolean mute) {
        this.mute = mute;
    }




}

