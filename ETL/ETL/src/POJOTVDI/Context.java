package POJOTVDI;
// Generated 27/03/2010 09:17:04 by Hibernate Tools 3.2.1.GA



/**
 * Context generated by hbm2java
 */
public class Context  implements java.io.Serializable {


     private Integer idContext;
     private Integer contextIdContext;
     private Integer documentIddocument;
     private String id;

    public Context() {
    }

    public Context(Integer contextIdContext, Integer documentIddocument, String id) {
       this.contextIdContext = contextIdContext;
       this.documentIddocument = documentIddocument;
       this.id = id;
    }
   
    public Integer getIdContext() {
        return this.idContext;
    }
    
    public void setIdContext(Integer idContext) {
        this.idContext = idContext;
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




}

