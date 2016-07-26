package dpiki.notificator;

import dpiki.notificator.data.Client;

/**
 * Created by User on 09.07.2016.
 */
public interface OnCardViewClickListener {
    void onCardViewClicked(Client client, int position);
}
