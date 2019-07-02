package com.example.week4test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.net.DnsResolver;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {
    DatabaseHelper databaseHelper;
    ArrayList<Coffe> coffes;
    FragmentManager fragmentManager;
    ItemFragment itemFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(this);
        RestrofitExample restrofitExample=new RestrofitExample();
        coffes=new ArrayList<>();
        restrofitExample.getService().getCoffes().enqueue(new Callback<Coffe[]>() {

            @Override
            public void onResponse(Call<Coffe[]> call, Response<Coffe[]> response) {
                Coffe[] coffeResponse=response.body();

                if (databaseHelper.notEmpty()==false){
                    for(Coffe coffe:coffeResponse){
                        databaseHelper.insertCoffe(coffe);
                        Coffe coffe2=databaseHelper.queryForOneCoffeRecord(coffe.getId());

                    }
                }




                runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         for (Coffe coffe:databaseHelper.queryForAllCoffeRecords()){
                            coffes.add(coffe);

                         }
                         Log.d("TAG2",coffes.size()+" dddd");
                         fragmentManager = getSupportFragmentManager();
                         itemFragment = ItemFragment.newInstance(1,coffes);
                         itemFragment.setCoffes(coffes);
                         fragmentManager
                                 .beginTransaction()
                                 .add(R.id.frmPlaceHolderOne, itemFragment)
                                 .addToBackStack("BLUE")
                                 .commit();

                     }
                });







            }

            @Override
            public void onFailure(Call<Coffe[]> call, Throwable t) {

                Log.d("TAG", "dsdsd"+(restrofitExample.getService().getCoffes()).request().url());
                Log.d("TAG","BRUHHH "+t.getLocalizedMessage());
                t.printStackTrace();

            }


        });



    }

    @Override
    public void onListFragmentInteraction() {


    }
}
