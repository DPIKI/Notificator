package dpiki.notificator.network.dataobjects;

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
        return true;
    }
}
