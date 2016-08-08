package dpiki.notificator.network.gson;

public class Helper {

    public static String ArrayToString(Object[] array) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            str.append(":{");
            str.append(array[i].toString());
            str.append("}");
            if (i < array.length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }
}
