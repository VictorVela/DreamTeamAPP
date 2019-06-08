package com.example.dreamteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.dreamteam.fragment.ContactFragment;
import com.example.dreamteam.fragment.FindJobFragment;
import com.example.dreamteam.fragment.GalleryFragment;
import com.example.dreamteam.fragment.HomeFragment;
import com.example.dreamteam.fragment.Information2Fragment;
import com.example.dreamteam.fragment.InformationFragment;
import com.example.dreamteam.fragment.JobFragment;
import com.example.dreamteam.fragment.NewPortifolioFragment;
import com.example.dreamteam.fragment.PersonFragment;
import com.example.dreamteam.model.Job;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DreamTeam extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private EditText nomeJob, valorJob;

    private FrameLayout frameLayout;

    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio2);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, homeFragment);
        fragmentTransaction.commit();

        frameLayout = findViewById(R.id.frameContainer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        referencia.child("pontos").setValue("100");


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, homeFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_gallery) {

            GalleryFragment galleryFragment = new GalleryFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, galleryFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_exit) {

                usuario.signOut();
                finish();

        }else if (id == R.id.nav_information) {
            Information2Fragment information2Fragment = new Information2Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, information2Fragment);
            fragmentTransaction.commit();

        }else if (id == R.id.nav_portifolio) {
            NewPortifolioFragment newPortifolioFragment = new NewPortifolioFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, newPortifolioFragment);
            fragmentTransaction.commit();

        }else if (id == R.id.nav_job) {
            JobFragment jobFragment = new JobFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, jobFragment);
            fragmentTransaction.commit();

        }else if (id == R.id.nav_find_job) {
            FindJobFragment findJobFragment = new FindJobFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, findJobFragment);
            fragmentTransaction.commit();

        }else if (id == R.id.nav_pessoa) {
            PersonFragment personFragment = new PersonFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, personFragment);
            fragmentTransaction.commit();

        }else if (id == R.id.nav_contact) {
            enviarEmail();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void enviarEmail(){

        Intent email = new Intent( Intent.ACTION_SEND );
        /*
        È possivel passar no array mais de um e-mail, basta colocar uma virgula depois do
        primeiro e-mail
         */
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"victorgvela@hotmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App DREAM TEAM");
        email.putExtra(Intent.EXTRA_TEXT, "Mensagem automática");

        //configurar apps para e-mail
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Escolha o App de e-mail:"));

    }

}
