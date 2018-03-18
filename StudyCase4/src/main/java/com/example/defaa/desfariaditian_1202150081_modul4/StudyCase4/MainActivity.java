package com.example.defaa.desfariaditian_1202150081_modul4.StudyCase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listNama(View view) {
        Intent a = new Intent(MainActivity.this,DaftarMahasiwa.class);
        startActivity(a); //untuk membuka activity DaftarMahasiswa jika button di klik
    }

    public void cariGambar(View view) {
        Intent b = new Intent(MainActivity.this,CariGambar.class);
        startActivity(b); //untuk membuka activity CariGambar jika button di klik
    }
}
