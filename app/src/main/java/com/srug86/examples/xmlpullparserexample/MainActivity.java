package com.srug86.examples.xmlpullparserexample;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.srug86.examples.xmlpullparserexample.adapter.CategoryAdapter;
import com.srug86.examples.xmlpullparserexample.domain.Template;
import com.srug86.examples.xmlpullparserexample.handler.XmlPullParserHandler;

import java.io.File;
import java.io.FileInputStream;


public class MainActivity extends ActionBarActivity {

    private TextView tvTemplateName;
    private RecyclerView rvTemplateContent;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemplateName = (TextView) findViewById(R.id.tvTemplateName);
        rvTemplateContent = (RecyclerView) findViewById(R.id.rvTemplateContent);

        Template template = loadTemplate();
        if (template == null) { return; }

        tvTemplateName.setText(template.getName());
        mAdapter = new CategoryAdapter(template.getCategoryList());
        rvTemplateContent.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        rvTemplateContent.setLayoutManager(mLayoutManager);

        //rvTemplateContent.setHasFixedSize(true);
    }

    private Template loadTemplate() {
        Template template = null;

        try {
            XmlPullParserHandler parser = new XmlPullParserHandler();
            File xmlFile = new File("/storage/emulated/0/Documents/customer.xml");
            FileInputStream fis = new FileInputStream(xmlFile);
            template = parser.parse(fis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return template;
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
