package org.iesbelen.dao;

import org.iesbelen.model.Producto;

public class ProductoDTO extends Producto {
    private String nombreCategoria;

    public ProductoDTO(){

    }

    public String getNombreCategoria(){
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria){
        this.nombreCategoria = nombreCategoria;
    }
}
