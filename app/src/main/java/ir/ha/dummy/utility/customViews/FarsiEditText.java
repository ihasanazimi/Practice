package ir.ha.dummy.utility.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;

import ir.ha.dummy.R;

public class FarsiEditText extends AppCompatEditText {


    private static Typeface defaultTypeface;

    public FarsiEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FarsiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public FarsiEditText(Context context) {
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
//            setGravity(Gravity.RIGHT);
            //setTextSize(getTextSize()*scale);

        }
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        final InputConnection ic = super.onCreateInputConnection(editorInfo);
        EditorInfoCompat.setContentMimeTypes(editorInfo, new String[]{"image/*", "image/png", "image/gif", "image/jpeg"});

        if (ic != null) {
            return InputConnectionCompat.createWrapper(ic, editorInfo,
                    (inputContentInfo, flags, opts) -> {
//                        Toast.makeText(getContext(), getContext().getString(R.string.no_gif_supported), Toast.LENGTH_SHORT).show();
                        return true;
                    });
        }
        return null;
    }
}