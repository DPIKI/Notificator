package dpiki.notificator.data;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Lenovo on 11.08.2016.
 */
public class RealEstate {
    public Long id;
    public String type;
    public String updatedAt;
    public Integer hasPhone;
    public Integer firm;
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
    public String dateFreed;
    public Long[] idRent;
    public Long[] idLiftingEquipments;
    public Long[] idCommunications;

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
        if (r.idTypeApartment != null && this.idTypeApartment != null
                && !r.idTypeApartment.equals(this.idTypeApartment))
            return false;
        if (r.dateFreedFrom != null && this.dateFreed != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateFreedFrom = null;
            Date dateFreed = null;
            try {
                dateFreedFrom = sdf.parse(r.dateFreedFrom);
                dateFreed = sdf.parse(this.dateFreed);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            if (dateFreed == null || dateFreedFrom == null){
                return false;
            }

            if (dateFreedFrom.after(dateFreed))
                return false;
        }

        if (r.dateFreedTo != null && this.dateFreed != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateFreedTo = null;
            Date dateFreed = null;
            try {
                dateFreedTo = sdf.parse(r.dateFreedTo);
                dateFreed = sdf.parse(this.dateFreed);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            if (dateFreed == null || dateFreedTo == null){
                return false;
            }

            if (dateFreedTo.before(dateFreed))
                return false;
        }
        if (r.idRent != null && this.idRent != null
                && !Arrays.asList(this.idRent).contains(r.idRent))
            return false;
        if (r.idLiftingEquipment != null && this.idLiftingEquipments != null
                && !Arrays.asList(this.idLiftingEquipments).contains(r.idLiftingEquipment))
            return false;
        if (r.idCommunication != null && this.idCommunications != null
                && !Arrays.asList(this.idCommunications).contains(r.idCommunication))
            return false;

        return true;
    }
}
