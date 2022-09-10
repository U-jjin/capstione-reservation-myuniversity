package com.example.myuniversity3.InnerReservation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import com.example.myuniversity3.R;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    int year, month,day;
    String check_date;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance(); // 오늘 날짜로 디폴트값을 설정하기 위해 캘린더 객체 선언
         year = c.get(Calendar.YEAR);
         month = c.get(Calendar.MONTH);              // MONTH : 0~11
         day = c.get(Calendar.DAY_OF_MONTH);
        c2.add(Calendar.MONTH,+1);


        DatePickerDialog datePickerDialog =new DatePickerDialog(getActivity(),this, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(c2.getTimeInMillis());

        // Create a new instance of DatePickerDialog and return it
        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        StInnerActivity activity = (StInnerActivity) getActivity();              // 예약 내부시설액티비티의의 에 접근하기 위해 액티비티 객체 선언

        check_date = String.valueOf(year*10000+(month+1)*100+day);
        String print_date =year+"년 "+(month+1)+"월 "+day+"일";
        activity.setDbdate(check_date);
        activity.date_tv.setTextColor(this.getResources().getColor(R.color.external_point));
        activity.date_tv.setText(print_date);

        Log.i("dbdate10",activity.getDbdate());
        activity.dbcheck(check_date);
       activity.refresh();


    }

}
