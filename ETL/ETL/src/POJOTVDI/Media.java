package POJOTVDI;
// Generated 27/03/2010 09:17:04 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Media generated by hbm2java
 */
public class Media  implements java.io.Serializable {


     private Integer idMedia;
     private Integer contextIdContext;
     private Integer documentIddocument;
     private String id;
     private String status;
     private Date time;

    public Media() {
    }

    public Media(Integer contextIdContext, Integer documentIddocument, String id, String status, Date time) {
       this.contextIdContext = contextIdContext;
       this.documentIddocument = documentIddocument;
       this.id = id;
       this.status = status;
       this.time = time;
    }
   
    public Integer getIdMedia() {
        return this.idMedia;
    }
    
    public void setIdMedia(Integer idMedia) {
        this.idMedia = idMedia;
    }
    public Integer getContextIdContext() {
        return this.contextIdContext;
    }
    
    public void setContextIdContext(Integer contextIdContext) {
        this.contextIdContext = contextIdContext;
    }
    public Integer getDocumentIddocument() {
        return this.documentIddocument;
    }
    
    public void setDocumentIddocument(Integer documentIddocument) {
        this.documentIddocument = documentIddocument;
    }
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getTime() {
        return this.time;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }




}


