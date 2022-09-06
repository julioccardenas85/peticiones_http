package com.example.peticioneshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText idText, titleTxt, bodyTxt;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idText = findViewById(R.id.idText);
        titleTxt = findViewById(R.id.titleTxt);
        bodyTxt = findViewById(R.id.bodyTxt);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leerAPI();
                Toast toast = Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void leerAPI() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //se ejecuta cuando da una respuesta el webservice y capturamos info

                try {
                    JSONObject jsonObject = new JSONObject(response); //convierte a un objeto
                    //opcion 1 para capturar el objeto
                    idText.setText(jsonObject.getString("userId"));
                    //opc 2
                    String title = jsonObject.getString("title");
                    titleTxt.setText(title);

                    bodyTxt.setText(jsonObject.getString("body"));

                    //agregamos try y catch
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        //llamamos string req para ejecutar solo envio
        Volley.newRequestQueue(this).add(postRequest);
    }

}