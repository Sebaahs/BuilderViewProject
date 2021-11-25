package com.sebaahs.builderview.src.usecases.validation.pages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.provides.preference.LocalPreferences;
import com.sebaahs.builderview.src.provides.preference.PreferencesKey;
import com.sebaahs.builderview.src.usecases.home.HomeActivity;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment {

    private Intent intent;
    private TextView tv_btn_signin;
    private EditText et_mail, et_pass, et_pass2;
    private Button btn_register;

    private TextView toLogin;
    private FragmentManager fragmentManager;

    private LocalPreferences preferences;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public RegisterFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();


        toLogin = view.findViewById(R.id.register_btn_to_login);
        btn_register = view.findViewById(R.id.register_btn_login);
        et_mail = view.findViewById(R.id.register_et_mail);
        et_pass = view.findViewById(R.id.register_et_password);
        et_pass2 = view.findViewById(R.id.register_et_repeat_password);


        toLogin.setOnClickListener(v -> {

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_validation, LogInFragment.class, null)
                    .commit();
        });
        preferences = new LocalPreferences(this.getActivity());
        //Register
        Register();

        return view;
    }


    public void Register() {

        btn_register.setOnClickListener(v -> {

            if (!validation())return;

            auth.createUserWithEmailAndPassword(et_mail.getText().toString(), et_pass.getText().toString()).addOnCompleteListener(task -> {
                if (!task.isSuccessful()){
                      firebaseAlert(task.getException().getMessage());
                      return;
                }
                preferences.setPreferenceData(getActivity(),PreferencesKey.EMAIL,et_mail.getText().toString());
                preferences.setPreferenceData(getActivity(),PreferencesKey.PASSWORD,et_pass.getText().toString());
                toHome(et_mail.getText().toString());
            });
        });
    }

    private void toHome(String email) {
        startActivity(new Intent(getActivity(), HomeActivity.class).putExtra("email", email));
    }

    public void firebaseAlert(String massage) {
        Toast.makeText(getActivity(), "Firebase error: " + massage, Toast.LENGTH_LONG).show();
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
        if (!et_pass.getText().toString().equals(et_pass2.getText().toString())){
            Toast.makeText(getContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}