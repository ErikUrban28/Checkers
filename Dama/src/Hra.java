package src;

import figurka.*;
import sachovnica.*;
/**
 * Tato trieda reprezentuje logiku hry.
 * Deklarujem atributy, ktore reprezentuju manazera hry, farbu hraca, ktory je na tahu, figurky, pozicie x  a y, boolean, ktory zistuje, ci sa hra ma vypnut a  sachovnicu
 */
public class Hra {
    private Sachovnica sachovnica;
    private Manazer manazer;
    private FarbaHraca naTahu;
    private Figurka nastavenaFigurka;
    private int stareX;
    private int stareY;
    private Figurka poslednaSkakajucaFigurka;
    private Figurka maSkakatFigurka;
    private boolean vypniSa = false;
    /**
     * Konstruktor hry ktory inicializuje atributy
     */
    public Hra() {
        this.sachovnica = new Sachovnica();
        this.manazer = new Manazer();
    }

    /**
     * Tato metoda nastavi, aby program reagoval na input mysi a nastavi aby bol cerveny hrac na rade.
     */
    public void spusti() {
        this.manazer.spravujObjekt(this);
        this.naTahu = FarbaHraca.CERVENY;
        System.out.println(FarbaHraca.CERVENY);
    }

    /**
     * Tato metoda umozuje presun figuriek po sachovnici, zaroven sa stara aj o striedanie hracov, kontrolovanie podmienok pri skakani a posune, vypise vyhercov, zmeny figuriek a ukoncenie hry.
     * Pouzivaju ju manazer, reaguje na mys.
     *
     * @param x reprezentuje x suradnice v priestore.
     * @param y reprezentuje y suradnice v priestore
     */
    public void vyberSuradnice(int x, int y) {
        //nastavy policko podla zadanych suradnic
        Policko policko = this.sachovnica.dajPolickoNaSuradniciach(x, y);
        //skontroluje ci nejaka Dama.figurka sa nema zmenit na damu;
        this.sachovnica.zmenNaDamu();
        if (this.nastavenaFigurka == null) {
            if (this.naTahu == FarbaHraca.CERVENY) {
                //ak nastavenaFigurka ukazuje na null, tak zacne ukazovat na figurku na kliknutom policku
                this.nastavenaFigurka = this.sachovnica.prehladajPoleFigurok("cervena", policko);
                this.stareX = policko.poziciaX();
                this.stareY = policko.poziciaY();
                this.sachovnica.zmenNaDamu();

            } else {
                this.nastavenaFigurka = this.sachovnica.prehladajPoleFigurok("cierna", policko);
                this.stareX = policko.poziciaX();
                this.stareY = policko.poziciaY();
                this.sachovnica.zmenNaDamu();
            }
            //ked uz nastavena Dama.figurka nie je nulova, zacni kontrolovat podmienky skakania a v pripade porusenia podmienok mazania, .
            if (this.nastavenaFigurka != null) {
                this.sachovnica.zmenNaDamu();
                if (this.naTahu == FarbaHraca.CERVENY) {
                    for (Figurka figurka : this.sachovnica.getCerveneFigurky()) {
                        if (figurka != this.nastavenaFigurka && this.sachovnica.jeObsadeneVOkoliNepriatelom(figurka.getPolicko())) {
                            this.maSkakatFigurka = figurka;
                        }
                    }
                    for (Figurka druhaFigurka : this.sachovnica.getCerveneFigurky()) {
                        if (druhaFigurka != this.maSkakatFigurka && druhaFigurka != this.nastavenaFigurka && this.sachovnica.jeObsadeneVOkoliNepriatelom(druhaFigurka.getPolicko())) {
                            this.sachovnica.zmazFigurku(this.sachovnica.getCerveneFigurky(), druhaFigurka);
                            this.sachovnica.zmazFigurku(this.sachovnica.getCerveneFigurky(), this.maSkakatFigurka);
                            this.maSkakatFigurka = null;
                        }
                    }
                    if (this.maSkakatFigurka != null && !this.sachovnica.jeObsadeneVOkoliNepriatelom(this.nastavenaFigurka.getPolicko())) {

                        this.sachovnica.zmazFigurku(this.sachovnica.getCerveneFigurky(), this.maSkakatFigurka);
                        this.maSkakatFigurka = null;
                    }
                    this.maSkakatFigurka = null;
                } else {
                    for (Figurka figurka : this.sachovnica.getCierneFigurky()) {
                        if (figurka != this.nastavenaFigurka && this.sachovnica.jeObsadeneVOkoliNepriatelom(figurka.getPolicko())) {
                            this.maSkakatFigurka = figurka;
                        }
                    }
                    for (Figurka druhaFigurka : this.sachovnica.getCierneFigurky()) {
                        if (druhaFigurka != this.maSkakatFigurka && druhaFigurka != this.nastavenaFigurka && this.sachovnica.jeObsadeneVOkoliNepriatelom(druhaFigurka.getPolicko())) {
                            this.sachovnica.zmazFigurku(this.sachovnica.getCierneFigurky(), druhaFigurka);
                            this.sachovnica.zmazFigurku(this.sachovnica.getCierneFigurky(), this.maSkakatFigurka);
                            this.maSkakatFigurka = null;
                        }
                    }
                    if (this.maSkakatFigurka != null && !this.sachovnica.jeObsadeneVOkoliNepriatelom(this.nastavenaFigurka.getPolicko())) {

                        this.sachovnica.zmazFigurku(this.sachovnica.getCierneFigurky(), this.maSkakatFigurka);
                        this.maSkakatFigurka = null;
                    }
                }
                this.maSkakatFigurka = null;
            }
            // Ak nejaka Dama.figurka skakala, overuj tieto podmienky.
            if (this.poslednaSkakajucaFigurka != null) {
                if (this.sachovnica.jeObsadeneVOkoliNepriatelom(this.poslednaSkakajucaFigurka.getPolicko())) {
                    Figurka novaNastavenaFigurka = null;
                    if (this.poslednaSkakajucaFigurka.getHrac() == FarbaHraca.CERVENY) {
                        novaNastavenaFigurka = this.sachovnica.prehladajPoleFigurok("cervena", policko);
                        this.stareX = policko.poziciaX();
                        this.stareY = policko.poziciaY();
                    } else {
                        novaNastavenaFigurka = this.sachovnica.prehladajPoleFigurok("cierna", policko);
                        this.stareX = policko.poziciaX();
                        this.stareY = policko.poziciaY();
                    }
                    if (novaNastavenaFigurka != null) {
                        if (novaNastavenaFigurka.getHrac() == FarbaHraca.CERVENY) {

                            this.naTahu = FarbaHraca.CERVENY;
                        } else {
                            this.naTahu = FarbaHraca.CIERNY;
                        }
                        this.nastavenaFigurka = novaNastavenaFigurka;
                    } else {
                        this.poslednaSkakajucaFigurka = null;
                    }

                    if (this.nastavenaFigurka != null && this.poslednaSkakajucaFigurka != this.nastavenaFigurka) {
                        if (this.poslednaSkakajucaFigurka != null) {
                            if (this.poslednaSkakajucaFigurka.getHrac() == FarbaHraca.CERVENY) {
                                this.sachovnica.zmazFigurku(this.sachovnica.getCerveneFigurky(), this.poslednaSkakajucaFigurka);
                                this.naTahu = FarbaHraca.CERVENY;
                            } else {
                                this.sachovnica.zmazFigurku(this.sachovnica.getCierneFigurky(), this.poslednaSkakajucaFigurka);
                                this.naTahu = FarbaHraca.CIERNY;
                            }
                        }
                    }

                }
            }

        } else {
            //tato vetva sa vykonava, ak mame nakliknutu nejaku figurku. Vykonava pohy a skakanie
            if (!policko.getObsadenost()) {
                int dx = policko.poziciaX() - stareX;
                int dy = policko.poziciaY() - stareY;
                if (!this.sachovnica.jeObsadeneVOkoliNepriatelom(this.nastavenaFigurka.getPolicko())) {
                    if (this.naTahu == FarbaHraca.CERVENY && dy == -1 && Math.abs(dx) == 1) {
                        this.nastavenaFigurka.getPolicko().setJeObsadeny(false);
                        this.nastavenaFigurka.prekresliFigurku(policko);
                        policko.setJeObsadeny(true);
                        this.nastavenaFigurka = null;
                        this.naTahu = FarbaHraca.CIERNY;
                        System.out.println(this.naTahu);
                        this.sachovnica.zmenNaDamu();

                    } else if (this.naTahu == FarbaHraca.CIERNY && dy == 1 && Math.abs(dx) == 1) {
                        this.nastavenaFigurka.getPolicko().setJeObsadeny(false);
                        this.nastavenaFigurka.prekresliFigurku(policko);
                        policko.setJeObsadeny(true);
                        this.nastavenaFigurka = null;
                        this.naTahu = FarbaHraca.CERVENY;
                        System.out.println(this.naTahu);
                        this.sachovnica.zmenNaDamu();
                    }
                }
                if (this.nastavenaFigurka != null) {
                    if (this.nastavenaFigurka.getTypFigurky() == TypFigurky.CERVENA || this.nastavenaFigurka.getTypFigurky() == TypFigurky.CIERNA) {
                        Policko obsadenePolicko = this.sachovnica.dajPolicko((policko.poziciaX() + stareX) / 2, (policko.poziciaY() + stareY) / 2);
                        this.skok(obsadenePolicko, dy, dx, policko);
                        this.sachovnica.zmenNaDamu();
                    }
                    if (this.nastavenaFigurka.getTypFigurky() == TypFigurky.CERVENA_DAMA || this.nastavenaFigurka.getTypFigurky() == TypFigurky.CIERNA_DAMA) {
                        this.skokDamou(dy, dx, policko);
                    }
                }
            }
            this.sachovnica.zmenNaDamu();

            this.nastavenaFigurka = null;

            this.ukonci();
            if (this.vypniSa) {
                this.manazer.prestanSpravovatObjekt(this);
                System.exit(0);
            }
        }


    }

