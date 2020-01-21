package ro.atelieruldigital.news.search;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import ro.atelieruldigital.news.R;

public class DatePickerFragment extends DialogFragment {

/*    OnDataPassStart dataPasser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPassStart)context;
    }

    public  interface OnDataPassStart{
        public void onDataPassStart(String data);
    }*/

    private int mYear, mMonth, mDay;

    public void setYear(int year) {
        mYear = year;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public int getmYear() {
        return mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmDay() {
        return mDay;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), dateSetListener, mYear, mMonth, mDay);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
//                    Toast.makeText(getActivity(), "The selected date is " + view.getYear() +
//                            " - " + view.getDayOfMonth() +
//                            " - " + (view.getMonth() + 1), Toast.LENGTH_SHORT).show();

//                    String startDate = view.getDayOfMonth() + "/" + (view.getMonth() + 1) + "/" + view.getYear();
                    //TODO
                    String dayFormated = "";
                    String monthFormated = "";
                    if (view.getDayOfMonth() <= 9) {
                        dayFormated = "0" + String.valueOf(view.getDayOfMonth());
                    } else {
                        dayFormated = String.valueOf(view.getDayOfMonth());
                    }
                    if (view.getMonth() + 1 <= 9) {
                        monthFormated = "0" + String.valueOf(view.getMonth() + 1);
                    } else {
                        monthFormated = String.valueOf(view.getMonth() + 1);
                    }

                    String startDate = view.getYear() + "-" + dayFormated + "-" + monthFormated;
                    Toast.makeText(getActivity(), "The selected date is " + startDate, Toast.LENGTH_SHORT).show();
                   //dataPasser.onDataPassStart(startDate);
                    Button mbtn;
                    mbtn=getActivity().findViewById(R.id.button_date_picker);
                    //Log.e("SETDATA",mbtn.getText().toString());
                    //mbtn.setText(startDate);
                    if(mbtn.getText().toString().equals("DD"+"/"+"MM"+"/"+"YY")==true) {
                        mbtn.setText(startDate);
                        Log.e("SETDATA", mbtn.getText().toString());
                    }
                    else{
                        Button mbtn2=getActivity().findViewById(R.id.button_date_picker);
                        mbtn2.setText(startDate);
                        Log.e("SETDATA2",mbtn2.getText().toString());

                    }
                }

            };

}