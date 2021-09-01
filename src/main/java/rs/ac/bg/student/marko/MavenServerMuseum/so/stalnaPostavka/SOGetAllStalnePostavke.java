package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja vraca sve stalne postavke iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOGetAllStalnePostavke extends AbstractSO {

	/**
	 * lista stalnih postavki kao List
	 */
    private List<StalnaPostavka> list;

    /**
     * Vraca listu stalnih postavki
     * @return lista stalnih postavki kao List
     */
    public List<StalnaPostavka> getList() {
        return list;
    }

    /**
  	 * Metoda za validaciju
  	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
  	 * @throws Exception <ul>
  	 * <li>Ako je prosledjeni objekat null</li>
  	 * <li>Ako prosledjeni objekat nije instanca klase StalnaPostavka</li>
  	 * </ul>
  	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof StalnaPostavka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StalnaPostavka");
        }
    }

    /**
     * Vraca sve stalne postavke iz sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase StalnaPostavka</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<StalnaPostavka>) (List<?>) dBBroker.getAll((StalnaPostavka) entity);
    }

}