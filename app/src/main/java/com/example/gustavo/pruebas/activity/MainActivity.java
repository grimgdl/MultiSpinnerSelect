package com.example.gustavo.pruebas.activity;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gustavo.pruebas.R;
import com.grimgdl.model.Product;
import com.grimgdl.ui.widget.MultiSpinnerSelect;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        MultiSpinnerSelect.MultiSpinnerListener, View.OnClickListener {

    Button btnSelected;
    TextView txt;
    MultiSpinnerSelect multiSpinnerSelect;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelected =  findViewById(R.id.button);
        btnSelected.setOnClickListener(this);

        txt = findViewById(R.id.txt_texto);

        multiSpinnerSelect = findViewById(R.id.spinner_product);
        multiSpinnerSelect.setItems(getProducts(),"Nothing Selected",this);
    }

    private ArrayList<Product> getProducts(){
        ArrayList<Product> arrayList = new ArrayList<>();
        for (int i=0; i < 20; i++){
            final Product producto = new Product(i + 1, "Producto " + i, "gr2" + i);


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

        if(v.getId() == R.id.button ){ getSelected(); }

    }

    public void getSelected(){
        List<Product> sele = multiSpinnerSelect.getSelectedItems();

        txt.setText("");
        txt.setText("item id\t\t\t\t\t\tnombre\n");
        for (Product producto : sele){
            txt.append(producto.getId()+"\t\t\t\t\t\t\t\t\t\t"+producto.getNombre());
            txt.append(" \n");

        }
    }
}