    /**
     * Tato metoda ukonci hru a vyhlasi vitaza, tiez nastavy atribut vypniSa na true.
     */
    public void ukonci() {
        if (this.sachovnica.getCierneFigurky().isEmpty()) {
            System.out.println("Vyhral: " + FarbaHraca.CERVENY.ohlasVyhercu());
            this.manazer.prestanSpravovatObjekt(this);
            this.vypniSa = true;
        } else if (this.sachovnica.getCerveneFigurky().isEmpty()) {
            System.out.println("Vyhral: " + FarbaHraca.CIERNY.ohlasVyhercu());
            this.manazer.prestanSpravovatObjekt(this);
            this.vypniSa = true;
        }
    }

    /**
     * Metoda, ktora reprezentuje skok figurky dopredu / dozadu po diagonale.
     *
     * @param obsadenePolicko   je policko, na ktorom stoji nepriatelska Dama.figurka
     * @param dy                rozdiel medzi sucastnym polickom nakliknutej figurky a cielenym polickom osY
     * @param dx                rozdiel medzi sucastnym polickom nakliknutej figurky a cielenym polickom osX
     * @param polickoPoSkonceni je policko kde chcem skocit s figurku
     */
    public void skok(Policko obsadenePolicko, int dy, int dx, Policko polickoPoSkonceni) {
        if (obsadenePolicko.getObsadenost()) {

            if (this.naTahu == FarbaHraca.CERVENY && Math.abs(dy) == 2 && Math.abs(dx) == 2) {

                if (this.sachovnica.prehladajPoleFigurok(obsadenePolicko).getHrac() == FarbaHraca.CIERNY) {
                    this.poslednaSkakajucaFigurka = nastavenaFigurka;
                    this.nastavenaFigurka.getPolicko().setJeObsadeny(false);
                    this.nastavenaFigurka.prekresliFigurku(polickoPoSkonceni);
                    polickoPoSkonceni.setJeObsadeny(true);
                    this.sachovnica.zmazFigurku(this.sachovnica.getCierneFigurky(), obsadenePolicko);
                    this.naTahu = FarbaHraca.CIERNY;
                    System.out.println(this.naTahu);
                }
            } else if (this.naTahu == FarbaHraca.CIERNY && Math.abs(dy) == 2 && Math.abs(dx) == 2) {
                if (this.sachovnica.prehladajPoleFigurok(obsadenePolicko).getHrac() == FarbaHraca.CERVENY) {
                    this.poslednaSkakajucaFigurka = nastavenaFigurka;
                    this.nastavenaFigurka.getPolicko().setJeObsadeny(false);
                    this.nastavenaFigurka.prekresliFigurku(polickoPoSkonceni);
                    polickoPoSkonceni.setJeObsadeny(true);
                    this.sachovnica.zmazFigurku(this.sachovnica.getCerveneFigurky(), obsadenePolicko);
                    this.naTahu = FarbaHraca.CERVENY;
                    System.out.println(this.naTahu);
                }
            }
        }
    }
    /**
     * Metoda, ktora reprezentuje pohyb damy .
     *
     * @param dy                rozdiel medzi sucastnym polickom nakliknutej figurky a cielenym polickom osY
     * @param dx                rozdiel medzi sucastnym polickom nakliknutej figurky a cielenym polickom osX
     * @param polickoPoSkonceni je policko kde sa chceme pohnut s damou
     */
    public void skokDamou(int dy, int dx, Policko polickoPoSkonceni) {

        if (this.nastavenaFigurka.getTypFigurky() == TypFigurky.CERVENA_DAMA && Math.abs(dy) == Math.abs(dx)) {
            if (this.volnoDama(this.nastavenaFigurka.getPolicko(), polickoPoSkonceni)) {
                this.poslednaSkakajucaFigurka = nastavenaFigurka;
                this.nastavenaFigurka.getPolicko().setJeObsadeny(false);
                Policko pomocnePolicko = new Policko((new Suradnice(this.nastavenaFigurka.getPolicko().getPoziciaX(), this.nastavenaFigurka.getPolicko().getPoziciaY())), this.sachovnica.getVelkostPolicka());
                this.nastavenaFigurka.prekresliFigurku(polickoPoSkonceni);
                polickoPoSkonceni.setJeObsadeny(true);
                this.vymazVdiagonale(pomocnePolicko, polickoPoSkonceni);
                this.naTahu = FarbaHraca.CIERNY;
                System.out.println(this.naTahu);

            }
        } else if (this.nastavenaFigurka.getTypFigurky() == TypFigurky.CIERNA_DAMA && Math.abs(dy) == Math.abs(dx)) {
            if (this.volnoDama(this.nastavenaFigurka.getPolicko(), polickoPoSkonceni)) {
                this.poslednaSkakajucaFigurka = nastavenaFigurka;
                this.nastavenaFigurka.getPolicko().setJeObsadeny(false);
                Policko pomocnePolicko = new Policko((new Suradnice(this.nastavenaFigurka.getPolicko().getPoziciaX(), this.nastavenaFigurka.getPolicko().getPoziciaY())), this.sachovnica.getVelkostPolicka());
                this.nastavenaFigurka.prekresliFigurku(polickoPoSkonceni);
                polickoPoSkonceni.setJeObsadeny(true);
                this.vymazVdiagonale(pomocnePolicko, polickoPoSkonceni);
                this.naTahu = FarbaHraca.CERVENY;
                System.out.println(this.naTahu);
            }
        }
    }

