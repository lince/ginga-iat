package entidades.iteraction;

import java.util.ArrayList;

public class Program {

    private int idProgram;
    private int age;
    private String genre;
    private String subgenre;
    private int code;
    private String name;
    private ArrayList<Meta> metas;
    private Channel channel;

    public Program() {
        setMetas(new ArrayList<Meta>());
    }

    public String trata(String passa) {
        passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
        passa = passa.replaceAll("[âãàáä]", "a");
        passa = passa.replaceAll("[ÊÈÉË]", "E");
        passa = passa.replaceAll("[êèéë]", "e");
        passa = passa.replaceAll("[ÎÍÌÏ]", "I");
        passa = passa.replaceAll("[îíìï]", "i");
        passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
        passa = passa.replaceAll("[ôõòóö]", "o");
        passa = passa.replaceAll("[ÛÙÚÜ]", "U");
        passa = passa.replaceAll("[ûúùü]", "u");
        passa = passa.replaceAll("Ç", "C");
        passa = passa.replaceAll("ç", "c");
        passa = passa.replaceAll("[ýÿ]", "y");
        passa = passa.replaceAll("Ý", "Y");
        passa = passa.replaceAll("ñ", "n");
        passa = passa.replaceAll("Ñ", "N");
        passa = passa.replaceAll("['<>\\|/]", "");
        return passa;
    }

    /**
     * @return the idProgram
     */
    public int getIdProgram() {
        return idProgram;
    }

    /**
     * @param idProgram
     *            the idProgram to set
     */
    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(int age) {
        this.age = age;
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
        this.name = trata(name);
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setMetas(ArrayList<Meta> metas) {
        this.metas = metas;
    }

    public ArrayList<Meta> getMetas() {
        return metas;
    }

    /**
     * @return the channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
