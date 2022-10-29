package com.example.dw3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class Venta extends AppCompatActivity {


    String titulo= "mi_actividad";
    int id_producto,id_usuario;
    float precio;
    String fechaCaptura,iprod,iusua,prec,nombre1,descripcion1,idcliente,idven;
    int id_venta=7;
    int id_cliente=1;
    TextView nombreTv,descripcionTv,precioTv;
    ImageButton register;
    //-------------------

    private static String url="https://proyectodesarrollosql.com/Android/registrar_venta.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        register=findViewById(R.id.ibVenta);
        this.setTitle(R.string.mi_titulo);
        //ImageButton register;
        idven=String.valueOf(id_venta);
        idven=String.valueOf(id_venta);
        idcliente=String.valueOf(id_cliente);
        id_producto=getIntent().getIntExtra("ID_PRODUCTO",0);
        iprod=String.valueOf(id_producto);
        id_usuario=getIntent().getIntExtra("ID_USUARIO",0);
        iusua=String.valueOf(id_usuario);
        nombre1=getIntent().getStringExtra("NOMBRE");
        descripcion1=getIntent().getStringExtra("DESCRIPCION");
        precio=getIntent().getFloatExtra("PRECIO",0);
        prec=String.valueOf(precio);
        fechaCaptura=getIntent().getStringExtra("FECHACAPTURA");

        nombreTv=findViewById(R.id.tvVnombre);
        descripcionTv=findViewById(R.id.tvVdescripcion);
        precioTv=findViewById(R.id.tvVprecio);

        nombreTv.setText(nombre1);
        descripcionTv.setText(descripcion1);
        precioTv.setText(prec);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarVenta(url);
            }
        });

    }
/*

    private void registrarVenta(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){

                    System.out.println("COOONEXION REALIZADA CONE EXITO");
                }
                else{
                    Toast.makeText(Venta.this, "ERROR",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Venta.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                //parametros.put("id_venta",id_venta);
                parametros.put("id_cliente",idcliente);

                parametros.put("id_producto",iprod);
                parametros.put("id_usuario",iusua);
                parametros.put("precio",prec);
                parametros.put("fechaCompra",fechaCaptura);


                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/




    private void registrarVenta(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {

                    System.out.println("COOONEXION REALIZADA CONE EXITO");
                } else {
                    Toast.makeText(Venta.this, "ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Venta.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("id_venta",idven);
                parametros.put("id_cliente", idcliente);

                parametros.put("id_producto", iprod);
                parametros.put("id_usuario", iusua);
                parametros.put("precio", prec);
                parametros.put("fechaCompra", fechaCaptura);


                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}