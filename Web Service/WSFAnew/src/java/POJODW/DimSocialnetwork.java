package POJODW;
// Generated 30/06/2010 16:19:25 by Hibernate Tools 3.2.1.GA



/**
 * DimSocialnetwork generated by hbm2java
 */
public class DimSocialnetwork  implements java.io.Serializable {


     private Integer idDimSocialNetwork;
     private String name;

    public DimSocialnetwork() {
    }

    public DimSocialnetwork(String name) {
       this.name = name;
    }
   
    public Integer getIdDimSocialNetwork() {
        return this.idDimSocialNetwork;
    }
    
    public void setIdDimSocialNetwork(Integer idDimSocialNetwork) {
        this.idDimSocialNetwork = idDimSocialNetwork;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }




}


