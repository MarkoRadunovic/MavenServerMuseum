package rs.ac.bg.student.marko.MavenServerMuseum.so.tipEksponata;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.TipEksponata;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja vraca sve tipove eksponata iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOGetAllTipoviEksponata extends AbstractSO {

	
	/**
	 * lista tipova eksponata iz sistema kao List
	 */
    private List<TipEksponata> list;


    /**
  	 * Metoda za validaciju
  	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
  	 * @throws Exception <ul>
  	 * <li>Ako je prosledjeni objekat null</li>
  	 * <li>Ako prosledjeni objekat nije instanca klase TipEksponata</li>
  	 * </ul>
  	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof TipEksponata)) {
            throw new Exception("Prosledjeni objekat nije klase TipEksponata");
        }
    }

    /**
     * Vraca sve tipove eksponata iz sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase TipEksponata</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<TipEksponata>) (List<?>) dBBroker.getAll((TipEksponata) entity);
    }

    /**
     * Vraca listu tipova eksponata iz sistema
     * @return lista tipova kao List
     */
    public List<TipEksponata> getList() {
        return list;
    }

}
