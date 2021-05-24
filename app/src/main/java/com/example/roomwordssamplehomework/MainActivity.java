package com.example.roomwordssamplehomework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DocumentViewModel mDocumentViewModel;
    public static final int UPDATE_DOCUMENT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);

        mDocumentViewModel = ViewModelProviders.of(this).get(DocumentViewModel.class);

        mDocumentViewModel.getAllDocuments().observe(this, new Observer<List<Document>>() {

            @Override
            public void onChanged(@Nullable final List<Document> documents) {
                textView.setText(documents.get(0).getDocument());
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditDocumentActivity.class);
                intent.putExtra("Document", mDocumentViewModel.getAllDocuments().getValue().get(0).getDocument());
                startActivityForResult(intent, UPDATE_DOCUMENT_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_DOCUMENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Document document = new Document(data.getStringExtra(EditDocumentActivity.EXTRA_REPLY));
            mDocumentViewModel.updateDocument(document);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}