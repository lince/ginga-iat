package entidades;

import entidades.head.Head;
import entidades.iteraction.Iteraction;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Watch_Tv {

    private int idWatch_Tv;
    private Country country;
    private Timestamp startDate;
    private Timestamp endDate;
    private Head head;
    private ArrayList<Iteraction> iteractions;
    private Log log;

    public static String alteraChar(String entrada, char c, int i) {
        String retorno = "";
        for (int j = 0; j < i; j++) {
            retorno += entrada.charAt(j);
        }
        retorno += c;
        for (int j = ++i; j < entrada.length(); j++) {
            retorno += entrada.charAt(j);
        }
        return retorno;
    }

    public Watch_Tv() {
        iteractions = new ArrayList<Iteraction>();
        log = new Log();
    }

    /**
     * @return the idWatch_Tv
     */
    public int getIdWatch_Tv() {
        return idWatch_Tv;
    }

    /**
     * @param idWatch_Tv
     *            the idWatch_Tv to set
     */
    public void setIdWatch_Tv(int idWatch_Tv) {
        this.idWatch_Tv = idWatch_Tv;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @return the startDate
     */
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(Timestamp startDate) {

        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Timestamp getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the head
     */
    public Head getHead() {
        return head;
    }

    /**
     * @param head
     *            the head to set
     */
    public void setHead(Head head) {
        this.head = head;
    }

    /**
     * @return the iteractions
     */
    public ArrayList<Iteraction> getIteractions() {
        return iteractions;
    }

    /**
     * @param iteractions
     *            the iteractions to set
     */
    public void setIteractions(ArrayList<Iteraction> iteractions) {
        this.iteractions = iteractions;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Log getLog() {
        return log;
    }
}
