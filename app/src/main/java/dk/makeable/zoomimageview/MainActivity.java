package dk.makeable.zoomimageview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ZoomImageView mImageView;

    EditText mTxtX;
    EditText mTxtY;
    EditText mTxtWidth;
    EditText mTxtHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ZoomImageView) findViewById(R.id.img_image);

        mTxtX = (EditText) findViewById(R.id.txt_x);
        mTxtY = (EditText) findViewById(R.id.txt_y);
        mTxtWidth = (EditText) findViewById(R.id.txt_width);
        mTxtHeight = (EditText) findViewById(R.id.txt_height);
    }

    public void onZoom(View v) {
        try {
            int x = Integer.parseInt(mTxtX.getText().toString());
            int y = Integer.parseInt(mTxtY.getText().toString());
            int width = Integer.parseInt(mTxtWidth.getText().toString());
            int height = Integer.parseInt(mTxtHeight.getText().toString());
            float centerX = x + width / 2;
            float centerY = y + height / 2;

            /* Temporary conversion (because original image is in xxxhdpi) */
            centerX /= 4;
            centerY /= 4;
            width /= 4;
            height /= 4;

            mImageView.zoom(centerX, centerY, width, height, 0.7f, 1500, new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message message) {
                    // Zoom finished
                    return true;
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
