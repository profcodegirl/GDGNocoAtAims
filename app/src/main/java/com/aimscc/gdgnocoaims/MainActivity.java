package com.aimscc.gdgnocoaims;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_map) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
            Uri.parse("https://maps.google.com/maps?q=Ed+Beatty+Hall,+Beatty+Ct,+Greeley,+CO+80634,+USA"));

            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_aims_info)
        {
            String url = "http://www.aims.edu";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        else if(id == R.id.action_gdgnoco_info)
        {
            String url = "http://www.meetup.com/GDGNoCo/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements Shaker.Callback {

        MediaPlayer mp2;
        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cow_and_bell);
            Shaker shaker = new Shaker(getActivity(), 3.0d, 0, this);
            mp2 = MediaPlayer.create(getActivity(),R.raw.frying_pan);
            rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("Long click", "Long Click");
                    mp.start();
                    return false;
                }
            });

            return rootView;
        }

        @Override
        public void shakingStarted() {
            Log.d("Shake","Shake22");
            //MediaPlayer mp2 = MediaPlayer.create(getActivity(),R.raw.frying_pan);
            mp2.start();
        }

        @Override
        public void shakingStopped() {


        }
    }
}
