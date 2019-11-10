package com.example.elton.playermusica.Activity;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.design.widget.TabLayout;
import com.example.elton.playermusica.Adapter.ViewPagerAdapter;
import com.example.elton.playermusica.Fragment.AlbumFragment;
import com.example.elton.playermusica.Fragment.ArtistaFragment;
import com.example.elton.playermusica.Fragment.MusicaFragment;
import com.example.elton.playermusica.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private ImageView setaArtista,setaAlbum,setaMusica;
    private EditText artistaText,albumText,musicaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------TabLayout-------------------------------------------
        tabLayout = (TabLayout) findViewById(R.id.table_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        viewPager = (ViewPager) findViewById(R.id.viewpagger);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new ArtistaFragment(),"Artista");
        adapter.AddFragment(new AlbumFragment(),"Album");
        adapter.AddFragment(new MusicaFragment(),"Musica");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //------------------------------TabLayout-------------------------------------------



        //------------------------------Artista----------------------------------------------
        //------------------------------Album------------------------------------------------
        albumText = findViewById(R.id.album_text);
        setaAlbum = findViewById(R.id.album_seta);


        //------------------------------Album------------------------------------------------
        //------------------------------Musica-----------------------------------------------
        musicaText = findViewById(R.id.musica_text);
        setaMusica = findViewById(R.id.musica_seta);

        //------------------------------Musica------------------------------------------------

    }

    @Override
    public void onClick(View v) {
    }
}