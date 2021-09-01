package rs.ac.bg.student.marko.MavenServerMuseum.so.specijalnost;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Specijalnost;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja vraca sve specijalnosti iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOGetAllSpecijalnosti extends AbstractSO {

	/**
	 * lista specijalnosti kao List
	 */
    private List<Specijalnost> list;


    /**
  	 * Metoda za validaciju
  	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
  	 * @throws Exception <ul>
  	 * <li>Ako je prosledjeni objekat null</li>
  	 * <li>Ako prosledjeni objekat nije instanca klase Specijalnost</li>
  	 * </ul>
  	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Specijalnost)) {
            throw new Exception("Prosledjeni objekat nije klase Specijalnost");
        }
    }

    /**
     * Vraca sve specijalnosti iz sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Specijalnost</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Specijalnost>) (List<?>) dBBroker.getAll((Specijalnost) entity);
    }

    /**
     * Vraca listu specijalnosti
     * @return lista specijalnosti kao List
     */
    public List<Specijalnost> getList() {
        return list;
    }

}
