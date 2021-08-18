package com.grimgdl.model;


public class Product {

    private String _title;
    private String _subTitle;
    private boolean checked = false;
    private int _id;

    public Product(int id, String title, String subtitle ){
        this._id = id;
        this._title = title;
        this._subTitle = subtitle;

    }

    public String getNombre() {
        return _title;
    }

    public void setNombre(String nombre) {
        this._title = nombre;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getPresentacion() {
        return _subTitle;
    }

    public void setPresentacion(String _presentacion) {
        this._subTitle = _presentacion;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
