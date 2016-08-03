package dpiki.notificator;

import dpiki.notificator.data.Requirement;

/**
 * Created by User on 09.07.2016.
 */
public interface OnViewClickListener {
    void onViewClicked(Requirement requirement, int position);
}
