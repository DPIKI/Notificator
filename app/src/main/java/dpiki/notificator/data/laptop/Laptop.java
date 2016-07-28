package dpiki.notificator.data.laptop;

import java.util.Date;

import dpiki.notificator.data.Product;

/**
 * Created by Lenovo on 25.07.2016.
 */
public class Laptop extends Product {
    public String param11;
    public String param12;
    public String param13;
    public String param14;

    public Laptop(Integer id, String name, String creationDate, String param11, String param12, String param13, String param14) {
        super(id, name, creationDate);
        this.param11 = param11;
        this.param12 = param12;
        this.param13 = param13;
        this.param14 = param14;
    }
}
