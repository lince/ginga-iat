package POJODW;
// Generated 19/04/2010 17:52:17 by Hibernate Tools 3.2.1.GA



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


