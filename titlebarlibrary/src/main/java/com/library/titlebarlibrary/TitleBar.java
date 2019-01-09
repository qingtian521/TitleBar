package com.library.titlebarlibrary;

import android.app.Activity;
import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.Bitmap;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;

import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 封装的自定义titleBar
 *
 * @author renlei
 * create 2019/01/07
 */
public class TitleBar extends FrameLayout implements View.OnClickListener {

    /**
     * 沉浸式状态属性
     * IMMERSIVE_TRANSPARENT 全部透明
     * IMMERSIVE_TRANSLUCENT 半透明
     */
    private static final int IMMERSIVE_TRANSPARENT = 0x01;
    private static final int IMMERSIVE_TRANSLUCENT = 0x02;

    /**
     * 中间标题
     */
    private TextView text_title;

    /**
     * 两边按钮
     */
    private ImageView img_right, img_left;

    /**
     * 布局管理，控制各个控件的位置
     */
    private LayoutParams params_left, params_right, params_title;

    /**
     * titleBar的背景颜色
     */
    private int backgroundColor;

    /**
     * titleBar的背景图片
     */
    private Drawable backgroundDrable;

    /**
     * 标题文字大小
     */
    private float titleSize = 18;

    /**
     * 标题文字颜色
     */
    private int titleColor;

    /**
     * 设置标题文字大小
     *
     * @param titleSize size
     */
    public void setTitleSize(float titleSize) {
        this.titleSize = titleSize;
    }

    /**
     * 设置标题文字颜色
     *
     * @param titleColor color
     */
    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    /**
     * 设置标题文字
     *
     * @param titleText text
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    /**
     * 设置左侧图片
     *
     * @param drawable_left 图片资源 Drawable
     */
    public void setDrawable_left(Drawable drawable_left) {
        this.drawable_left = drawable_left;
    }

    /**
     * 设置左侧图片
     *
     * @param drawable_right 图片资源 Drawable
     */
    public void setDrawable_right(Drawable drawable_right) {
        this.drawable_right = drawable_right;
    }

    /**
     * 设置左侧图片
     *
     * @param bmp_left 图片资源 Bitmap
     */
    public void setBmp_left(Bitmap bmp_left) {
        this.bmp_left = bmp_left;
    }

    /**
     * 设置左侧图片
     *
     * @param bmp_right 图片资源 Bitmap
     */
    public void setBmp_right(Bitmap bmp_right) {
        this.bmp_right = bmp_right;
    }

    /**
     * 标题文字
     */
    private String titleText = "";

    /**
     * 左右两边图片文件 Drawable
     */
    private Drawable drawable_left, drawable_right;

    /**
     * 左右两边图片文件 Bitmap
     */
    private Bitmap bmp_left, bmp_right;

    /**
     * 左侧图片padding
     */
    private int leftImg_paddingLeft, leftImg_paddingTop, leftImg_paddingRight, leftImg_paddingBottom, leftImg_padding;

    /**
     * 左侧图片margin
     */
    private int leftImg_marginLeft, leftImg_marginTop, leftImg_marginRight, leftImg_marginBottom, leftImg_margin;

    /**
     * 右侧图片padding
     */
    private int rightImg_paddingLeft, rightImg_paddingTop, rightImg_paddingRight, rightImg_paddingBottom, rightImg_padding;

    /**
     * 右侧图片margin
     */
    private int rightImg_marginLeft, rightImg_marginTop, rightImg_marginRight, rightImg_marginBottom, rightImg_margin;

    /**
     * 点击事件listener
     */

    private OnTitleBarClickListener onTitleBarClickListener = null;

    /**
     * 设置点击事件监听
     *
     * @param onTitleBarClickListener onTitleBarClickListener
     */
    public void setOnTitleBarClickListener(OnTitleBarClickListener onTitleBarClickListener) {
        this.onTitleBarClickListener = onTitleBarClickListener;
    }

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setAttrs(context, attrs);
        //中间标题
        text_title = new TextView(context);
        text_title.setText(titleText);
        text_title.setTextSize(titleSize);
        text_title.setTextColor(titleColor);
        params_title = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params_title.gravity = Gravity.CENTER;
        addView(text_title, params_title);

