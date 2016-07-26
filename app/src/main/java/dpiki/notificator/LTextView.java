package dpiki.notificator;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by prog1 on 26.07.2016.
 */
public class LTextView extends TextView {
    public static Typeface typeface;

    public LTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (typeface == null) {
            String fontPath = "fonts/SFtextregular.ttf";
            typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
        }
        setTypeface(typeface);
    }
}
