package dpiki.notificator.data;

/**
 * Created by Lenovo on 26.07.2016.
 */
public class Recommendation {
    public Client client;
    public Product product;

    public Recommendation(Client client, Product product) {
        this.client = client;
        this.product = product;
    }
}
