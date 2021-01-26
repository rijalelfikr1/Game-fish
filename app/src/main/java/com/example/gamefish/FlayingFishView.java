package com.example.gamefish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;


public class FlayingFishView extends View
{
    private Bitmap fish [] = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;
    private int canvasWight, canvasHeight;
    private int yellowX, yellowY, yellowSpeed = 16;
    private Paint yellowPaint = new Paint();
    private int greenX, greenY, GreenSpeed = 20;
    private Paint greenPaint = new Paint();
    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();
    private int score;
    private boolean touch = false;
    private Bitmap backgroundImage;
    private Paint scorePaint = new Paint();
    private Bitmap life [] = new Bitmap[2];

    public FlayingFishView(Context context)
    {
        super(context);
        fish [0]= BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish [1]= BitmapFactory.decodeResource(getResources(), R.drawable.fish2);
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(65);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);
        fishSpeed = 550;
        score = 0;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvasWight = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImage, 0,0,null);

        int minfishY = fish[0].getHeight();
        int maxfishY = canvasHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;
        if (fishY < minfishY){
            fishY = minfishY;
        }
        if (fishY > maxfishY){
            fishY = maxfishY;
        }
        fishSpeed = fishSpeed + 2;
        if(touch){
         canvas.drawBitmap(fish[1], fishX, fishY, null);
         touch = false;
        }
        else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }

        yellowX = yellowX - yellowSpeed;
        if (hitBallChecker(yellowX, yellowY)){
            score = score + 5;
            yellowX = -100;
        }
        if(yellowX < 0){
            yellowX = canvasWight + 19;
            yellowY = (int) Math.floor(Math.random() * (maxfishY - minfishY)) + minfishY;
        }
        canvas.drawCircle(yellowX, yellowY, 20, yellowPaint);

        greenX = greenX - GreenSpeed;
        if (hitBallChecker(greenX, greenY)){
            score = score + 10;
            greenX = -100;
        }
        if(greenX < 0){
            greenX = canvasWight + 19;
            greenY = (int) Math.floor(Math.random() * (maxfishY - minfishY)) + minfishY;
        }
        canvas.drawCircle(greenX, greenY, 20, greenPaint);

        redX = redX - redSpeed;
        if (hitBallChecker(redX, redY)){
            redX = -100;
        }
        if(redX < 0){
            redX = canvasWight + 19;
            redY = (int) Math.floor(Math.random() * (maxfishY - minfishY)) + minfishY;
        }
        canvas.drawCircle(redX, redY, 25, redPaint);

        canvas.drawText("Score : " +score, 10, 50, scorePaint);
        canvas.drawBitmap(life[0],420,10,null);
        canvas.drawBitmap(life[0],490,10,null);
        canvas.drawBitmap(life[0],560,10,null);

    }

    public boolean hitBallChecker(int x, int y){
        if (fishX < x && x < (fishX - fish[0].getWidth())  &&  fishY < y && y < (fishY - fish[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;

            fishSpeed = -22;
        }
        return true;
    }
}
