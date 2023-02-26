package sachovnica;
import figurka.*;
import java.util.ArrayList;
/**
 * Tato trieda reprezentuje sachovnicu.
 * Deklarujem tu dva ArrayListy pre figurky a  pole poli, ktore bude reprezentovat sachovnicu a finalne atributy, ktore
 * reprezentuju vlastnosti sachovnice.
 */

public class Sachovnica {
    private Policko[][] policka;
    private static final int POCET_RIADKOV = 10;
    private static final int POCET_STLPCOV = 10;
    private static final int VELKOST_POLICKA = 58;
    private static final int LAVY_HORNY_ROH_X = 0;
    private static final int LAVY_HORNY_ROH_Y = 0;
    private ArrayList<Figurka> cierneFigurky;
    private ArrayList<Figurka> cerveneFigurky;

    /**
     * Konstruktor sachovnice, tu inicializujem ArrayListy, pole poli a volam metodu, ktora mi nastavy a vykresli
     * sachovnicu a jej zakladny stav.
     */
    public Sachovnica() {
        this.policka = new Policko[this.POCET_STLPCOV][this.POCET_RIADKOV];
        this.cierneFigurky = new ArrayList<>();
        this.cerveneFigurky = new ArrayList<>();
        this.vytvorenieZakladnehoStavu();
    }


    /**
     * Vytvorenie policok na zadanych suradniciach.
     * Vsetky policka budu cierne.
     * Vykreslovanie / suradnice zacinaju v lavom hornom rohu!
     *
     * Dalej sa v metode kontroluje, ci je sucet indexu riadka a indexu stlpca parne cislo, ak ano, policko zmeni svoju farbu
     * na cervenu, ak nie (co znamena ze policko ma byt cierne) a index riadka je mensi ako 4 alebo vacsi ako 5, vykresli sa na toto cierne
     * policko Dama.figurka.
     */
    private void vytvorenieZakladnehoStavu() {
        for (int indexRiadka = 0; indexRiadka < POCET_RIADKOV; indexRiadka++) {
            for (int indexStlpca = 0; indexStlpca < POCET_STLPCOV; indexStlpca++) {
                int poziciaX = LAVY_HORNY_ROH_X + this.VELKOST_POLICKA * indexStlpca;
                int poziciaY = LAVY_HORNY_ROH_Y + this.VELKOST_POLICKA * indexRiadka;
                this.policka[indexRiadka][indexStlpca] = new Policko(new Suradnice(poziciaX, poziciaY), this.VELKOST_POLICKA);

                if ((indexRiadka + indexStlpca) % 2 == 0) {
                    this.policka[indexRiadka][indexStlpca].zmenFarbuPolickaNaCervenu();
                } else if (indexRiadka < 4) {
                    this.vytvorenieFigurky(poziciaX, poziciaY, TypFigurky.CIERNA, this.policka[indexRiadka][indexStlpca], FarbaHraca.CIERNY);
                    this.policka[indexRiadka][indexStlpca].setJeObsadeny(true);
                } else if (indexRiadka > 5) {
                    this.vytvorenieFigurky(poziciaX, poziciaY, TypFigurky.CERVENA, this.policka[indexRiadka][indexStlpca], FarbaHraca.CERVENY);
                    this.policka[indexRiadka][indexStlpca].setJeObsadeny(true);
                }
            }
        }
    }

    /**
     * Metoda, ktora vytvara nove figurky a pridava ich do prislusneho ArrayListu.
     *
     * @param poziciaX predstavuje X os v priestore.
     * @param poziciaY predstavuje Y os v priestore.
     * @param typ      predstavuje o aky typ figurky sa jedna.
     * @param policko  predstavuje policko na ktorom sa nachadza Dama.figurka.
     * @param hrac     predstavuje, aky hrac vlastni tuto figurku.
     */
    private void vytvorenieFigurky(int poziciaX, int poziciaY, TypFigurky typ, Policko policko, FarbaHraca hrac) {
        if ((hrac == FarbaHraca.CIERNY)) {
            this.cierneFigurky.add(new Figurka(new Suradnice(poziciaX, poziciaY), this.VELKOST_POLICKA, typ, policko, hrac));
            this.cierneFigurky.get(this.cierneFigurky.size() - 1).zobrazFigurku();
        } else {
            this.cerveneFigurky.add(new Figurka(new Suradnice(poziciaX, poziciaY), this.VELKOST_POLICKA, typ, policko, hrac));
            this.cerveneFigurky.get(this.cerveneFigurky.size() - 1).zobrazFigurku();
        }
    }

