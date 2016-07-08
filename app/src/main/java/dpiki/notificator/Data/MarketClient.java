package dpiki.notificator.data;

/**
 * Created by Lenovo on 01.07.2016.
 */
public class MarketClient {
    Integer id;
    String name;
    String pref1;
    String pref2;
    String pref3;
    Integer unreadNotificationCount;

    public MarketClient(Integer id, String name, String pref1,
                        String pref2, String pref3, Integer unreadNotificationCount) {
        this.id = id;
        this.name = name;
        this.pref1 = pref1;
        this.pref2 = pref2;
        this.pref3 = pref3;
        this.unreadNotificationCount = unreadNotificationCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPref1() {
        return pref1;
    }

    public void setPref1(String pref1) {
        this.pref1 = pref1;
    }

    public String getPref2() {
        return pref2;
    }

    public void setPref2(String pref2) {
        this.pref2 = pref2;
    }

    public String getPref3() {
        return pref3;
    }

    public void setPref3(String pref3) {
        this.pref3 = pref3;
    }

    public Integer getUnreadNotificationCount() {
        return this.unreadNotificationCount;
    }

    public void setUnreadNotificationCount(int unreadNotificationCount) {
        this.unreadNotificationCount = unreadNotificationCount;
    }

}
