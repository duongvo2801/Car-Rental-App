package com.example.carrentelrealtimeapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.carrentelrealtimeapp.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WebsiteActivity extends AppCompatActivity {
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayLink = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    Intent intent;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        AsyncTask<String, Void, String> content = new RSSFeed().execute("https://vnexpress.net/rss/oto-xe-may.rss");
        intent = new Intent(WebsiteActivity.this, DetailWeb.class);
        listView = findViewById(R.id.lvWebsite);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String link = arrayLink.get(i);
                intent.putExtra("openLink", link);
                startActivity(intent);
            }
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.theme_tele));
    }

    public class RSSFeed extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return content.toString();
        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);
            XMLParse xmlParse = new XMLParse();
            try {
                Document document = xmlParse.getDocument(s);
                NodeList nodeList = document.getElementsByTagName("item");
                String title = " ";
                String link = "";
                for (int i = 0; i < nodeList.getLength(); i++){
                    Element element = (Element) nodeList.item(i);
                    title = xmlParse.getValue(element, "title");
                    arrayList.add(title);
                    link = xmlParse.getValue(element, "link");
                    arrayLink.add(link);
                }
                arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(arrayAdapter);

            }catch (SAXException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}