package POJODW;
// Generated 30/06/2010 16:19:25 by Hibernate Tools 3.2.1.GA



/**
 * DimProgram generated by hbm2java
 */
public class DimProgram  implements java.io.Serializable {


     private Integer idDimProgram;
     private Integer channelCode;
     private String channelName;
     private String name;
     private Integer age;
     private String genre;
     private String subgenre;

    public DimProgram() {
    }

    public DimProgram(Integer channelCode, String channelName, String name, Integer age, String genre, String subgenre) {
       this.channelCode = channelCode;
       this.channelName = channelName;
       this.name = name;
       this.age = age;
       this.genre = genre;
       this.subgenre = subgenre;
    }
   
    public Integer getIdDimProgram() {
        return this.idDimProgram;
    }
    
    public void setIdDimProgram(Integer idDimProgram) {
        this.idDimProgram = idDimProgram;
    }
    public Integer getChannelCode() {
        return this.channelCode;
    }
    
    public void setChannelCode(Integer channelCode) {
        this.channelCode = channelCode;
    }
    public String getChannelName() {
        return this.channelName;
    }
    
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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
    public String getSubgenre() {
        return this.subgenre;
    }
    
    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }




}


