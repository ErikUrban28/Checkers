package sachovnica;
import figurka.*;

/**
 * Trieda policko reprezentuje policko v sachovnici.
 */
public class Policko {
    /**
     * Atribut suradnice reprezentuje suradnice policka a jeho velkost;
     */
    private Suradnice suradnice;
    /**
     * atributy obldznik tvar a formu policka.
     */
    private Obdlznik obdlznik;
    /**
     * atribut jeObsadeny informuje, ci je policko obsadene figurkov alebo nie.
     */
    private boolean jeObsadeny;
    /**
     * Atribut velkost reprezentuje velkost policka.
     */
    private int velkost;

    /**
     * Konstruktor inicializuje policka, jeho zakladny tvar, polohu, typ, farbu (cierna) a zaroven ho zobrazi na platne.
     *
     * @param suradnice inicializuje atribut suradnice podla tohto parametra, konkretne x a y poziciu policka.
     * @param velkost inicializuje atribut velkost podla tohto parametra.
     */
    public Policko(Suradnice suradnice, int velkost) {
        this.suradnice = suradnice;
        this.velkost = velkost;
        this.obdlznik = new Obdlznik();
        this.obdlznik.zmenPolohu(this.suradnice.getPoziciaX(), this.suradnice.getPoziciaY());
        this.obdlznik.zmenStrany(this.velkost, this.velkost);
        this.obdlznik.zmenFarbu("black");
        this.obdlznik.zobraz();
    }

    /**
     * Tato metoda meni farbu policka na cervenu
     */
    public void zmenFarbuPolickaNaCervenu() {
        this.obdlznik.zmenFarbu("red");
    }

    /**
     * Setter, nastavuje obsadenost policka
     *
     * @param obsadeny nastavi obsadenost policka podla daneho parametra.
     */
    public void setJeObsadeny(boolean obsadeny) {
        this.jeObsadeny = obsadeny;
    }

    /**
     * Getter, na zistenie obsadenosti policka.
     *
     * @return vrati, ci je policko obsadene.
     */
    public boolean getObsadenost() {
        return this.jeObsadeny;
    }

    /**
     * Getter, na zistenie pozicie x policka.
     *
     * @return akutalnu polohu policka na osi x v pixeloch.
     */
    public int getPoziciaX() {
        return this.suradnice.getPoziciaX();
    }

    /**
     * Getter, na zistenie pozicie y policka.
     *
     * @return akutalnu polohu policka na osi y v pixeloch.
     */
    public int getPoziciaY() {
        return this.suradnice.getPoziciaY();
    }

    /**
     * Getter, na zistenie pozicie x policka.
     * @return vrati nam x poziciu policka, vydelenu jeho velkostou.
     */
    public int poziciaX() {
        return this.suradnice.getPoziciaX() / this.velkost;
    }
    /**
     * Getter, na zistenie pozicie y policka.
     * @return vrati nam y poziciu policka, vydelenu jeho velkostou.
     */
    public int poziciaY() {
        return this.suradnice.getPoziciaY() / this.velkost;
    }


    /**
     * Metoda overuje ci su dane suradnice platne pre dane policko.
     *
     * @param x parameter, ktory reprezentuje x suradnice
     * @param y parameter, ktory reprezentuje y suradnice
     * @return vrati, ci su dane suradnice platne alebo nie.
     */
    public boolean obsahujeSuradnice(int x, int y) {
        return (x < this.getPoziciaX() + this.velkost && x >= this.getPoziciaX() && y < this.getPoziciaY() + this.velkost && y >= this.getPoziciaY());
    }
    /**
     * Metoda overuje ci su dane suradnice platne pre dane policko.
     *
     * @param x parameter, ktory reprezentuje x suradnice
     * @param y parameter, ktory reprezentuje y suradnice
     * @return vrati, ci su dane suradnice platne alebo nie.
     */
    public boolean obsahujeSuradniceSachovnica(int x, int y) {
        return x == this.poziciaX() && y == this.poziciaY();
    }
}
