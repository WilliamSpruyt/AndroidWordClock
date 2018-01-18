package qwerky.tk.wordclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by Family on 22/12/2016.
 */

public class WordClockSurfaceView extends SurfaceView {


    Random r = new Random();
    int iconWidth;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mSingularityPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private MyThread myThread;
    private SurfaceHolder surfaceHolder;
    private int WIDTH = 100;
    private int HEIGHT = 100;
    final TimeGiver tg=new TimeGiver();
    private String wordy="";

    public WordClockSurfaceView(Context context) {
        super(context);
        init();
    }

    public WordClockSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WordClockSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
         
        return false;
    }

    private void init() {
         

        myThread = new MyThread(this);

        surfaceHolder = getHolder();




        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                myThread.setRunning(true);
                Log.i("bollox", "" + myThread.getState());
                if (myThread.getState() == Thread.State.NEW) myThread.start();
                if (myThread.getState() == Thread.State.TERMINATED) {
                    myThread = new MyThread(WordClockSurfaceView.this);
                    myThread.setRunning(true);
                    myThread.start();
                    Log.i("billox", "" + myThread.getState());
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                myThread.setRunning(false);
                while (retry) {
                    try {
                        myThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    protected void drawSomething(Canvas canvas) {

        WIDTH = canvas.getWidth();
        HEIGHT = canvas.getHeight();
        wordy=tg.wordTimeMaker();
        //System.out.println("*"+wordy);
       // canvas.drawColor(Color.BLACK);



       // mTextPaint.dr(Color.WHITE);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(16);
        canvas.drawText(wordy, 10, 10, mTextPaint);
        //canvas.drawPaint(mPaint);




    }











}