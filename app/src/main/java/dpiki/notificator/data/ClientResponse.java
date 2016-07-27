package dpiki.notificator.data;

import java.util.List;

import dpiki.notificator.data.laptop.LaptopClient;
import dpiki.notificator.data.phone.PhoneClient;


/**
 * Created by Lenovo on 25.07.2016.
 */
public class ClientResponse {
    public List<LaptopClient> laptops;
    public List<PhoneClient> phones;
}
