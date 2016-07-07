package dpiki.notificator;

/**
 * Created by prog1 on 07.07.2016.
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.text.SimpleDateFormat;
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class MyFetcherTest {
    private SimpleDateFormat sdf;

    @Before
    public void setUp() throws Exception {
        sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Test
    public void isMatch_isCorrect() throws Exception{
        
    }


}
