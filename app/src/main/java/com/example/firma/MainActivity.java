package com.example.firma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firma.transacciones.Transacciones;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText descripcion;
    Button btn_guardar, btn_galeria;
    View view5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        descripcion = (EditText) findViewById(R.id.txtdescripcion);
        view5 = (View) findViewById(R.id.viewfirma);

        btn_guardar = (Button)findViewById(R.id.btnguardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savefirma();
            }
        });

        btn_galeria = (Button)findViewById(R.id.btngaleria);
        btn_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void savefirma(){

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try{

            ContentValues valores = new ContentValues();

            valores.put(Transacciones.image, Viewfirma(view5));
            valores.put(Transacciones.descripcion, descripcion.getText().toString());

            Long resultado = db.insert(Transacciones.tabla_firmas, Transacciones.id, valores);

            Toast.makeText(getApplicationContext(), "FIRMA INGRESADA: " + resultado.toString(), Toast.LENGTH_LONG).show();


        }
        catch (Exception e){
            e.printStackTrace();
        }
       ClearScreen();

    }

    public static byte[]  Viewfirma(View view5) {
        view5.setDrawingCacheEnabled(true);
        Bitmap bitmap = view5.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void ClearScreen() {
        descripcion.setText("");
        //view5.setText("");
    }

}