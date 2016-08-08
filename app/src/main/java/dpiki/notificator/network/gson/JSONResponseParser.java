package dpiki.notificator.network.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONResponseParser {

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    public static List<SearchNearContainer> parseSearchNear(String jsonString) {
        List<SearchNearContainer> realestates = new ArrayList<>();

        try {
            SearchNearContainer[] realestateContainers = gson.fromJson(jsonString, SearchNearContainer[].class);
            realestates = Arrays.asList(realestateContainers);
        } catch (Exception ex) {
        }
        for (SearchNearContainer snc : realestates) {
            if (snc.getRealestate().getRealestateInstanceType().contains("Apartment")) {
                snc.getRealestateType().setTypeInfo(RealestateInfoType.APPARTMENT.ordinal());
            }//else
            if (snc.getRealestate().getRealestateInstanceType().contains("Rent")) {
                snc.getRealestateType().setTypeInfo(RealestateInfoType.RENT.ordinal());
            }//else
            if (snc.getRealestate().getRealestateInstanceType().contains("Land")) {
                snc.getRealestateType().setTypeInfo(RealestateInfoType.LAND.ordinal());
            }//else
            if (snc.getRealestate().getRealestateInstanceType().contains("Households")) {
                snc.getRealestateType().setTypeInfo(RealestateInfoType.HOUSEHOLDS.ordinal());
            }//else
            if (snc.getRealestate().getRealestateInstanceType().contains("Commercial")) {
                snc.getRealestateType().setTypeInfo(RealestateInfoType.COMMERCIAL.ordinal());
            }//else
            //{throw  new Exception("type!");}
        }
        return realestates;
    }

    public static List<RealestateContainer> parseRealestateAppartment(String jsonString) {
        List<RealestateContainer> realestates = new ArrayList<>();
        try {
            RealestateContainer[] realestateContainers = gson.fromJson(jsonString, RealestateContainer[].class);
            realestates = Arrays.asList(realestateContainers);
        } catch (Exception ex) {
        }
        for (RealestateContainer snc : realestates) {
            if (snc.getRealestate()[0].getRealestateInstanceType().contains("Appartment")) {
                snc.getRealestateType()[0].setTypeInfo(RealestateInfoType.APPARTMENT.ordinal());
            }
            if (snc.getRealestate()[0].getRealestateInstanceType().contains("Rent")) {
                snc.getRealestateType()[0].setTypeInfo(RealestateInfoType.RENT.ordinal());
            }
            if (snc.getRealestate()[0].getRealestateInstanceType().contains("Land")) {
                snc.getRealestateType()[0].setTypeInfo(RealestateInfoType.LAND.ordinal());
            }
            if (snc.getRealestate()[0].getRealestateInstanceType().contains("Households")) {
                snc.getRealestateType()[0].setTypeInfo(RealestateInfoType.HOUSEHOLDS.ordinal());
            }
            if (snc.getRealestate()[0].getRealestateInstanceType().contains("Commercial")) {
                snc.getRealestateType()[0].setTypeInfo(RealestateInfoType.COMMERCIAL.ordinal());
            }
        }
        return realestates;
    }

    public static List<Office> parseRealestateOffice(String jsonString) {
        List<Office> realestates = new ArrayList<>();
        try {
            Office[] ofices = gson.fromJson(jsonString, Office[].class);
            realestates = Arrays.asList(ofices);
        } catch (Exception ex) {
        }
        return realestates;
    }
//    
//    public static List<RealestateCommercialContainer> parseRealestateCommercial(String jsonString) {
//        List<RealestateCommercialContainer> realestates = new ArrayList<>();
//        try {
//            RealestateCommercialContainer[] realestateContainers = gson.fromJson(jsonString, RealestateCommercialContainer[].class);
//            realestates = Arrays.asList(realestateContainers);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return realestates;
//    }
//    
//    public static List<RealestateHouseholdsContainer> parseRealestateHouseholds(String jsonString) {
//        List<RealestateHouseholdsContainer> realestates = new ArrayList<>();
//        try {
//            RealestateHouseholdsContainer[] realestateContainers = gson.fromJson(jsonString, RealestateHouseholdsContainer[].class);
//            realestates = Arrays.asList(realestateContainers);
//        } catch (Exception ex) {
//        }
//        return realestates;
//    }
//    
//    public static List<RealestateLandContainer> parseRealestateLand(String jsonString) {
//        List<RealestateLandContainer> realestates = new ArrayList<>();
//        try {
//            RealestateLandContainer[] realestateContainers = gson.fromJson(jsonString, RealestateLandContainer[].class);
//            realestates = Arrays.asList(realestateContainers);
//        } catch (Exception ex) {
//        }
//        return realestates;
//    }
//    
//    public static List<RealestateRentContainer> parseRealestateRent(String jsonString) {
//        List<RealestateRentContainer> realestates = new ArrayList<>();
//        try {
//            RealestateRentContainer[] realestateContainers = gson.fromJson(jsonString, RealestateRentContainer[].class);
//            realestates = Arrays.asList(realestateContainers);
//        } catch (Exception ex) {
//        }
//        return realestates;
//    }

    public static List<Employee> parseEmployees(String jsonString) {
        List<Employee> employees = new ArrayList();
        try {
            Employee[] emps = gson.fromJson(jsonString, Employee[].class);
            employees = Arrays.asList(emps);
        } catch (Exception ex) {
        }
        return employees;
    }

}
