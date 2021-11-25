package com.sebaahs.builderview.src.usecases.validation.pages;

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
import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.usecases.home.HomeActivity;


public class RegisterFragment extends Fragment {

    private Intent intent;
    private TextView tv_btn_signin;
    private EditText et_mail, et_pass, et_pass2;
    private Button btn_register;

    private TextView toLogin;
    private FragmentManager fragmentManager;

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

        //Register
        Register();

        return view;
    }


    public void Register() {


        btn_register.setOnClickListener(v -> {

            if (!validation()) {
                //ERROR DE VALIDACION DE CAMPOS
                return;
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(et_mail.getText().toString(), et_pass.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            firebaseAlert();
                            return;
                        }
                        toHome(task.getResult().getUser().getEmail());
                    });

        });

    }

    public boolean validation() {
        //COMPLETE VALIDATION
        return true;
    }

    public void firebaseAlert() {
        Toast.makeText(getContext(),"ERROR FIREBASE",Toast.LENGTH_SHORT).show();
    }

    public void toHome(String email) {
        Intent intent = new Intent(getActivity(), HomeActivity.class)
                .putExtra("email", email);
        startActivity(intent);
    }

}