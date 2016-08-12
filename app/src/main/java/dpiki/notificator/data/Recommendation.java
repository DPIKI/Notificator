package dpiki.notificator.data;

/**
 * Created by Lenovo on 26.07.2016.
 */
public class Recommendation {
    public Long idRequisition;
    public Long idRealEstate;
    public String type;

    public Recommendation() {

    }

    public Recommendation(Long idRequisition, Long idRealEstate, String type) {
        this.idRequisition = idRequisition;
        this.idRealEstate = idRealEstate;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Recommendation))
            return false;

        Recommendation r = (Recommendation) o;
        return idRequisition.equals(r.idRequisition) &&
                type.equals(r.type) &&
                idRealEstate.equals(r.idRealEstate);
    }
}
