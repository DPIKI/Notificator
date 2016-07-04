package dpiki.notificator.data;

/**
 * Created by Lenovo on 01.07.2016.
 */
public class Recommendation {
    public MarketClient client;
    public Phone phone;

    public Recommendation(MarketClient client, Phone phone) {
        this.client = client;
        this.phone = phone;
    }
}
