package com.ryg.chapter_3.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * 创建者：黄凯军
 * 创建时间：2016/2/26 15:20
 * 类描述：利用Scroller实现全屏移动
 */
public class ScrollButton extends TextView {
    private static final String LAG = "ScrollButton";
    int mLastX;
    int mLastY;
    private Scroller mScroller;

    public ScrollButton(Context context) {
        this(context, null);
    }

    public ScrollButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());

    }

//    /**
//     * 慢慢的移动到某一点
//     *
//     * @param destX
//     * @param destY
//     */
//    public void smoothScrollTo(int destX, int destY) {
//        int scrollX = getScrollX();
//        int scrollY = getScrollY();
//        int dx = destX - scrollX;
//        int dy = destY - scrollY;
//        mScroller.startScroll(scrollX, scrollY, dx, dy);
//        invalidate();
//    }
//
//    @Override
//    public void computeScroll() {
//        if (mScroller.computeScrollOffset()) {
//            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//        }
//        postInvalidate();
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int dy = 0;
        int dx = 0;
        int destX = 0;
        int destY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                //上一次位置减去现在的位置，得到新移动了的距离
                dx =  mLastX - ((int)event.getRawX()) ;
                dy =  mLastY - ((int)event.getRawY()) ;
                //目的坐标 = 已经滑动了的距离 加上 新移动的距离
                destX = getScrollX() + dx;
                destY = getScrollY() + dy;
                scrollTo(destX, destY);
//                scrollBy(dx,dy);
                break;
            case MotionEvent.ACTION_UP:
                break;


        }
        //保留最后位置
        mLastX = (int) event.getRawX();
        mLastY = (int) event.getRawY();
        Log.i(LAG,  "  mLastX:" + mLastX + " mLastY:" + mLastY + "dx:" + dx + " dy:" + dy+" destX:"+destX+" destY:"+destY);
        return true;
    }
}
