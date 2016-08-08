package dpiki.notificator.network.dataobjects;

/**
 * Created by Lenovo on 05.08.2016.
 */
public abstract class RealtyBase {
    public Long id;
    public Long idAddress;
    public Boolean firm;

    public RealtyBase() {

    }

    public RealtyBase(Long id, Long idAddress, Boolean firm) {
        this.id = id;
        this.idAddress = idAddress;
        this.firm = firm;
    }

    public abstract boolean isMatch(RequirementBase reqBase);
}
