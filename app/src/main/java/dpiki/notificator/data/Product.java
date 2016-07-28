package dpiki.notificator.data;

/**
 * Created by prog1 on 28.07.2016.
 */
public class Product {
    public Integer id;
    public String name;
    public String creationDate;

    public Product(Integer id, String name, String creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }
}
