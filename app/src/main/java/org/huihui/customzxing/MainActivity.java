package org.huihui.customzxing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import org.huihui.easyzxing.activity.CaptureActivity;


public class MainActivity extends AppCompatActivity {

    private android.widget.RelativeLayout activitymain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activitymain = (RelativeLayout) findViewById(R.id.activity_main);
        activitymain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CaptureActivity.start(MainActivity.this, true, 0);
            }
        });
    }
}
