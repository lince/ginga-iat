package POJODW;
// Generated 19/04/2010 22:24:40 by Hibernate Tools 3.2.1.GA



/**
 * ResourceNetwork generated by hbm2java
 */
public class ResourceNetwork  implements java.io.Serializable {


     private Integer idResourceNetwork;
     private int dimDeviceIdDimDevice;
     private String speed;
     private String model;

    public ResourceNetwork() {
    }

	
    public ResourceNetwork(int dimDeviceIdDimDevice) {
        this.dimDeviceIdDimDevice = dimDeviceIdDimDevice;
    }
    public ResourceNetwork(int dimDeviceIdDimDevice, String speed, String model) {
       this.dimDeviceIdDimDevice = dimDeviceIdDimDevice;
       this.speed = speed;
       this.model = model;
    }
   
    public Integer getIdResourceNetwork() {
        return this.idResourceNetwork;
    }
    
    public void setIdResourceNetwork(Integer idResourceNetwork) {
        this.idResourceNetwork = idResourceNetwork;
    }
    public int getDimDeviceIdDimDevice() {
        return this.dimDeviceIdDimDevice;
    }
    
    public void setDimDeviceIdDimDevice(int dimDeviceIdDimDevice) {
        this.dimDeviceIdDimDevice = dimDeviceIdDimDevice;
    }
    public String getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(String speed) {
        this.speed = speed;
    }
    public String getModel() {
        return this.model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }




}

