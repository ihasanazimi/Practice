package ir.ha.myapplication.utility.costumviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

/**
 * Created by Amin Amini on 6/11/18.
 */

public class RoundedImageView extends AppCompatImageView {
    public RoundedImageView(Context context) {
        super(context);
        init(null, null);
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }



    @BindingAdapter("corner")
    public static void setCorner(RoundedImageView v, float r){
        v.setCorners(r);
    }

    public void setCorners(float r){
        setCorners(r,r,r,r);
    }
    float[] radii = new float[]{0,0,0,0,0,0,0,0};
    public void setCorners(float lt, float rt, float rb, float lb){
        radii[0] = radii[1] = lt;
        radii[2] = radii[3] = rt;
        radii[4] = radii[5] = lb;
        radii[6] = radii[7] = rb;
    }


    void init(Context context, AttributeSet attrs){
        if(attrs != null){
//            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView);
//            setCorners(a.getDimension(R.styleable.RoundedImageView_riv_cornerSize, 0f));
//            a.recycle();
        }
    }

    private final Path clipPath = new Path();
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        clipPath.reset();
        clipPath.addRoundRect(new RectF(canvas.getClipBounds()), radii , Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }

}
