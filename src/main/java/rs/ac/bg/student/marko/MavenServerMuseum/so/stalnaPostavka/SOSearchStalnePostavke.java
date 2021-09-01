package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOSearchStalnePostavke extends AbstractSO {

    private List<StalnaPostavka> list;

    public List<StalnaPostavka> getList() {
        return list;
    }

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof String)) {
            throw new Exception("Prosledjeni objekat nije instanca klase String");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<StalnaPostavka>) (List<?>) dBBroker.search(new StalnaPostavka(), (String) entity);
    }

}