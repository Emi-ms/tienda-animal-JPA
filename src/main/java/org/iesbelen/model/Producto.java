package org.iesbelen.model;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private String foto;
    private int idCategoria;
    private int stock;

    private String detalle;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Producto: " +
                "Id Producto: " + idProducto +
                " Nombre: " + nombre +
                " Precio: " + precio +
                " Foto: " + foto +
                " Id Categoria: " + idCategoria +
                " Stock: " + stock +
                " Detalle: " + detalle;
    }
}
