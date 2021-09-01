package rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Izlozba;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja vraca sve izlozbe iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOGetAllIzlozbe extends AbstractSO {

	/**
	 * lista izlozbi kao List
	 */
    private List<Izlozba> list;

    /**
     * Vraca listu izlozbi iz sistema
     * @return lista izlozbi kao List
     */
    public List<Izlozba> getList() {
        return list;
    }

    /**
  	 * Metoda za validaciju
  	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
  	 * @throws Exception <ul>
  	 * <li>Ako je prosledjeni objekat null</li>
  	 * <li>Ako prosledjeni objekat nije instanca klase Izlozba</li>
  	 * </ul>
  	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Izlozba)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Izlozba");
        }
    }

    /**
     * Vraca sve izlozbe iz sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase Izlozba</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Izlozba>) (List<?>) dBBroker.getAll((Izlozba) entity);
    }

}
