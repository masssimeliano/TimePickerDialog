package com.example.timepickerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mTVTime;
    private Button mBSetTimePicker;

    private final int ID_TIME_PICKER_DIALOG = 1;

    private final String LOGS = "TimePickerDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVTime = (TextView) findViewById(R.id.mTVTime);
        mBSetTimePicker = (Button) findViewById(R.id.mBSetTimePicker);

        mBSetTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(ID_TIME_PICKER_DIALOG);
            }
        });
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            StringBuffer time = new StringBuffer();

            if (hourOfDay <= 9)
                time.append("0" + hourOfDay + ":");
            else
                time.append(hourOfDay + ":");

            if (minute <= 9)
                time.append("0" + minute);
            else
                time.append(minute);

            mTVTime.setText(time.toString());

            Log.d(LOGS, "Set new TimePicker");
            Log.d(LOGS, "Time - " + hourOfDay + ":" + minute);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == ID_TIME_PICKER_DIALOG) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, timeSetListener, Calendar.getInstance().getTime().getHours(), Calendar.getInstance().getTime().getMinutes(), true);

            timePickerDialog.show();

            return timePickerDialog;
        }

        return super.onCreateDialog(id);
    }
}