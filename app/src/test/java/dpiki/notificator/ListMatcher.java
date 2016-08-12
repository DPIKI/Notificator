package dpiki.notificator;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.List;

public class ListMatcher<T> extends BaseMatcher<List<T>> {
        List<T> toCompare;

        public ListMatcher(List<T> toCompare) {
            this.toCompare = toCompare;
        }

        @Override
        public boolean matches(Object item) {
            if (!(item instanceof List))
                return false;


            List<T> testList = (List<T>) item;
            if (testList.size() != toCompare.size())
                return false;

            for (T i : testList) {
                boolean flag = false;
                for (T j : toCompare) {
                    if (j.equals(i)) {
                        flag = true;
                        break;
                    }
                }

                if (!flag)
                    return false;
            }

            return true;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText(toCompare.toString());
        }
    }
