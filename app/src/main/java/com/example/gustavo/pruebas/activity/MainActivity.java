package com.example.gustavo.pruebas.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gustavo.pruebas.R;
import com.example.gustavo.pruebas.model.Producto;
import com.example.gustavo.pruebas.widget.MultiSpinnerSelect;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements
        MultiSpinnerSelect.MultiSpinnerListener, View.OnClickListener {

    Button btnSelected;
    TextView txt;
    MultiSpinnerSelect multiSpinnerSelect;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelected = (Button) findViewById(R.id.button);
        btnSelected.setOnClickListener(this);

        txt = (TextView) findViewById(R.id.txt_texto);

        multiSpinnerSelect = (MultiSpinnerSelect)
                findViewById(R.id.spinner_product);
        multiSpinnerSelect.setItems(getProductos(),"Nothing Selected",this);
    }

    private ArrayList<Producto> getProductos(){
        ArrayList<Producto> arrayList = new ArrayList<>();
        for (int i=0; i < 20; i++){
            final Producto producto = new Producto();
            producto.setId(i + 1);
            producto.setNombre("Producto " + i);
            producto.setPresentacion("gr2" + i);

            arrayList.add(producto);
        }


        return arrayList;
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

    @Override
    public void onItemsSelected(boolean[] selected) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:

                    getSelected();


                break;
        }

    }

    public void getSelected(){
        ArrayList<Producto> sele = multiSpinnerSelect.getSelectedItems();

        txt.setText("");
        txt.setText("item id\t\t\t\t\t\tnombre\n");
        for (Producto producto : sele){
            txt.append(producto.getId()+"\t\t\t\t\t\t\t\t\t\t"+producto.getNombre());
            txt.append(" \n");

        }
    }
}
