package com.grimgdl.ui.widget;




import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Spinner;


import com.grimgdl.adapter.Productos;
import com.grimgdl.model.Producto;

import java.util.ArrayList;


public class MultiSpinnerSelect extends Spinner implements
        OnMultiChoiceClickListener, OnCancelListener {

    private ArrayList<Producto> items;
    private boolean[] selected;
    private String defaultText;
    private MultiSpinnerListener listener;
    Productos arrayAdapter;
    ArrayList<Producto> selectedProduct = new ArrayList<>();


    public MultiSpinnerSelect(Context context) {
        super(context);
    }

    public MultiSpinnerSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiSpinnerSelect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        Log.d("Multi", "OnClick");
        if (isChecked)
            selected[which] = true;
        else
            selected[which] = false;

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.d("Multi", "onCancel");
        StringBuilder spinnerBuffer = new StringBuilder();
        ArrayList<Producto> productos = arrayAdapter.getProductos();
        String spinnerText;
        boolean someUnSelected = false;
        for (Producto producto : productos){
            if (producto.isChecked()){
                spinnerBuffer.append(producto.getNombre());
                spinnerBuffer.append(", ");
            } else {
                someUnSelected = true;
            }

        }

        if (someUnSelected){
            spinnerText = spinnerBuffer.toString();
            if (spinnerText.length() > 2)
                spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
            else
                spinnerText = defaultText;

        } else {
            spinnerText = defaultText;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, new String[]{ spinnerText });
        setAdapter(adapter);
        listener.onItemsSelected(selected);


    }

    @Override
    public boolean performClick() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setAdapter(arrayAdapter, null);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setOnCancelListener(this);

        AlertDialog alertDialog = builder.create();

        ListView listView = alertDialog.getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto producto = (Producto) parent.getItemAtPosition(position);

                if (producto.isChecked())
                    producto.setChecked(false);
                else
                    producto.setChecked(true);


                arrayAdapter.notifyDataSetChanged();

            }
        });

        //alertDialog





        alertDialog.show();
        return true;
    }

    public void setItems(ArrayList<Producto> items, String allText, MultiSpinnerListener listener){

        this.items = items;
        this.defaultText = allText;
        this.listener = listener;

        //all selected by default
        selected = new boolean[items.size()];
        for (int i = 0; i < selected.length; i++)
            selected[i] = false;

        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, new String[]{allText});
        setAdapter(adapter);*/
        arrayAdapter = new Productos(getContext(),android.R.layout.simple_spinner_item,
                items);
        setAdapter(arrayAdapter);


    }


    public interface MultiSpinnerListener {
        public void onItemsSelected(boolean[] selected);
    }

    public ArrayList<Producto> getSelectedItems(){
        ArrayList<Producto> productos = arrayAdapter.getProductos();
        ArrayList<Producto> produtoSelected = new ArrayList<>();
        for (Producto producto : productos){
            if (producto.isChecked()){
                produtoSelected.add(producto);
            }
        }
        return produtoSelected;
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

    public ArrayList<Producto> getSelectedProduct(){
        return selectedProduct;
    }
}
