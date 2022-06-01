package com.grimgdl.ui.widget;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Spinner;


import com.grimgdl.R;
import com.grimgdl.adapter.ItemsAdapter;
import com.grimgdl.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MultiSpinnerSelect extends Spinner implements
         OnCancelListener {

    private String defaultText;
    private ItemsAdapter arrayAdapter;
    List<Product> selectedProduct = new ArrayList<>();
    private final Context _context;


    public MultiSpinnerSelect(Context context) {
        super(context);
        this._context = context;

        setBackgroundResource(R.drawable.blue_outline);

    }

    public MultiSpinnerSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        this._context = context;
        setBackgroundResource(R.drawable.blue_outline);
    }

    public MultiSpinnerSelect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this._context = context;

        setBackgroundResource(R.drawable.blue_outline);

    }



    @Override
    public void onCancel(DialogInterface dialog) {
        List<Product> productos = arrayAdapter.getProductos();
        List<Product> checkProducts = new ArrayList<>();
        String spinnerText;
        for (Product producto : productos){
            if (producto.isChecked()){
                checkProducts.add(producto);
            }
        }

        int checkSize = checkProducts.size();

        if (checkSize > 0 ){

            if (checkSize == 1){
                spinnerText = "1 item selected";
            }else {
                spinnerText = "some items selected";
            }

        }else {
            spinnerText = defaultText;

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(_context,
                android.R.layout.simple_spinner_item, new String[]{ spinnerText });
        setAdapter(adapter);
        this.clearFocus();


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

        alertDialog.show();
        return true;
    }

    public void setItems(List<Product> items, String allText){

        this.defaultText = allText;


        final Product product = new Product(0, allText, "");


        items.add(0, product);

        arrayAdapter = new ItemsAdapter(getContext(),items);
        setAdapter(arrayAdapter);

    }


    public interface MultiSpinnerListener {
        void onItemsSelected(boolean[] selected);
    }

    public List<Product> getSelectedItems(){
        List<Product> items = arrayAdapter.getProductos();

        return items.stream().filter(Product::isChecked).collect(Collectors.toList());
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
