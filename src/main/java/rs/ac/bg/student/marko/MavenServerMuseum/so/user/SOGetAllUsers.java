package rs.ac.bg.student.marko.MavenServerMuseum.so.user;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.User;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

/**
 * Sistemska operacija koja nasledjuje apstraktnu klasu AbstractSO i koja vraca sve korisnike iz sistema.
 * @author Marko Radunovic
 * @version 0.1
 */
public class SOGetAllUsers extends AbstractSO{

	/**
	 * lista korsinika sistema kao List
	 */
    private List<User> list; 
    
    /**
  	 * Metoda za validaciju
  	 * @param entity instanca klase Object koja predstavlja zahtev koji se prosledjuje
  	 * @throws Exception <ul>
  	 * <li>Ako je prosledjeni objekat null</li>
  	 * <li>Ako prosledjeni objekat nije instanca klase User</li>
  	 * </ul>
  	 */
    @Override
    protected void validate(Object entity) throws Exception {
        if(!(entity instanceof User))
            throw new Exception("Prosledjeni objekat nije instanca klase User");
    }

    /**
     * Vraca sve korisnike sistema
     * @param entity instanca klase Object koja predstavlja zahtev
     * @throws Exception <ul>
	 * <li>Ako je prosledjeni objekat null</li>
	 * <li>Ako prosledjeni objekat nije instanca klase User</li>
	 * <li>Ako se desi neka greska u komunikaciji sa bazom</li>
	 * </ul>
     */
    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<User>)(List<?>)dBBroker.getAll((User)entity);
    }

    /**
     * Vraca listu korisnika sistema
     * @return lista korisnika kao List
     */
    public List<User> getList() {
        return list;
    }
    
    
}