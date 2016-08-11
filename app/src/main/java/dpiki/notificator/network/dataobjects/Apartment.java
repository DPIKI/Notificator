package dpiki.notificator.network.dataobjects;


/**
 * Created by prog1 on 01.08.2016.
 */
public class Apartment extends RealtyBase {

    public Long idTypeApartment;
    public Double cost;
    public Double totalArea;
    public Double livingArea;
    public Double kitchenArea;
    public Integer floor;
    public Integer floorAll;
    public Long idFund;
    public Long idState;
    public Long idWallMaterial;

    public Apartment() {

    }

    public Apartment(Long id, Long idAddress, Integer firm) {
        super(id, idAddress, firm);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof ApartmentReq))
            return false;
        ApartmentReq req = (ApartmentReq) reqBase;

        if (req.idAddress != null && this.idAddress != null
                && !req.idAddress.equals(this.idAddress))
            return false;
        if (req.firm != null && this.firm != null
                && !req.firm.equals(this.firm))
            return false;

        if (req.idTypeApartment != null && this.idTypeApartment != null
                && !req.idTypeApartment.equals(this.idTypeApartment))
            return false;
        if (req.costFrom != null && this.cost != null
                && this.cost < req.costFrom)
            return false;
        if (req.costTo != null && this.cost != null
                && this.cost > req.costTo)
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

        if (req.floorAll != null && this.floorAll != null
                && !req.floorAll.equals(this.floorAll)) {
            return false;
        }
        if (req.floor != null && this.floor != null
                && !req.floor.equals(this.floor)) {
            return false;
        }
        if (req.notFirst != null
                && req.notFirst.equals(1)
                && this.floor != null
                && this.floor.equals(1)) {
            return false;
        }
        if (req.notLast != null
                && req.notLast.equals(1)
                && this.floor != null
                && this.floorAll != null
                && this.floor.equals(this.floorAll)) {
            return false;
        }

        if (req.idFund != null && this.idFund != null
                && !req.idFund.equals(this.idFund))
            return false;
        if (req.idState != null && this.idState != null
                && !req.idState.equals(this.idState))
            return false;
        if (req.idWallMaterial != null && this.idWallMaterial != null
                && !req.idWallMaterial.equals(this.idWallMaterial))
            return false;

        return true;
    }
}
