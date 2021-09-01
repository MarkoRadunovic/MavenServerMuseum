package rs.ac.bg.student.marko.MavenServerMuseum.so.kustos;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja vraca sve kustose iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOGetAllKustosi extends AbstractSO {

	/**
	 * lista kustosa u sistemu kao List
	 */
    private List<Kustos> list;

    /**
     * Vraca listu kustosa
     * @return lista kustosa kao List
     */
    public List<Kustos> getList() {
        return list;
    }

    /**
  	 * Metoda za validaciju
  	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
  	 * @throws Exception <ul>
  	 * <li>Ako je prosledjeni objekat null</li>
  	 * <li>Ako prosledjeni objekat nije instanca klase Kustos</li>
  	 * </ul>
  	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Kustos)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kustos");
        }
    }

    /**
     * Vraca sve kustose iz sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Kustos</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Kustos>) (List<?>) dBBroker.getAll((Kustos) entity);
    }

}