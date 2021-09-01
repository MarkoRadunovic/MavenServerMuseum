package rs.ac.bg.student.marko.MavenServerMuseum.so.ocena;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.OcenaEksponata;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja vraca sve ocene eksponata iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOGetAllOcene extends AbstractSO {

	/**
	 * lista ocena eksponata kao List
	 */
    private List<OcenaEksponata> list;

    /**
  	 * Metoda za validaciju
  	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
  	 * @throws Exception <ul>
  	 * <li>Ako je prosledjeni objekat null</li>
  	 * <li>Ako prosledjeni objekat nije instanca klase OcenaEksponata</li>
  	 * </ul>
  	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof OcenaEksponata)) {
            throw new Exception("Prosledjeni objekat nije klase OcenaEksponata");
        }
    }

    /**
     * Vraca sve ocene eksponata iz sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase OcenaEksponata</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<OcenaEksponata>) (List<?>) dBBroker.getAll((OcenaEksponata) entity);
    }

    /**
     * Vraca listu ocena eksponata
     * @return lista ocena kao List
     */
    public List<OcenaEksponata> getList() {
        return list;
    }

}
