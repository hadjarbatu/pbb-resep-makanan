package com.d3ti2a.resepapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import static com.d3ti2a.resepapp.Login.TAG_ID;
import static com.d3ti2a.resepapp.Login.TAG_USERNAME;

public class MainActivity extends AppCompatActivity {
    public DataAdapter dataAdapter;
    public RecyclerView recyclerView;
    public ArrayList<DataModel> dataModelArrayList=new ArrayList<DataModel>();

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    String id, username;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        //membuat data yang akan ditampilkan dalam list
        //file .html mengambil di folder assets
        inputData("Ayam Bakar Bumbu Bali","artikel_1.html");
        inputData("Sate Ayam Srepeh","artikel_2.html");
        inputData("Pizza Sosis Jumbo (Tanpa Ulen)","artikel_3.html");
        inputData("Nasgor Mawut (Mawut Sayur)","artikel_4.html");
        inputData("Fuyung Hai ala Chef Lidya","artikel_5.html");
        inputData("Lobster bumbu Padang","artikel_6.html");
        inputData("Sop Iga Sapi Enak Sedep Banget","artikel_7.html");
        inputData("Opor Ayam Kampung","artikel_8.html");
        inputData("Bebek Goreng Sambel Ijo","artikel_9.html");
        inputData("Soto Ayam Kampung","artikel_10.html");
        inputData("Bakso Ayam","artikel_11.html");
        inputData("Ikan Gurame Bakar","artikel_12.html");
        inputData("Pisang Bakar Coklat Keju","artikel_13.html");
        inputData("Keto Martabak Terang Bulan","artikel_14.html");
        inputData("Ingkung Ayam Kampung","artikel_15.html");
        //inputData("Artikel 16","artikel_16.html");

        //menampilkan data ke dalam recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(this,dataModelArrayList);
        recyclerView.setAdapter(dataAdapter);

        //menambahakan header
        DataModel headerModel = new DataModel();
        headerModel.setViewType(2);
        dataModelArrayList.add(0,headerModel);

        //menambahkan footer
        DataModel footerModel = new DataModel();
        footerModel.setViewType(3);
        dataModelArrayList.add(footerModel);
    }

    //fungsi input
    public void inputData(String judul,String konten){
        DataModel dataModel = new DataModel();
        dataModel.setJudul(judul);
        dataModel.setKonten(konten);
        dataModel.setViewType(1);
        dataModelArrayList.add(dataModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atas, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout:
                logoutKan();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logoutKan() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(Login.session_status, false);
        editor.putString(TAG_ID, null);
        editor.putString(TAG_USERNAME, null);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, Login.class);
        finish();
        startActivity(intent);
    }


}

