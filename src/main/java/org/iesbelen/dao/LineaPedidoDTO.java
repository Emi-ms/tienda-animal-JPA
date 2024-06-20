package org.iesbelen.dao;

import org.iesbelen.model.LineaPedido;

public class LineaPedidoDTO extends LineaPedido {

    private String nombreProducto;

    public LineaPedidoDTO(){

    }

    public String getNombreProducto(){
        return nombreProducto;
    }

    public void setNombreProducto(String nombreCategoria){
        this.nombreProducto = nombreCategoria;
    }
}