        //左边按钮
        img_left = new ImageView(context);
        img_left.setImageDrawable(drawable_left);
        img_left.setPadding(leftImg_padding + leftImg_paddingLeft,
                leftImg_padding + leftImg_paddingTop,
                leftImg_padding + leftImg_paddingRight,
                leftImg_padding + leftImg_paddingBottom);
        if (bmp_left != null) img_left.setImageBitmap(bmp_left);
        params_left = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params_left.gravity = Gravity.CENTER_VERTICAL | Gravity.START;
        params_left.setMargins(leftImg_margin + leftImg_marginLeft,
                leftImg_margin + leftImg_marginTop,
                leftImg_margin + leftImg_marginRight,
                leftImg_margin + leftImg_marginBottom);
        addView(img_left, params_left);

        //右边按钮
        img_right = new ImageView(context);
        img_right.setImageDrawable(drawable_right);
        img_right.setPadding(rightImg_padding + rightImg_paddingLeft,
                rightImg_padding + rightImg_paddingTop,
                rightImg_padding + rightImg_paddingRight,
                rightImg_padding + rightImg_paddingBottom);
        if (bmp_right != null) img_right.setImageBitmap(bmp_right);
        params_right = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params_right.setMargins(rightImg_margin + rightImg_marginLeft,
                rightImg_margin + rightImg_marginTop,
                rightImg_margin + rightImg_marginRight,
                rightImg_margin + rightImg_marginBottom);
        params_right.gravity = Gravity.CENTER_VERTICAL | Gravity.END;
        addView(img_right, params_right);

        //background包括color和Drawable,这里分开取值
        if(getBackground() instanceof ColorDrawable){
            backgroundColor =  ((ColorDrawable) getBackground()).getColor();
        }else {
            backgroundDrable = getBackground();
        }

        if(backgroundColor == 0 && backgroundDrable == null){
            setBackgroundColor(getResources().getColor(R.color.colorPrimary)); //默认状态栏颜色
        }

