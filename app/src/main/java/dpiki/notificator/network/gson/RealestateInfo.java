package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class RealestateInfo {

    private int typeInfo = -1;

    private Long id;
    @SerializedName("id_type_apartment")
    private Long idTypeApartment;
    private Integer floor;
    @SerializedName("floor_all")
    private Integer floorAll;
    @SerializedName("id_wall_material")
    private Long idWallMaterial;
    @SerializedName("id_state")
    private Long idState;
    private Double cost;
    @SerializedName("id_fund")
    private Long idFund;
    @SerializedName("is_finish_build")
    private Integer isFinishBuild;
    @SerializedName("id_finish_build")
    private Long idFinishBuild;
    @SerializedName("date_finish_build")
    private Date dateFinishBuild;
    @SerializedName("is_luxury")
    private Integer isLuxury;
    @SerializedName("total_area")
    private Double totalArea;
    @SerializedName("living_area")
    private Double livingArea;
    @SerializedName("kitchen_area")
    private Double kitchenArea;
    @SerializedName("special_price")
    private Double specialPrice;
    private Integer terms;
    private String conditions;
    @SerializedName("is_urgent_sale")
    private Integer isUrgentSale;
    @SerializedName("is_mortgage")
    private Integer isMortgage;
    @SerializedName("id_floor")
    private Long idFloor;
    @SerializedName("id_window_material")
    private Long idWindowMaterial;
    @SerializedName("id_bathroom")
    private Long idBathroom;
    @SerializedName("id_comfort")
    private Long idComfort;
    @SerializedName("id_hot_water")
    private Long idHotWater;
    @SerializedName("id_heating")
    private Long idHeating;
    @SerializedName("id_loggia")
    private Long idLoggia;
    @SerializedName("id_balcony")
    private Long idBalcony;
    @SerializedName("has_elevator")
    private Integer hasElevator;
    private String ceiling;
    private String adjacent;
    private String renting;
    private String construction;
    @SerializedName("id_location")
    private Long idLocation;
    @SerializedName("src_layout")
    private String srcLayout;

    @SerializedName("hall_area")
    private Double hallArea;
    @SerializedName("land_area")
    private Double landArea;
    @SerializedName("rent_area")
    private Double rentArea;
    @SerializedName("sell_price")
    private Double sellPrice;
    @SerializedName("sell_price_square_meter")
    private Double sellPriceSquareMeter;
    @SerializedName("rent_price")
    private Double rentPrice;
    @SerializedName("rent_price_square_meter")
    private Double rentPriceSquareMeter;
    @SerializedName("id_ownership")
    private Long idOwnership;
    @SerializedName("elevator_tong")
    private Double elevatorTong;
    @SerializedName("id_security")
    private Long idSecurity;
    @SerializedName("id_number_storeys")
    private Long idNumberStoreys;
    @SerializedName("is_station")
    private Integer isStation;
    @SerializedName("id_station")
    private Long idStation;
    @SerializedName("is_leased_line")
    private Integer isLeasedLine;
    @SerializedName("count_numbers")
    private Integer countNumbers;
    @SerializedName("count_free_pairs")
    private Integer countFreePairs;
    @SerializedName("date_putting_house")
    private Date datePuttingHouse;
    private Integer stead;
    @SerializedName("room_count")
    private Integer roomCount;
    @SerializedName("id_roof")
    private Long idRoof;
    @SerializedName("id_yard")
    private Long idYard;
    @SerializedName("id_entry")
    private Long idEntry;
    @SerializedName("has_garage")
    private Integer hasGarage;
    @SerializedName("id_sewerage")
    private Long idSewerage;
    @SerializedName("url_layout")
    private String urlLayout;

    private Integer steadX;
    private Integer steadY;

    @SerializedName("id_apartment")
    private Long idApartment;
    private Integer prepayment;
    @SerializedName("id_furniture")
    private Long idFurniture;
    @SerializedName("about_furniture")
    private String aboutFurniture;
    @SerializedName("has_phone")
    private Integer hasPhone;

    private Address address;
    @SerializedName("elevator")
    private Elevator[] elevators;
    private Window[] windows;

    @SerializedName("type_rent")
    private TypeRent[] typeRent;
    @SerializedName("is_garden")
    private Integer isGarden;

    @SerializedName("communication")
    private Communication[] communication;
    @SerializedName("communication_possible")
    private Communication[] communicationPossible;
    @SerializedName("profile")
    private Profile[] profile;
    @SerializedName("commercial_payment")
    private CommercialPayment[] commercialPayment;
    @SerializedName("commercial_communication")
    private Communication[] commercialCommunication;
    @SerializedName("lifting_equipment")
    private LiftingEquipment[] liftingEquipment;

    /**
     * @return the typeInfo
     */
    public int getTypeInfo() {
        return typeInfo;
    }

    /**
     * @param typeInfo the typeInfo to set
     */
    public void setTypeInfo(int typeInfo) {
        this.typeInfo = typeInfo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the idTypeApartment
     */
    public Long getIdTypeApartment() {
        return idTypeApartment;
    }

    /**
     * @param idTypeApartment the idTypeApartment to set
     */
    public void setIdTypeApartment(Long idTypeApartment) {
        this.idTypeApartment = idTypeApartment;
    }

    /**
     * @return the floor
     */
    public Integer getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    /**
     * @return the floorAll
     */
    public Integer getFloorAll() {
        return floorAll;
    }

    /**
     * @param floorAll the floorAll to set
     */
    public void setFloorAll(Integer floorAll) {
        this.floorAll = floorAll;
    }

    /**
     * @return the idWallMaterial
     */
    public Long getIdWallMaterial() {
        return idWallMaterial;
    }

    /**
     * @param idWallMaterial the idWallMaterial to set
     */
    public void setIdWallMaterial(Long idWallMaterial) {
        this.idWallMaterial = idWallMaterial;
    }

    /**
     * @return the idState
     */
    public Long getIdState() {
        return idState;
    }

    /**
     * @param idState the idState to set
     */
    public void setIdState(Long idState) {
        this.idState = idState;
    }

    /**
     * @return the cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * @return the idFund
     */
    public Long getIdFund() {
        return idFund;
    }

    /**
     * @param idFund the idFund to set
     */
    public void setIdFund(Long idFund) {
        this.idFund = idFund;
    }

    /**
     * @return the isFinishBuild
     */
    public Integer getIsFinishBuild() {
        return isFinishBuild;
    }

    /**
     * @param isFinishBuild the isFinishBuild to set
     */
    public void setIsFinishBuild(Integer isFinishBuild) {
        this.isFinishBuild = isFinishBuild;
    }

    /**
     * @return the idFinishBuild
     */
    public Long getIdFinishBuild() {
        return idFinishBuild;
    }

    /**
     * @param idFinishBuild the idFinishBuild to set
     */
    public void setIdFinishBuild(Long idFinishBuild) {
        this.idFinishBuild = idFinishBuild;
    }

    /**
     * @return the dateFinishBuild
     */
    public Date getDateFinishBuild() {
        return dateFinishBuild;
    }

    /**
     * @param dateFinishBuild the dateFinishBuild to set
     */
    public void setDateFinishBuild(Date dateFinishBuild) {
        this.dateFinishBuild = dateFinishBuild;
    }

    /**
     * @return the isLuxury
     */
    public Integer getIsLuxury() {
        return isLuxury;
    }

    /**
     * @param isLuxury the isLuxury to set
     */
    public void setIsLuxury(Integer isLuxury) {
        this.isLuxury = isLuxury;
    }

    /**
     * @return the totalArea
     */
    public Double getTotalArea() {
        return totalArea;
    }

    /**
     * @param totalArea the totalArea to set
     */
    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    /**
     * @return the livingArea
     */
    public Double getLivingArea() {
        return livingArea;
    }

    /**
     * @param livingArea the livingArea to set
     */
    public void setLivingArea(Double livingArea) {
        this.livingArea = livingArea;
    }

    /**
     * @return the kitchenArea
     */
    public Double getKitchenArea() {
        return kitchenArea;
    }

    /**
     * @param kitchenArea the kitchenArea to set
     */
    public void setKitchenArea(Double kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    /**
     * @return the specialPrice
     */
    public Double getSpecialPrice() {
        return specialPrice;
    }

    /**
     * @param specialPrice the specialPrice to set
     */
    public void setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
    }

    /**
     * @return the terms
     */
    public Integer getTerms() {
        return terms;
    }

    /**
     * @param terms the terms to set
     */
    public void setTerms(Integer terms) {
        this.terms = terms;
    }

    /**
     * @return the conditions
     */
    public String getConditions() {
        return conditions;
    }

    /**
     * @param conditions the conditions to set
     */
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    /**
     * @return the isUrgentSale
     */
    public Integer getIsUrgentSale() {
        return isUrgentSale;
    }

    /**
     * @param isUrgentSale the isUrgentSale to set
     */
    public void setIsUrgentSale(Integer isUrgentSale) {
        this.isUrgentSale = isUrgentSale;
    }

    /**
     * @return the isMortgage
     */
    public Integer getIsMortgage() {
        return isMortgage;
    }

    /**
     * @param isMortgage the isMortgage to set
     */
    public void setIsMortgage(Integer isMortgage) {
        this.isMortgage = isMortgage;
    }

    /**
     * @return the idFloor
     */
    public Long getIdFloor() {
        return idFloor;
    }

    /**
     * @param idFloor the idFloor to set
     */
    public void setIdFloor(Long idFloor) {
        this.idFloor = idFloor;
    }

    /**
     * @return the idWindowMaterial
     */
    public Long getIdWindowMaterial() {
        return idWindowMaterial;
    }

    /**
     * @param idWindowMaterial the idWindowMaterial to set
     */
    public void setIdWindowMaterial(Long idWindowMaterial) {
        this.idWindowMaterial = idWindowMaterial;
    }

    /**
     * @return the idBathroom
     */
    public Long getIdBathroom() {
        return idBathroom;
    }

    /**
     * @param idBathroom the idBathroom to set
     */
    public void setIdBathroom(Long idBathroom) {
        this.idBathroom = idBathroom;
    }

    /**
     * @return the idComfort
     */
    public Long getIdComfort() {
        return idComfort;
    }

    /**
     * @param idComfort the idComfort to set
     */
    public void setIdComfort(Long idComfort) {
        this.idComfort = idComfort;
    }

    /**
     * @return the idHotWater
     */
    public Long getIdHotWater() {
        return idHotWater;
    }

    /**
     * @param idHotWater the idHotWater to set
     */
    public void setIdHotWater(Long idHotWater) {
        this.idHotWater = idHotWater;
    }

    /**
     * @return the idHeating
     */
    public Long getIdHeating() {
        return idHeating;
    }

    /**
     * @param idHeating the idHeating to set
     */
    public void setIdHeating(Long idHeating) {
        this.idHeating = idHeating;
    }

    /**
     * @return the idLoggia
     */
    public Long getIdLoggia() {
        return idLoggia;
    }

    /**
     * @param idLoggia the idLoggia to set
     */
    public void setIdLoggia(Long idLoggia) {
        this.idLoggia = idLoggia;
    }

    /**
     * @return the idBalcony
     */
    public Long getIdBalcony() {
        return idBalcony;
    }

    /**
     * @param idBalcony the idBalcony to set
     */
    public void setIdBalcony(Long idBalcony) {
        this.idBalcony = idBalcony;
    }

    /**
     * @return the hasElevator
     */
    public Integer getHasElevator() {
        return hasElevator;
    }

    /**
     * @param hasElevator the hasElevator to set
     */
    public void setHasElevator(Integer hasElevator) {
        this.hasElevator = hasElevator;
    }

    /**
     * @return the ceiling
     */
    public String getCeiling() {
        return ceiling;
    }

    /**
     * @param ceiling the ceiling to set
     */
    public void setCeiling(String ceiling) {
        this.ceiling = ceiling;
    }

    /**
     * @return the adjacent
     */
    public String getAdjacent() {
        return adjacent;
    }

    /**
     * @param adjacent the adjacent to set
     */
    public void setAdjacent(String adjacent) {
        this.adjacent = adjacent;
    }

    /**
     * @return the renting
     */
    public String getRenting() {
        return renting;
    }

    /**
     * @param renting the renting to set
     */
    public void setRenting(String renting) {
        this.renting = renting;
    }

    /**
     * @return the construction
     */
    public String getConstruction() {
        return construction;
    }

    /**
     * @param construction the construction to set
     */
    public void setConstruction(String construction) {
        this.construction = construction;
    }

    /**
     * @return the idLocation
     */
    public Long getIdLocation() {
        return idLocation;
    }

    /**
     * @param idLocation the idLocation to set
     */
    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
    }

    /**
     * @return the srcLayout
     */
    public String getSrcLayout() {
        return srcLayout;
    }

    /**
     * @param srcLayout the srcLayout to set
     */
    public void setSrcLayout(String srcLayout) {
        this.srcLayout = srcLayout;
    }

    /**
     * @return the hallArea
     */
    public Double getHallArea() {
        return hallArea;
    }

    /**
     * @param hallArea the hallArea to set
     */
    public void setHallArea(Double hallArea) {
        this.hallArea = hallArea;
    }

    /**
     * @return the landArea
     */
    public Double getLandArea() {
        return landArea;
    }

    /**
     * @param landArea the landArea to set
     */
    public void setLandArea(Double landArea) {
        this.landArea = landArea;
    }

    /**
     * @return the rentArea
     */
    public Double getRentArea() {
        return rentArea;
    }

    /**
     * @param rentArea the rentArea to set
     */
    public void setRentArea(Double rentArea) {
        this.rentArea = rentArea;
    }

    /**
     * @return the sellPrice
     */
    public Double getSellPrice() {
        return sellPrice;
    }

    /**
     * @param sellPrice the sellPrice to set
     */
    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * @return the sellPriceSquareMeter
     */
    public Double getSellPriceSquareMeter() {
        return sellPriceSquareMeter;
    }

    /**
     * @param sellPriceSquareMeter the sellPriceSquareMeter to set
     */
    public void setSellPriceSquareMeter(Double sellPriceSquareMeter) {
        this.sellPriceSquareMeter = sellPriceSquareMeter;
    }

    /**
     * @return the rentPrice
     */
    public Double getRentPrice() {
        return rentPrice;
    }

    /**
     * @param rentPrice the rentPrice to set
     */
    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    /**
     * @return the rentPriceSquareMeter
     */
    public Double getRentPriceSquareMeter() {
        return rentPriceSquareMeter;
    }

    /**
     * @param rentPriceSquareMeter the rentPriceSquareMeter to set
     */
    public void setRentPriceSquareMeter(Double rentPriceSquareMeter) {
        this.rentPriceSquareMeter = rentPriceSquareMeter;
    }

    /**
     * @return the idOwnership
     */
    public Long getIdOwnership() {
        return idOwnership;
    }

    /**
     * @param idOwnership the idOwnership to set
     */
    public void setIdOwnership(Long idOwnership) {
        this.idOwnership = idOwnership;
    }

    /**
     * @return the elevatorTong
     */
    public Double getElevatorTong() {
        return elevatorTong;
    }

    /**
     * @param elevatorTong the elevatorTong to set
     */
    public void setElevatorTong(Double elevatorTong) {
        this.elevatorTong = elevatorTong;
    }

    /**
     * @return the idSecurity
     */
    public Long getIdSecurity() {
        return idSecurity;
    }

    /**
     * @param idSecurity the idSecurity to set
     */
    public void setIdSecurity(Long idSecurity) {
        this.idSecurity = idSecurity;
    }

    /**
     * @return the idNumberStoreys
     */
    public Long getIdNumberStoreys() {
        return idNumberStoreys;
    }

    /**
     * @param idNumberStoreys the idNumberStoreys to set
     */
    public void setIdNumberStoreys(Long idNumberStoreys) {
        this.idNumberStoreys = idNumberStoreys;
    }

    /**
     * @return the isStation
     */
    public Integer getIsStation() {
        return isStation;
    }

    /**
     * @param isStation the isStation to set
     */
    public void setIsStation(Integer isStation) {
        this.isStation = isStation;
    }

    /**
     * @return the idStation
     */
    public Long getIdStation() {
        return idStation;
    }

    /**
     * @param idStation the idStation to set
     */
    public void setIdStation(Long idStation) {
        this.idStation = idStation;
    }

    /**
     * @return the isLeasedLine
     */
    public Integer getIsLeasedLine() {
        return isLeasedLine;
    }

    /**
     * @param isLeasedLine the isLeasedLine to set
     */
    public void setIsLeasedLine(Integer isLeasedLine) {
        this.isLeasedLine = isLeasedLine;
    }

    /**
     * @return the countNumbers
     */
    public Integer getCountNumbers() {
        return countNumbers;
    }

    /**
     * @param countNumbers the countNumbers to set
     */
    public void setCountNumbers(Integer countNumbers) {
        this.countNumbers = countNumbers;
    }

    /**
     * @return the countFreePairs
     */
    public Integer getCountFreePairs() {
        return countFreePairs;
    }

    /**
     * @param countFreePairs the countFreePairs to set
     */
    public void setCountFreePairs(Integer countFreePairs) {
        this.countFreePairs = countFreePairs;
    }

    /**
     * @return the datePuttingHouse
     */
    public Date getDatePuttingHouse() {
        return datePuttingHouse;
    }

    /**
     * @param datePuttingHouse the datePuttingHouse to set
     */
    public void setDatePuttingHouse(Date datePuttingHouse) {
        this.datePuttingHouse = datePuttingHouse;
    }

    /**
     * @return the stead
     */
    public Integer getStead() {
        return stead;
    }

    /**
     * @param stead the stead to set
     */
    public void setStead(Integer stead) {
        this.stead = stead;
    }

    /**
     * @return the roomCount
     */
    public Integer getRoomCount() {
        return roomCount;
    }

    /**
     * @param roomCount the roomCount to set
     */
    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    /**
     * @return the idRoof
     */
    public Long getIdRoof() {
        return idRoof;
    }

    /**
     * @param idRoof the idRoof to set
     */
    public void setIdRoof(Long idRoof) {
        this.idRoof = idRoof;
    }

    /**
     * @return the idYard
     */
    public Long getIdYard() {
        return idYard;
    }

    /**
     * @param idYard the idYard to set
     */
    public void setIdYard(Long idYard) {
        this.idYard = idYard;
    }

    /**
     * @return the idEntry
     */
    public Long getIdEntry() {
        return idEntry;
    }

    /**
     * @param idEntry the idEntry to set
     */
    public void setIdEntry(Long idEntry) {
        this.idEntry = idEntry;
    }

    /**
     * @return the hasGarage
     */
    public Integer getHasGarage() {
        return hasGarage;
    }

    /**
     * @param hasGarage the hasGarage to set
     */
    public void setHasGarage(Integer hasGarage) {
        this.hasGarage = hasGarage;
    }

    /**
     * @return the idSewerage
     */
    public Long getIdSewerage() {
        return idSewerage;
    }

    /**
     * @param idSewerage the idSewerage to set
     */
    public void setIdSewerage(Long idSewerage) {
        this.idSewerage = idSewerage;
    }

    /**
     * @return the urlLayout
     */
    public String getUrlLayout() {
        return urlLayout;
    }

    /**
     * @param urlLayout the urlLayout to set
     */
    public void setUrlLayout(String urlLayout) {
        this.urlLayout = urlLayout;
    }

    /**
     * @return the steadX
     */
    public Integer getSteadX() {
        return steadX;
    }

    /**
     * @param steadX the steadX to set
     */
    public void setSteadX(Integer steadX) {
        this.steadX = steadX;
    }

    /**
     * @return the steadY
     */
    public Integer getSteadY() {
        return steadY;
    }

    /**
     * @param steadY the steadY to set
     */
    public void setSteadY(Integer steadY) {
        this.steadY = steadY;
    }

    /**
     * @return the idApartment
     */
    public Long getIdApartment() {
        return idApartment;
    }

    /**
     * @param idApartment the idApartment to set
     */
    public void setIdApartment(Long idApartment) {
        this.idApartment = idApartment;
    }

    /**
     * @return the prepayment
     */
    public Integer getPrepayment() {
        return prepayment;
    }

    /**
     * @param prepayment the prepayment to set
     */
    public void setPrepayment(Integer prepayment) {
        this.prepayment = prepayment;
    }

    /**
     * @return the idFurniture
     */
    public Long getIdFurniture() {
        return idFurniture;
    }

    /**
     * @param idFurniture the idFurniture to set
     */
    public void setIdFurniture(Long idFurniture) {
        this.idFurniture = idFurniture;
    }

    /**
     * @return the aboutFurniture
     */
    public String getAboutFurniture() {
        return aboutFurniture;
    }

    /**
     * @param aboutFurniture the aboutFurniture to set
     */
    public void setAboutFurniture(String aboutFurniture) {
        this.aboutFurniture = aboutFurniture;
    }

    /**
     * @return the hasPhone
     */
    public Integer getHasPhone() {
        return hasPhone;
    }

    /**
     * @param hasPhone the hasPhone to set
     */
    public void setHasPhone(Integer hasPhone) {
        this.hasPhone = hasPhone;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the elevators
     */
    public Elevator[] getElevators() {
        return elevators;
    }

    /**
     * @param elevators the elevators to set
     */
    public void setElevators(Elevator[] elevators) {
        this.elevators = elevators;
    }

    /**
     * @return the windows
     */
    public Window[] getWindows() {
        return windows;
    }

    /**
     * @param windows the windows to set
     */
    public void setWindows(Window[] windows) {
        this.windows = windows;
    }

    /**
     * @return the typeRent
     */
    public TypeRent[] getTypeRent() {
        return typeRent;
    }

    /**
     * @param typeRent the typeRent to set
     */
    public void setTypeRent(TypeRent[] typeRent) {
        this.typeRent = typeRent;
    }

    /**
     * @return the isGarden
     */
    public Integer getIsGarden() {
        return isGarden;
    }

    /**
     * @param isGarden the isGarden to set
     */
    public void setIsGarden(Integer isGarden) {
        this.isGarden = isGarden;
    }

    /**
     * @return the communication
     */
    public Communication[] getCommunication() {
        return communication;
    }

    /**
     * @param communication the communication to set
     */
    public void setCommunication(Communication[] communication) {
        this.communication = communication;
    }

    /**
     * @return the communicationPossible
     */
    public Communication[] getCommunicationPossible() {
        return communicationPossible;
    }

    /**
     * @param communicationPossible the communicationPossible to set
     */
    public void setCommunicationPossible(Communication[] communicationPossible) {
        this.communicationPossible = communicationPossible;
    }

    /**
     * @return the profile
     */
    public Profile[] getProfile() {
        return profile;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(Profile[] profile) {
        this.profile = profile;
    }

    /**
     * @return the commercialPayment
     */
    public CommercialPayment[] getCommercialPayment() {
        return commercialPayment;
    }

    /**
     * @param commercialPayment the commercialPayment to set
     */
    public void setCommercialPayment(CommercialPayment[] commercialPayment) {
        this.commercialPayment = commercialPayment;
    }

    /**
     * @return the commercialCommunication
     */
    public Communication[] getCommercialCommunication() {
        return commercialCommunication;
    }

    /**
     * @param commercialCommunication the commercialCommunication to set
     */
    public void setCommercialCommunication(Communication[] commercialCommunication) {
        this.commercialCommunication = commercialCommunication;
    }

    /**
     * @return the liftingEquipment
     */
    public LiftingEquipment[] getLiftingEquipment() {
        return liftingEquipment;
    }

    /**
     * @param liftingEquipment the liftingEquipment to set
     */
    public void setLiftingEquipment(LiftingEquipment[] liftingEquipment) {
        this.liftingEquipment = liftingEquipment;
    }
}
