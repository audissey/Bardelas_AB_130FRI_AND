package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView newsListView;
    private ArrayList<String> newsList;
    private Random random = new Random();

    // HashMap to store title and description pairs
    private HashMap<String, String> newsDetailsMap = new HashMap<>();

    private String[] sampleNewsHeadlines = {
            "Breaking: Major Tech Breakthrough in AI!",
            "Sports: Local Team Wins Championship",
            "Entertainment: New Blockbuster Movie Released",
            "Politics: Global Summit Brings Leaders Together",
            "Economy: Stock Markets Soar Amid Optimism",
            "Health: New Vaccine Announced for Disease",
            "Weather: Storm Expected to Hit Coast Tomorrow",
            "Science: New Discovery on Mars!",
            "Travel: Best Destinations for 2024 Revealed",
            "Tech: Smartphone Companies Announce New Devices",
            "Finance: Cryptocurrency Prices See Sharp Rise",
            "Education: New Online Courses for Free Learning",
            "Environment: Wildfires Contained After Massive Efforts",
            "Space: Astronauts Set New Record in Spacewalk",
            "Fashion: Fall 2024 Trends Take Center Stage",
            "Lifestyle: Top Fitness Trends for a Healthier You"
    };

    private String[] sampleNewsDescriptions = {
            "The tech world has seen a major breakthrough in AI today. A new algorithm developed by researchers is capable of mimicking human reasoning, surpassing current limitations in natural language understanding and autonomous decision-making. Experts believe this advancement will revolutionize industries such as healthcare, robotics, and customer service.",

            "In an exhilarating game, the local team triumphed over their rivals in the national championship. The game went into overtime, with a last-minute goal securing victory. Fans are celebrating in the streets, and the win marks the team's first championship in over a decade, solidifying their status as local heroes.",

            "The latest blockbuster movie has hit the screens, receiving rave reviews from critics and audiences alike. Directed by an acclaimed filmmaker, the movie features stunning visuals, a captivating storyline, and a cast of A-list stars. It's already being tipped as a strong contender for the upcoming awards season.",

            "At the global summit, world leaders discussed critical issues ranging from climate change to global economic recovery post-pandemic. Key agreements were made to accelerate green energy initiatives and increase cooperation in handling international conflicts. The summit is being hailed as a success in advancing global collaboration.",

            "The stock markets are soaring, reflecting increased optimism about the global economy's recovery. Investors are particularly bullish on tech and energy sectors, which have seen significant growth in recent months. Analysts predict continued gains, but caution about potential volatility as inflation concerns linger.",

            "A new vaccine has been announced that promises to tackle a widespread disease that has affected millions worldwide. Early trials show promising results, with over 90% efficacy. Governments and health organizations are preparing for mass production and distribution in hopes of curbing future outbreaks.",

            "A storm is expected to hit the coast tomorrow with heavy rainfall and winds exceeding 100 mph. Meteorologists have issued warnings for residents to take necessary precautions, including securing properties and stocking up on essentials. The storm is predicted to last through the weekend, potentially causing widespread flooding and damage.",

            "Scientists have discovered a new element on Mars, opening new possibilities for space exploration. The element, found in a unique rock formation, could hold clues to the planet's geological history and the potential for future human colonization. NASA is planning further missions to study this groundbreaking discovery.",

            "Check out the top travel destinations for 2024, featuring beautiful locations ranging from tropical islands to historic European cities. With travel restrictions easing, more people are planning vacations to explore new cultures, relax on pristine beaches, or hike through stunning landscapes. Experts recommend early bookings to avoid the expected rush.",

            "Tech companies unveiled their latest smartphones, featuring groundbreaking technologies like foldable displays, advanced AI cameras, and faster processors. Consumers are particularly excited about the extended battery life and enhanced connectivity features that make these devices ideal for both work and play.",

            "Cryptocurrency prices have surged dramatically, with Bitcoin leading the charge at an all-time high. Investors are flocking to digital assets as inflation fears rise, and governments around the world explore new regulations. Analysts are divided on whether this is a bubble or the beginning of a new financial era.",

            "Several online platforms are offering free educational courses for learners worldwide, covering subjects from coding to business management. As remote learning grows in popularity, many students and professionals are taking advantage of these resources to improve their skills and gain new certifications. The trend is expected to continue in the coming years.",

            "Firefighters have successfully contained the wildfires after a massive effort spanning multiple states. The fires, which destroyed thousands of acres of land, were exacerbated by dry conditions and high winds. Communities affected by the devastation are beginning to rebuild, with support from government relief programs.",

            "Astronauts have set a new spacewalk record, spending more than 12 hours outside the International Space Station. During the mission, they completed repairs to a critical solar panel, ensuring the station continues to receive power. This milestone further demonstrates human endurance in the harsh conditions of space.",

            "The latest fall trends in fashion are out, with bold colors and patterns dominating the runway. Designers are embracing a mix of retro and futuristic styles, with oversized coats, neon accessories, and sustainable materials taking center stage. Fashion influencers are already showcasing how to incorporate these looks into everyday wardrobes.",

            "Check out the latest fitness trends to help you achieve your health goals this year. From high-intensity interval training (HIIT) to mindful yoga, there's something for everyone. Fitness experts emphasize the importance of finding a routine that balances physical health with mental well-being, especially after the challenges of the past few years.",

            "New research shows that a plant-based diet can significantly improve heart health and reduce the risk of chronic diseases. Nutritionists are encouraging people to incorporate more vegetables, fruits, and whole grains into their meals. Plant-based alternatives to meat and dairy products are gaining popularity as consumers seek healthier and more sustainable options.",

            "A breakthrough in quantum computing could revolutionize the tech industry, enabling faster processing speeds and solving problems that were previously thought impossible. This advancement is expected to have far-reaching implications, particularly in fields such as cryptography, artificial intelligence, and material science.",

            "The global art community is buzzing as a lost masterpiece from the Renaissance era has been discovered in a private collection. The painting, believed to be the work of a famous Italian artist, has been authenticated by experts and will soon be on display at a major museum. Art historians are calling this one of the most significant finds of the century.",

            "A new study reveals that sleep deprivation can have long-term negative effects on mental health, increasing the risk of anxiety and depression. Doctors are recommending better sleep hygiene practices, including reducing screen time before bed and establishing a consistent sleep schedule. The findings have sparked a renewed interest in sleep research."
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListView = findViewById(R.id.newsListView);
        newsList = new ArrayList<>();

        // Populate news list and map with corresponding descriptions
        generateRandomNews(10); // Change the number here for more news items

        // Set up the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsList);
        newsListView.setAdapter(adapter);

        // Set up click listener for the ListView
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNewsTitle = newsList.get(position);
                String selectedNewsDescription = newsDetailsMap.get(selectedNewsTitle);

                // Create an Intent to start NewsDetailActivity
                Intent intent = new Intent(MainActivity.this, com.example.newsapp.NewsDetailActivity.class);
                intent.putExtra("news_title", selectedNewsTitle);
                intent.putExtra("news_description", selectedNewsDescription);
                startActivity(intent);
            }
        });
    }

    private void generateRandomNews(int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            int randomIndex = random.nextInt(sampleNewsHeadlines.length);
            String randomNewsTitle = sampleNewsHeadlines[randomIndex];
            String randomNewsDescription = sampleNewsDescriptions[randomIndex];

            // Add title to the list and map with its description
            newsList.add(randomNewsTitle);
            newsDetailsMap.put(randomNewsTitle, randomNewsDescription);
        }
    }
}