    /**
     * Tato metoda kontroluje ci sa dama moze presunut na urcite policko.
     * @param zaciatocne reprezentuje policko, na ktorom je dama.
     * @param konecne reprezentuje policko, kde sa chce dama dostat.
     * @return nam vrati true,ak je pohyb damy mozny.
     */
    public boolean volnoDama(Policko zaciatocne, Policko konecne) {
        Figurka zaciatocnaFigurka = this.sachovnica.prehladajPoleFigurok(zaciatocne);

        int deltaX = konecne.poziciaX() - zaciatocne.poziciaX();
        int deltaY = konecne.poziciaY() - zaciatocne.poziciaY();
        if (deltaX == 0 && deltaY == 0) {
            return false;
        }
        int smerX = deltaX > 0 ? 1 : -1;
        int smerY = deltaY > 0 ? 1 : -1;
        boolean bolaFigurkaOponenta = false;
        for (int i = 1; i < Math.abs(deltaX); i++) {
            int x = zaciatocne.poziciaX() + smerX * i;
            int y = zaciatocne.poziciaY() + smerY * i;
            Policko policko = this.sachovnica.dajPolicko(x, y);
            if (policko.getObsadenost()) {
                Figurka figurka = this.sachovnica.prehladajPoleFigurok(policko);
                if (figurka.getHrac() == zaciatocnaFigurka.getHrac()) {
                    return false;
                } else {
                    if (bolaFigurkaOponenta) {
                        return false;
                    }
                    bolaFigurkaOponenta = true;
                }

            } else {
                bolaFigurkaOponenta = false;
            }


        }
        return true;
    }

