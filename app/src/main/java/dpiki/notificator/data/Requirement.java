package dpiki.notificator.data;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class Requirement {
    public Integer id;
    public String type;
    public Integer unreadRecommendations;

    public Requirement() {

    }

    public Requirement(Integer id, String type, Integer unreadRecommendations) {
        this.id = id;
        this.type = type;
        this.unreadRecommendations = unreadRecommendations;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Requirement) {
            Requirement r = (Requirement) o;
            return this.id.equals(r.id) &&
                    this.type.equals(r.type) &&
                    this.unreadRecommendations.equals(r.unreadRecommendations);
        } else {
            return false;
        }
    }
}
