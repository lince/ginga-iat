package POJOTVDI;
// Generated 27/03/2010 09:17:04 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer idUser;
     private Character genre;
     private Date birth;
     private String identification;

    public User() {
    }

    public User(Character genre, Date birth, String identification) {
       this.genre = genre;
       this.birth = birth;
       this.identification = identification;
    }
   
    public Integer getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public Character getGenre() {
        return this.genre;
    }
    
    public void setGenre(Character genre) {
        this.genre = genre;
    }
    public Date getBirth() {
        return this.birth;
    }
    
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public String getIdentification() {
        return this.identification;
    }
    
    public void setIdentification(String identification) {
        this.identification = identification;
    }




}

