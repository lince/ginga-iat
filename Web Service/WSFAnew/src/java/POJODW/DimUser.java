package POJODW;
// Generated 30/06/2010 16:19:25 by Hibernate Tools 3.2.1.GA



/**
 * DimUser generated by hbm2java
 */
public class DimUser  implements java.io.Serializable {


     private Integer idDimUser;
     private Integer age;
     private String genre;

    public DimUser() {
    }

    public DimUser(Integer age, String genre) {
       this.age = age;
       this.genre = genre;
    }
   
    public Integer getIdDimUser() {
        return this.idDimUser;
    }
    
    public void setIdDimUser(Integer idDimUser) {
        this.idDimUser = idDimUser;
    }
    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getGenre() {
        return this.genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }




}


