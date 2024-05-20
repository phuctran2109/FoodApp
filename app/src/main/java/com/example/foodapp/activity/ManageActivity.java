package com.example.foodapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodapp.R;
import com.example.foodapp.fragment.AdminBillFragment;
import com.example.foodapp.fragment.AdminChartFragment;
import com.example.foodapp.fragment.AdminMemberFragment;
import com.example.foodapp.fragment.AdminNoticeFragment;
import com.example.foodapp.fragment.AdminProductFragment;
//import com.example.foodapp.fragment.AdminTypeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ManageActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Set default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new AdminProductFragment()).commit();
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();
                if (itemId == R.id.product_manager) {
                    selectedFragment = new AdminProductFragment();
                } else if (itemId == R.id.member_manager) {
                    selectedFragment = new AdminMemberFragment();
//                } else if (itemId == R.id.type_product) {
//                    selectedFragment = new AdminTypeFragment();
//                }
                } else if (itemId == R.id.bill) {
                    selectedFragment = new AdminBillFragment();
                } else if (itemId == R.id.chart) {
                    selectedFragment = new AdminChartFragment();
                } else if (itemId == R.id.notification) {
                    selectedFragment = new AdminNoticeFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                }

                return true;
            }
        });
    }
}
