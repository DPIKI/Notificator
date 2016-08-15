package dpiki.notificator.data;

/**
 * Created by Lenovo on 11.08.2016.
 */
public class Requisition {
    public Long id;
    public String type;
    public Long idFund;
    public Double hallAreaFrom;
    public Double hallAreaTo;
    public Double landAreaFrom;
    public Double landAreaTo;
    public Double rentAreaFrom;
    public Double rentAreaTo;
    public Double sellPriceFrom;
    public Double sellPriceTo;
    public Double sellPriceSquareMeterFrom;
    public Double sellPriceSquareMeterTo;
    public Double rentalPriceFrom;
    public Double rentalPriceTo;
    public Double rentalPriceSquareMeterFrom;
    public Double rentalPriceSquareMeterTo;
    public Double totalAreaFrom;
    public Double totalAreaTo;
    public Double livingAreaFrom;
    public Double livingAreaTo;
    public Double kitchenAreaFrom;
    public Double kitchenAreaTo;
    public Integer steadFrom;
    public Integer steadTo;
    public Long idWallMaterial;
    public Long idTypeApartment;
    public Long idAddress;
    public Double costFrom;
    public Double costTo;
    public Integer firm;
    public Integer floor;
    public Integer floorAll;
    public Integer notFirst;
    public Integer notLast;
    public Integer roomCountFrom;
    public Integer roomCountTo;
    public Integer prepayment;
    public Long idComfort;
    public Long idFurniture;
    public Long idState;
    public Long idYard;
    public Long idEntry;
    public Long idRent;
    public Long idLiftingEquipment;
    public Long idCommunication;
    public Integer idPhone;
    public String dateFreedFrom;
    public String dateFreedTo;
    public Integer unreadRecommendationsCount;

    public Requisition() {

    }

    public Requisition(Long id, String type, Long idFund, Double hallAreaFrom, Double hallAreaTo, Double landAreaFrom, Double landAreaTo, Double rentAreaFrom, Double rentAreaTo, Double sellPriceFrom, Double sellPriceTo, Double sellPriceSquareMeterFrom, Double sellPriceSquareMeterTo, Double rentalPriceFrom, Double rentalPriceTo, Double rentalPriceSquareMeterFrom, Double rentalPriceSquareMeterTo, Double totalAreaFrom, Double totalAreaTo, Double livingAreaFrom, Double livingAreaTo, Double kitchenAreaFrom, Double kitchenAreaTo, Integer steadFrom, Integer steadTo, Long idWallMaterial, Long idTypeApartment, Long idAddress, Double costFrom, Double costTo, Integer firm, Integer floor, Integer floorAll, Integer notFirst, Integer notLast, Integer roomCountFrom, Integer roomCountTo, Integer prepayment, Long idComfort, Long idFurniture, Long idState, Long idYard, Long idEntry, Long idRent, Long idLiftingEquipment, Long idCommunication, Integer idPhone, String dateFreedFrom, String dateFreedTo, Integer unreadRecommendationsCount) {
        this.id = id;
        this.type = type;
        this.idFund = idFund;
        this.hallAreaFrom = hallAreaFrom;
        this.hallAreaTo = hallAreaTo;
        this.landAreaFrom = landAreaFrom;
        this.landAreaTo = landAreaTo;
        this.rentAreaFrom = rentAreaFrom;
        this.rentAreaTo = rentAreaTo;
        this.sellPriceFrom = sellPriceFrom;
        this.sellPriceTo = sellPriceTo;
        this.sellPriceSquareMeterFrom = sellPriceSquareMeterFrom;
        this.sellPriceSquareMeterTo = sellPriceSquareMeterTo;
        this.rentalPriceFrom = rentalPriceFrom;
        this.rentalPriceTo = rentalPriceTo;
        this.rentalPriceSquareMeterFrom = rentalPriceSquareMeterFrom;
        this.rentalPriceSquareMeterTo = rentalPriceSquareMeterTo;
        this.totalAreaFrom = totalAreaFrom;
        this.totalAreaTo = totalAreaTo;
        this.livingAreaFrom = livingAreaFrom;
        this.livingAreaTo = livingAreaTo;
        this.kitchenAreaFrom = kitchenAreaFrom;
        this.kitchenAreaTo = kitchenAreaTo;
        this.steadFrom = steadFrom;
        this.steadTo = steadTo;
        this.idWallMaterial = idWallMaterial;
        this.idTypeApartment = idTypeApartment;
        this.idAddress = idAddress;
        this.costFrom = costFrom;
        this.costTo = costTo;
        this.firm = firm;
        this.floor = floor;
        this.floorAll = floorAll;
        this.notFirst = notFirst;
        this.notLast = notLast;
        this.roomCountFrom = roomCountFrom;
        this.roomCountTo = roomCountTo;
        this.prepayment = prepayment;
        this.idComfort = idComfort;
        this.idFurniture = idFurniture;
        this.idState = idState;
        this.idYard = idYard;
        this.idEntry = idEntry;
        this.idRent = idRent;
        this.idLiftingEquipment = idLiftingEquipment;
        this.idCommunication = idCommunication;
        this.idPhone = idPhone;
        this.dateFreedFrom = dateFreedFrom;
        this.dateFreedTo = dateFreedTo;
        this.unreadRecommendationsCount = unreadRecommendationsCount;
    }

