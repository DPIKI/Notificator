package dpiki.notificator.data.laptop;

import dpiki.notificator.data.Client;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.laptop.Laptop;

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
