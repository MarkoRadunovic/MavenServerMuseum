package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja cuva stalnu postavku u sistemu.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOSaveStalnaPostavka extends AbstractSO {

	/**
	 * Metoda za validaciju cuvanja stalne postavke. Proverava da li stalna postavka kao takva postoji u sistemu.
	 * @param entity instanca klase Object koja predstavlja zahtev koji se salje
	 * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase StalnaPostavka</li>
	 * <li>Ako prosledjeni objekat vec postoji kao takav u sistemu poredeci naziv</li>
	 * 
	 * </ul>
	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof StalnaPostavka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StalnaPostavka");
        }

        StalnaPostavka stalnaPostavka = (StalnaPostavka) entity;
        List<StalnaPostavka> list = (List<StalnaPostavka>) (List<?>) dBBroker.getAll(stalnaPostavka);
        for (StalnaPostavka s : list) {
            if (s.getNazivPostavke().equals(stalnaPostavka.getNazivPostavke())) {
                throw new Exception("Vec postoji takva postavka");
            }
        }
    }

    /**
     * Metoda koja cuva vrednosti stalne postavke
     * @param entity instanca klase Object koja predstavlja zahtev koji se salje
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase StalnaPostavka</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.insert((StalnaPostavka) entity);
    }

}
