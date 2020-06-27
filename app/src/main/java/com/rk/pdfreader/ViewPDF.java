package com.rk.pdfreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.muddzdev.styleabletoast.StyleableToast;

import java.io.File;

public class ViewPDF extends AppCompatActivity {

    public static int position = -1;
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_p_d_f);

        pdfView = findViewById(R.id.pdfView);
        position = getIntent().getIntExtra("position",-1);

        loadFile();
    }

    private void loadFile() {
        pdfView.fromFile(MainActivity.modelList.get(position).getFile())
                .enableSwipe(true)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_delete){
            File file = MainActivity.modelList.get(position).getFile();
            file.delete();
            MainActivity.modelList.remove(position);
            StyleableToast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT,R.style.customToastStyle).show();
            startActivity(new Intent(ViewPDF.this,MainActivity.class));
            return true;
        }
        else
        return super.onOptionsItemSelected(item);
    }
}