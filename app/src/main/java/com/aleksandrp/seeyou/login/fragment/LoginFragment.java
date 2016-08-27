package com.aleksandrp.seeyou.login.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private ClickLoginListener mListener;

    private EditText etMail, etPass;

    private static LoginFragment fragment;

    public static LoginFragment getInstance() {
        if (fragment == null) {
            fragment = new LoginFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof ClickLoginListener) {
            mListener = (ClickLoginListener) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View mView) {
        etMail = (EditText) mView.findViewById(R.id.et_email);
        etPass = (EditText) mView.findViewById(R.id.et_password);

        validationMailAndPass();

        mView.findViewById(R.id.ib_fb).setOnClickListener(this);
        mView.findViewById(R.id.ib_google).setOnClickListener(this);
        mView.findViewById(R.id.ib_twitter).setOnClickListener(this);
        mView.findViewById(R.id.bt_sign_in).setOnClickListener(this);
        mView.findViewById(R.id.rl_press_registration).setOnClickListener(this);
    }

    private void validationMailAndPass() {
        etMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {

            }

            @Override
            public void onTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {

            }

            @Override
            public void afterTextChanged(Editable mEditable) {
                if (UtilsApp.isValidEmail(etMail.getText().toString())) {
                    etMail.setTextColor(getResources().getColor(R.color.dark));
                } else etMail.setTextColor(getResources().getColor(R.color.colorAccent));

            }
        });

        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {

            }

            @Override
            public void onTextChanged(CharSequence mCharSequence, int mI, int mI1, int mI2) {

            }

            @Override
            public void afterTextChanged(Editable mEditable) {
                if (UtilsApp.isValidPassword(etPass.getText().toString())) {
                    etPass.setTextColor(getResources().getColor(R.color.dark));
                } else etPass.setTextColor(getResources().getColor(R.color.colorAccent));

            }
        });
    }

    @Override
    public void onClick(View mView) {
        UtilsApp.disableDoubleClick(mView);
        switch (mView.getId()) {
            case R.id.ib_fb:
                mListener.signInWithSocialNetworks(STATICS_PARAMS.FB);
                break;

            case R.id.ib_google:
                mListener.signInWithSocialNetworks(STATICS_PARAMS.GOOGLE);
                break;

            case R.id.ib_twitter:
                mListener.signInWithSocialNetworks(STATICS_PARAMS.TWITTER);
                break;

            case R.id.bt_sign_in:
                String mail = etMail.getText().toString();
                String pass = etPass.getText().toString();
                if (checkValidData(mail, pass)) {
                    mListener.signIn(mail, pass);
                }
                break;

            case R.id.rl_press_registration:
                mListener.registering();
                break;
        }
    }

    private boolean checkValidData(String mMail, String mPass) {
        if (UtilsApp.isValidEmail(etMail.getText().toString()) &&
                UtilsApp.isValidPassword(etPass.getText().toString())) {
            return true;
        } else
            return false;
    }


    public interface ClickLoginListener {
        void signInWithSocialNetworks(String keySocNet);

        void signIn(String mail, String pass);

        void registering();
    }
}

