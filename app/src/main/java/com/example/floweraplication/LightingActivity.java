package com.example.floweraplication;



import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.floweraplication.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class LightingActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private TextView lightLevel, lightinfo;
    ConstraintLayout back;
    double alpha;
    double MAXALPHA=1;
    float maxlux;
    View view;
    Toolbar toolbar;

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting);
        lightLevel = (TextView) findViewById(R.id.light_level);
        lightinfo = findViewById(R.id.LightTextInfo);
        toolbar = findViewById(R.id.toolbarLight);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        maxlux = sensor.getMaximumRange();
        back = findViewById(R.id.background);
        view = findViewById(R.id.view);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
// The value of the first subscript in the values array is the current light intensity
            float value = event.values[0];
            NumberFormat format = new DecimalFormat("#0");
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            alpha = ((value*MAXALPHA)/maxlux)*100;

            if (value<50){
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true);
                @ColorInt int color = typedValue.data;
                view.setBackgroundColor(color);
                lightinfo.setText("Крайне недостаточно света");

            } else if (value>=50&&value<100) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true);
                @ColorInt int color = typedValue.data;
                view.setBackgroundColor(color);
                lightinfo.setText("Недостаточно света");
            }
            else if (value>=100&&value<150) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true);
                @ColorInt int color = typedValue.data;
                view.setBackgroundColor(color);
                lightinfo.setText("Не подходит для растений");}
            else if (value>=150&&value<500) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true);
                @ColorInt int color = typedValue.data;
                view.setBackgroundColor(color);
                lightinfo.setText("Подходит для тенилюбивых растений");}
            else if (value>=500&&value<1500) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true);
                @ColorInt int color = typedValue.data;
                view.setBackgroundColor(color);
                lightinfo.setText("Подходит для комнатных растений");}
            else if (value>=1500&&value<40000) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true);
                @ColorInt int color = typedValue.data;
                view.setBackgroundColor(color);
                lightinfo.setText("Подходит для светолюбивых растений");}


            view.setAlpha((float) alpha);
            lightLevel.setText(""+format.format(value));

            int i =(int) ((value/40000)*100);


        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}