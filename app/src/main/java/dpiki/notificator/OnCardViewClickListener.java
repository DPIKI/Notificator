package dpiki.notificator;

import dpiki.notificator.data.MarketClient;

/**
 * Created by User on 09.07.2016.
 */
public interface OnCardViewClickListener {
    void onCardViewClicked(MarketClient client, int position);
}
