package org.iesbelen.model;

public class LineaPedido {
    private int idLineaPedido;
    private int idPedido;
    private int idProducto;
    private int cantidad;

    public int getIdLineaPedido() {
        return idLineaPedido;
    }

    public void setIdLineaPedido(int idLineaPedido) {
        this.idLineaPedido = idLineaPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "LineaPedido: " +
                "Id Linea Pedido: " + idLineaPedido +
                " Id Pedido: " + idPedido +
                " Id Producto: " + idProducto +
                " Cantidad: " + cantidad;
    }
}
