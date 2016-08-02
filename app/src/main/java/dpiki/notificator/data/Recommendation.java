package dpiki.notificator.data;

/**
 * Created by Lenovo on 26.07.2016.
 */
public class Recommendation {
    public Requirement requirement;
    public Realty realty;

    public Recommendation(Requirement requirement, Realty realty) {
        this.requirement = requirement;
        this.realty = realty;
    }
}
