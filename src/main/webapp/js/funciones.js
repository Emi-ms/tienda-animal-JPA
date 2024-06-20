$(document).ready(function (){
    $("tr #botonBorrar").click(function (){
        let idProducto = $(this).parent().find("#idProducto").val();
        eliminar(idProducto);
        alert(idProducto)

    });
    function eliminar(idProducto){
        let url= `${pageContext.request.contextPath}/tienda_animales/pedidos`;
        $.ajax({
            type:"POST",
            url:url,
            data:"idProducto="+idProducto,
            success: function (data,textStatus,jqXHR){
                alert("registro eliminado");
            }
        })
    }
});