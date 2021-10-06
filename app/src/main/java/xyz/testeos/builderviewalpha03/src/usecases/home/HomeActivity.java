package xyz.testeos.builderviewalpha03.src.usecases.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xyz.testeos.builderviewalpha03.R;
import xyz.testeos.builderviewalpha03.src.model.domain.Material;
import xyz.testeos.builderviewalpha03.src.usecases.budget.BudgetFragment;
import xyz.testeos.builderviewalpha03.src.usecases.builds.BuildsFragment;
import xyz.testeos.builderviewalpha03.src.usecases.editArea.EditAreaFragment;
import xyz.testeos.builderviewalpha03.src.usecases.materialsList.MaterialsAdapter;
import xyz.testeos.builderviewalpha03.src.usecases.materialsList.MaterialsFragment;
import xyz.testeos.builderviewalpha03.src.usecases.materialsList.MaterialsViewModel;
import xyz.testeos.builderviewalpha03.src.usecases.providerList.ProviderListFragment;

public class HomeActivity extends AppCompatActivity {

    private BudgetFragment budgetFragment;
    private Bundle objSend;
    private HomeViewModel viewModel;
    private List<Material> materialsList;
    private ViewPagerAdapter mAdapter;
    private ViewPager2 viewPager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Set del tema de la aplicacion ( remplaza el splashscreen )
        setTheme(R.style.Theme_Design_Light_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Instancia del budget fragment
        budgetFragment = new BudgetFragment();
        //Instancia de la lista de materiales
        materialsList = new ArrayList<>();

        progressBar = findViewById(R.id.progress);

        //Instancia del viewmodel
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);


        viewPager = findViewById(R.id.viewPager);

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

            TabLayout tabLayout = findViewById(R.id.tabMenu);

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