package com.example.nhuviet.hockontlin_synthetic_signin.CutomView;

/**
 * Created by nhuvi on 07/08/2017.
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.nhuviet.hockontlin_synthetic_signin.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by nhuvi on 16/05/2017.
 */

@SuppressLint("AppCompatCustomView")
public class PassWork_EditText  extends EditText {
    Drawable eye, eyestrike;
    Boolean visible = false;
    Boolean useStrike = false;
    Drawable drawable;
    Boolean usevalidata = false;
    int ALPHA = (int) (255 * .70f);
    String MATCHER_PATERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Pattern pattern;
    Matcher matcher;

    public PassWork_EditText(Context context) {
        super(context);
        khoitao(null);
    }

    public PassWork_EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao(attrs);
    }

    public PassWork_EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao(attrs);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PassWork_EditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao(attrs);
    }
    private void khoitao(AttributeSet attributeSet){

        this.pattern = Pattern.compile(MATCHER_PATERN);

        if(attributeSet != null) {
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.PassWord_EditText, 0, 0);
            this.useStrike = array.getBoolean(R.styleable.PassWord_EditText_useeyeTrike,false);
            this.usevalidata = array.getBoolean(R.styleable.PassWord_EditText_usevalidata,false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyestrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

        if (this.usevalidata){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) v.getParentForAccessibility();
                        matcher = pattern.matcher(chuoi);
                        if(!matcher.matches()){
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu bao gồm 6-20 kí tự, một số và một chữ hoa");
                        }else {
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");

                        }
                    }
                }
            });
        }
        caidat();

    }

    private void caidat (){
        setInputType(InputType.TYPE_CLASS_TEXT | (visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD ));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible? eyestrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[0],drawable,drawables[0]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())){
            visible = !visible;
            caidat();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}