package POJODW;
// Generated 19/04/2010 22:24:40 by Hibernate Tools 3.2.1.GA



/**
 * IteractionHasDimUserId generated by hbm2java
 */
public class IteractionHasDimUserId  implements java.io.Serializable {


     private int iteractionIdIteraction;
     private int dimUserIdDimUser;

    public IteractionHasDimUserId() {
    }

    public IteractionHasDimUserId(int iteractionIdIteraction, int dimUserIdDimUser) {
       this.iteractionIdIteraction = iteractionIdIteraction;
       this.dimUserIdDimUser = dimUserIdDimUser;
    }
   
    public int getIteractionIdIteraction() {
        return this.iteractionIdIteraction;
    }
    
    public void setIteractionIdIteraction(int iteractionIdIteraction) {
        this.iteractionIdIteraction = iteractionIdIteraction;
    }
    public int getDimUserIdDimUser() {
        return this.dimUserIdDimUser;
    }
    
    public void setDimUserIdDimUser(int dimUserIdDimUser) {
        this.dimUserIdDimUser = dimUserIdDimUser;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof IteractionHasDimUserId) ) return false;
		 IteractionHasDimUserId castOther = ( IteractionHasDimUserId ) other; 
         
		 return (this.getIteractionIdIteraction()==castOther.getIteractionIdIteraction())
 && (this.getDimUserIdDimUser()==castOther.getDimUserIdDimUser());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIteractionIdIteraction();
         result = 37 * result + this.getDimUserIdDimUser();
         return result;
   }   


}


