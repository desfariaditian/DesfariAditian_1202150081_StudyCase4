package com.example.defaa.desfariaditian_1202150081_modul4.StudyCase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class DaftarMahasiwa extends AppCompatActivity {

    private ListView mListMahasiswa;
    private Button mStartAsyncTask;
    private ProgressBar mProgressBar;
    private String[] daftarMahasiswaArray = {
            "Desfari", "Aditian", "Arif", "Defa", "Farel", "Awen", "Astrid", "Silvia", "Rizka"
            , "Dewi", "Ina", "Jafar"};
    private AddItemToListView mAddItemToListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mahasiwa);

        mListMahasiswa = findViewById(R.id.listMahasiswa); //mengambil id dari layout activity daftarmahasiswa
        mProgressBar = findViewById(R.id.progressBar); //mengambil id dari layout daftarmahasiswa
        mStartAsyncTask = findViewById(R.id.buttonMulai); //mengambil id dari layout daftarmahasiswa

        mListMahasiswa.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>())); //membuat adapter

        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //proses adapter dengan AsyncTask
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> { //menggunakan void karna tidak ada parameter khusus

        private ArrayAdapter<String> mAdapter;
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(DaftarMahasiwa.this); //membuat dialog

        @Override
        protected void onPreExecute() { //method yang dipanggil sebelum task dijalankan
            mAdapter = (ArrayAdapter<String>) mListMahasiswa.getAdapter(); //casting suggestion
            //isi progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setCancelable(true); //agar proses dapat dibatalkan
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setProgress(0);
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss(); //untuk mengaktifkan button cancel
                }
            });
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) { //method yang mengupdate progres
            for (String item : daftarMahasiswaArray) {
                publishProgress(item);
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) { //method yang berguna untuk memberitau bahwa datang sedang diambil
            mAdapter.add(values[0]);

            Integer current_status = (int) ((counter / (float) daftarMahasiswaArray.length) * 100);
            mProgressBar.setProgress(current_status);
            mProgressDialog.setProgress(current_status);
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) { //method yang dijalan jika semua method diatas selesai dijalankan
            //hide progreebar
            mProgressBar.setVisibility(View.GONE);

            //remove progress dialog
            mProgressDialog.dismiss();
            mListMahasiswa.setVisibility(View.VISIBLE);
        }
    }
}
