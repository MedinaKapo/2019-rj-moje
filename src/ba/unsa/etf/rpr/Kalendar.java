package ba.unsa.etf.rpr;

import com.sun.source.tree.Tree;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Kalendar implements ba.unsa.etf.rpr.Pretrazivanje {
    private ArrayList<Dogadjaj> listaDogadjaja=new ArrayList<>();

    @Override
    public String toString() {
        String vracam="";
        for(Dogadjaj d:listaDogadjaja){
            vracam+=d.toString();
            vracam+="\n";
        }
        return vracam;
    }

    public void zakaziDogadjaj(Dogadjaj dogadjaj) {
        listaDogadjaja.add(dogadjaj);
    }

    public ArrayList<Dogadjaj> dajKalendar() {
        return listaDogadjaja;
    }

    public void zakaziDogadjaje(List<Dogadjaj> dogadjaji) {
        for(Dogadjaj d:dogadjaji){
            listaDogadjaja.add(d);
        }
    }

    public void otkaziDogadjaj(Dogadjaj dogadjaj) {
        for(Dogadjaj d:listaDogadjaja){
            if(d.equals(dogadjaj)){
                listaDogadjaja.remove(d);
            }
        }
    }

    public void otkaziDogadjaje(List<Dogadjaj> dogadjaji) {
        for(Dogadjaj d:dogadjaji){
            if(listaDogadjaja.contains(d)){
                listaDogadjaja.remove(d);
            }
        }
    }

    public Map<LocalDate, List<Dogadjaj>> dajKalendarPoDanima() {
        Map<LocalDate, List<Dogadjaj>> mapa = new HashMap<>();
        for (Dogadjaj dogadjaj : listaDogadjaja){
            if(!mapa.containsKey(dogadjaj.getPocetak().toLocalDate())){
                mapa.put(dogadjaj.getPocetak().toLocalDate(), new ArrayList<>());
            }

            mapa.get(dogadjaj.getPocetak().toLocalDate()).add(dogadjaj);
        }

        return mapa;
    }

    public Dogadjaj dajSljedeciDogadjaj(LocalDateTime of) {
        List<Dogadjaj> lista=listaDogadjaja.stream().filter(dogadjaj -> dogadjaj.getPocetak().isAfter(of)).collect(Collectors.toList());
        if(lista.isEmpty())throw new IllegalArgumentException("Nemate događaja nakon navedenog datuma");
        return lista.get(0);

    }
    @Override
    public List<Dogadjaj> filtrirajPoKriteriju(Predicate<Dogadjaj> kriterij) {
        return listaDogadjaja.stream().filter(kriterij).collect(Collectors.toList());
    }

    @Override
    public List<Dogadjaj> dajDogadjajeZaDan(LocalDateTime dan) {
       // return listaDogadjaja.stream().filter(dogadjaj -> dogadjaj.getPocetak().isEqual(dan)).collect(Collectors.toList());
        ArrayList<Dogadjaj> vracam=new ArrayList<>();
        for(Dogadjaj d:listaDogadjaja){
            if(d.getPocetak().isEqual(dan))vracam.add(d);
        }
        return vracam;
    }

    @Override
    public List<Dogadjaj> dajSortiraneDogadjaje(BiFunction<Dogadjaj, Dogadjaj, Integer> kriterijSortiranja) {
        return listaDogadjaja.stream().sorted(kriterijSortiranja::apply)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Dogadjaj> dajSortiranePoPrioritetu() {
        TreeSet<Dogadjaj> vracam=new TreeSet<>();
       for(Dogadjaj d:listaDogadjaja){
           vracam.add(d);
       }
       return vracam;
    }

    @Override
    public boolean daLiSamSlobodan(LocalDateTime dan) {
        for(Dogadjaj d:listaDogadjaja){
            if(d.getPocetak().isEqual(dan) || d.getKraj().isEqual(dan))return false;
            else if(d.getPocetak().isBefore(dan) && d.getKraj().isAfter(dan))return false;

        }
        return true;
    }

    @Override
    public boolean daLiSamSlobodan(LocalDateTime pocetak, LocalDateTime kraj) {
        if(pocetak.isAfter(kraj)){
            throw new IllegalArgumentException("Neispravni podaci o početku i kraju");
        }
      return false;
    }
}