    /**
     * Metoda, ktora vrati figurku, ktora splna zadane parametre.
     *
     * @param farba   slovny retazec, ktory hovori, aku farbu ma Dama.figurka
     * @param policko reprezentuje policko, na ktorom Dama.figurka ma stat
     * @return vrat figurku, ktora je farby parametra metody a stoji na policku parametra metody.
     */
    public Figurka prehladajPoleFigurok(String farba, Policko policko) {
        if (farba.equals("cervena")) {
            for (int i = 0; i < this.cerveneFigurky.size(); i++) {
                if (this.cerveneFigurky.get(i).getPolicko() == policko) {
                    return this.cerveneFigurky.get(i);
                }
            }
        } else {
            for (int i = 0; i < this.cierneFigurky.size(); i++) {
                if (this.cierneFigurky.get(i).getPolicko() == policko) {
                    return this.cierneFigurky.get(i);
                }
            }
        }

        return null;
    }
    /**
     * Metoda, ktora vrati figurku, ktora splna zadane parametre.
     *
     * @param policko reprezentuje policko, na ktorom Dama.figurka ma stat
     * @return vrat figurku, ktora stoji na policku parametra metody.
     */
    public Figurka prehladajPoleFigurok(Policko policko) {

        for (int i = 0; i < this.cerveneFigurky.size(); i++) {
            if (this.cerveneFigurky.get(i).getPolicko() == policko) {
                return this.cerveneFigurky.get(i);
            }
        }

        for (int i = 0; i < this.cierneFigurky.size(); i++) {
            if (this.cierneFigurky.get(i).getPolicko() == policko) {
                return this.cierneFigurky.get(i);

            }
        }
        return null;
    }


