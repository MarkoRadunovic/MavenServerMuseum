package rs.ac.bg.student.marko.MavenServerMuseum.so;

import rs.ac.bg.student.marko.MavenServerMuseum.database.DBBroker;


	/**
	 * Apstraktna klasa koja predstavlja opstu sistemsku operaciju i nasledjuju je sve klase koje su konkretne sistemske operacije.
	 * @author Marko Radunovic
	 * @version 0.1
	 *
	 */
	public abstract class AbstractSO {

	/**
	 * broker baze podataka kao instanca klase DBBroker
	 */
    protected DBBroker dBBroker;

    /**
     * Konstruktor bez parametara koji incijalizuje objekat klase DBBbroker i postavlja ga kao vrednost atributa.
     */
    public AbstractSO() {
        dBBroker = new DBBroker();
    }

    /**
     * Komunicira sa bazom; Zapocinje transakciju, izvrsava operaciju, ukoliko su provereni uslovi zadovoljeni,
     * cuva promene ili ih odbacuje i zatvara transakciju
     * @param param Object koji predstavlja objekat zahteva za izvrsenje transakcije
     * @throws Exception Ukoliko neki od uslova nije zadovoljen 
     */
    public final void templateExecute(Object entity) throws Exception {
        try {
            startTransaction();
            validate(entity);
            execute(entity);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    /**
     * Zapocinje transakciju nad bazom podataka
     * @throws Exception Ako se pri konekciji sa bazom javi greska
     */
    private void startTransaction() throws Exception {
        dBBroker.connect();
    }

    protected abstract void validate(Object entity) throws Exception;

    protected abstract void execute(Object entity) throws Exception;

    /**
     * Potvrdjuje izmene nad bazom podataka
     * @throws Exception Ukoliko se desi greska sa konekcijom nad bazom
     */
    private void commitTransaction() throws Exception {
        dBBroker.commit();
    }

    /**
     * Ponistava transakciju
     * @throws Exception Ukoliko se desi greska sa konekcijom nad bazom
     */
    private void rollbackTransaction() throws Exception {
        dBBroker.rollback();
    }

    /**
     * Prekida konekciju nad bazom podataka
     * @throws Exception Ukoliko se desi greska sa konekcijom nad bazom
     */
    private void disconnect() throws Exception {
        dBBroker.disconnect();
    }

}