package mo.oa.io.mo.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import mo.oa.io.mo.R;
import mo.oa.io.mo.Utils.LogUtils;

/**
 * Created by max-code on 2016/9/22.
 */

public class TabSelectorLayouts extends ViewGroup {
    private static final String TAG = "TabSelectLayoutTAG";
    //界面中的viewpager
    private ViewPager vp;
    //fontsize
    private int textSize = 12;
    //padding
    private int DrawblePadding = 10;
    //fontcolor
    private int NormalColor,SelectColor;


    //viewpager页监听
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            TabView tabview = (TabView) getChildAt(position);
            if(position>0){
                tabview.setoffset(1-positionOffset);
                TabView nextTab = (TabView) getChildAt(position+1);
                nextTab.setoffset(positionOffset);
            }else{
                tabview.setoffset(1-positionOffset);
            }
        }

        @Override
        public void onPageSelected(int position) {
            setCurrentItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public TabSelectorLayouts(Context context) {
        super(context);
    }

    public TabSelectorLayouts(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public TabSelectorLayouts(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = null;
        try {
            array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.TabSelectorLayout,defStyleAttr,0);
            int count = array.getIndexCount();
            for (int i = 0;i<count;i++){
                int index = array.getIndex(i);
                if(index == R.styleable.TabSelectorLayout_NormalColor){
                    NormalColor = array.getColor(index, Color.BLACK);
                }
                else if(index == R.styleable.TabSelectorLayout_SelectColor){
                    SelectColor = array.getColor(index,Color.BLACK);
                }
                else if(index == R.styleable.TabSelectorLayout_DrawblePadding){
                    DrawblePadding = array.getDimensionPixelSize(index,10);
                }
                else if(index == R.styleable.TabSelectorLayout_android_textSize){
                    textSize = array.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,textSize,getResources().getDisplayMetrics()));
                }
            }
            LogUtils.E("normaltextcolor:"+NormalColor+"/selectTextColor:"+SelectColor+"/DrawblePadding:"+DrawblePadding+"/textSize:"+textSize);
        } finally {
            if(array!=null){
                //回收array
                array.recycle();
            }
        }
    }
    //tab获取
    public static Tab newTab(){
        return new Tab();
    }

    public int addTab(Tab tab){
        if(tab == null){
            throw new NullPointerException("tab is null");
        }
        if(tab.title==null||tab.selectDrawble==null||tab.normalDrawble==null){
            throw new NullPointerException("tab 包含空指针参数");
        }
        TabView tabview = new TabView(getContext());
        tabview.attachTab(tab);
        tabview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        addView(tabview,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        tab.setPosition(indexOfChild(tabview));
        return tab.position;
    }

    @Override
    public void addView(View child,int index, ViewGroup.LayoutParams params){
        if(child instanceof TabView){
            super.addView(child,index,params);
        }else{
            throw new IllegalArgumentException("参数不对");
        }
    }

    //绑定viewpager
    public void bindViewPager(ViewPager pager){
        if(pager == null){
            throw new NullPointerException("viewpager空指针");
        }
        if(vp == pager){
            return ;
        }
        if(vp!=null){
            vp.removeOnPageChangeListener(onPageChangeListener);
        }
        PagerAdapter pagerAdapter = pager.getAdapter();
        if(pagerAdapter == null){
            throw new IllegalArgumentException("pageradapter空指针");
        }
        if(pagerAdapter.getCount() != getChildCount()){
            throw new IllegalArgumentException("pageradapter的size异常");
        }
        pager.addOnPageChangeListener(onPageChangeListener);
        vp = pager;
        setCurrentItem(vp.getCurrentItem());
    }

    public void setCurrentItem(int item){
        int count = getChildCount();
        for (int i =0;i<count;i++){
            TabView tab = (TabView) getChildAt(i);
            tab.setSelected(item == i);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        final int count = getChildCount();
        int childwidth = (width - getPaddingLeft() - getPaddingRight())/count;
        int maxchildHeight = 0;
        for (int i = 0;i<count ;i++){
            final View child = getChildAt(i);
            child.measure(MeasureSpec.makeMeasureSpec(childwidth,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST));
            int w = child.getMeasuredWidth();
            int h = child.getMeasuredHeight();
            maxchildHeight = maxchildHeight>h?maxchildHeight:h;

        }
        int actionbarh = getActionBarH();
        int h = actionbarh>maxchildHeight?actionbarh:maxchildHeight;
        setMeasuredDimension(width,h+getPaddingTop()+getPaddingBottom());
    }

    private int getActionBarH(){
        TypedValue tv = new TypedValue();
        if(getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize,tv,true)){
            return TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return 0;
    }

    //自定义子布局params
    public static class LayoutParams extends MarginLayoutParams{

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    //tab属性实体类
    public static final class Tab{
        //未选部分
        Drawable normalDrawble;
        //选中部分
        Drawable selectDrawble;
        //position
        int position;
        //title
        String title;

        public Tab() {
        }

        public Drawable getNormalDrawble() {
            return normalDrawble;
        }

        public Tab setNormalDrawble(Drawable d) {
            normalDrawble = d;
            return this;
        }

        public Drawable getSelectDrawble() {
            return selectDrawble;
        }

        public Tab setSelectDrawble(Drawable d) {
            this.selectDrawble = d;
            return this;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getTitle() {
            return title;
        }

        public Tab setTitle(String title) {
            this.title = title;
            return this;
        }
    }

    private class TabView extends View{
        private int viewNormalalpha = 255;
        private int viewWidth;
        private int viewHeight;
        private Paint textNormalPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.SUBPIXEL_TEXT_FLAG);
        private Paint textSelectPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.SUBPIXEL_TEXT_FLAG);
        private Rect rect = new Rect();
        private Tab tab;

        public TabView(Context context) {
            this(context,null);
        }

        public TabView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initText();
        }

        private void initText(){
            textNormalPaint.setColor(NormalColor);
            textSelectPaint.setColor(SelectColor);
            textNormalPaint.setTextSize(textSize);
            textSelectPaint.setTextSize(textSize);
        }
        private void meaureText(){
            textNormalPaint.getTextBounds(tab.title,0,tab.title.length(),rect);
        }
        void attachTab(Tab tab){
            this.tab = tab;
        }
        void setoffset(float offset){
            viewNormalalpha = (int) (255-offset*255);
            invalidate();
        }

        @Override
        public void setSelected(boolean selected) {
            viewNormalalpha = selected?0:255;
            super.setSelected(selected);
        }
        //meaure方法
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int width =  MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            int withmode = MeasureSpec.getMode(widthMeasureSpec);
            int heightmode = MeasureSpec.getMode(heightMeasureSpec);
            int w = 0;
            int h = 0;
            meaureText();
            int contentWidth =  Math.max(rect.width(),Math.max(tab.normalDrawble.getIntrinsicWidth(),tab.selectDrawble.getIntrinsicWidth()));
            int desirewidth = getPaddingLeft()+getPaddingRight()+contentWidth;
            switch(withmode){
            case MeasureSpec.AT_MOST:{
                w = Math.min(width,desirewidth);
            }break;
                case MeasureSpec.EXACTLY:{
                    w= width;
                }break;
                case MeasureSpec.UNSPECIFIED:{
                    w = desirewidth;
                }break;
            }
            int contentHeight = (int) (textNormalPaint.getFontSpacing()+Math.max(tab.normalDrawble.getIntrinsicHeight(),tab.selectDrawble.getIntrinsicHeight()));
            int desireHeight = getPaddingTop() + contentHeight+getPaddingBottom();
            switch(heightmode){
                case MeasureSpec.AT_MOST:{
                    h = Math.min(height,desireHeight);
                }break;
                case MeasureSpec.EXACTLY:{
                    h = height;
                }break;
                case MeasureSpec.UNSPECIFIED:{
                    h = height;
                }break;
            }
            setMeasuredDimension(w,h);
            viewWidth = getWidth();
            viewHeight = getHeight();
        }
        //图形绘制
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            DrawNormalBitmap(canvas);
            DrawSelectBitmap(canvas);
            drawText(canvas);
        }
        private void drawText(Canvas canvas){
            int drawbleHeight = Math.max(tab.normalDrawble.getIntrinsicHeight(),tab.selectDrawble.getIntrinsicHeight());
            float x = (viewWidth - rect.width())/2.0f;
            float y = (viewHeight + drawbleHeight+ rect.height() + DrawblePadding)>>1;
            textNormalPaint.setAlpha(viewNormalalpha);
            canvas.drawText(tab.title,x,y,textNormalPaint);
            textSelectPaint.setAlpha(255-viewNormalalpha);
            canvas.drawText(tab.title,x,y,textSelectPaint);
        }
        //绘制未选中bitmap
        private void DrawNormalBitmap(Canvas canvas){
            int width = tab.normalDrawble.getIntrinsicWidth();
            int height = tab.normalDrawble.getIntrinsicHeight();
            int left = (viewWidth - width )/2;
            int top = (viewHeight - height - DrawblePadding - rect.height())>>1;
            tab.normalDrawble.setBounds(left,top,left+width,top+height);
            tab.normalDrawble.setAlpha(viewNormalalpha);
            tab.normalDrawble.draw(canvas);
        }

        //绘制已选中bitmap
        private void DrawSelectBitmap(Canvas canvas){
            int width = tab.selectDrawble.getIntrinsicWidth();
            int height = tab.selectDrawble.getIntrinsicHeight();
            int left = (viewWidth - width )/2;
            int top = (viewHeight - height - DrawblePadding - rect.height())>>1;
            tab.selectDrawble.setBounds(left,top,left+width,top+height);
            tab.selectDrawble.setAlpha(255-viewNormalalpha);
            tab.selectDrawble.draw(canvas);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        int lefposition = getPaddingLeft();
        int parentTop = getPaddingTop();
        int parentBottom = bottom - top - getPaddingBottom();
        int parentHeight = parentBottom - parentTop;
        Rect rect = new Rect();
        for (int i=0;i<count ;i++){
            View view = getChildAt(i);
            if(view.getVisibility() == GONE){
                LayoutParams lp = (LayoutParams) view.getLayoutParams();
                int width = view.getMeasuredWidth();
                int height = view.getMeasuredHeight();
                lefposition += lp.leftMargin;
                rect.left = lefposition;
                rect.bottom = rect.top + height;
                rect.top = (parentHeight - height)/2;
                rect.right = rect.left+width;
                view.layout(rect.left,rect.top,rect.right,rect.bottom);
                lefposition += width;
                lefposition += lp.rightMargin;
            }
        }
    }
}