    public Requisition(Requisition r) {
        this.id = r.id;
        this.type = r.type;
        this.idFund = r.idFund;
        this.hallAreaFrom = r.hallAreaFrom;
        this.hallAreaTo = r.hallAreaTo;
        this.landAreaFrom = r.landAreaFrom;
        this.landAreaTo = r.landAreaTo;
        this.rentAreaFrom = r.rentAreaFrom;
        this.rentAreaTo = r.rentAreaTo;
        this.sellPriceFrom = r.sellPriceFrom;
        this.sellPriceTo = r.sellPriceTo;
        this.sellPriceSquareMeterFrom = r.sellPriceSquareMeterFrom;
        this.sellPriceSquareMeterTo = r.sellPriceSquareMeterTo;
        this.rentalPriceFrom = r.rentalPriceFrom;
        this.rentalPriceTo = r.rentalPriceTo;
        this.rentalPriceSquareMeterFrom = r.rentalPriceSquareMeterFrom;
        this.rentalPriceSquareMeterTo = r.rentalPriceSquareMeterTo;
        this.totalAreaFrom = r.totalAreaFrom;
        this.totalAreaTo = r.totalAreaTo;
        this.livingAreaFrom = r.livingAreaFrom;
        this.livingAreaTo = r.livingAreaTo;
        this.kitchenAreaFrom = r.kitchenAreaFrom;
        this.kitchenAreaTo = r.kitchenAreaTo;
        this.steadFrom = r.steadFrom;
        this.steadTo = r.steadTo;
        this.idWallMaterial = r.idWallMaterial;
        this.idTypeApartment = r.idTypeApartment;
        this.idAddress = r.idAddress;
        this.costFrom = r.costFrom;
        this.costTo = r.costTo;
        this.firm = r.firm;
        this.floor = r.floor;
        this.floorAll = r.floorAll;
        this.notFirst = r.notFirst;
        this.notLast = r.notLast;
        this.roomCountFrom = r.roomCountFrom;
        this.roomCountTo = r.roomCountTo;
        this.prepayment = r.prepayment;
        this.idComfort = r.idComfort;
        this.idFurniture = r.idFurniture;
        this.idState = r.idState;
        this.idYard = r.idYard;
        this.idEntry = r.idEntry;
        this.idRent = r.idRent;
        this.idLiftingEquipment = r.idLiftingEquipment;
        this.idCommunication = r.idCommunication;
        this.idPhone = r.idPhone;
        this.dateFreedFrom = r.dateFreedFrom;
        this.dateFreedTo = r.dateFreedTo;
        this.unreadRecommendationsCount = r.unreadRecommendationsCount;
    }
}
