package qwerky.tk.wordclock;

import android.graphics.Canvas;

/**
 * Created by Family on 18/01/2018.
 */

public class MyThread extends Thread {

    WordClockSurfaceView myView;
    private boolean running = false;

    public MyThread(WordClockSurfaceView view) {
        myView = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {

            Canvas canvas = myView.getHolder().lockCanvas();

            if (canvas != null) {
                synchronized (myView.getHolder()) {

                    myView.drawSomething(canvas);
                }
                myView.getHolder().unlockCanvasAndPost(canvas);
            }

            try {
                sleep(30);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
