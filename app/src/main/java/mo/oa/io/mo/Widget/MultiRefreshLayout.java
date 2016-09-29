package mo.oa.io.mo.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import mo.oa.io.mo.R;

/**
 * Created by max-code on 2016/9/25.
 */

public class MultiRefreshLayout extends SwipeRefreshLayout {
    //refresh回调
    private CanChildScrollUpCallBack canChildScrollUpCallBack;
    //顶层Drawble绘制
    private Drawable FregroundDrawble;

    public void setCanChildScrollUpCallBack(CanChildScrollUpCallBack canChildScrollUpCallBack) {
        this.canChildScrollUpCallBack = canChildScrollUpCallBack;
    }

    public MultiRefreshLayout(Context context) {
        super(context);
    }

    public MultiRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiRefreshLayout,0,0);
        FregroundDrawble = typedArray.getDrawable(R.styleable.MultiRefreshLayout_foreground);
        if(FregroundDrawble!=null){
            FregroundDrawble.setCallback(this);
            setWillNotDraw(false);
        }
        typedArray.recycle();
    }
    //复写OnSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(FregroundDrawble!=null){
            FregroundDrawble.setBounds(0,0,w,h);
        }
    }

    public interface CanChildScrollUpCallBack{
        boolean CanChildScrollUpCallBackMethod();
    }

    //child是否可scroll
    @Override
    public boolean canChildScrollUp() {
        if(canChildScrollUpCallBack!=null){
            return canChildScrollUpCallBack.CanChildScrollUpCallBackMethod();
        }
        return super.canChildScrollUp();
    }
}
