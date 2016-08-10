package dpiki.notificator.network.dataobjects;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Household extends RealtyBase {
    public Integer stead;
    public Double cost;
    public Double totalArea;
    public Double livingArea;
    public Double kitchenArea;
    public Long idState;
    public Long idWallMaterial;
    public Long idEntry;
    public Long idFurniture;

    public Household() {

    }

    public Household(Long id, Long idAddress, Integer firm) {
        super(id, idAddress, firm);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof HouseholdReq))
            return false;
        HouseholdReq req = (HouseholdReq) reqBase;

        if (req.idAddress != null && this.idAddress != null
                && !req.idAddress.equals(this.idAddress))
            return false;
        if (req.firm != null && this.firm != null
                && !req.firm.equals(this.firm))
            return false;

        if (req.steadFrom != null && this.stead != null
                && req.steadFrom > this.stead)
            return false;
        if (req.steadTo != null && this.stead != null
                && req.steadTo < this.stead)
            return false;

        if (req.costFrom != null && this.cost != null
                && req.costFrom > this.cost)
            return false;
        if (req.costTo != null && this.cost != null
                && req.costTo < this.cost)
            return false;

        if (req.totalAreaFrom != null && this.totalArea != null
                && req.totalAreaFrom > this.totalArea)
            return false;
        if (req.totalAreaTo != null && this.totalArea != null
                && req.totalAreaTo < this.totalArea)
            return false;
        if (req.livingAreaFrom != null && this.livingArea != null
                && req.livingAreaFrom > this.livingArea)
            return false;
        if (req.livingAreaTo != null && this.livingArea != null
                && req.livingAreaTo < this.livingArea)
            return false;
        if (req.kitchenAreaFrom != null && this.kitchenArea != null
                && req.kitchenAreaFrom > this.kitchenArea)
            return false;
        if (req.kitchenAreaTo != null && this.kitchenArea != null
                && req.kitchenAreaTo < this.kitchenArea)
            return false;

        if (req.idState != null && this.idState != null
                && !req.idState.equals(this.idState))
            return false;
        if (req.idWallMaterial != null && this.idWallMaterial != null
                && !req.idWallMaterial.equals(this.idWallMaterial))
            return false;
        if (req.idEntry != null && this.idEntry != null
                && !req.idEntry.equals(this.idEntry))
            return false;
        if (req.idFurniture != null && this.idFurniture != null
                && !req.idFurniture.equals(this.idFurniture))
            return false;

        return true;
    }
}
