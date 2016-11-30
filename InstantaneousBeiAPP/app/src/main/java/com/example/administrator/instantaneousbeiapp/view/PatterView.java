package com.example.administrator.instantaneousbeiapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.instantaneousbeiapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class PatterView extends View {
    //监听器
    OnPatterChangeListener onPatterChangeListener;
    //矩阵
    public Matrix matrix = new Matrix();
    //按下的点的集合
    List<Point> pointList = new ArrayList<Point>();
    //9点
    public Point[][] points = new Point[3][3];
    boolean isInit, isSelect, isFinish, movingNoPoint;
    float width, height, offsetsX, offsetsY, bitmapR, movingX, movingY;
    Bitmap pointNormal, pointPressed, pointError, linePressed, lineError;
    //画笔
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PatterView(Context context) {
        super(context);
    }

    public PatterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PatterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PatterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initPoints();
        }
        //画点
        points2Canvas(canvas);
        //划线
        if (pointList.size() > 0) {
            Point a = pointList.get(0);
            for (int i = 0; i < pointList.size(); i++) {
                Point b = pointList.get(i);
                line2Canvas(canvas, a, b);
                a = b;
            }
            //绘制鼠标坐标点
            if (movingNoPoint) {
                line2Canvas(canvas, a, new Point(movingX, movingY));
            }
        }
    }

    /**
     * 将点绘制到画布上
     */
    private void points2Canvas(Canvas canvas) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Point point = points[i][j];
                if (point.state == Point.STATE_PRESSED) {
                    canvas.drawBitmap(pointPressed, point.x - bitmapR, point.y - bitmapR, paint);
                } else if (point.state == Point.STATE_ERROR) {
                    canvas.drawBitmap(pointError, point.x - bitmapR, point.y - bitmapR, paint);
                } else {
                    canvas.drawBitmap(pointNormal, point.x - bitmapR, point.y - bitmapR, paint);
                }
            }
        }
    }

    /**
     * 划线
     *
     * @param canvas
     * @param a      第一个点
     * @param b      第二个点
     */
    public void line2Canvas(Canvas canvas, Point a, Point b) {
        //线的长度
        float lineLength = (float) Point.distance(a, b);
        float degress = getDegrees(a, b);
        canvas.rotate(degress,a.x,a.y);
        if (a.state == Point.STATE_PRESSED) {
            matrix.setScale(lineLength / linePressed.getWidth(), 1);
            matrix.postTranslate(a.x - linePressed.getWidth()/2, a.y - linePressed.getWidth()/2);
            canvas.drawBitmap(linePressed, matrix, paint);
        } else {
            matrix.setScale(lineLength / lineError.getWidth(), 1);
            matrix.postTranslate(a.x - lineError.getWidth()/2, a.y - lineError.getWidth()/2);
            canvas.drawBitmap(linePressed, matrix, paint);
        }
        canvas.rotate(-degress,a.x,a.y);
    }

    /**
     * 初始化点
     */
    private void initPoints() {
        width = getWidth();
        height = getHeight();

        //横屏
        if (width > height) {
            offsetsX = (width - height) / 2;
            width = height;
            //竖屏
        } else {
            offsetsY = (height - width) / 2;
            height = width;
        }

        //3.图片资源
        pointNormal = BitmapFactory.decodeResource(getResources(), R.mipmap.grey);//正常
        pointPressed = BitmapFactory.decodeResource(getResources(), R.mipmap.wallet_solid_round_green_cyan);//划过
        pointError = BitmapFactory.decodeResource(getResources(), R.mipmap.wallet_concentric_circles_blue_gery);//错误
        linePressed = BitmapFactory.decodeResource(getResources(), R.mipmap.ddd);//划过
        lineError = BitmapFactory.decodeResource(getResources(), R.mipmap.qqq);//错误

        //4.点的坐标
        points[0][0] = new Point(offsetsX + width / 4, offsetsY + width / 4);
        points[0][1] = new Point(offsetsX + width / 2, offsetsY + width / 4);
        points[0][2] = new Point(offsetsX + width - width / 4, offsetsY + width / 4);

        points[1][0] = new Point(offsetsX + width / 4, offsetsY + width / 2);
        points[1][1] = new Point(offsetsX + width / 2, offsetsY + width / 2);
        points[1][2] = new Point(offsetsX + width - width / 4, offsetsY + width / 2);

        points[2][0] = new Point(offsetsX + width / 4, offsetsY + width - width / 4);
        points[2][1] = new Point(offsetsX + width / 2, offsetsY + width - width / 4);
        points[2][2] = new Point(offsetsX + width - width / 4, offsetsY + width - width / 4);

        //图片资源的半径
        bitmapR = pointNormal.getHeight() / 2;

        //6.设置密码
        int index =1;
        for(Point[] points :this.points){
            for(Point point :points){
                point.index = index;
                index ++;
            }
        }
        //7.初始化完成
        isInit = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        movingNoPoint = false;
        isFinish = false;
        movingX = event.getX();
        movingY = event.getY();
        Point point = null;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://鼠标按下
                //重新绘制
                if(onPatterChangeListener != null){
                    onPatterChangeListener.onPatterStart(true);
                }
                resetPoint();
                point = checkSelectPoint();
                if (point != null) {
                    isSelect = true;
                }
                break;
            case MotionEvent.ACTION_MOVE://鼠标移动
                if (isSelect) {
                    point = checkSelectPoint();
                    if (point == null) {
                        movingNoPoint = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP://鼠标抬起
                isFinish = true;
                isSelect = false;
                break;

        }

        //选中重复检查
        if (!isFinish && isSelect && point != null) {
            //交叉点
            if (crossPoint(point)) {
                movingNoPoint = true;
                //新点
            } else {
                point.state = Point.STATE_PRESSED;//设置状态
                pointList.add(point);
            }
        }

        //绘制结束
        if (isFinish) {
            //绘制不成立
            if (pointList.size() == 1) {
                resetPoint();
                //绘制错误
            } else if (pointList.size() < 5 && pointList.size() > 0) {
                errorPoint();
                if(onPatterChangeListener != null){
                    onPatterChangeListener.onPatterChange(null);
                }
                //绘制成功
            }else {
                if(onPatterChangeListener != null){
                    String passwordStr ="";
                    for(int i=0;i<pointList.size();i++){
                        passwordStr = passwordStr + pointList.get(i).index;
                    }
                    if(!TextUtils.isEmpty(passwordStr)){

                    }
                    onPatterChangeListener.onPatterChange(passwordStr);
                }
            }
        }

        //刷新View
        postInvalidate();
        return true;
    }


    /**
     * 交叉点
     */
    public boolean crossPoint(Point point) {
        if (pointList.contains(point)) {
            return true;
        } else {
            return false;
        }
    }

    //绘制不成立
    public void resetPoint() {
        for(int i =0;i<pointList.size();i++){
            Point point =pointList.get(i);
            point.state=Point.STATE_NORMAL;
        }
        pointList.clear();
    }

    //绘制错误
    public void errorPoint() {
        for (Point point : pointList) {
            point.state = Point.STATE_ERROR;
        }
    }

    /**
     * 检查是否选中
     *
     * @return
     */
    public Point checkSelectPoint() {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Point point = points[i][j];
                if (Point.with(point.x, point.y, bitmapR, movingX, movingY)) {
                    return point;
                }
            }
        }
        return null;
    }
    /**
     * 获取角度
     * @param
     * @param
     * @return
     */
    public float getDegrees(Point a,Point b){
        float ax = a.x;
        float ay= a.y;
        float bx = b.x;
        float by = b.y;
        float degrees =0;
//        if(bx == ax)//y轴相等90度（270）
//        {
//            if(by>ay)//在y轴的下边90度
//            {
//                degrees = 90;
//            }else if(by<ay)//在y轴的上边270度
//            {
//                degrees = 270;
//            }
//        }else  if(by == ay){
//            if(bx>ax)//在y轴的下边0度
//            {
//                degrees = 0;
//            }else if(bx<ax)//在y轴的上边180度
//            {
//                degrees = 180;
//            }
//        }else{
//            if(bx>ax)//在y轴的右边270-90度
//            {
//                if(by >ay){
//                    degrees = 0;
//                    degrees =degrees +switchDegrees()
//                }else if(by<ay)//在y轴的下边0-90度
//                {
//                    degrees = 270;
//                }
//            }else if(by<ay)//在y轴的下边0-90度
//            {
//                degrees = 270;
//            }
//        }
        if ( ax == bx ) {
            if ( by > ay ) {
                degrees = 90;
            }
            else {
                degrees = 270;
            }
        }
        else if ( by == ay ) {
            if ( ax > bx ) {
                degrees = 180;
            }
            else {
                degrees = 0;
            }
        }
        else {
            if ( ax > bx ) {
                if ( ay > by ) {// 第三象限
                    degrees = 180 + ( float ) ( Math.atan2( ay - by , ax - bx ) * 180 / Math.PI );
                }
                else {// 第二象限
                    degrees = 180 - ( float ) ( Math.atan2( by - ay , ax - bx ) * 180 / Math.PI );
                }
            }
            else {
                if ( ay > by ) {// 第四象限
                    degrees = 360 - ( float ) ( Math.atan2( ay - by , bx - ax ) * 180 / Math.PI );
                }
                else {// 第一象限
                    degrees = ( float ) ( Math.atan2( by - ay , bx - ax ) * 180 / Math.PI );
                }
            }
        }
        return degrees;
    }

    //1.自定义的点
    public static class Point{
        //正常
        public static int STATE_NORMAL = 0;
        //选中
        public static int STATE_PRESSED = 1;
        //错误
        public static int STATE_ERROR = 2;
        public float x,y;
        public int index =0,state =0;
        public Point(){}

        public Point(float x,float y){
            this.x = x;
            this.y = y;
        }

        /**
         * 2点之间的距离
         * @param a
         * @param b
         * @return 距离
         */
        public static double distance(Point a,Point b){
            return Math.sqrt(Math.abs(a.x-b.x)*Math.abs(a.x-b.x)+Math.abs(a.y-b.y)*Math.abs(a.y-b.y));
        }
        /**
         * 是否重合
         * r 圆的半径
         */                          //参考点的x轴坐标                       移动的点的x轴坐标
        public static boolean with(float pointX,float pointY,float r,float movingX,float movingY){
            //开方（勾股定理）
            return  Math.sqrt((pointX-movingX)*(pointX-movingX)+(pointY-movingY)*(pointY-movingY))<r;
        }
    }

    /**
     * 图案监听器
     */
    public static interface OnPatterChangeListener{
        //图案改变
        void onPatterChange(String passwordStr);
        //开始图案绘制
        void onPatterStart(boolean isStart);
    }
    /**
     * 设置图案监听器
     */
    public void setPatterChangeListener(OnPatterChangeListener onPatterChangeListener){
        if(onPatterChangeListener != null){
            this.onPatterChangeListener = onPatterChangeListener;
        }
    }
}
