function toggleProductos(select) {
    document.getElementById('productos-contenido')
        .classList.toggle('d-none', select.value !== 'productos');
}