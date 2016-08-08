package dpiki.notificator.network.dataobjects;

/**
 * Created by Lenovo on 08.08.2016.
 */
public class RealestateV1 {
    public Long id;
    public Long idAddress;
    public Boolean firm;
    public Boolean withPhoto;

    /**
     * Apartment
      */
    public Long idTypeApartment;
    //public Boolean buildOption; // TODO : ??? (RealestateInfo.isFinishBuild, RealestateInfo.idFinishBuild)
    //public Boolean finished; // TODO : ??? (RealestateInfo.idFinishBuild)
    public Double cost;
    public Double totalArea;
    public Double livingArea;
    public Double kitchenArea;
    public Integer floor;
    public Integer floorAll;
    public Long idFund;
    public Long idState;
    public Long idWallMaterial;

    /**
     * Rent
     */
    public Integer roomCount;
    public Integer prepayment;
    public Long idComfort;
    public Long idFurniture;
    public Long idYard;
    public Long idEntry;
    public Boolean hasPhone;
    public String dateFreed; // TODO : where?
    public Long[] idRent;

    /**
     * Households and Land
     */
    public Integer stead;

    /**
     * Commercial
     */
    public Double hallArea;
    public Double landArea;
    public Double rentArea;
    public Double sellPrice;
    public Double sellPriceSquereMeter;
    public Double rentPrice;
    public Double rentPriceSquareMeter;
    public Long[] idLiftingEquipments;
    public Long[] idCommunications;
}
