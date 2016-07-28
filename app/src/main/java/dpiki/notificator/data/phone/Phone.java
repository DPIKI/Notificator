package dpiki.notificator.data.phone;

import java.util.Date;

import dpiki.notificator.data.Product;

/**
 * Created by Lenovo on 25.07.2016.
 */
public class Phone extends Product {
    public String param1;
    public String param2;
    public String param3;

    public Phone(Integer id, String name, String creationDate, String param1, String param2, String param3) {
        super(id, name, creationDate);
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }
}
