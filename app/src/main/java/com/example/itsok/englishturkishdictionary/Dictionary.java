package com.example.itsok.englishturkishdictionary;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class Dictionary extends ActionBarActivity {

    private final String TAG = "PYSHARP";
/////////////////////////////////////////////////////////////////////////////////////////////
    private HorizontalListView mHlvSimpleList;

    private String[] mSimpleListValues;

    private int myItemInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        Log.d(TAG, "BASLIYOR");
        Toast.makeText(this, "PYSHARP",
                Toast.LENGTH_LONG).show();

	///////////////////////////////////////////////////////////////////////////
        // Get references to UI widgets
        mHlvSimpleList = (HorizontalListView) findViewById(R.id.hlvSimpleList);
        mHlvSimpleList.setDividerWidth(30);
	///////////////////////////////////////////////////////////////////////////
        mHlvSimpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemi, long mylng) {
                myItemInt = myItemi;
                SelectRunnable selectRunnable = new SelectRunnable();
                selectRunnable.run();
            }
        });
        //////////////////////////////////////////////////////////////////////

        //HashMap<String, String > hashMap = new HashMap();
        /*
        define set, it keeps list of key words.
         */
        Set<String> keyIngSet = new TreeSet<String>();
        /*
        read file from assets.
         */
        try {
            AssetManager mngr=getAssets();
            BufferedReader buf;
            buf = new BufferedReader(new InputStreamReader(mngr.open("ing/ing.txt")));
            String mString;
            while ((mString = buf.readLine())!= null) {
                keyIngSet.add(mString);
            }

            Log.d(TAG, "string: " + mString);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "CATCH",
                    Toast.LENGTH_LONG).show();

        }

        mSimpleListValues = new String[keyIngSet.size()];

        keyIngSet.toArray(mSimpleListValues);
        Log.d(TAG, "VALUE: " + mSimpleListValues[1]);
        setupSimpleList();

        /*
        binary search
         */
        /*
        Set<String> valueSet = new TreeSet<String>();
        String[] strArry = new String[valueSet.size()];
        valueSet.toArray(strArry);

        for (int i = 0; i < 51000; i++) {
            String val = getRandomString();
            valueSet.add(val);
        }

        long start = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++) {
            List<String> list = new ArrayList<String>();
            String prefix = strArry[i].substring(0, 3);
            int pos = Arrays.binarySearch(strArry, prefix);
            while (pos >= 0 && pos < strArry.length) {
                if (strArry[pos].startsWith(prefix)) {
                    list.add(strArry[pos]);
                } else {
                    break;
                }
                pos++;
            }
        }
        long end = System.currentTimeMillis();
        long duration = end - start;
        Log.d(TAG, "duration: " + duration);
        */

    }
//////////////////////////////////////////////////////////////
public class SelectRunnable implements Runnable {
    @Override
    public void run() {
        /*
         * Code you want to run on the thread goes here
         */
        String selectedFromList = (String) (mHlvSimpleList.getItemAtPosition(myItemInt));
        Log.d(TAG, "SELECTED ITEM: " + selectedFromList);
    }
}

///////////////////////////////////////////////////////////////////////////////
        private void setupSimpleList() {
        // Make an array adapter using the built in android layout to render a list of strings
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.hlistview,mSimpleListValues);
            // Assign adapter to the HorizontalListView
            mHlvSimpleList.setAdapter(adapter);
        } catch (Exception e) {
            Log.d(TAG, "NULL POINTER");
        }


    }

///////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dictionary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
