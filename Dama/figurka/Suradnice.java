package figurka;

/**
 * Trieda, ktora modeluje funkciu suradnic v priestore.
 */
public class Suradnice {
    /**
     * Dva privatne atributy, ktore charakterizuju X a Y poziciu v priestore.
     */
    private int poziciaX;
    private int poziciaY;

    /**
     * Kontstruktor novych suradnic, inicializuje atributy podla zadanych parametrov.
     *
     * @param poziciaX hodnota tohto parametra je priradena atributu poziciaX.
     * @param poziciaY hodnota tohto parametra je priradena atributu poziciaY.
     */
    public Suradnice(int poziciaX, int poziciaY) {
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
    }

    /**
     * Getter pre atribut poziciaX.
     *
     * @return vrati poziciuX ako cele cislo(integer).
     */
    public int getPoziciaX() {
        return this.poziciaX;
    }

    /**
     * Getter pre atribut poziciaY.
     *
     * @return vrati poziciuY ako cele cislo(integer).
     */
    public int getPoziciaY() {
        return this.poziciaY;
    }

}
