package rs.ac.bg.student.marko.MavenServerMuseum.so.kustos;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOSearchKustosi extends AbstractSO {

    private List<Kustos> list;

    public List<Kustos> getList() {
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
        list = (List<Kustos>) (List<?>) dBBroker.search(new Kustos(), (String) entity);
    }

}