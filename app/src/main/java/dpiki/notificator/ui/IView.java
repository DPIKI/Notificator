package dpiki.notificator.ui;

import java.util.List;

/**
 * Created by Lenovo on 12.08.2016.
 */
public interface IView {
    void showMessage(String message);
    void showInvalidSync();
    void showRequisitions(List<RequisitionInfoContainer> requisitions);
    void showProgress();

    class RequisitionInfoContainer {
        public long id;
        public String type;
        public String fio;
        public int unread;

        public RequisitionInfoContainer(long id, String type, String fio, int unread) {
            this.id = id;
            this.type = type;
            this.fio = fio;
            this.unread = unread;
        }
    }
}
