package dpiki.notificator.network.dataobjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Rent extends RealtyBase {
    public Double cost;
    public Integer floor;
    public Integer floorAll;
    public Integer roomCount;
    public Integer prepayment;
    public Integer hasPhone;
    public String dateFreed;
    public Long[] idRent;
    public Long idEntry;
    public Long idTypeApartment;
    public Long idComfort;
    public Long idFurniture;
    public Long idYard;
    public Long idState;

    public Rent() {

    }

    public Rent(Long id, Long idAddress, Integer firm) {
        super(id, idAddress, firm);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof RentReq))
            return false;
        RentReq req = (RentReq) reqBase;

        if (req.idAddress != null && this.idAddress != null
                && !req.idAddress.equals(this.idAddress))
            return false;
        if (req.firm != null && this.firm != null
                && !req.firm.equals(this.firm))
            return false;

        if (req.idTypeApartment != null && this.idTypeApartment != null
                && !req.idTypeApartment.equals(this.idTypeApartment))
            return false;
        if (req.costFrom != null && this.cost != null
                && req.costFrom > this.cost)
            return false;
        if (req.costTo != null && this.cost != null
                && req.costTo < this.cost)
            return false;

        if (req.floorAll != null && this.floorAll != null
                && !req.floorAll.equals(this.floorAll))
            return false;
        if (req.floor != null && this.floor != null
                && !req.floor.equals(this.floor))
            return false;
        if (req.notFirst != null
                && req.notFirst.equals(1)
                && this.floor != null
                && this.floor.equals(1))
            return false;
        if (req.notLast != null
                && req.notLast.equals(1)
                && this.floor != null
                && this.floorAll != null
                && !this.floor.equals(this.floorAll))
            return false;

        if (req.idState != null && this.idState != null
                && !req.idState.equals(this.idState))
            return false;
        if (req.roomCountFrom != null && this.roomCount != null
                && req.roomCountFrom > this.roomCount)
            return false;
        if (req.roomCountTo != null && this.roomCount != null
                && req.roomCountTo < this.roomCount)
            return false;
        if (req.prepayment != null && this.prepayment != null
                && !req.prepayment.equals(this.prepayment))
            return false;
        if (req.idComfort != null && this.idComfort != null
                && !req.idComfort.equals(this.idComfort))
            return false;
        if (req.idFurniture != null && this.idFurniture != null
                && !req.idFurniture.equals(this.idFurniture))
            return false;
        if (req.idYard != null && this.idYard != null
                && !req.idYard.equals(this.idYard))
            return false;
        if (req.idPhone != null && this.hasPhone != null
                && !req.idPhone.equals(this.hasPhone))
            return false;

        if (req.dateFreedFrom != null && this.dateFreed != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dateFreedFrom = null;
            Date dateFreed = null;
            try {
                dateFreedFrom = sdf.parse(req.dateFreedFrom);
                dateFreed = sdf.parse(this.dateFreed);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (dateFreed == null || dateFreedFrom == null){
                return false;
            }

            if (dateFreedFrom.after(dateFreed))
                return false;
        }

        if (req.dateFreedTo != null && this.dateFreed != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dateFreedTo = null;
            Date dateFreed = null;
            try {
                dateFreedTo = sdf.parse(req.dateFreedTo);
                dateFreed = sdf.parse(this.dateFreed);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (dateFreed == null || dateFreedTo == null){
                return false;
            }

            if (dateFreedTo.before(dateFreed))
                return false;
        }

        if (req.idRent != null && this.idRent != null
                && !Arrays.asList(this.idRent).contains(req.idRent))
            return false;
        if (req.idEntry != null && this.idEntry != null
                && !req.idEntry.equals(this.idEntry))
            return false;

        return true;
    }
}
