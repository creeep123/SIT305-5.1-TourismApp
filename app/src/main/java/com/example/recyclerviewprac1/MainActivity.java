package com.example.recyclerviewprac1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements placeToGoRecyclerViewAdapter.OnRowClickListener {
    private TextView mTvTitle1, mTvTitle2;

    RecyclerView topDestinationRecyclerView;
    topDestinationRecyclerViewAdapter topDestinationRecyclerViewAdapter;
    List<topDestination> topDestinationList = new ArrayList<>();

    RecyclerView placeToGoRecyclerView;
    com.example.recyclerviewprac1.placeToGoRecyclerViewAdapter placeToGoRecyclerViewAdapter;
    List<placeToGo> placeToGoList = new ArrayList<>();

    Integer[] imageList = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    Integer[] imageListPlaceToGo = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    String[] placeNameList = {"Sydney Opera House", "Ayers Rock", "The Great Ocean Road", "Wave Rock"};
    String[] descriptionList =
            {
                    "The Sydney Opera House, one of the most distinctive buildings of the 20th century and a world-renowned center for the performing arts, has become a landmark of the city of Sydney. The Opera House was officially inaugurated in 1973 and is located at Bennelong Point on Sydney Harbor, with its characteristic sail shape and the Sydney Harbour Bridge, reflecting the surrounding scenery.",
                    "Located in the middle of the Australian continent, Ayers Rock rises miraculously and alone in the middle of the flat desert, like a natural monument of desolation, transcending space and time. Two-thirds of it is sunken into the ground and one-third floats on the surface; scientists still cannot decipher the exact origin of this unique rock in the world.",
                    "The Great Ocean Road is located in the west of Melbourne and was built to honor the soldiers who fought in World War I. Construction of the road began in 1919 and the entire route was opened in 1932. ",
                    "Wave Rock is a huge rock formation in Australia, located near Hayden City on the edge of the western grain growing region. It is shaped like a row of huge, frozen waves that are about to be broken. It rises 15 meters above the flat ground and is about 100 meters long. Wave Rock has made countless travellers marvel at nature's masterpiece."
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTitle1 = findViewById(R.id.tv_title1);
        mTvTitle2 = findViewById(R.id.tv_title2);

        topDestinationRecyclerView = findViewById(R.id.rv_horizontal);
        topDestinationRecyclerViewAdapter = new topDestinationRecyclerViewAdapter(topDestinationList, MainActivity.this);
        topDestinationRecyclerView.setAdapter(topDestinationRecyclerViewAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topDestinationRecyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < imageList.length; i++) {
            topDestination topDestination = new topDestination(i, imageList[i]);
            topDestinationList.add(topDestination);
        }

        placeToGoRecyclerView = findViewById(R.id.rv_vertical);
        placeToGoRecyclerViewAdapter = new placeToGoRecyclerViewAdapter(placeToGoList, MainActivity.this, this);
        placeToGoRecyclerView.setAdapter(placeToGoRecyclerViewAdapter);

        RecyclerView.LayoutManager layoutManagerPlaceToGo = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        placeToGoRecyclerView.setLayoutManager(layoutManagerPlaceToGo);

        for (int i = 0; i < imageListPlaceToGo.length; i++) {
            placeToGo placeToGo = new placeToGo(i, imageListPlaceToGo[i], placeNameList[i], descriptionList[i]);
            placeToGoList.add(placeToGo);
        }
    }

    @Override
    public void onItemClick(int position) {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = PlaceFragment.newInstance(placeNameList[position], descriptionList[position], imageListPlaceToGo[position]);
        fragmentTransaction.add(R.id.firstFrameLayout1, fragment).commitAllowingStateLoss();
        placeToGoRecyclerView.setVisibility(View.INVISIBLE);
        topDestinationRecyclerView.setVisibility(View.INVISIBLE);
        mTvTitle1.setVisibility(View.INVISIBLE);
        mTvTitle2.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (placeToGoRecyclerView.getVisibility() == View.INVISIBLE) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragmentManager.getFragments().get(0));
            fragmentTransaction.commit();
            placeToGoRecyclerView.setVisibility(View.VISIBLE);
            topDestinationRecyclerView.setVisibility(View.VISIBLE);
            mTvTitle1.setVisibility(View.VISIBLE);
            mTvTitle2.setVisibility(View.VISIBLE);
        }
    }
}