package ba.unsa.etf.rpr;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Dogadjaj {
    private String naziv;
    private LocalDateTime pocetak,kraj;

    public Dogadjaj(String naziv, LocalDateTime pocetak, LocalDateTime kraj) throws NeispravanFormatDogadjaja {
        if(pocetak.isAfter(kraj))throw new NeispravanFormatDogadjaja("Neispravan format pocetka i kraja dogadjaja");
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public Dogadjaj() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalDateTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalDateTime pocetak) throws NeispravanFormatDogadjaja {
        if(pocetak.isAfter(this.kraj))throw new NeispravanFormatDogadjaja("Neispravan format pocetka i kraja dogadjaja");
        this.pocetak = pocetak;
    }

    public LocalDateTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalDateTime kraj) throws NeispravanFormatDogadjaja {
        if(this.pocetak.isAfter(kraj))throw new NeispravanFormatDogadjaja("Neispravan format pocetka i kraja dogadjaja");
        this.kraj = kraj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dogadjaj dogadjaj = (Dogadjaj) o;
        return Objects.equals(naziv, dogadjaj.naziv) &&
                Objects.equals(pocetak, dogadjaj.pocetak) &&
                Objects.equals(kraj, dogadjaj.kraj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, pocetak, kraj);
    }
    public abstract String toString();

    public abstract int compareTo(Dogadjaj o);
}
