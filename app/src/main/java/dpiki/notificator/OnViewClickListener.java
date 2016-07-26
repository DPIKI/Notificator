package dpiki.notificator;

import dpiki.notificator.data.Client;

/**
 * Created by User on 09.07.2016.
 */
public interface OnViewClickListener {
    void onViewClicked(Client client, int position);
}
