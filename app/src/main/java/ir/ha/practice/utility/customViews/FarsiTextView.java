package ir.ha.practice.utility.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;


public class FarsiTextView extends androidx.appcompat.widget.AppCompatTextView {


    public static final float scale = 1.2f; //1.05f; //1.2f;
    private static Typeface defaultTypeface;

    public FarsiTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FarsiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public FarsiTextView(Context context) {
        super(context);
        init();
    }

    public static Typeface getTypeface(Context ctx) {
        if (defaultTypeface == null) {
            defaultTypeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/iransanslight.ttf");
        }
        return defaultTypeface;
    }

    private void init() {
        if (!isInEditMode()) {

            Typeface tf = getTypeface(getContext());
            setTypeface(tf);
            //setTextSize(getTextSize()*scale);
            setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize() * scale);
        }
    }



}