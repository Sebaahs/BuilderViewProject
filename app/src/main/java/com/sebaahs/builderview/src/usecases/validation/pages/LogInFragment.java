package com.sebaahs.builderview.src.usecases.validation.pages;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.provides.preference.LocalPreferences;
import com.sebaahs.builderview.src.provides.preference.PreferencesKey;
import com.sebaahs.builderview.src.usecases.home.HomeActivity;


public class LogInFragment extends Fragment {

    private Button btn_login,btn_google;
    private EditText et_mail,et_pass;
    private TextView toRegister;
    private FragmentManager fragmentManager;
    private LocalPreferences preferences;

    public LogInFragment() {    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_log_in, container, false);


        fragmentManager = getActivity().getSupportFragmentManager();

        toRegister = view.findViewById(R.id.login_btn_to_register);
        btn_login = view.findViewById(R.id.login_btn_login);
        et_mail = view.findViewById(R.id.login_et_mail);
        et_pass = view.findViewById(R.id.login_et_password);


        toRegister.setOnClickListener(v -> {

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_validation,new RegisterFragment(),null)
                    .commit();
        });

        //LOGIN
        preferences = new LocalPreferences(this.getActivity());
        if (!EstadoDeSesion()){
            getActivity().findViewById(R.id.validation_blank_screen).setVisibility(View.GONE);
        }

        return view;
    }



    public void login(){

        btn_login.setOnClickListener(v -> {
            if (!validation())return;
            firebaseAuth("","");
        });

    }

    private void firebaseAuth(String mail, String pass) {

        if (mail.equals("") || pass.equals("")){
            mail = et_mail.getText().toString();
            pass = et_pass.getText().toString();

            preferences.setPreferenceData(getActivity(),PreferencesKey.EMAIL,mail);
            preferences.setPreferenceData(getActivity(),PreferencesKey.PASSWORD,pass);
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(task -> {
                    if(!task.isSuccessful()){
                        firebaseAlert();
                        return;
                    }

                    toHome(task.getResult().getUser().getEmail());
                });
    }

    public boolean validation(){

        if (et_mail.getText().toString().equals("") || et_pass.getText().toString().equals("")){
            Toast.makeText(getContext(),"Complete todos los campos",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!et_mail.getText().toString().contains("@") || !et_mail.getText().toString().contains(".") || et_mail.getText().toString().length() < 8){
            Toast.makeText(getContext(),"Formato de correo no valido",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et_pass.getText().toString().length() < 6){
            Toast.makeText(getContext(),"La contraseña debe contener un minimo de 6 caracteres",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void firebaseAlert(){
        Toast.makeText(getContext(),"ERROR FIREBASE",Toast.LENGTH_SHORT).show();
    }

    public void toHome(String email) {
        Intent intent = new Intent(getActivity(), HomeActivity.class)
                .putExtra("email", email);
        startActivity(intent);
    }

    public boolean EstadoDeSesion(){


        if (!preferences.string(getContext(),PreferencesKey.EMAIL).equals("") &&
                !preferences.string(getContext(),PreferencesKey.PASSWORD).equals("")){

            firebaseAuth(preferences.string(getContext(),PreferencesKey.EMAIL), preferences.string(getContext(),PreferencesKey.PASSWORD));
            return true;
        }

        login();
        return false;
    }

}