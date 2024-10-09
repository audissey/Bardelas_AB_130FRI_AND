package com.example.bottomnavigationbar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.bottomnavigationbar.databinding.ActivityMainBinding;


import com.example.bottomnavigationbar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {  // Handle home selection
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.profile) {  // Handle profile selection
                replaceFragment(new ProfileFragment());
            } else if (itemId == R.id.friends) {  // Handle friends selection
                replaceFragment(new FriendsFragment());
            }
            return true; // Return true to indicate the selection was handled
        });
    }

    // Helper method to replace the fragment in the container
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);  // Make sure R.id.frame_layout is correct
        fragmentTransaction.commit();
    }
}
