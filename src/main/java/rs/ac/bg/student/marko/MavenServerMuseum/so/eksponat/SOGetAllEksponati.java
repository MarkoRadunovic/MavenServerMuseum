package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja vraca sve eksponate iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOGetAllEksponati extends AbstractSO {

	/**
	 * lista eksponata u sistemu kao List
	 */
    private List<Eksponat> list;

    /**
     * Vraca listu eksponata
     * @return lista eksponata kao List
     */
    public List<Eksponat> getList() {
        return list;
    }

    /**
	 * Metoda za validaciju
	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
	 * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Eksponat</li>
	 * </ul>
	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Eksponat)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Eksponat");
        }
    }

    /**
     * Vraca sve eksponate iz sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Eksponat</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Eksponat>) (List<?>) dBBroker.getAll((Eksponat) entity);
    }
}
