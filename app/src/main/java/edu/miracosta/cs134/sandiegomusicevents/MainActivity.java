package edu.miracosta.cs134.sandiegomusicevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.miracosta.cs134.sandiegomusicevents.model.JSONLoader;
import edu.miracosta.cs134.sandiegomusicevents.model.MusicEvent;

public class MainActivity extends AppCompatActivity {

    private List<MusicEvent> eventsList;
    private MusicEventListAdapter mMusicEventListAdapter;
    private ListView mMusicEventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        eventsList = new ArrayList<>();
//        eventsList.add(new MusicEvent("Justin Timberlake", "February 20", "Thursday", "8:00 PM", "Pechanga Arena", "San Diego"));
//        eventsList.add(new MusicEvent("Blues Traveler", "February 27", "Thursday", "7:00 PM", "House of Blues", "San Diego"));
//        eventsList.add(new MusicEvent("Gladys Knight", "March 7", "Saturday", "8:00 PM", "Pechanga Resort and Casino", "Temecula"));
//        eventsList.add(new MusicEvent("Ariana Grande", "April 10", "Friday", "3:30 AM", "Coachella 2020", "Indio"));
//        eventsList.add(new MusicEvent("Goo Goo Dolls", "June 12", "Friday", "7:00 PM", "North Island Amphitheatre", "Chula Vista"));
//        eventsList.add(new MusicEvent("Rebelution", "June 12", "Friday", "7:00 PM", "North Island Amphitheatre", "Chula Vista"));
//        eventsList.add(new MusicEvent("Carlos Santana", "June 21", "Sunday", "7:00 PM", "North Island Amphitheatre", "Chula Vista"));
//        eventsList.add(new MusicEvent("Ozzy Osbourne", "July 21", "Tuesday", "7:30 PM", "North Island Amphitheatre", "Chula Vista"));

        // pull data from JSON
        try {
            eventsList = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: Wire up the musicEventsListView
        mMusicEventListView = findViewById(R.id.musicEventListView);
        // TODO: Instantiate a new ListAdapter (MusicEventListAdapter)
        mMusicEventListAdapter = new MusicEventListAdapter(this, R.layout.event_list_item, eventsList);
        // TODO: Set the adapter of the list view to the newly instantiated adapter
        mMusicEventListView.setAdapter(mMusicEventListAdapter);
    }

    public void openEventDetails(View v)
    {
        // Extract the "tag"
        MusicEvent clickedEvent = (MusicEvent) v.getTag();  // down cast to MusicEvent object
        // Set up an intent
        Intent intent = new Intent(this, EventDetailsActivity.class);

        // Fill the intent with the details about the clicked event
        intent.putExtra("Artist", clickedEvent.getArtist());
        intent.putExtra("Date", clickedEvent.getDate());
        intent.putExtra("Day", clickedEvent.getDay());
        intent.putExtra("Time", clickedEvent.getTime());
        intent.putExtra("Venue", clickedEvent.getVenue());
        intent.putExtra("City", clickedEvent.getCity());
        intent.putExtra("State", clickedEvent.getState());
        intent.putExtra("ImageName", clickedEvent.getImageName());


        // Go to (startActivity) event details
        startActivity(intent);
    }
}
