package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOGetAllStalnePostavke extends AbstractSO {

    private List<StalnaPostavka> list;

    public List<StalnaPostavka> getList() {
        return list;
    }

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof StalnaPostavka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StalnaPostavka");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<StalnaPostavka>) (List<?>) dBBroker.getAll((StalnaPostavka) entity);
    }

}