package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja pretrazuje stalne postavke u sistemu.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOSearchStalnePostavke extends AbstractSO {

	/**
	 * lista stalnih postavki iz sistema kao List
	 */
    private List<StalnaPostavka> list;

    /**
     * Vraca listu stalnih postavki iz sistema kao List
     * @return
     */
    public List<StalnaPostavka> getList() {
        return list;
    }

    /**
   	 * Metoda za validaciju pretrazivanja stalnih postavki.
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
     * Pretrazuje izlzobe iz sistema prema prosledjenom parametru
     * @param entity instanca klase Object koja predstavlja parametar pretrage
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase String</li>
	 * <li>Ukoliko se desi neka greska u konekciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<StalnaPostavka>) (List<?>) dBBroker.search(new StalnaPostavka(), (String) entity);
    }

}