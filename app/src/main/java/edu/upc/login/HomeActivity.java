package edu.upc.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

import edu.upc.login.Entidades.Item;
import edu.upc.login.Fragments.DetalleItemFragment;
import edu.upc.login.Fragments.FragmentCamara;
import edu.upc.login.Fragments.FragmentInventario;
import edu.upc.login.Fragments.FragmentPerfil;
import edu.upc.login.Fragments.FragmentEstadisticas;
import edu.upc.login.Fragments.FragmentItems;
import edu.upc.login.Fragments.MainFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iComunicaFragments {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    //variables para cargar fragment principal

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //variables del fragmentdetalle item
    DetalleItemFragment detalleItemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigationView);

        //establecer evento onclick al navigation view

        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open , R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //cargar fragment principal
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new MainFragment());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.inventario){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentInventario());
            fragmentTransaction.commit();

        }

        if(menuItem.getItemId() == R.id.items){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentItems());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.statistics){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentEstadisticas());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.perfil){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentPerfil());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.camara){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentCamara());
            fragmentTransaction.commit();

        }

        return false;
    }

    @Override
    public void enviarObjeto(Item item) {
        // Aquí se realiza toda la logica necesaria para poder realizar el envío
        detalleItemFragment = new DetalleItemFragment();
        //objeto de tipo bundle para transportar la información
        Bundle bundleEnvio = new Bundle();
        //enviar el objeto que está llegando con Serializable
        bundleEnvio.putSerializable("objeto", item);
        detalleItemFragment.setArguments(bundleEnvio);
        //abrir fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, detalleItemFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
