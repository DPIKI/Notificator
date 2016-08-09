package dpiki.notificator.data;

/**
 * Created by Lenovo on 26.07.2016.
 */
public class Recommendation {
    public Long idRequirement;
    public Long idRealty;
    public String type;

    public Recommendation() {

    }

    public Recommendation(Long idRequirement, Long idRealty, String type) {
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
