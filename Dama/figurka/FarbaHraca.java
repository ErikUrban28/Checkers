package figurka;

/**
 * Enum, ktory reprezentuje mozny typ hraca.
 * Obsahuje jeden privatny atribut farbaHraca, ktory bude reprezentovat nazov(vo forme String) danej instancie enumu.
 */
public enum FarbaHraca {
    CERVENY("cervenyHrac"),
    CIERNY("ciernyHrac");

    private String farbaHraca;
    /**
     * Konstruktor, ktory prijma ako parameter slovny retazec  inicializuje atribut farbaHraca.
     *
     * @param farbaHraca - Atributu farbaHraca je priradena hodnota parametra farbaHraca.
     */
    FarbaHraca(String farbaHraca) {
        this.farbaHraca = farbaHraca;
    }

    /**
     * Prekrytie metody toString, ktoru dedi kazdy objekt.
     *
     * @return vrati o akeho hraca sa jedna.
     */
    public String toString() {
        return "POZOR!! NA TAHU JE : " + this.farbaHraca;
    }

    /**
     * Tato metoda ohlasi vyhercu
     * @return vrati nam retazec s nazvom vyhercu.
     */
    public String ohlasVyhercu() {
        return this.farbaHraca;
    }
}
