package POJODW;
// Generated 19/04/2010 17:52:17 by Hibernate Tools 3.2.1.GA



/**
 * IteractionHasDimTimeId generated by hbm2java
 */
public class IteractionHasDimTimeId  implements java.io.Serializable {


     private int iteractionIdIteraction;
     private int dimTimeIdDimTime;

    public IteractionHasDimTimeId() {
    }

    public IteractionHasDimTimeId(int iteractionIdIteraction, int dimTimeIdDimTime) {
       this.iteractionIdIteraction = iteractionIdIteraction;
       this.dimTimeIdDimTime = dimTimeIdDimTime;
    }
   
    public int getIteractionIdIteraction() {
        return this.iteractionIdIteraction;
    }
    
    public void setIteractionIdIteraction(int iteractionIdIteraction) {
        this.iteractionIdIteraction = iteractionIdIteraction;
    }
    public int getDimTimeIdDimTime() {
        return this.dimTimeIdDimTime;
    }
    
    public void setDimTimeIdDimTime(int dimTimeIdDimTime) {
        this.dimTimeIdDimTime = dimTimeIdDimTime;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof IteractionHasDimTimeId) ) return false;
		 IteractionHasDimTimeId castOther = ( IteractionHasDimTimeId ) other; 
         
		 return (this.getIteractionIdIteraction()==castOther.getIteractionIdIteraction())
 && (this.getDimTimeIdDimTime()==castOther.getDimTimeIdDimTime());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIteractionIdIteraction();
         result = 37 * result + this.getDimTimeIdDimTime();
         return result;
   }   


}

