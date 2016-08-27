package com.aleksandrp.seeyou.context_dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

import java.util.ArrayList;

/**
 * Created by AleksandrP on 20.06.2016.
 */
public class DialogSelectCountry extends AlertDialog {

    private Context mContext;
    private ArrayList<String> countriesList;
    private String flag;

    private OnInteractionListener mListener;

    public DialogSelectCountry(Context context, ArrayList<String> countriesList, String flag) {
        super(context);

        // Init dialog view
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_country, null);

        this.mContext = context;
        this.countriesList = countriesList;
        this.flag = flag;

        mListener = (OnInteractionListener) context;

        // Init
        initUi(view);
    }

    private void initUi(View mView) {

        final ListView mLvMenu = (ListView) mView.findViewById(R.id.list_countries);
        ArrayAdapter adapter = new ArrayAdapter(
                mContext,
                R.layout.custom_layout_alert_dialog,
                countriesList);

        mLvMenu.setAdapter(adapter);

        this.setView(mView);

        mLvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UtilsApp.disableDoubleClick(view);
                if (flag.equals(STATICS_PARAMS.COUNTRY)) {
                    mListener.onSelectCountry(countriesList.get(position));
                } else if (flag.equals(STATICS_PARAMS.CATEGORY)) {
                    mListener.onSelectCategory(countriesList.get(position));
                } else {
                    mListener.onSelectCity(countriesList.get(position));
                }
                close();
            }
        });
    }


    public interface OnInteractionListener {

        void onSelectCountry(String country);

        void onSelectCity(String country);

        void onSelectCategory(String category);
    }

    public void close() {
        this.cancel();
    }
}