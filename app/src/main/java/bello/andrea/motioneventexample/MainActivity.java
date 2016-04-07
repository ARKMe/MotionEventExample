package bello.andrea.motioneventexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends Activity implements View.OnTouchListener  {
    int clickCount;
    private RelativeLayout rootLayout;
    private int startX;
    private int startY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);

        Button buttonNewImage = (Button)findViewById(R.id.button_new_image);
        buttonNewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage();
            }
        });

        clickCount = 0;

    }

    private void addImage() {
        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.toad);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(layoutParams);
        rootLayout.addView(iv, layoutParams);
        iv.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(final View view, MotionEvent event) {
        final int currentX = (int) event.getRawX();
        final int currentY = (int) event.getRawY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startX = currentX;
                startY = currentY;
                break;

            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                if (pointerCount == 1){
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = layoutParams.leftMargin + currentX - startX;
                    layoutParams.topMargin = layoutParams.topMargin + currentY - startY;
                    startX = currentX;
                    startY = currentY;
                    view.setLayoutParams(layoutParams);
                }

        }
        return true;
    }
}