package dpiki.notificator.network.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory {

    private Directory() {
    }

    static Directory instace = null;

    private Map<Long, String> typeApartment;
    private Map<Long, String> typeBalcony;
    private Map<Long, String> typeBathroom;

    private Map<Long, String> typeCities;
    private Map<Long, String> typeComfort;
    private Map<Long, String> typeCommercialCommunication;
    private Map<Long, String> typeCommercialPayment;
    private Map<Long, String> typeCommunication;
    private Map<Long, TypeDistrict> typeDistrict;
    private Map<Long, String> typeElevator;
    private Map<Long, String> typeEntry;
    private Map<Long, String> typeFinishBuild;

    private Map<Long, String> typeFloors;
    private Map<Long, String> typeFund;
    private Map<Long, String> typeFurniture;
    private Map<Long, String> typeHeating;
    private Map<Long, String> typeHotWater;
    private Map<Long, String> typeLiftingEquipment;
    private Map<Long, String> typeLocation;
    private Map<Long, String> typeLoggia;
    private Map<Long, String> typeNumberStoreys;
    private Map<Long, String> typeOwnership;
    private Map<Long, String> typeProfile;
    private Map<Long, String> typeRealestateState;

    private Map<Long, String> typeRealestateStatus;
    private Map<Long, String> typeRent;
    private Map<Long, String> typeRoof;
    private Map<Long, String> typeSecurity;
    private Map<Long, String> typeSewerage;

    private Map<Long, String> typeSources;
    private Map<Long, String> typeStation;
    private Map<Long, String> typeWallMaterialApartment;
    private Map<Long, String> typeWallMaterialHouseholds;

    private Map<Long, String> typeWindowMaterial;
    private Map<Long, String> typeWindows;
    private Map<Long, String> typeYard;

    private Map<Long, String> typeInfrastructure;

    private Map<Long, AdministrativeDistrict> typeAdministrativeDistrict;

    DirectoryUtils utils = new DirectoryUtils();

    public String[] getValues(Map<Long, String> type) {
        return utils.getValues(type);
    }

    private final JsonParser parser = new JsonParser();

    public static Directory getInstance() {
        if (instace == null) {
            instace = new Directory();
        }
        return instace;
    }

    private Map<Long, String> parse(JsonElement element, String firstField, String secondField) {
        Map<Long, String> map = new HashMap<>();
        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            int size = array.size();
            for (int i = 0; i < size; i++) {
                if (array.get(i).isJsonObject()) {
                    JsonObject jObject = (JsonObject) array.get(i);
                    try {
                        Long id = Long.parseLong(jObject.get(firstField).getAsString());
                        String value = jObject.get(secondField).getAsString();
                        map.put(id, value);
                    } catch (Exception ex) {

                    }
                }
            }
        }
        return map;
    }

    private Map<Long, AdministrativeDistrict> parseAdministrativeDistrict(JsonElement element) {
        Map<Long, AdministrativeDistrict> map = new HashMap<>();
        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            int size = array.size();
            for (int i = 0; i < size; i++) {
                if (array.get(i).isJsonObject()) {
                    JsonObject jObject = (JsonObject) array.get(i);
                    try {

                        Long id = Long.parseLong(jObject.get("id").getAsString());
                        Long idCity = Long.parseLong(jObject.get("id_city").getAsString());
                        String administrativeDistrict = jObject.get("administrative_district").getAsString();
                        map.put(id, new AdministrativeDistrict(idCity, administrativeDistrict));
                    } catch (Exception ex) {

                    }
                }
            }
        }
        return map;
    }

    private Map<Long, TypeDistrict> parseTypeDistrict(JsonElement element) {
        Map<Long, TypeDistrict> map = new HashMap<>();
        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            int size = array.size();
            for (int i = 0; i < size; i++) {
                if (array.get(i).isJsonObject()) {
                    JsonObject jObject = (JsonObject) array.get(i);
                    try {

                        Long id = Long.parseLong(jObject.get("id").getAsString());
                        String district = jObject.get("district").getAsString();
                        String subdistrict = jObject.get("subdistrict").getAsString();
                        map.put(id, new TypeDistrict(id, district, subdistrict));
                    } catch (Exception ex) {

                    }
                }
            }
        }
        return map;
    }

