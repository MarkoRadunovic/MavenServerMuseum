package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja cuva eksponat u sistemu.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOSaveEksponat extends AbstractSO {

	/**
	 * Metoda za validaciju cuvanja eksponata. Proverava da li eksponat vec postoji u sistemu.
	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
	 * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Eksponat</li>
	 * <li>Ako se dogodi neka greska pri komunikaciji sa bazom</li>
	 * <li>Ako prosledjeni objekat vec postoji u sistemu kao eksponat</li>
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
            if (e.getNazivEksponata().equals(eksponat.getNazivEksponata())) {
                throw new Exception("Vec postoji takav eksponat");
            }
        }
    }

    /**
     * Cuva eksponat unutar sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Eksponat</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        Eksponat eksponat = (Eksponat) entity;
        dBBroker.insert(eksponat);
        StalnaPostavka stalnaPostavka = eksponat.getStalnaPostavka();
        stalnaPostavka.setBrojEksponata(stalnaPostavka.getBrojEksponata()+1);
        dBBroker.update(stalnaPostavka);
        
    }

}

