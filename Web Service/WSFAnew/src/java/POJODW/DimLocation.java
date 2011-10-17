package POJODW;
// Generated 30/06/2010 16:19:25 by Hibernate Tools 3.2.1.GA



/**
 * DimLocation generated by hbm2java
 */
public class DimLocation  implements java.io.Serializable {


     private Integer idDimLocation;
     private String zip;
     private Double longitude;
     private Double latitude;
     private String city;
     private String state;
     private String uf;

    public DimLocation() {
    }

    public DimLocation(String zip, Double longitude, Double latitude, String city, String state, String uf) {
       this.zip = zip;
       this.longitude = longitude;
       this.latitude = latitude;
       this.city = city;
       this.state = state;
       this.uf = uf;
    }
   
    public Integer getIdDimLocation() {
        return this.idDimLocation;
    }
    
    public void setIdDimLocation(Integer idDimLocation) {
        this.idDimLocation = idDimLocation;
    }
    public String getZip() {
        return this.zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    public Double getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getUf() {
        return this.uf;
    }
    
    public void setUf(String uf) {
        this.uf = uf;
    }




}

