package com.grimgdl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.grimgdl.R;
import com.grimgdl.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ItemsAdapter extends ArrayAdapter<Product>{

    Context context;
    List<Product> productos;
    LayoutInflater inflater;
    Product modelProducto;

    private class ViewHolder{
        TextView txtNombre;
        TextView txtPresentacion;
        CheckBox checkBox;

    }
    public ItemsAdapter(Context context, List<Product> objects) {
        super(context, android.R.layout.simple_spinner_item, objects);

        this.context = context;
        this.productos = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder viewHolder;
        if (row == null){
            row = inflater.inflate(R.layout.row_producto, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.txtNombre =  row.findViewById(R.id.txt_nombre_producto);
            viewHolder.txtPresentacion =  row.findViewById(R.id.txt_presenta_producto);
            viewHolder.checkBox = row.findViewById(R.id.checkBoxPro);
            row.setTag(viewHolder);


        }else{
            viewHolder = (ViewHolder) row.getTag();
        }

        modelProducto = productos.get(position);

        viewHolder.txtNombre.setText(modelProducto.getNombre());
        viewHolder.txtPresentacion.setText(modelProducto.getPresentacion());
        viewHolder.checkBox.setChecked(modelProducto.isChecked());


        return row;

    }

    public List<Product> getProductos(){
        return productos;
    }
}
