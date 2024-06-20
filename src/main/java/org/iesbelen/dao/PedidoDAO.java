package org.iesbelen.dao;

import org.iesbelen.model.Carrito;
import org.iesbelen.model.LineaPedido;
import org.iesbelen.model.Pedido;

import java.util.List;

public interface PedidoDAO {
    void crate(Pedido pedido, List<Carrito> lineasPedido);
    List<Pedido> getByIdUser(int idUsuario);
    Pedido getByIdPedido(int idPedido);
}
