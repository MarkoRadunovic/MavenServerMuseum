package rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba;

import java.util.Date;
import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Izlozba;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.OcenaEksponata;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i sluzi za izmenu izlozbe u sistemu.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOEditIzlozba extends AbstractSO {

	/**
	 * Metoda za validaciju izmene izlzobe.
	 * @param entity instanca klase Object koja predstavlja zahtev koji se salje
	 * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Izlozba</li>
	 * <li>Ako prosledjeni objekat vec postoji kao takav u sistemu poredeci naziv i id</li>
	 * <li>Ako prosledjeni objekat ima neodgovarajuci datum</li>
	 * 
	 * </ul>
	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Izlozba)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Izlozba");
        }

        Izlozba izlozba = (Izlozba) entity;
        List<Izlozba> list = (List<Izlozba>) (List<?>) dBBroker.getAll(izlozba);
        for (Izlozba i : list) {
            if (i.getNazivIzlozbe().equals(izlozba.getNazivIzlozbe()) && i.getIzlozbaId()!=izlozba.getIzlozbaId()) {
                throw new Exception("Vec postoji takva izlozba");
            }
            
            if((i.getIzlozbaId()!=izlozba.getIzlozbaId()) && (isDateBetween(i.getDatumPocetka(), i.getDatumZavrsetka(), izlozba.getDatumPocetka()) 
                    || isDateBetween(i.getDatumPocetka(), i.getDatumZavrsetka(), izlozba.getDatumZavrsetka())))
                throw new Exception("Datum izlozbe se preklapa sa datumom izlozbe: "+i.getNazivIzlozbe());
        }
    }

    /**
     * Metoda koja azurira vrednosti izlozbe i vrednosti ocena eksponata u sistemu
     * @param entity instanca klase Object koja predstavlja zahtev koji se salje
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Izlozba</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        Izlozba izlozba = (Izlozba) entity;
        List<Izlozba> list = (List<Izlozba>) (List<?>) dBBroker.getAll(izlozba);
        for(Izlozba i:list){
            if(izlozba.getIzlozbaId() == i.getIzlozbaId()){
                for(OcenaEksponata o:i.getList()){
                    if(!izlozba.getList().contains(o))
                        dBBroker.delete(o);
                }
                
                for(OcenaEksponata o: izlozba.getList()){
                    if(!i.getList().contains(o))
                        dBBroker.insert(o);
                    else {
                        OcenaEksponata oc = i.getList().get(i.getList().indexOf(o));
                        if(oc.getOcena()!=o.getOcena())
                            dBBroker.update(o);
                    }
                }
            }
        }
        dBBroker.update((Izlozba) entity);
    }

    /**
     * Metoda koja proverava da li se datum dateToCheck nalazi izmedju datuma od i datuma do i vraca true ako je tako.
     * @param dateFrom datum pocetka izlozbe kao Date
     * @param dateTo datum zavrsetka izlozbe kao Date
     * @param dateToCheck datum koji proveravamo kao Date
     * @return true ako je dateToCheck izmedju datuma dateFrom i datuma dateTo
     */
    private boolean isDateBetween(Date dateFrom,Date dateTo,Date dateToCheck){
        return dateFrom.equals(dateToCheck) || dateTo.equals(dateToCheck) || (dateToCheck.after(dateFrom) && dateToCheck.before(dateTo));
    }
}