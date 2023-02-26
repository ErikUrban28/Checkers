package figurka;
import sachovnica.*;
/**
 * Tato trieda reprezentuje figurku.
 */
public class Figurka {
    /**
     * Atribut typ reprezentuje typ figurky.
     * Atribut hrac reprezentuje akemu hracovi patri Dama.figurka .
     * Atribut policko reprezentuje policko, na ktorom je Dama.figurka vykreslena.
     * Atribut obrazok  pomaha nastavit graficky vzhlad figurky.
     * Atribut suradnice reprezentuje suradnice, kde sa nachadza Dama.figurka.
     * Atribut velkost reprezentuje aku velkost ma Dama.figurka.
     */
    private int velkost;
    private TypFigurky typ;
    private FarbaHraca hrac;
    private Policko policko;
    private Obrazok obrazok;
    private Suradnice suradnice;

    /**
     * Konstruktor, ktory inicializuje atributy podla danych parametrov
     *
     * @param suradnice inicialiuje atribut suradnice podla tohto parametra.
     * @param velkost   inicializuje atribut velkost podla tohto parametra.
     * @param typ       inicialiuje atribut typ podla tohto parametra.
     * @param policko   inicialiuje atribut policko podla tohto parametra.
     * @param hrac      inicialiuje atribut hrac podla tohto parametra.
     */
    public Figurka(Suradnice suradnice, int velkost, TypFigurky typ, Policko policko, FarbaHraca hrac) {
        this.suradnice = suradnice;
        this.velkost = velkost;
        this.typ = typ;
        this.policko = policko;
        this.hrac = hrac;
    }

    /**
     * Getter pre atribut typ
     *
     * @return vrati typ figurky , ktoru dana Dama.figurka reprezentuje
     */
    public TypFigurky getTypFigurky() {
        return this.typ;
    }

    /**
     * Getter pre atribut hrac
     *
     * @return vrati akemu hracovi dana Dama.figurka patri.
     */
    public FarbaHraca getHrac() {
        return this.hrac;
    }

    /**
     * Getter pre atribut policko
     *
     * @return vrati na akom policku stoji Dama.figurka
     */
    public Policko getPolicko() {
        return this.policko;
    }

    /**
     * Vytvori, zobrazi a upravy podobu figurky a umiestni ju na potrebne suradnice.
     */
    public void zobrazFigurku() {
        if (this.obrazok == null) {
            this.obrazok = new Obrazok(this.typ.getSubor());
        } else {
            this.obrazok.zmenObrazok(this.typ.getSubor());
        }
        this.obrazok.zmenVelkost(this.velkost, this.velkost);
        this.obrazok.zmenPolohu(this.suradnice.getPoziciaX() + (this.velkost / 2), this.suradnice.getPoziciaY() + (this.velkost / 2)); //Argumentom je StredX / StredY , nie horny lavy roh !!
        this.obrazok.zobraz();
    }

    /**
     * Nastavi figurku na dane policko, zaroven upravy jeho podobu na pozadovany tvar.
     *
     * @param policko prijma parameter, ktory reprezentuje policko, na ktore chceme figurku prekreslit.
     */
    public void prekresliFigurku(Policko policko) {
        this.suradnice = new Suradnice(policko.getPoziciaX(), policko.getPoziciaY());
        this.policko = policko;
        this.obrazok.skry();
        this.obrazok.zmenPolohu(this.suradnice.getPoziciaX() + (this.velkost / 2), this.suradnice.getPoziciaY() + (this.velkost / 2));
        this.obrazok.zobraz();
    }

    /**
     * Tato metoda zmaze figurku.
     */
    public void zmazFigurku() {
        if (this.obrazok == null ) {
            return;
        }
        this.obrazok.skry();
        this.obrazok = null;
        this.policko.setJeObsadeny(false);

    }
}
