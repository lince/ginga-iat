package POJODW;
// Generated 19/04/2010 17:52:17 by Hibernate Tools 3.2.1.GA



/**
 * IteractionHasDimDataId generated by hbm2java
 */
public class IteractionHasDimDataId  implements java.io.Serializable {


     private int iteractionIdIteraction;
     private int dimDataIdDimData;

    public IteractionHasDimDataId() {
    }

    public IteractionHasDimDataId(int iteractionIdIteraction, int dimDataIdDimData) {
       this.iteractionIdIteraction = iteractionIdIteraction;
       this.dimDataIdDimData = dimDataIdDimData;
    }
   
    public int getIteractionIdIteraction() {
        return this.iteractionIdIteraction;
    }
    
    public void setIteractionIdIteraction(int iteractionIdIteraction) {
        this.iteractionIdIteraction = iteractionIdIteraction;
    }
    public int getDimDataIdDimData() {
        return this.dimDataIdDimData;
    }
    
    public void setDimDataIdDimData(int dimDataIdDimData) {
        this.dimDataIdDimData = dimDataIdDimData;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof IteractionHasDimDataId) ) return false;
		 IteractionHasDimDataId castOther = ( IteractionHasDimDataId ) other; 
         
		 return (this.getIteractionIdIteraction()==castOther.getIteractionIdIteraction())
 && (this.getDimDataIdDimData()==castOther.getDimDataIdDimData());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIteractionIdIteraction();
         result = 37 * result + this.getDimDataIdDimData();
         return result;
   }   


}


