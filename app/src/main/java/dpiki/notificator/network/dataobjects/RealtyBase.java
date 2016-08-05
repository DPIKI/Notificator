package dpiki.notificator.network.dataobjects;

/**
 * Created by Lenovo on 05.08.2016.
 */
public abstract class RealtyBase {
    public Integer id;
    public String createdAt;

    public RealtyBase() {

    }

    public RealtyBase(Integer id, String createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public abstract boolean isMatch(RequirementBase reqBase);
}
