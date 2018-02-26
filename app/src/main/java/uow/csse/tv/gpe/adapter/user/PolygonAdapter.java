package uow.csse.tv.gpe.adapter.user;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vian on 2/9/2018.
 */

public class PolygonAdapter extends View {

    private int mHeight;
    private int mWidth;

    //边框的画笔
    private Paint mPaint;
    //文字的画笔
    private Paint textPaint;
    //实力区域的画笔
    private Paint realPaint;

    //正n边型
    private int count = 6;
    //角度
    private float angle = (float) (Math.PI * 2 / count);
    //圆的半径
    private float r = 50;
    //等级分级的个数
    private int levelCount = 5;
    //一些标注
    private String[] explains = {"Focus Level", "Team Spirit", "Psychological Status", "Technical Skill", "Communication", "Physical Skill"};
    //文字大小
    private int textSize = 30;
    //文字与图形的距离
    private int margin = 4;
    //实力数据
    private int[] realData;
    //边框颜色
    private int strokeColor = 0xFF000000;
    //实力区域颜色
    private int strengthColor = Color.argb(100,86,234, 57);
    //文字颜色
    private int textColor = Color.rgb(0, 0, 0);
    //线的粗细
    private int strokeWidth = 2;

    //坐标
    private float x;
    private float y;



    public PolygonAdapter(Context context) {
        super(context);
    }

    public PolygonAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PolygonAdapter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boolean isDraw = initData();
        if (isDraw) {
            canvas.translate(mWidth / 2, mHeight / 2);
            initPaint();

            drawPolygonAdapter(canvas);
            drawLines(canvas);
            drawText(canvas);
            drawReal(canvas);
        }
    }

    /**
     * 数据做一些校验
     */
    private boolean initData() {
        boolean flag = true;
        if (realData == null || realData.length == 0) {
            flag = false;
            return flag;
        }
        if (realData.length != count) {
            throw new IllegalArgumentException("realData的大小必须为" + count + "个");
        }
        return flag;
    }


    /**
     * 绘制多边形
     *
     * @param canvas
     */
    private void drawPolygonAdapter(Canvas canvas) {
        Path path = new Path();
        for (int j = 1; j <= levelCount; j++) {
            float r = this.r * j;
            path.reset();
            for (int i = 1; i <= count; i++) {
                x = (float) (Math.cos(i * angle) * r);
                y = (float) (Math.sin(i * angle) * r);
                if (i == 1) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mPaint);
        }
    }

    /**
     * 绘制线条
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        float r = this.r * levelCount;
        for (int i = 1; i <= count; i++) {
            path.reset();
            //移动到中心
            path.moveTo(0, 0);
            x = (float) (Math.cos(i * angle) * r);
            y = (float) (Math.sin(i * angle) * r);
            path.lineTo(x, y);
            canvas.drawPath(path, mPaint);
        }
    }

    /**
     * 绘制文本;如果直接用x,y来绘制，丑陋不堪，这里做了些调整
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        float r = this.r * levelCount;
        for (int i = 0; i < count; i++) {
            x = (float) (Math.cos(i * angle) * r);
            y = (float) (Math.sin(i * angle) * r);

            //文本
            String text = explains[(i ) % explains.length];
            //文本长度
            float textLength = textPaint.measureText(text);

            //说明：点在x轴上话，y理论上为0,但是现实很残酷，只是接近0，所以给了个粗糙的判断
            if (y < 30 && y > -30 && x > 0) {
                //x轴的正方向
                x = x + margin;
                y = y + textSize / 3;
                canvas.drawText(text, x, y, textPaint);
            } else if (y < 30 && y > -30 && x < 0) {
                //x轴的负方向
                x = x - textLength - margin;
                y = y + textSize / 3;
                canvas.drawText(text, x, y, textPaint);
            } else if (x > 0 && y > 0) {
                //第一象限
                y = y + textSize + margin;
                x = x - textSize / 2;
                canvas.drawText(text, x, y, textPaint);
            } else if (x > 0 && y < 0) {
                //第二象限
                x = x - textSize / 2;
                y = y - margin;
                canvas.drawText(text, x, y, textPaint);
            } else if (x < 0 && y < 0) {
                //第三象限
                y = y - margin;
                x = x - textLength / 2;
                canvas.drawText(text, x, y, textPaint);
            } else if (y > 0 && x < 0) {
                //第四象限
                y = y + textSize + margin;
                x = x - textLength / 2;
                canvas.drawText(text, x, y, textPaint);
            }
            /*打印坐标*/
//            String print = String.format("x-->%f,y-->%f", x, y);
//            Log.e("dd", print);
            /*打印坐标*/
        }
    }

    /**
     * 绘制实力状况
     *
     * @param canvas
     */
    private void drawReal(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < realData.length; i++) {
            int realLevel = realData[i];
            if (realData == null || realData.length == 0) {
                return;
            }
            if (realData.length != count) {
                throw new IllegalArgumentException("realData的大小必须为" + count + "个");
            }
            if (realLevel < 0 || realLevel > levelCount) {
                throw new IllegalArgumentException(String.format("水平数据必须大于等于0且小于等于%d", levelCount));
            }
            float r = this.r * realLevel;
            x = (float) (Math.cos(i * angle) * r);
            y = (float) (Math.sin(i * angle) * r);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path, realPaint);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        if (mPaint != null)
            return;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(strokeColor);
        mPaint.setStrokeWidth(strokeWidth);

        textPaint = new Paint();
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);

        realPaint = new Paint();
        realPaint.setColor(strengthColor);
        realPaint.setAntiAlias(true);
        realPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    /**
     * 设置数据
     *
     * @param realData
     */
    public void setRealData(int[] realData) {
        this.realData = realData;
        invalidate();
    }
}
