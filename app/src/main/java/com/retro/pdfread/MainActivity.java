package com.retro.pdfread;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_PDF_FILE = 1; // Код запроса для выбора PDF файла

    private Button openPdfButton;

    private ActivityResultLauncher<String> mGetPdfFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openPdfButton = findViewById(R.id.open_pdf_button);

        mGetPdfFile = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result != null) {
                            // Передача URI выбранного PDF файла в другую активность
                            Intent intent = new Intent(MainActivity.this, PDFViewerActivity.class);
                            intent.putExtra("pdfUri", result.toString());
                            startActivity(intent);
                        }
                    }
                });

        openPdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Вызов диалога выбора файла PDF
                mGetPdfFile.launch("application/pdf");
            }
        });
    }
}
