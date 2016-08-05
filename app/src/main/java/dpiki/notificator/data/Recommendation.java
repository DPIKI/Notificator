package dpiki.notificator.data;

/**
 * Created by Lenovo on 26.07.2016.
 */
public class Recommendation {
    public Integer idRequirement;
    public Integer idRealty;
    public String type;

    public Recommendation() {

    }

    public Recommendation(Integer idRequirement, Integer idRealty, String type) {
        this.idRequirement = idRequirement;
        this.idRealty = idRealty;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Recommendation))
            return false;

        Recommendation r = (Recommendation) o;
        return idRequirement.equals(r.idRequirement) &&
                type.equals(r.type) &&
                idRealty.equals(r.idRealty);
    }
}
