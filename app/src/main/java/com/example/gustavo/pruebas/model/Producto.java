package com.example.gustavo.pruebas.model;


public class Producto {

    private String _nombre;
    private String _presentacion;
    private boolean checked = false;
    private int _id;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getPresentacion() {
        return _presentacion;
    }

    public void setPresentacion(String _presentacion) {
        this._presentacion = _presentacion;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
