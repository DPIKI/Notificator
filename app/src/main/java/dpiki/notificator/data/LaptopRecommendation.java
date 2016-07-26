package dpiki.notificator.data;

/**
 * Created by Lenovo on 26.07.2016.
 */
public class LaptopRecommendation extends Recommendation {
    public Laptop laptop;

    public LaptopRecommendation(Client client, Laptop laptop) {
        super(client);
        this.laptop = laptop;
    }
}
