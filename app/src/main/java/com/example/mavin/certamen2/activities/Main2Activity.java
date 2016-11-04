package com.example.mavin.certamen2.activities;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mavin.certamen2.R;
import com.example.mavin.certamen2.UIAdapter;
import com.example.mavin.certamen2.conection.HttpServerConnection;
import com.example.mavin.certamen2.model.github;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String uUsuario;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        uUsuario = getIntent().getExtras().getString("usuario");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(Void... params) {
                //return new HttpServerConnection().connectToServer("https://api.github.com/users/"+uUsuario+"/repos", 18000);
                return new HttpServerConnection().connectToServer("http://www.mocky.io/v2/57eee3822600009324111202", 18384);

            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    mAdapter = new UIAdapter(getLista(result));
                    mRecyclerView.setAdapter(mAdapter);

                } else {
                    setContentView(R.layout.no_encontrada);

                }
            }
        };

        task.execute();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private List<github> getLista(String result) {
        List<github> listaProyectos = new ArrayList<github>();
        try {
            JSONArray lista = new JSONArray(result);

            int size = lista.length();

            for (int i = 0; i < size; i++) {
                github git = new github();
                JSONObject objeto = lista.getJSONObject(i);

                git.setName(objeto.getString("name"));
                git.setDescription(objeto.getString("description"));
                git.setUpdated_at(objeto.getString("updated_at"));
                git.setHtml_url(objeto.getString("html_url"));


                listaProyectos.add(git);
            }
            return listaProyectos;

        } catch (JSONException e) {
            e.printStackTrace();
            return listaProyectos;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
