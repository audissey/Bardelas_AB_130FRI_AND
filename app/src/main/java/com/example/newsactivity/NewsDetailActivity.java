package com.example.newsapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailActivity extends AppCompatActivity {

    private TextView newsTitleTextView;
    private TextView newsDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // Get the title and description from the Intent
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsDescription = getIntent().getStringExtra("news_description");

        // Set up the TextViews
        newsTitleTextView = findViewById(R.id.newsTitleTextView);
        newsDescriptionTextView = findViewById(R.id.newsDescriptionTextView);

        // Display the news title and description
        newsTitleTextView.setText(newsTitle);
        newsDescriptionTextView.setText(newsDescription);
    }
}
