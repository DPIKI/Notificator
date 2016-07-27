package dpiki.notificator.data.phone;

import dpiki.notificator.data.Client;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.phone.Phone;

/**
 * Created by Lenovo on 26.07.2016.
 */
public class PhoneRecommendation extends Recommendation {
    public Phone phone;

    public PhoneRecommendation(Client client, Phone phone) {
        super(client);
        this.phone = phone;
    }
}
