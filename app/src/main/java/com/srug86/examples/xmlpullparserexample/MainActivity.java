package com.srug86.examples.xmlpullparserexample;

import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.srug86.examples.xmlpullparserexample.domain.Template;
import com.srug86.examples.xmlpullparserexample.handler.XmlPullParserHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.srug86.examples.xmlpullparserexample.R.xml.customer;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Template template;
        try {
            XmlPullParserHandler parser = new XmlPullParserHandler();
            File toPath = Environment.getExternalStorageDirectory();
            //File toPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            //File toPath = Environment.getDataDirectory();
            File xmlFile = new File("/storage/emulated/0/Documents/customer.xml");
            FileInputStream fis = new FileInputStream(xmlFile);
            template = parser.parse(fis);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
