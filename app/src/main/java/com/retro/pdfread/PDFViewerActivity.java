package com.retro.pdfread;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

public class PDFViewerActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

    private PDFView pdfView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfView = findViewById(R.id.pdfView);
        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        Uri uri = Uri.parse(intent.getStringExtra("pdfUri"));

        displayFromUri(uri);
    }

    private void displayFromUri(Uri uri) {
        pdfView.fromUri(uri)
                .defaultPage(0)
                .onPageChange(this)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        // Код, выполняемый при смене страницы
    }

    @Override
    public void loadComplete(int nbPages) {
        progressBar.setVisibility(ProgressBar.GONE);
    }
}
