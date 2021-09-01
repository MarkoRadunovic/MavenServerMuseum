package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOSearchEksponati extends AbstractSO {

    private List<Eksponat> list;

    public List<Eksponat> getList() {
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
        list = (List<Eksponat>) (List<?>) dBBroker.search(new Eksponat(), (String) entity);
    }

}
