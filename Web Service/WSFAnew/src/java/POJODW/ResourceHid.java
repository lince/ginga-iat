package POJODW;
// Generated 30/06/2010 16:19:25 by Hibernate Tools 3.2.1.GA



/**
 * ResourceHid generated by hbm2java
 */
public class ResourceHid  implements java.io.Serializable {


     private Integer idResourceHid;
     private int dimDeviceIdDimDevice;
     private String model;
     private String type;

    public ResourceHid() {
    }

	
    public ResourceHid(int dimDeviceIdDimDevice) {
        this.dimDeviceIdDimDevice = dimDeviceIdDimDevice;
    }
    public ResourceHid(int dimDeviceIdDimDevice, String model, String type) {
       this.dimDeviceIdDimDevice = dimDeviceIdDimDevice;
       this.model = model;
       this.type = type;
    }
   
    public Integer getIdResourceHid() {
        return this.idResourceHid;
    }
    
    public void setIdResourceHid(Integer idResourceHid) {
        this.idResourceHid = idResourceHid;
    }
    public int getDimDeviceIdDimDevice() {
        return this.dimDeviceIdDimDevice;
    }
    
    public void setDimDeviceIdDimDevice(int dimDeviceIdDimDevice) {
        this.dimDeviceIdDimDevice = dimDeviceIdDimDevice;
    }
    public String getModel() {
        return this.model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }




}