    /**
     * Tato metoda vymaze nepriatelske figurky, ktorymi presla dama, pri svojom pohybe.
     * @param zaciatocne reprezentuje zaciatocne policko, s ktoreho sa dama pohla.
     * @param konecne reprezentuje policko, na ktore sa dama posunula.
     */
    public void vymazVdiagonale(Policko zaciatocne, Policko konecne) {
        int deltaX = konecne.poziciaX() - zaciatocne.poziciaX();
        int deltaY = konecne.poziciaY() - zaciatocne.poziciaY();
        int smerX = deltaX > 0 ? 1 : -1;
        int smerY = deltaY > 0 ? 1 : -1;

        for (int i = 1; i < Math.abs(deltaX); i++) {
            int x = zaciatocne.poziciaX() + smerX * i;
            int y = zaciatocne.poziciaY() + smerY * i;
            Policko policko = this.sachovnica.dajPolicko(x, y);
            if (policko.getObsadenost()) {
                Figurka figurka = this.sachovnica.prehladajPoleFigurok(policko);
                if (figurka.getHrac() == FarbaHraca.CERVENY) {
                    this.sachovnica.zmazFigurku(this.sachovnica.getCerveneFigurky(), figurka);
                } else {
                    this.sachovnica.zmazFigurku(this.sachovnica.getCierneFigurky(), figurka);
                }
            }
        }
    }
}


