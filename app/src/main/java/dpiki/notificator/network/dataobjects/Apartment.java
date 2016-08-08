package dpiki.notificator.network.dataobjects;


/**
 * Created by prog1 on 01.08.2016.
 */
public class Apartment extends RealtyBase {
    public Integer idTypeApartment;
    public Double cost;
    public Double totalArea;
    public Double livingArea;
    public Double kitchenArea;
    public Integer floor;
    public Integer floorAll;
    public Integer idFund;
    public Integer idState;
    public Integer idWallMaterial;

    public Apartment() {

    }

    public Apartment(Long id, Long idAddress, Boolean firm) {
        super(id, idAddress, firm);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof ApartmentReq))
            return false;
        ApartmentReq req = (ApartmentReq) reqBase;

        if (req.idAddress == null || !req.idAddress.equals(this.idAddress))
            return false;
        if (req.firm == null || !req.firm.equals(this.firm))
            return false;
        if (req.idTypeApartment == null || !req.idTypeApartment.equals(this.idTypeApartment))
            return false;
        if (req.costFrom != null && this.cost < req.costFrom)
            return false;
        if (req.costTo != null && this.cost > req.costTo)
            return false;
        if (req.totalAreaFrom == null || req.totalAreaFrom > this.totalArea)
            return false;
        if (req.totalAreaTo == null || req.totalAreaTo < this.totalArea)
            return false;
        if (req.livingAreaFrom == null || req.livingAreaFrom > this.livingArea)
            return false;
        if (req.livingAreaTo == null || req.livingAreaTo < this.livingArea)
            return false;
        if (req.kitchenAreaFrom == null || req.kitchenAreaFrom > this.kitchenArea)
            return false;
        if (req.kitchenAreaTo == null || req.kitchenAreaTo < this.kitchenArea)
            return false;

        if (req.floorAll != null) {
            if (this.floorAll != null) {
                if (!req.floorAll.equals(this.floorAll)) {
                    return false;
                }
                if (this.floor != null) {
                    if (!req.floor.equals(this.floor)) {
                        return false;
                    }
                }
            }
            if (req.floor != null) {
                if (this.floor != null) {
                    if (!req.floor.equals(this.floor)) {
                        return false;
                    }
                }
                if (req.notFirst != null) {
                    if ()
                }
            }
        }

        if (req.floor != null) {
            if (req.notFirst && this.floor.equals(1)){
                return false;
            }
            if (this.floorAll != null
                    && req.floorAll != null
                    && req.notLast
                    && this.floor.equals(this.floorAll)){
                return false;
            }

        }

        if (!req.idFund.equals(this.idFund))
            return false;
        if (!req.idState.equals(this.idState))
            return false;
        if (!req.idWallMaterial.equals(this.idWallMaterial))
            return false;

        return true;
    }

}
