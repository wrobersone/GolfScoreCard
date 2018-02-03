package com.teamtreehouse.golfscorecard;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity {
    private static final String PRES_FILE = "com.teamtreehouse.golfscorecard.preferences";
    private static final String KEY_STROKE = "key_stroke";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Hole[] mHoles = new Hole[18];
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PRES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        // Initialize holes
        int strokes = 0;
        for (int i = 0; i < mHoles.length; i++) {
            strokes = mSharedPreferences.getInt(KEY_STROKE + i, 0);
            mHoles[i] = new Hole("Hole " + (i + 1) + " :", strokes);
        }

        mListAdapter = new ListAdapter(this, mHoles);
        setListAdapter(mListAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (int i = 0; i < mHoles.length; i++) {
            mEditor.putInt(KEY_STROKE + i, mHoles[i].getStroke());
        }
        mEditor.apply();
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
        if (id == R.id.action_clear_strokes) {
            mEditor.clear();
            mEditor.apply();

            for (Hole hole: mHoles) {
                hole.setStroke(0);
            }
            mListAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
