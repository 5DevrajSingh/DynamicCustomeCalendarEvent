package com.iottech.mycustomecalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.iottech.mycustomecalendar.retro.ApiClient;
import com.iottech.mycustomecalendar.retro.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    CustomCalendarView calendarView;
    Calendar currentCalendar;
    String user_id;
    ImageView iv_back;

    List<String> presentList = new ArrayList<>();
    List<String> absentList = new ArrayList<>();
    List<String> leaveList = new ArrayList<>();
    List<String> holidayList = new ArrayList<>();
    List<String> weekendNameList = new ArrayList<>();
    List<String> weekendDatess = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

        //Initialize calendar with date
        currentCalendar = Calendar.getInstance(Locale.getDefault());

        //Show monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

        //call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);

//        hitCalenderApi();
        String persent = "2022-04-19,2022-11-03,2022-11-04,2022-11-09,2022-11-15,2022-11-16,2022-11-17,2022-11-21,2022-11-23,2022-11-24,2022-11-25,2022-11-29,2023-02-01,2023-02-02,2023-02-03,2023-02-06,2023-02-07,2023-02-09";
        presentList = Arrays.asList(persent.split(","));
        List<DayDecorator> decorators = new ArrayList<>();
        decorators.add(new DisabledColorDecorator());
        calendarView.setDecorators(decorators);
        calendarView.refreshCalendar(currentCalendar);
    }



    private class DisabledColorDecorator implements DayDecorator {
        @Override
        public void decorate(DayView dayView) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//formating according to my need
            String date = formatter.format(dayView.getDate());

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            String dayOfTheWeek = sdf.format(dayView.getDate());

            if (dayOfTheWeek.equals("Sunday")) {
                int color = Color.parseColor("#ac3235");
//               dayView.setBackgroundColor(color);
                dayView.setTextColor(color);
            }

//            if (weekendNameList.equals(dayOfTheWeek))
//            {
//                int color = Color.parseColor("#ac3235");
////               dayView.setBackgroundColor(color);
//                dayOfWeek.setTextColor(color);
//            }

            if (weekendDatess.contains(date)) {
                int color = Color.parseColor("#ac3235");
//               dayView.setBackgroundColor(color);
                dayView.setTextColor(color);
            }

            if (presentList.contains(date)) {
                int color = Color.parseColor("#579F2B");
                dayView.setBackgroundColor(color);
            }
            if (absentList.contains(date)) {
                int color = Color.parseColor("#F40002");
                dayView.setBackgroundColor(color);
            }
            if (leaveList.contains(date)) {
                int color = Color.parseColor("#F3AF22");
                dayView.setBackgroundColor(color);
            }
            if (holidayList.contains(date)) {
                int color = Color.parseColor("#5483D4");
                dayView.setBackgroundColor(color);
            }
//            if (CalendarUtils.isPastDay(dayView.getDate())) {
//                int color = Color.parseColor("#a9afb9");
//                dayView.setBackgroundColor(color);
//            }

        }
    }
}