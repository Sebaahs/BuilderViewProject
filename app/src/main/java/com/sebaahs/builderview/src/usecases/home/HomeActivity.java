package com.sebaahs.builderview.src.usecases.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Material;
import com.sebaahs.builderview.src.usecases.budget.BudgetFragment;
import com.sebaahs.builderview.src.usecases.builds.BuildsFragment;
import com.sebaahs.builderview.src.usecases.editArea.EditAreaFragment;
import com.sebaahs.builderview.src.usecases.materialsList.MaterialsListActivity;
import com.sebaahs.builderview.src.usecases.providerList.ProviderListActivity;

public class HomeActivity extends AppCompatActivity {

    private Intent intent;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private TabLayout tabLayout;
    private BudgetFragment budgetFragment;
    private Bundle objSend;
    private HomeViewModel viewModel;
    private List<Material> materialsList;
    private ViewPagerAdapter mAdapter;
    private ViewPager2 viewPager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Instancia del budget fragment
        budgetFragment = new BudgetFragment();
        //Instancia de la lista de materiales
        materialsList = new ArrayList<>();

        //Inicializacion de IU
        progressBar = findViewById(R.id.progress);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabMenu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_drawer);
        toolbar = findViewById(R.id.toolbar);

        //setup toolbar
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(item -> {

            if (item.getItemId() == R.id.menu_item_materials){
                intent = new Intent(this, MaterialsListActivity.class);
                startActivity(intent);
                return false;
            }
            if (item.getItemId() == R.id.menu_item_providers){
                intent = new Intent(this, ProviderListActivity.class);
                startActivity(intent);
                return false;
            }
            if (item.getItemId() == R.id.menu_item_settings){
                //intent = new Intent(this, SettingsActivity.class);
            }
            if (item.getItemId() == R.id.menu_item_info){
                //intent = new Intent(this, InfoActivity.class);
            }
            if (item.getItemId() == R.id.menu_item_logout){
                //LOGOUT
                Toast.makeText(this,"LOGOUT",Toast.LENGTH_SHORT).show();
                return false;
            }
            return false;
        });



        //Instancia del viewmodel
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());


        //Observer
        viewModel.getLdMaterialsListObserver().observe(this, materials -> {

            if (materials != null){
                //Instancia del parametro bundle
                objSend = new Bundle();

                objSend.putParcelableArrayList("key", (ArrayList<? extends Parcelable>) materials);
                budgetFragment.setArguments(objSend);
                progressBar.setVisibility(View.GONE);
            }

            mAdapter.addFragment(new BuildsFragment());
            mAdapter.addFragment(new EditAreaFragment());
            mAdapter.addFragment(budgetFragment);

            viewPager.setAdapter(mAdapter);

            new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {

                    if (position == 0)tab.setText("Mi Casa");
                    if (position == 1)tab.setText("Listado");
                    if (position == 2)tab.setText("Computo");


                }
            }).attach();
        });

        //llamada a la API
        viewModel.MakeCall();

    }

}