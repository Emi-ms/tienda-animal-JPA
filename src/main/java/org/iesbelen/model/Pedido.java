package org.iesbelen.model;

import java.util.Date;
import java.util.Objects;


public class Pedido implements Comparable<Pedido> {
    private int idPedido;
    private int idUsuario;
    private Date fechaPedido;
    private double precioTotal;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "Pedido: " +
                "Id Pedido: " + idPedido +
                " Id Usuario: " + idUsuario +
                " Fecha Pedido: " + fechaPedido +
                " Precio Total: " + precioTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return idPedido == pedido.idPedido && idUsuario == pedido.idUsuario && Double.compare(precioTotal, pedido.precioTotal) == 0 && Objects.equals(fechaPedido, pedido.fechaPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, idUsuario, fechaPedido, precioTotal);
    }

    @Override
    public int compareTo(Pedido o) {
        return this.getIdPedido() - o.getIdPedido();
    }
}