//    private Map<Long, Map<String, String>> parseDistrict(JsonElement element, String firstField, String... secondField) {
//        Map<Long, Map<String, String>> map = new HashMap();
//        if (element.isJsonArray()) {
//            JsonArray array = element.getAsJsonArray();
//            int size = array.size();
//            for (int i = 0; i < size; i++) {
//                if (array.get(i).isJsonObject()) {
//                    JsonObject jObject = (JsonObject) array.get(i);
//                    try {
//                        Long id = Long.parseLong(jObject.get(firstField).getAsString());
//                        HashMap<String, String> buf = new HashMap();
//                        for (int j = 0; j < secondField.length; j++) {
//                            //value += "," + jObject.get(secondField[j]).getAsString();
//                            buf.put(secondField[j], jObject.get(secondField[j]).getAsString());
//                        }
//                         map.put(id, buf);
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        }
//        return map;
//    }
//    private Map<Long, String> parseDistrict(JsonElement element, String firstField, String... secondField) {
//        Map<Long, String> map = new HashMap<>();
//        if (element.isJsonArray()) {
//            JsonArray array = element.getAsJsonArray();
//            int size = array.size();
//            for (int i = 0; i < size; i++) {
//                if (array.get(i).isJsonObject()) {
//                    JsonObject jObject = (JsonObject) array.get(i);
//                    try {
//                        Long id = Long.parseLong(jObject.get(firstField).getAsString());
//                        String value = "";
//                        for (int j = 0; j < secondField.length; j++) {
//                            value += "," + jObject.get(secondField[j]).getAsString();
//                        }
//                        map.put(id, value);
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        }
//        return map;
//    }
    public void parseDirectory(String json) {
        JsonElement element = parser.parse(json);
        if (element.isJsonObject()) {
            JsonObject rootObject = (JsonObject) element;

            JsonElement jsonElement = rootObject.get("type_administrative_district");

            typeAdministrativeDistrict = parseAdministrativeDistrict(jsonElement);

            jsonElement = rootObject.get("type_apartment");
            typeApartment = parse(jsonElement, "id", "type_apartment");

            jsonElement = rootObject.get("type_balcony");
            typeBalcony = parse(jsonElement, "id", "balcony");

            jsonElement = rootObject.get("type_bathroom");
            typeBathroom = parse(jsonElement, "id", "bathroom");

            jsonElement = rootObject.get("type_cities");
            typeCities = parse(jsonElement, "id", "city");

            jsonElement = rootObject.get("type_comfort");
            typeComfort = parse(jsonElement, "id", "comfort");

            jsonElement = rootObject.get("type_comfort");
            typeComfort = parse(jsonElement, "id", "comfort");

            jsonElement = rootObject.get("type_commercial_communication");
            typeCommercialCommunication = parse(jsonElement, "id", "commercial_communication");

            jsonElement = rootObject.get("type_commercial_payment");
            typeCommercialPayment = parse(jsonElement, "id", "commercial_payment");

            jsonElement = rootObject.get("type_communication");
            typeCommunication = parse(jsonElement, "id", "communication");

            jsonElement = rootObject.get("type_district");
            typeDistrict = parseTypeDistrict(jsonElement);

            jsonElement = rootObject.get("type_elevator");
            typeElevator = parse(jsonElement, "id", "elevator");

            jsonElement = rootObject.get("type_entry");
            typeEntry = parse(jsonElement, "id", "entry");

            jsonElement = rootObject.get("type_finish_build");
            typeFinishBuild = parse(jsonElement, "id", "finish_build");

            jsonElement = rootObject.get("type_floors");
            typeFloors = parse(jsonElement, "id", "floors");

            jsonElement = rootObject.get("type_fund");
            typeFund = parse(jsonElement, "id", "fund");

            jsonElement = rootObject.get("type_furniture");
            typeFurniture = parse(jsonElement, "id", "furniture");

            jsonElement = rootObject.get("type_heating");
            typeHeating = parse(jsonElement, "id", "heating");

            jsonElement = rootObject.get("type_hot_water");
            typeHotWater = parse(jsonElement, "id", "hot_water");

            jsonElement = rootObject.get("type_lifting_equipment");
            typeLiftingEquipment = parse(jsonElement, "id", "lifting_equipment");

            jsonElement = rootObject.get("type_location");
            typeLocation = parse(jsonElement, "id", "location");

            jsonElement = rootObject.get("type_loggia");
            typeLoggia = parse(jsonElement, "id", "loggia");

            jsonElement = rootObject.get("type_number_storeys");
            typeNumberStoreys = parse(jsonElement, "id", "number_storeys");

            jsonElement = rootObject.get("type_ownership");
            typeOwnership = parse(jsonElement, "id", "ownership");

            jsonElement = rootObject.get("type_profile");
            typeProfile = parse(jsonElement, "id", "profile");

            jsonElement = rootObject.get("type_realestate_state");
            typeRealestateState = parse(jsonElement, "id", "state");

            jsonElement = rootObject.get("type_realestate_status");
            typeRealestateStatus = parse(jsonElement, "id", "status");

            jsonElement = rootObject.get("type_rent");
            typeRent = parse(jsonElement, "id", "type_rent");

            jsonElement = rootObject.get("type_roof");
            typeRoof = parse(jsonElement, "id", "roof");

            jsonElement = rootObject.get("type_security");
            typeSecurity = parse(jsonElement, "id", "security");

            jsonElement = rootObject.get("type_sewerage");
            typeSewerage = parse(jsonElement, "id", "sewerage");

            jsonElement = rootObject.get("type_sources");
            typeSources = parse(jsonElement, "id", "source");

            jsonElement = rootObject.get("type_station");
            typeStation = parse(jsonElement, "id", "station");

            jsonElement = rootObject.get("type_wall_material_apartment");
            typeWallMaterialApartment = parse(jsonElement, "id", "wall_material");

            jsonElement = rootObject.get("type_wall_material_households");
            typeWallMaterialHouseholds = parse(jsonElement, "id", "wall_material");

            jsonElement = rootObject.get("type_window_material");
            typeWindowMaterial = parse(jsonElement, "id", "window_material");

            jsonElement = rootObject.get("type_windows");
            typeWindows = parse(jsonElement, "id", "windows");

            jsonElement = rootObject.get("type_yard");
            typeYard = parse(jsonElement, "id", "yard");

            jsonElement = rootObject.get("type_infrastructure");
            typeInfrastructure = parse(jsonElement, "id", "infrastructure");

        }

    }

    public Map<Long, String> getTypeApartment() {
        return typeApartment;
    }

    public Map<Long, String> getTypeBalcony() {
        return typeBalcony;
    }

    public Map<Long, String> getTypeBathroom() {
        return typeBathroom;
    }

    public Map<Long, String> getTypeCities() {
        return typeCities;
    }

    public Map<Long, String> getTypeComfort() {
        return typeComfort;
    }

    public Map<Long, String> getTypeCommercialCommunication() {
        return typeCommercialCommunication;
    }

    public Map<Long, String> getTypeCommercialPayment() {
        return typeCommercialPayment;
    }

    public Map<Long, String> getTypeCommunication() {
        return typeCommunication;
    }

    public Map<Long, TypeDistrict> getTypeDistrict() {
        return typeDistrict;
    }

    public Map<Long, String> getTypeElevator() {
        return typeElevator;
    }

    public Map<Long, String> getTypeEntry() {
        return typeEntry;
    }

    public Map<Long, String> getTypeFinishBuild() {
        return typeFinishBuild;
    }

    public Map<Long, String> getTypeFloors() {
        return typeFloors;
    }

    public Map<Long, String> getTypeFund() {
        return typeFund;
    }

    public Map<Long, String> getTypeFurniture() {
        return typeFurniture;
    }

    public Map<Long, String> getTypeHeating() {
        return typeHeating;
    }

    public Map<Long, String> getTypeHotWater() {
        return typeHotWater;
    }

    public Map<Long, String> getTypeLiftingEquipment() {
        return typeLiftingEquipment;
    }

    public Map<Long, String> getTypeLocation() {
        return typeLocation;
    }

    public Map<Long, String> getTypeLoggia() {
        return typeLoggia;
    }

    public Map<Long, String> getTypeNumberStoreys() {
        return typeNumberStoreys;
    }

    public Map<Long, String> getTypeOwnership() {
        return typeOwnership;
    }

    public Map<Long, String> getTypeProfile() {
        return typeProfile;
    }

    public Map<Long, String> getTypeRealestateState() {
        return typeRealestateState;
    }

    public Map<Long, String> getTypeRealestateStatus() {
        return typeRealestateStatus;
    }

    public Map<Long, String> getTypeRent() {
        return typeRent;
    }

    public Map<Long, String> getTypeRoof() {
        return typeRoof;
    }

    public Map<Long, String> getTypeSecurity() {
        return typeSecurity;
    }

    public Map<Long, String> getTypeSewerage() {
        return typeSewerage;
    }

    public Map<Long, String> getTypeSources() {
        return typeSources;
    }

    public Map<Long, String> getTypeStation() {
        return typeStation;
    }

    public Map<Long, String> getTypeWallMaterialApartment() {
        return typeWallMaterialApartment;
    }

    public Map<Long, String> getTypeWallMaterialHouseholds() {
        return typeWallMaterialHouseholds;
    }

    public Map<Long, String> getTypeWindowMaterial() {
        return typeWindowMaterial;
    }

    public Map<Long, String> getTypeWindows() {
        return typeWindows;
    }

    public Map<Long, String> getTypeYard() {
        return typeYard;
    }

    public Map<Long, AdministrativeDistrict> getTypeAdministrativeDistrict() {
        return typeAdministrativeDistrict;
    }

    public Map<Long, String> getTypeInfrastructure() {
        return typeInfrastructure;
    }

    public AdministrativeDistrict[] getValuesTypeAdministrativeDistrict() {
        return utils.getValuesTypeAdministrativeDistrict(typeAdministrativeDistrict);
    }

    public TypeDistrict[] getValuesTypeDistrict() {
        return utils.getValuesTypeDistrict(typeDistrict);
    }

    public String[] getSubDistricts() {
        return utils.getSubDistricts(typeDistrict);
    }

}
