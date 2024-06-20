package org.iesbelen.dao;

import org.iesbelen.model.LineaPedido;

import java.util.List;
import java.util.Optional;

public interface LineaPedidoDAO {
    void crate(LineaPedido lineaPedido);
    List<LineaPedido> getLineasPorPedido(int idPedido);

    public Optional<String> getNombreProducto(int id);

}
