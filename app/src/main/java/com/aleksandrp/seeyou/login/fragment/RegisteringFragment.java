package com.aleksandrp.seeyou.login.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public class RegisteringFragment extends Fragment implements View.OnClickListener,
        AllRequestImpl.SignInOK {

    private RegisteringFragmentListener mFragmentListener;

    private EditText etMail, etPass, name, surname;
    private CheckBox mCheckBox;

    private Context mContext;

    private static RegisteringFragment fragment;

    public static RegisteringFragment getInstance(Context mContext) {
        if (fragment == null) {
            fragment = new RegisteringFragment(mContext);
        }
        return fragment;
    }

    public RegisteringFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof RegisteringFragmentListener) {
            mFragmentListener = (RegisteringFragmentListener) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_refistering, container, false);

        view.findViewById(R.id.rl_back).setOnClickListener(this);
        view.findViewById(R.id.ib_fb).setOnClickListener(this);
        view.findViewById(R.id.ib_google).setOnClickListener(this);
        view.findViewById(R.id.ib_twitter).setOnClickListener(this);
        view.findViewById(R.id.bt_register_register_activity).setOnClickListener(this);

        name = (EditText) view.findViewById(R.id.et_name);
        surname = (EditText) view.findViewById(R.id.et_surname);
        etMail = (EditText) view.findViewById(R.id.et_create_mail);
        etPass = (EditText) view.findViewById(R.id.et_create_pass);
        mCheckBox = (CheckBox) view.findViewById(R.id.cb_license_register);
        validationMailAndPass();

        return view;
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
            case R.id.rl_back:
                name.setText("");
                surname.setText("");
                etMail.setText("");
                etPass.setText("");
                mCheckBox.setChecked(false);
                mFragmentListener.onBackPress();
                break;
            case R.id.ib_fb:
                mFragmentListener.registerInWithSocialNetworks(STATICS_PARAMS.FB);
                break;

            case R.id.ib_google:
                mFragmentListener.registerInWithSocialNetworks(STATICS_PARAMS.GOOGLE);
                break;

            case R.id.ib_twitter:
                mFragmentListener.registerInWithSocialNetworks(STATICS_PARAMS.TWITTER);
                break;


            case R.id.bt_register_register_activity:
                if (name.getText().toString().length() > 0) {
                    if (mCheckBox.isChecked()) {
                        if (UtilsApp.isValidEmail(etMail.getText().toString()) &&
                                UtilsApp.isValidPassword(etPass.getText().toString())) {
                            mFragmentListener.shoeProgress();
                            AllRequest request = new AllRequestImpl(mContext);
                            request.register(
                                    name.getText().toString(),
                                    etMail.getText().toString(),
                                    etPass.getText().toString(),
                                    surname.getText().toString(),
                                    "",
                                    "",
                                    "",
                                    "",
                                    this
                            );
                        }
                    } else Toast.makeText(mContext, R.string.you_need_accept, Toast.LENGTH_SHORT).show();
                }else Toast.makeText(mContext, R.string.input_name, Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void okSignIn() {
        mFragmentListener.hideProgressAndGoToMainActivity();
    }

    @Override
    public void errorSignIn() {
//        no do
    }

    @Override
    public void errorRegister() {
        Toast.makeText(mContext, R.string.database_insertion_error, Toast.LENGTH_SHORT).show();
    }

    public interface RegisteringFragmentListener {

        void onBackPress();

        void registerInWithSocialNetworks(String keySocNetwork);

        void shoeProgress();

        void hideProgressAndGoToMainActivity();
    }

}
