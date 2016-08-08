package dpiki.notificator.network.dataobjects;

/**
 * Created by Lenovo on 08.08.2016.
 */
public class RealestateV1 {
    /**
     * Apartment
      */
    public Long id;
    public Long idTypeApartment;
    public Boolean buildOption; // TODO : ??? (RealestateInfo.isFinishBuild, RealestateInfo.idFinishBuild)
    public Boolean finished; // TODO : ??? (RealestateInfo.idFinishBuild)
    public Long idAddress;
    public Double cost;
    public Boolean firm; // TODO : ??? (Realestate.isFirmContactPerson)
    public Boolean withPhoto; // TODO : where to pick photos
    public Long idVariant; // TODO : where?
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
    public Boolean hasPhone; // TODO : idPhone
    public String dateFreed; // TODO : where?
    public Long[] idRent; // TODO : array or single value

    /**
     * Households and Land
     */
    public Integer stead;

    /**
     * Commercial
     */
    public Double hallArea;
    public Double landArea; // TODO : land? really? in Commercial?
    public Double rentArea; // TODO : rent? really? in Commercial?
    public Double sellPrice;
    public Double sellPriceSquereMeter;
    public Double rentPrice; // TODO : rental?
    public Double rentPriceSquareMeter; // TODO : rental?
    public Long[] idLiftingEquipments;
    public Long[] idCommunications;
}
