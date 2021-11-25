package com.sebaahs.builderview.src.usecases.ganeratePdf;

import android.content.RestrictionEntry;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.view.View;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Calculation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplatePDF {

    private Bitmap btm, scaledBtm;
    private PdfDocument document;
    private Paint paint;

    public TemplatePDF(PdfDocument document, Paint paint) {
        this.document = document;
        this.paint = paint;
    }

    public void createRemittance(List<Calculation> data, String nameFile){
        // create a new document
        PdfDocument document = new PdfDocument();

        // create a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

        //Canvas
        Canvas canvas = page.getCanvas();

        // draw something on the page

        //--> Isologotipo
        btm = BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.banner_remito);
        scaledBtm = Bitmap.createScaledBitmap(btm, 220, 30, false);

        canvas.drawBitmap(scaledBtm,10,10,paint);

        //--> Titulo
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(12f);
        canvas.drawText("Remito", pageInfo.getPageWidth()/2, 30, paint);

        //--> SubTitulo
        paint.setTextSize(8f);
        paint.setColor(Color.rgb(128, 0, 64));
        canvas.drawText("Remito", pageInfo.getPageWidth()/2, 40, paint);

        //--> Tabla
        int startXPosition = 15;
        int endXPosition = pageInfo.getPageWidth() - 10;
        int startYPosition = 60;

        paint.setTextSize(8f);
        paint.setColor(Color.rgb(128, 0, 64));

        canvas.drawLine(startXPosition, startYPosition, endXPosition,startYPosition + 3, paint);

        for (int i = 0; i < data.size(); i++){
            canvas.drawLine(startXPosition, startYPosition, endXPosition,startYPosition + 3, paint);
            canvas.drawText(data.get(i).getName(), startXPosition, startYPosition, paint);
            startYPosition += 20;
        }

        canvas.drawLine(pageInfo.getPageWidth()/2, 60, pageInfo.getPageWidth()/2,pageInfo.getPageHeight() - 30, paint);

        // finish the page
        document.finishPage(page);

        // creating file
        File file = new File(Environment.getExternalStorageState(), "/" + nameFile + ".pdf");

        try{
            document.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // close the document
        document.close();
    }


}