    /**
     * Tato metoda sluzi na zistenie policka na danych suradniciach v pixeloch.
     *
     * @param x reprezentuje os x v priestore.
     * @param y reprezentuje os z v priestore.
     * @return vrati platne policko v pripade, ze je suradnice policka su platne.
     */
    public Policko dajPolickoNaSuradniciach(int x, int y) {
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.obsahujeSuradnice(x, y)) {
                    return policko;
                }
            }
        }
        return null;
    }

    /**
     * Getter, vdaka ktoremu sa ziska velkost policka.
     * @return vrati velkost policka.
     */
    public int getVelkostPolicka() {
        return this.VELKOST_POLICKA;
    }
    /**
     * Tato metoda sluzi na zistenie policka na danych suradniciach na sachovnici.
     *
     * @param x reprezentuje os x v priestore.
     * @param y reprezentuje os z v priestore.
     * @return vrati platne policko v pripade, ze je suradnice policka su platne.
     */
    public Policko dajPolicko(int x, int y) {
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.obsahujeSuradniceSachovnica(x, y)) {
                    return policko;
                }
            }
        }
        return null;
    }

    /**
     * Getter pre atribut, ktory obsahuje cierne figurky.
     * @return vrati ArrayList ciernych figurok.
     */
    public ArrayList<Figurka> getCierneFigurky() {
        return this.cierneFigurky;
    }
    /**
     * Getter pre atribut, ktory obsahuje cervene figurky.
     * @return vrati ArrayList cervenych figurok.
     */
    public ArrayList<Figurka> getCerveneFigurky() {
        return this.cerveneFigurky;
    }

    /**
     * Tato metoda zmaze figurku z konkretneho ArrayListu, na danom policku.
     * @param zoznamFiguriek ArrayList, z ktoreho chceme mazat figurky.
     * @param policko policko, na ktorom sa nachadza Dama.figurka, ktoru chceme mazat.
     */
    public void zmazFigurku(ArrayList zoznamFiguriek, Policko policko) {
        Figurka figurkaNaPolicku = this.prehladajPoleFigurok(policko);
        figurkaNaPolicku.zmazFigurku();
        zoznamFiguriek.remove(figurkaNaPolicku);
    }

    /**
     * Tato metoda zmaze figurku z konkretneho ArrayListu,
     * @param zoznamFiguriek ArrayList, z ktoreho chceme mazat figurky.
     * @param figurka Dama.figurka, ktoru chceme zmazat.
     */
    public void zmazFigurku(ArrayList zoznamFiguriek, Figurka figurka) {

        figurka.zmazFigurku();
        zoznamFiguriek.remove(figurka);
    }

    /**
     * Tato metoda kontroluje volne a obsadene pozicie v okoli daneho policka, ktore je parametrom metody.
     * @param policko policko, ktoreho okolie prehladavam
     * @return vrati mi false, ak policko nema vo svojom okoli nepriatela a zaroven policko na skocenie figurky je obsadene, alebo ak na policku nie je Dama.figurka. Ak su tieto podmienky splenen, vrati true.
     */
    public boolean jeObsadeneVOkoliNepriatelom(Policko policko) {
        Figurka figurka = this.prehladajPoleFigurok(policko);
        if (figurka == null) {
            return false;
        }
        if (policko.poziciaX() < 8 && policko.poziciaY() < 8) {
            Policko doleVPravo = this.policka[policko.poziciaY() + 1][policko.poziciaX() + 1];
            Policko doleVPravo1 = this.policka[policko.poziciaY() + 2][policko.poziciaX() + 2];
            if (!doleVPravo1.getObsadenost() && doleVPravo.getObsadenost()) {
                Figurka dalsiaFigurka = this.prehladajPoleFigurok(doleVPravo);
                if (dalsiaFigurka.getHrac() != figurka.getHrac()) {
                    return true;
                }
            }
        }
        if (policko.poziciaX() > 1 && policko.poziciaY() < 8) {
            Policko doleVLavo = this.policka[policko.poziciaY() + 1][policko.poziciaX() - 1];
            Policko doleVLavo1 = this.policka[policko.poziciaY() + 2][policko.poziciaX() - 2];
            if (!doleVLavo1.getObsadenost() && doleVLavo.getObsadenost()) {
                Figurka dalsiaFigurka = this.prehladajPoleFigurok(doleVLavo);
                if (dalsiaFigurka.getHrac() != figurka.getHrac()) {
                    return true;
                }
            }
        }
        if (policko.poziciaX() < 8 && policko.poziciaY() > 1) {
            Policko horeVPravo = this.policka[policko.poziciaY() - 1][policko.poziciaX() + 1];
            Policko horeVPravo1 = this.policka[policko.poziciaY() - 2][policko.poziciaX() + 2];
            if (!horeVPravo1.getObsadenost() && horeVPravo.getObsadenost()) {
                Figurka dalsiaFigurka = this.prehladajPoleFigurok(horeVPravo);
                if (dalsiaFigurka.getHrac() != figurka.getHrac()) {
                    return true;
                }
            }
        }
        if (policko.poziciaX() > 1 && policko.poziciaY() > 1) {
            Policko horeVLavo = this.policka[policko.poziciaY() - 1][policko.poziciaX() - 1];
            Policko horeVLavo1 = this.policka[policko.poziciaY() - 2][policko.poziciaX() - 2];
            if (!horeVLavo1.getObsadenost() && horeVLavo.getObsadenost()) {
                Figurka dalsiaFigurka = this.prehladajPoleFigurok(horeVLavo);
                if (dalsiaFigurka.getHrac() != figurka.getHrac()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Tato metoda prehladava oba ArrayListy a zistuje ci sa obycajna Dama.figurka nenachadza na poslednom riadku sachovnice protivnikovej farby. Ak ano, zmaze ju a vytvori novu figurku dama .
     */
    public void zmenNaDamu() {
        for (int i = 0; i < this.cerveneFigurky.size(); i++) {
            if (this.cerveneFigurky.get(i).getTypFigurky() == TypFigurky.CERVENA && this.cerveneFigurky.get(i).getPolicko().poziciaY() == 0) {
                Policko polickoFigurky = this.cerveneFigurky.get(i).getPolicko();
                this.zmazFigurku(this.cerveneFigurky, this.cerveneFigurky.get(i));
                this.vytvorenieFigurky(polickoFigurky.getPoziciaX(), polickoFigurky.getPoziciaY(), TypFigurky.CERVENA_DAMA, polickoFigurky, FarbaHraca.CERVENY);
            }
        }
        for (int i = 0; i < this.cierneFigurky.size(); i++) {
            if (this.cierneFigurky.get(i).getTypFigurky() == TypFigurky.CIERNA && this.cierneFigurky.get(i).getPolicko().poziciaY() == 9) {
                Policko polickoFigurky = this.cierneFigurky.get(i).getPolicko();
                this.zmazFigurku(this.cierneFigurky, this.cierneFigurky.get(i));
                this.vytvorenieFigurky(polickoFigurky.getPoziciaX(), polickoFigurky.getPoziciaY(), TypFigurky.CIERNA_DAMA, polickoFigurky, FarbaHraca.CIERNY);
            }
        }
    }




}



