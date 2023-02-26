package figurka;

/**
 * Enum, ktory reprezentuje typy figuriek a ich prislusne farby.
 * Obsahuje dva privatne atributy :
 * Atribut subor reprezentuje cestu k suboru, ktory obsahuje obrazok danej firgurky,
 * Atribut nazov reprezentuje slovne pomenovanie(vo forme String) figurky.
 */
public enum TypFigurky {
    CIERNA("Dama/pics/cierna_figurka.png", "ciernyPesiak"),
    CERVENA("Dama/pics/cervena_figurka.png", "cervenyPesiak"),
    CIERNA_DAMA("Dama/pics/cierna_dama.png", "ciernaDama"),
    CERVENA_DAMA("Dama/pics/cervena_dama.png", "cervenaDama");
    private String subor;
    private String nazov;

    /**
     * Konstruktor, ktory prijma parametre subor a nazov, nasledne inicializuje atributy na dane hodnoty.
     *
     * @param subor Atributu subor je priradena hodnota parametra subor.
     * @param nazov Atributu nazov je priradena hodnota parametra nazov.
     */
    TypFigurky(String subor, String nazov) {
        this.subor = subor;
        this.nazov = nazov;
    }

    /**
     * Getter atributu subor.
     *
     * @return vracia hodnotu atributu subor.
     */
    public String getSubor() {
        return this.subor;
    }

    /**
     * Prekrytie metody toString, ktoru dedi kazdy objekt.
     *
     * @return vrati nam nazov figurky vo forme String.
     */
    public String toString() {
        return this.nazov;
    }
}
