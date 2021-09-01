package rs.ac.bg.student.marko.MavenServerMuseum.so.kustos;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja pretrazuje kustose iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOSearchKustosi extends AbstractSO {

	/**
	 * lista kustosa iz sistema kao List
	 */
    private List<Kustos> list;

    /**
     * Vraca listu kustosa iz sistema
     * @return lista kustosa kao List
     */
    public List<Kustos> getList() {
        return list;
    }

    /**
	 * Metoda za validaciju pretrazivanja kustosa.
	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
	 * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase String</li>
	 * </ul>
	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof String)) {
            throw new Exception("Prosledjeni objekat nije instanca klase String");
        }
    }


    /**
     * Pretrazuje kustose iz sistema prema prosledjenom parametru
     * @param entity instanca klase Object koja predstavlja parametar pretrage
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase String</li>
	 * <li>Ukoliko se desi neka greska u konekciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Kustos>) (List<?>) dBBroker.search(new Kustos(), (String) entity);
    }

}