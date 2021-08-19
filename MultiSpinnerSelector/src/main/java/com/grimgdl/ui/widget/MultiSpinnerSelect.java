package com.grimgdl.ui.widget;




import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Spinner;


import com.grimgdl.adapter.ItemsAdapter;
import com.grimgdl.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MultiSpinnerSelect extends Spinner implements
        OnMultiChoiceClickListener, OnCancelListener {

    private boolean[] selected;
    private String defaultText;
    private MultiSpinnerListener listener;
    private ItemsAdapter arrayAdapter;
    List<Product> selectedProduct = new ArrayList<>();


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
        selected[which] = isChecked;

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.d("Multi", "onCancel");
        StringBuilder spinnerBuffer = new StringBuilder();
        List<Product> productos = arrayAdapter.getProductos();
        String spinnerText;
        boolean someUnSelected = false;
        for (Product producto : productos){
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

        super.performClick();

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setAdapter(arrayAdapter, null);
        builder.setPositiveButton("Aceptar", (dialog, which) -> dialog.cancel());
        builder.setOnCancelListener(this);

        AlertDialog alertDialog = builder.create();



        ListView listView = alertDialog.getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Product producto = (Product) parent.getItemAtPosition(position);

            producto.setChecked(!producto.isChecked());


            arrayAdapter.notifyDataSetChanged();

        });

        //alertDialog

        alertDialog.show();
        return true;
    }

    public void setItems(List<Product> items, String allText, MultiSpinnerListener listener){

        this.defaultText = allText;
        this.listener = listener;

        //all selected by default
        selected = new boolean[items.size()];
        Arrays.fill(selected, false);


        arrayAdapter = new ItemsAdapter(getContext(),items);
        setAdapter(arrayAdapter);

    }


    public interface MultiSpinnerListener {
        void onItemsSelected(boolean[] selected);
    }

    public List<Product> getSelectedItems(){
        List<Product> productos = arrayAdapter.getProductos();
        ArrayList<Product> produtoSelected = new ArrayList<>();
        for (Product producto : productos){
            if (producto.isChecked()){
                produtoSelected.add(producto);
            }
        }
        return produtoSelected;
    }
    private List<Product> getPopulateListTest(){
        List<Product> arrayList = new ArrayList<>();
        for (int i=0; i < 20; i++){
            final Product producto = new Product(i + 1, "Producto " + i, "gr2" + i );

            arrayList.add(producto);
        }


        return arrayList;
    }

    public List<Product> getSelectedProduct(){
        return selectedProduct;
    }
}
