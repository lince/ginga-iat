package entidades.iteraction;

import entidades.nclStateMachine.nclStateMachine;
import java.sql.Date;
import java.sql.Timestamp;

public class Iteraction {

    private int idIteraction;
    private int watch_TV_idwatch_TV;
    private Timestamp time;
    private String tipo;
    private Key key;
    private Volume volume;
    private Program program;
    private nclStateMachine nclMachine;

    /**
     * @return the watch_TV_idwatch_TV
     */
    public int getWatch_TV_idwatch_TV() {
        return watch_TV_idwatch_TV;
    }

    /**
     * @param watch_TV_idwatch_TV
     *            the watch_TV_idwatch_TV to set
     */
    public void setWatch_TV_idwatch_TV(int watch_TV_idwatch_TV) {
        this.watch_TV_idwatch_TV = watch_TV_idwatch_TV;
    }

    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     *            the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * @return the volume
     */
    public Volume getVolume() {
        return volume;
    }

    /**
     * @param volume
     *            the volume to set
     */
    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    /**
     * @return the ncMachine
     */
    public nclStateMachine getNclMachine() {
        return nclMachine;
    }

    /**
     * @param ncMachine
     *            the ncMachine to set
     */
    public void setNclMachine(nclStateMachine nclMachine) {
        this.nclMachine = nclMachine;
    }

    public void setIdIteraction(int idIteraction) {
        this.idIteraction = idIteraction;
    }

    public int getIdIteraction() {
        return idIteraction;
    }

    /**
     * @return the program
     */
    public Program getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(Program program) {
        this.program = program;
    }
}
