package dpiki.notificator.data;


import java.util.Arrays;

/**
 * Created by Lenovo on 11.08.2016.
 */
public class RealEstate {
    public Long id;
    public String type;
    public String updatedAt;
    public Integer hasPhone;
    public Integer firm;
    public Integer hasElevator;
    public Double totalArea;
    public Double hallArea;
    public Double landArea;
    public Double rentArea;
    public Double livingArea;
    public Double kitchenArea;
    public Double sellPrice;
    public Double sellPriceSquareMeter;
    public Double rentPrice;
    public Double rentPriceSquareMeter;
    public Double cost;
    public Integer floor;
    public Integer floorAll;
    public Integer stead;
    public Integer steadX;
    public Integer steadY;
    public Integer roomCount;
    public Integer prepayment;
    public Long idYard;
    public Long idFund;
    public Long idEntry;
    public Long idState;
    public Long idAddress;
    public Long idComfort;
    public Long idFurniture;
    public Long idWallMaterial;
    public Long idTypeApartment;
    public Long idRealEstateState;
    public String dateFreed;
    public Long[] idRents;
    public Long[] idLiftingEquipments;
    public Long[] idCommunications;
    public Long[] idProfiles;

    public boolean isMatch(Requisition r) {
        if (r.type != null
                && this.type != null
                && !r.type.equals(this.type))
            return false;
        if (r.idPhone != null
                && r.idPhone != 0
                && this.hasPhone != null
                && !r.idPhone.equals(this.hasPhone))
            return false;
        if (r.firm != null && this.firm != null
                && !r.firm.equals(this.firm))
            return false;
        if (r.hasElevator != null && this.hasElevator != null
                && !r.hasElevator.equals(this.hasElevator))
            return false;
        if (r.totalAreaFrom != null && this.totalArea != null
                && r.totalAreaFrom > this.totalArea)
            return false;
        if (r.totalAreaTo != null && this.totalArea != null
                && r.totalAreaTo < this.totalArea)
            return false;
        if (r.hallAreaFrom != null && this.hallArea != null
                && r.hallAreaFrom > this.hallArea)
            return false;
        if (r.hallAreaTo != null && this.hallArea != null
                && r.hallAreaTo < this.hallArea)
            return false;
        if (r.landAreaFrom != null && this.landArea != null
                && r.landAreaFrom > this.landArea)
            return false;
        if (r.landAreaTo != null && this.landArea != null
                && r.landAreaTo < this.landArea)
            return false;
        if (r.rentAreaFrom != null && this.rentArea != null
                && r.rentAreaFrom > this.rentArea)
            return false;
        if (r.rentAreaTo != null && this.rentArea != null
                && r.rentAreaTo < this.rentArea)
            return false;
        if (r.livingAreaFrom != null && this.livingArea != null
                && r.livingAreaFrom > this.livingArea)
            return false;
        if (r.livingAreaTo != null && this.livingArea != null
                && r.livingAreaTo < this.livingArea)
            return false;
        if (r.kitchenAreaFrom != null && this.kitchenArea != null
                && r.kitchenAreaFrom > this.kitchenArea)
            return false;
        if (r.kitchenAreaTo != null && this.kitchenArea != null
                && r.kitchenAreaTo < this.kitchenArea)
            return false;
        if (r.sellPriceFrom != null && this.sellPrice != null
                && r.sellPriceFrom > this.sellPrice)
            return false;
        if (r.sellPriceTo != null && this.sellPrice != null
                && r.sellPriceTo < this.sellPrice)
            return false;
        if (r.sellPriceSquareMeterFrom != null && this.sellPriceSquareMeter != null
                && r.sellPriceSquareMeterFrom > this.sellPriceSquareMeter)
            return false;
        if (r.sellPriceSquareMeterTo != null && this.sellPriceSquareMeter != null
                && r.sellPriceSquareMeterTo < this.sellPriceSquareMeter)
            return false;
        if (r.rentalPriceFrom != null && this.rentPrice != null
                && r.rentalPriceFrom > this.rentPrice)
            return false;
        if (r.rentalPriceTo != null && this.rentPrice != null
                && r.rentalPriceTo < this.rentPrice)
            return false;
        if (r.rentalPriceSquareMeterFrom != null && this.rentPriceSquareMeter != null
                && r.rentalPriceSquareMeterFrom > this.rentPriceSquareMeter)
            return false;
        if (r.rentalPriceSquareMeterTo != null && this.rentPriceSquareMeter != null
                && r.rentalPriceSquareMeterTo < this.rentPriceSquareMeter)
            return false;
        if (r.costFrom != null && this.cost != null
                && r.costFrom > this.cost)
            return false;
        if (r.costTo != null && this.cost != null
                && r.costTo < this.cost)
            return false;
        if (r.floorAll != null && this.floorAll != null
                && !r.floorAll.equals(this.floorAll))
            return false;
        if (r.floor != null && this.floor != null
                && !r.floor.equals(this.floor))
            return false;
        if (r.notFirst != null
                && r.notFirst.equals(1)
                && this.floor != null
                && this.floor.equals(1))
            return false;
        if (r.notLast != null
                && r.notLast.equals(1)
                && this.floor != null
                && this.floorAll != null
                && this.floor.equals(this.floorAll))
            return false;
        if (r.steadFrom != null && this.stead != null
                && r.steadFrom > this.stead)
            return false;
        if (r.steadTo != null && this.stead != null
                && r.steadTo < this.stead)
            return false;
        if (r.steadXFrom != null && this.steadX != null
                && r.steadXFrom > this.steadX)
            return false;
        if (r.steadXTo != null && this.steadX != null
                && r.steadXTo < this.steadX)
            return false;
        if (r.steadYFrom != null && this.steadY != null
                && r.steadYFrom > this.steadY)
            return false;
        if (r.steadYTo != null && this.steadY != null
                && r.steadYTo < this.steadY)
            return false;
        if (r.roomCountFrom != null && this.roomCount != null
                && r.roomCountFrom > this.roomCount)
            return false;
        if (r.roomCountTo != null && this.roomCount != null
                && r.roomCountTo < this.roomCount)
            return false;
        if (r.prepayment != null && this.prepayment != null
                && !r.prepayment.equals(this.prepayment))
            return false;
        if (r.idYard != null && this.idYard != null
                && !r.idYard.equals(this.idYard))
            return false;
        if (r.idFund != null && this.idFund != null
                && !r.idFund.equals(this.idFund))
            return false;
        if (r.idEntry != null && this.idEntry != null
                && !r.idEntry.equals(this.idEntry))
            return false;
        if (r.idState != null && this.idState != null
                && !r.idState.equals(this.idState))
            return false;
        if (r.idAddress != null && this.idAddress != null
                && !r.idAddress.equals(this.idAddress))
            return false;
        if (r.idComfort != null && this.idComfort != null
                && !r.idComfort.equals(this.idComfort))
            return false;
        if (r.idFurniture != null && this.idFurniture != null
                && !r.idFurniture.equals(this.idFurniture))
            return false;
        if (r.idWallMaterial != null && this.idWallMaterial != null
                && !r.idWallMaterial.equals(this.idWallMaterial))
            return false;
        if (r.idTypeApartments != null && this.idTypeApartment != null
                && !Arrays.asList(r.idTypeApartments).contains(this.idTypeApartment))
            return false;
        if (r.idRealEstateState != null && this.idRealEstateState != null
                && !r.idRealEstateState.equals(this.idRealEstateState))
            return false;
        if (r.dateFreedFrom != null && this.dateFreed != null
                && r.dateFreedFrom.compareTo(dateFreed) > 0)
                return false;
        if (r.dateFreedTo != null && this.dateFreed != null
                && r.dateFreedTo.compareTo(dateFreed) < 0)
                return false;
        if (r.idRent != null && this.idRents != null
                && !Arrays.asList(this.idRents).contains(r.idRent))
            return false;
        if (r.idLiftingEquipments != null && this.idLiftingEquipments != null) {
            Boolean isExist = false;
            for (Long idLiftingEquipment : r.idLiftingEquipments) {
                if(Arrays.asList(this.idLiftingEquipments).contains(idLiftingEquipment)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist)
                return false;
        }
        if (r.idCommunications != null && this.idCommunications != null) {
            Boolean isExist = false;
            for (Long idCommunication : r.idCommunications) {
                if(Arrays.asList(this.idCommunications).contains(idCommunication)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist)
                return false;
        }
        if (r.idProfiles != null && this.idProfiles != null) {
            Boolean isExist = false;
            for (Long idProfile : r.idProfiles) {
                if (Arrays.asList(this.idProfiles).contains(idProfile)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist)
                return false;
        }

        return true;
    }

}
