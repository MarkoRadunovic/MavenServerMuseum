package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;


/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja pretrazuje eksponate iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOSearchEksponati extends AbstractSO {

	/**
	 * lista eksponata iz sistema kao List
	 */
    private List<Eksponat> list;

    /**
     * Vraca listu eksponata u sistemu
     * @return lista eksponata kao List
     */
    public List<Eksponat> getList() {
        return list;
    }

    
    /**
	 * Metoda za validaciju pretrazivanja eksponata.
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
     * Pretrazuje eksponate iz sistema prema prosledjenom parametru
     * @param entity instanca klase Object koja predstavlja parametar pretrage
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase String</li>
	 * <li>Ukoliko se desi neka greska u konekciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Eksponat>) (List<?>) dBBroker.search(new Eksponat(), (String) entity);
    }

}
