package com.example.nhuviet.hockontlin_synthetic_signin.CutomView;

/**
 * Created by nhuvi on 07/08/2017.
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.nhuviet.hockontlin_synthetic_signin.R;


/**
 * Created by nhuvi on 16/05/2017.
 */

@SuppressLint("AppCompatCustomView")
public class Clear_EditText extends EditText{

    Drawable crossx,nonecrossx;
    Boolean visible = false;
    Drawable drawable;
    public Clear_EditText(Context context) {
        super(context);
        khoitao();
    }

    public Clear_EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao();
    }

    public Clear_EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Clear_EditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao();
    }

    private void khoitao(){
        crossx = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        nonecrossx = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();
        cauhinh();
    }
    private void cauhinh(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible? crossx:nonecrossx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_DOWN  == event.getAction()&& event.getX() >= (getRight() - drawable.getBounds().width())){
            setText("");
        }
        return super.onTouchEvent(event);

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter ==0 && start == 0 ){
            visible = false;
            cauhinh();
        }else {
            visible = true;
            cauhinh();

        }
    }
}