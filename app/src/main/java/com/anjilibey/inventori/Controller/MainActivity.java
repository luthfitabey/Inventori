package com.anjilibey.inventori.Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.anjilibey.inventori.R;

public class MainActivity extends AppCompatActivity {
    GridLayout mainGrid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainGrid = findViewById(R.id.gridMain);
        setSingleEvent(mainGrid);
    }
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View myFragmentView = inflater.inflate(R.layout.activity_main, container, false);
//        GridLayout mainGrid = (GridLayout) myFragmentView.findViewById(R.id.gridMain);
//        setSingleEvent(mainGrid);
//        return myFragmentView;
//    }
    public void setSingleEvent(GridLayout mainGrid) {
        //loop all child item of maingrid
        for(int i=0; i<mainGrid.getChildCount();i++){
            //cardview merupakan bagian dari child gridview
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //toast bisa diganti dengan star new activity
//                    Toast.makeText(MainActivity.this, "Clicked at index"+finalI, Toast.LENGTH_SHORT).show();
                    if(finalI == 0 ){
                        Intent intent = new Intent(MainActivity.this, TambahBarang.class);
                        startActivity(intent);
                    }
                    else if(finalI == 1 ){
                        Intent intent = new Intent(MainActivity.this, LihatBarang.class);
                        startActivity(intent);
                    }
                    else if(finalI == 2 ){
                        Intent intent = new Intent(MainActivity.this, TambahBarang.class);
                        startActivity(intent);
                    }
                    else if(finalI == 3 ){
                        Intent intent = new Intent(MainActivity.this, LihatBarang.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "state : Hayo lupa belum bikin activity ya :)", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
