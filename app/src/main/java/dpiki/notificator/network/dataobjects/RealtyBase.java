package dpiki.notificator.network.dataobjects;

/**
 * Created by Lenovo on 05.08.2016.
 */
public abstract class RealtyBase {
    public Long id;
    public Long idAddress;
    public Boolean firm;
    public Boolean withPhoto;

    public RealtyBase() {

    }

    public RealtyBase(Long id, Long idAddress, Boolean firm, Boolean withPhoto) {
        this.id = id;
        this.idAddress = idAddress;
        this.firm = firm;
        this.withPhoto = withPhoto;
    }

    public abstract boolean isMatch(RequirementBase reqBase);
}
