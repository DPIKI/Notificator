/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpiki.notificator.network.gson;

import java.util.ArrayList;
import java.util.Map;


public class DirectoryUtils {

    public String[] getValues(Map<Long, String> type) {
        String[] rezalts = new String[type.size()];
        rezalts = type.values().toArray(rezalts);
        return rezalts;
    }

    public AdministrativeDistrict[] getValuesTypeAdministrativeDistrict(Map<Long, AdministrativeDistrict> typeAdministrativeDistrict) {
        AdministrativeDistrict[] rezalts = new AdministrativeDistrict[typeAdministrativeDistrict.size()];
        rezalts = typeAdministrativeDistrict.values().toArray(rezalts);
        return rezalts;
    }

    public TypeDistrict[] getValuesTypeDistrict(Map<Long, TypeDistrict> typeDistrict) {
        TypeDistrict[] rezalts = new TypeDistrict[typeDistrict.size()];
        rezalts = typeDistrict.values().toArray(rezalts);
        return rezalts;
    }

    public String[] getSubDistricts(Map<Long, TypeDistrict> typeDistrict) {
        TypeDistrict[] districts = getValuesTypeDistrict(typeDistrict);
        ArrayList<String> rez = new ArrayList(districts.length);
        for (TypeDistrict d : districts) {
            if (d.getSubdistrict().isEmpty() == false) {
                rez.add(d.getSubdistrict());
            }
        }
        rez.trimToSize();

        String[] rezalt = new String[rez.size()];
        rezalt = rez.toArray(rezalt);
        return rezalt;

    }

}
