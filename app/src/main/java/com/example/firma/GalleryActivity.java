package com.example.firma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.firma.tabla.signaturess;
import com.example.firma.transacciones.Transacciones;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    SQLiteConexion conexion;

    RecyclerView recycler;

    ArrayList<signaturess> galeria;
    List<signaturess> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        galeria = new ArrayList<>();

        // Inicializar Imagenes
        GetListGallery();

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);

        // Usar un administrador para LinearLayout
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // Crear un nuevo adaptador
        SignAdapter adapter = new SignAdapter(items);
        recycler.setAdapter(adapter);
    }


    private void GetListGallery() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        signaturess Items = null;
        galeria = new ArrayList<signaturess>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tabla_firmas, null);

        while (cursor.moveToNext()) {
            Items = new signaturess();
            Items.setId(cursor.getInt(0));
            Items.setImage(cursor.getBlob(1));
            Items.setDescripcion(cursor.getString(2));

            galeria.add(Items);
        }

        cursor.close();
        GalleryList();
    }

    private void GalleryList() {

        items = new ArrayList<>();

        for (int i = 0;  i < galeria.size(); i++){

            items.add(new signaturess(
                    galeria.get(i).getId(),
                    galeria.get(i).getImage(),
                    galeria.get(i).getDescripcion()));
        }
    }
}