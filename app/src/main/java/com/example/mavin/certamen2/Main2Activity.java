package com.example.mavin.certamen2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mavin.certamen2.conection.HttpServerConnection;

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
            protected void onPreExecute(){

            }

            @Override
            protected String doInBackground(Void... params) {
                //return new HttpServerConnection().connectToServer("https://api.github.com/users/"+uUsuario+"/repos", 18000);
                return new HttpServerConnection().connectToServer("http://www.mocky.io/v2/57eee3822600009324111202", 18384);

            }

            @Override
            protected void onPostExecute(String result) {
                if(result != null){
                    mAdapter = new UIAdapter(getLista(result));
                    mRecyclerView.setAdapter(mAdapter);

                }else {
                    setContentView(R.layout.no_encontrada);

                }
            }
        };

        task.execute();


    }

    private List<github> getLista(String result){
        List<github> listaProyectos = new ArrayList<github>();
        try {
            JSONArray lista = new JSONArray(result);

            int size = lista.length();

            for(int i = 0; i < size; i++){
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

}
