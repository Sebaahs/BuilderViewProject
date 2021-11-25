package com.sebaahs.builderview.src.usecases.ganeratePdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Calculation;

import java.util.ArrayList;
import java.util.List;

public class GeneratePdfActivity extends AppCompatActivity {

    private List<Calculation> data = new ArrayList<>();
    private TemplatePDF pdfFile;
    private Button btnDownloadPdf;
    private EditText etNameFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pdf);

        Bundle bundle = getIntent().getExtras();

        etNameFile = findViewById(R.id.generatePdf_et_name);
        btnDownloadPdf = findViewById(R.id.generatePdf_btn_downloadPdf);

        btnDownloadPdf.setOnClickListener(v -> {
            if(bundle.getParcelableArrayList("remittance") != null){
                data = bundle.getParcelableArrayList("remittance");

                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        ,PackageManager.PERMISSION_GRANTED);

                pdfFile.createRemittance(data,etNameFile.getText().toString());
            }
        });

    }
}