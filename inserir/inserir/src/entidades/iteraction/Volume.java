package entidades.iteraction;

public class Volume {

    private int idVolume;
    private int level;
    private boolean mute;

    /**
     * @return the idVolume
     */
    public int getIdVolume() {
        return idVolume;
    }

    /**
     * @param idVolume the idVolume to set
     */
    public void setIdVolume(int idVolume) {
        this.idVolume = idVolume;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the mute
     */
    public boolean isMute() {
        return mute;
    }

    /**
     * @param mute the mute to set
     */
    public void setMute(boolean mute) {
        this.mute = mute;
    }
}
