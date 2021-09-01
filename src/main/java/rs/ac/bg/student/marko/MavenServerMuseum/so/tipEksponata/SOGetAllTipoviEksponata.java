package rs.ac.bg.student.marko.MavenServerMuseum.so.tipEksponata;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.TipEksponata;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOGetAllTipoviEksponata extends AbstractSO {

    private List<TipEksponata> list;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof TipEksponata)) {
            throw new Exception("Prosledjeni objekat nije klase TipEksponata");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<TipEksponata>) (List<?>) dBBroker.getAll((TipEksponata) entity);
    }

    public List<TipEksponata> getList() {
        return list;
    }

}
