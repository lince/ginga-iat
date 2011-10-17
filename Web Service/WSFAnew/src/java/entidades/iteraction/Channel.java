package entidades.iteraction;

import java.util.ArrayList;

public class Channel {

    private int idChannel;
    private int code;
    private String name;

    public Channel() {
    }

    /**
     * @return the idChannel
     */
    public int getIdChannel() {
        return idChannel;
    }

    /**
     * @param idChannel
     *            the idChannel to set
     */
    public void setIdChannel(int idChannel) {
        this.idChannel = idChannel;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
