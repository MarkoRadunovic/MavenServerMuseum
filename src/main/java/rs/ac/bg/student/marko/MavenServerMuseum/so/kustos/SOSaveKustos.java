package rs.ac.bg.student.marko.MavenServerMuseum.so.kustos;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja cuva kustosa u sistemu.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOSaveKustos extends AbstractSO {

	/**
	 * Metoda za validaciju cuvanja kustosa. Proverava da li kustos vec postoji u sistemu.
	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
	 * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Kustos</li>
	 * <li>Ako se dogodi neka greska pri komunikaciji sa bazom</li>
	 * <li>Ako prosledjeni objekat vec postoji u sistemu kao kustos</li>
	 * </ul>
	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Kustos)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kustos");
        }
        List<Kustos> list = (List<Kustos>)(List<?>)dBBroker.getAll((Kustos) entity);
        Kustos kustos = (Kustos) entity;
        for (Kustos k : list) {
            if(k.getIme().equals(kustos.getIme())&& k.getPrezime().equals(kustos.getPrezime()))
                throw new Exception("Vec postoji kustos sa tim imenom i prezimenom");
        }
    }

    /**
     * Cuva kustosa unutar sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Kustos</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.insert((Kustos) entity);
    }

}

