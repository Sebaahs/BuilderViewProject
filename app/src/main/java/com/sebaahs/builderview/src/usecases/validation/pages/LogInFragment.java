package com.sebaahs.builderview.src.usecases.validation.pages;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.firebase.auth.FirebaseAuth;
import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.TypeOfCredentials;
import com.sebaahs.builderview.src.usecases.home.HomeActivity;


public class LogInFragment extends Fragment {

    private Button btn_login,btn_google;
    private EditText et_mail,et_pass;
    private TextView toRegister;
    private FragmentManager fragmentManager;
    public LogInFragment() {    }

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
        login();

        return view;
    }



    public void login(){


        btn_login.setOnClickListener(v -> {

            if (!validation())return;

            FirebaseAuth.getInstance().signInWithEmailAndPassword(et_mail.getText().toString(), et_pass.getText().toString())
                    .addOnCompleteListener(task -> {
                        if(!task.isSuccessful()){
                            firebaseAlert();
                            return;
                        }
                        toHome(task.getResult().getUser().getEmail(), TypeOfCredentials.EMAIL);
                    });

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

    public void toHome(String email, TypeOfCredentials provider) {
        Intent intent = new Intent(getActivity(), HomeActivity.class)
                .putExtra("email", email)
                .putExtra("provider", provider.name());
        startActivity(intent);
    }


}