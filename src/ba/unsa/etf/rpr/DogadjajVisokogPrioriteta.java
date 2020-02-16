package ba.unsa.etf.rpr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DogadjajVisokogPrioriteta extends Dogadjaj implements Comparable<Dogadjaj>{
    public DogadjajVisokogPrioriteta(String s, LocalDateTime of, LocalDateTime of1) throws NeispravanFormatDogadjaja {
        super(s,of,of1);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu (kk:mm)");
        String formatiraniPocetak = getPocetak().format(formatter);
        String formatiraniKraj = getKraj().format(formatter);

        return getNaziv() + " (visok prioritet) - početak: " +
                formatiraniPocetak + ", kraj: " + formatiraniKraj;
    }
    @Override
    public int compareTo(Dogadjaj o) {
        if((o instanceof DogadjajNiskogPrioriteta) || (o instanceof DogadjajSrednjegPrioriteta)){
            return 1;
        }

        return getNaziv().compareTo(o.getNaziv());
    }
}
