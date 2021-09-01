package rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Izlozba;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOGetAllIzlozbe extends AbstractSO {

    private List<Izlozba> list;

    public List<Izlozba> getList() {
        return list;
    }

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Izlozba)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Izlozba");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Izlozba>) (List<?>) dBBroker.getAll((Izlozba) entity);
    }

}
