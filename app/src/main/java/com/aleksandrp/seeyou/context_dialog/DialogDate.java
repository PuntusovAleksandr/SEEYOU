package com.aleksandrp.seeyou.context_dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

/**
 * Created by AleksandrP on 20.06.2016.
 */
public class DialogDate extends AlertDialog implements View.OnClickListener {

    private Context mContext;

    private DialogDateListener mListener;
    private DatePicker datePicker;

    public DialogDate(@NonNull Context context) {
        super(context);
        this.mContext = context;

        if (context instanceof DialogDateListener) {
            mListener = (DialogDateListener) context;
        }

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_date, null);

        view.findViewById(R.id.bt_data_picker).setOnClickListener(this);
        datePicker = (DatePicker) view.findViewById(R.id.data_picker);

        this.setView(view);
    }

    @Override
    public void onClick(View mView) {
        UtilsApp.disableDoubleClick(mView);
        String date = datePicker.getDayOfMonth() + "-" +
                (datePicker.getMonth() + 1) + "-" +
                datePicker.getYear();
        mListener.setDataInTextView(date);
        this.cancel();
    }

    public interface DialogDateListener {
        void setDataInTextView(String mData);
    }

}
