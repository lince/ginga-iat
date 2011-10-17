package entidades.nclStateMachine;

import java.util.ArrayList;

public class Document {

    private int iddocument;
    private String id;
    private ArrayList<Media> medias;
    private ArrayList<Context> contexts;

    public Document() {
        medias = new ArrayList<Media>();
        contexts = new ArrayList<Context>();
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
     * @return the iddocument
     */
    public int getIddocument() {
        return iddocument;
    }

    /**
     * @param iddocument
     *            the iddocument to set
     */
    public void setIddocument(int iddocument) {
        this.iddocument = iddocument;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {

        this.id = trata(id);
    }

    public void setMedias(ArrayList<Media> medias) {
        this.medias = medias;
    }

    public ArrayList<Media> getMedias() {
        return medias;
    }

    public void setContexts(ArrayList<Context> contexts) {
        this.contexts = contexts;
    }

    public ArrayList<Context> getContexts() {
        return contexts;
    }
}
