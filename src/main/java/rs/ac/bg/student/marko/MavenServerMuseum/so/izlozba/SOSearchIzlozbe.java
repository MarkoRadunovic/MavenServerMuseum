package rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Izlozba;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja pretrazuje izlozbe u sistemu.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOSearchIzlozbe extends AbstractSO {

	/**
	 * lista izlozbi u sistemu kao List
	 */
    private List<Izlozba> list;

    /**
     * Vraca listu izlozbi u sistemu
     * @return lista izlozbi kao List
     */
    public List<Izlozba> getList() {
        return list;
    }

    /**
	 * Metoda za validaciju pretrazivanja izlozbi.
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
        list = (List<Izlozba>) (List<?>) dBBroker.search(new Izlozba(), (String) entity);
    }

}