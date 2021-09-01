package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i sluzi za izmenu eksponata u sistemu.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOEditEksponat extends AbstractSO {

	/**
	 * Metoda za validaciju izmene eksponata. Proverava da li eksponat vec postoji u sistemu.
	 * @param entity instanca klase Object koja predstavlja zahtev koji se salje
	 * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Eksponat</li>
	 * <li>Ako prosledjeni objekat postoji u sistemu kao eksponat</li>
	 * </ul>
	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Eksponat)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Eksponat");
        }

        Eksponat eksponat = (Eksponat) entity;
        List<Eksponat> list = (List<Eksponat>) (List<?>) dBBroker.getAll(eksponat);
        for (Eksponat e : list) {
            if (e.getNazivEksponata() == (eksponat.getNazivEksponata()) && e.getEksponatId() != eksponat.getEksponatId()) {
                throw new Exception("Vec postoji eksponat sa unetim nazivom");
            }
        }
    }

    /**
     * Metoda koja azurira vrednosti eksponata u sistemu
     * @param entity instanca klase Object koja predstavlja zahtev koji se salje
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Eksponat</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        Eksponat eksponat = (Eksponat) entity;
        
        List<Eksponat> list = (List<Eksponat>) (List<?>) dBBroker.getAll(eksponat);
        for(Eksponat e: list)
            if(e.getEksponatId() == eksponat.getEksponatId() && e.getStalnaPostavka().getPostavkaId() != eksponat.getStalnaPostavka().getPostavkaId()){
                e.getStalnaPostavka().setBrojEksponata(e.getStalnaPostavka().getBrojEksponata()-1);
                dBBroker.update(e.getStalnaPostavka());
                eksponat.getStalnaPostavka().setBrojEksponata(eksponat.getStalnaPostavka().getBrojEksponata()+1);
                dBBroker.update(eksponat.getStalnaPostavka());
            }
        dBBroker.update((Eksponat) entity);
    }

}