        img_left.setOnClickListener(this);
        img_right.setOnClickListener(this);
        text_title.setOnClickListener(this);

    }

    /**
     * 自定义控件属性
     *
     * @param context context
     * @param attrs   attrs
     */
    private void setAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        titleText = array.getString(R.styleable.TitleBar_titleText);
        titleSize = array.getDimension(R.styleable.TitleBar_titleSize, 18);
        titleColor = array.getColor(R.styleable.TitleBar_titleColor, Color.BLACK);
        drawable_left = array.getDrawable(R.styleable.TitleBar_leftImg_drawable);
        drawable_right = array.getDrawable(R.styleable.TitleBar_rightImg_drawable);

        //左侧图片位置控制padding
        leftImg_padding = (int) array.getDimension(R.styleable.TitleBar_leftImg_padding, 0);
        leftImg_paddingLeft = (int) array.getDimension(R.styleable.TitleBar_leftImg_paddingLeft, 0);
        leftImg_paddingTop = (int) array.getDimension(R.styleable.TitleBar_leftImg_paddingTop, 0);
        leftImg_paddingRight = (int) array.getDimension(R.styleable.TitleBar_leftImg_paddingRight, 0);
        leftImg_paddingBottom = (int) array.getDimension(R.styleable.TitleBar_leftImg_paddingBottom, 0);

        //左侧图片位置控制margin
        leftImg_margin = (int) array.getDimension(R.styleable.TitleBar_leftImg_margin, 0);
        leftImg_marginLeft = (int) array.getDimension(R.styleable.TitleBar_leftImg_marginLeft, 0);
        leftImg_marginTop = (int) array.getDimension(R.styleable.TitleBar_leftImg_marginTop, 0);
        leftImg_marginRight = (int) array.getDimension(R.styleable.TitleBar_leftImg_marginRight, 0);
        leftImg_marginBottom = (int) array.getDimension(R.styleable.TitleBar_leftImg_marginBottom, 0);

        //右侧图片位置控制padding
        rightImg_padding = (int) array.getDimension(R.styleable.TitleBar_rightImg_padding, 0);
        rightImg_paddingLeft = (int) array.getDimension(R.styleable.TitleBar_rightImg_paddingLeft, 0);
        rightImg_paddingTop = (int) array.getDimension(R.styleable.TitleBar_rightImg_paddingTop, 0);
        rightImg_paddingRight = (int) array.getDimension(R.styleable.TitleBar_rightImg_paddingRight, 0);
        rightImg_paddingBottom = (int) array.getDimension(R.styleable.TitleBar_rightImg_paddingBottom, 0);

        //右侧图片位置控制margin
        rightImg_margin = (int) array.getDimension(R.styleable.TitleBar_rightImg_margin, 0);
        rightImg_marginRight = (int) array.getDimension(R.styleable.TitleBar_rightImg_marginLeft, 0);
        rightImg_marginTop = (int) array.getDimension(R.styleable.TitleBar_rightImg_marginTop, 0);
        rightImg_marginRight = (int) array.getDimension(R.styleable.TitleBar_rightImg_marginRight, 0);
        rightImg_marginBottom = (int) array.getDimension(R.styleable.TitleBar_rightImg_marginBottom, 0);
        array.recycle();
    }

    @Override
    public void onClick(View view) {
        if (onTitleBarClickListener == null) return;
        if (view == img_left) {
            onTitleBarClickListener.onLeftBtnClick(view);
        } else if (view == img_right) {
            onTitleBarClickListener.onRightBtnClick(view);
        } else if (view == text_title) {
            onTitleBarClickListener.onTitleClick(view);
        }
    }

    /**
     * 获取标题控件
     *
     * @return TextView
     */
    public TextView getText_title() {
        return text_title;
    }

    /**
     * 获取左侧图标控件
     *
     * @return ImageView
     */
    public ImageView getImg_right() {
        return img_right;
    }

    /**
     * 获取右侧图片文件
     *
     * @return ImageView
     */
    public ImageView getImg_left() {
        return img_left;
    }

    /**
     * 点击事件回调类
     */
    public interface OnTitleBarClickListener {

        void onLeftBtnClick(View view);

        void onRightBtnClick(View view);

        void onTitleClick(View view);
    }

    /**
     * 开启沉浸式
     *
     * @param activity                   当前activity
     * @param flag:IMMERSIVE_TRANSPARENT 表示全透明状态 or IMMERSIVE_TRANSLUCENT 半透明状态 ,仅6.0以上可以生效
     */
    public void startImmersive(Activity activity, int flag) {
        setColor(activity, flag);
    }

    /**
     * 开启沉浸式
     *
     * @param activity 当前activity
     */
    public void startImmersive(Activity activity) {
        setColor(activity, IMMERSIVE_TRANSPARENT);
    }

    /**
     * 开启沉浸式，适配 drawerLayout
     *
     * @param activity     activity
     * @param drawerLayout drawerLayout
     */
    public void startImmersive(Activity activity, DrawerLayout drawerLayout) {
        setColor(activity, drawerLayout, IMMERSIVE_TRANSPARENT);
    }

    /**
     * 设置状态栏颜色，适配drawerLayout
     *
     * @param activity     需要设置的activity
     * @param drawerLayout 适配drawerLayout
     */
    public void setColor(Activity activity, DrawerLayout drawerLayout, int flag) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_STABLE;
            activity.getWindow().getDecorView().setSystemUiVisibility(option);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (flag == IMMERSIVE_TRANSPARENT) {
                activity.getWindow().setStatusBarColor(backgroundColor);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 生成一个状态栏大小的矩形,优先drable
        View statusView = null;
        if (backgroundDrable != null) {
            statusView = createStatusView(activity, backgroundDrable);
        } else {
            statusView = createStatusView(activity, backgroundColor);
        }
        // 添加 statusView 到布局中
        ViewGroup decorView = (ViewGroup) drawerLayout.getChildAt(0);
        decorView.addView(statusView, 0);
        // 设置根布局的参数
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity 需要设置的activity
     */
    public void setColor(Activity activity, int flag) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_STABLE;
            activity.getWindow().getDecorView().setSystemUiVisibility(option);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (flag == IMMERSIVE_TRANSPARENT) {
                activity.getWindow().setStatusBarColor(backgroundColor);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 生成一个状态栏大小的矩形,优先drable
        View statusView = null;
        if (backgroundDrable != null) {
            statusView = createStatusView(activity, backgroundDrable);
        } else {
            statusView = createStatusView(activity, backgroundColor);
        }
        // 添加 statusView 到布局中
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        decorView.addView(statusView, 0);
        // 设置根布局的参数
        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        rootView.setFitsSystemWindows(true);
        rootView.setClipToPadding(true);
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */

    private View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param drawable 状态栏资源文件
     * @return 状态栏矩形条
     */

    private View createStatusView(Activity activity, Drawable drawable) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackground(drawable);
        return statusView;
    }
}
