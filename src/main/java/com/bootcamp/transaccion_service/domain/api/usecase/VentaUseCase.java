package com.bootcamp.transaccion_service.domain.api.usecase;

import com.bootcamp.transaccion_service.domain.api.IVentaServicePort;
import com.bootcamp.transaccion_service.domain.exception.ArticuloSinStockException;
import com.bootcamp.transaccion_service.domain.exception.VentaFallidaException;
import com.bootcamp.transaccion_service.domain.model.Venta;
import com.bootcamp.transaccion_service.domain.spi.*;
import com.bootcamp.transaccion_service.domain.utils.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.bootcamp.transaccion_service.domain.utils.SuministroConstants.*;

public class VentaUseCase implements IVentaServicePort {

    private final ICarritoPersistencePort carritoPersistencePort;
    private final IArticuloPersistencePort articuloPersistencePort;
    private final IVentaPersistencePort ventaPersistencePort;
    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IReporteVentaPersistencePort reporteVentaPersistencePort;

    public VentaUseCase(ICarritoPersistencePort carritoPersistencePort,
                        IArticuloPersistencePort articuloPersistencePort,
                        IVentaPersistencePort ventaPersistencePort,
                        IUsuarioPersistencePort usuarioPersistencePort,
                        IReporteVentaPersistencePort reporteVentaPersistencePort) {
        this.carritoPersistencePort = carritoPersistencePort;
        this.articuloPersistencePort = articuloPersistencePort;
        this.ventaPersistencePort = ventaPersistencePort;
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.reporteVentaPersistencePort = reporteVentaPersistencePort;
    }

    @Override
    public void realizarVenta() {
        ArticuloRequest articuloRequest = crearRequest();
        ArticuloCarritoInfoResponse articuloCarritoInfoResponse = carritoPersistencePort.getCarrito(articuloRequest);

        try {
            validarStockDeArticulos(articuloCarritoInfoResponse.getArticulos());

            restarStock(articuloCarritoInfoResponse.getArticulos());

            eliminarArticulosDelCarrito(articuloCarritoInfoResponse.getArticulos());

            guardarVenta(articuloCarritoInfoResponse);

            generarReporteVenta(articuloCarritoInfoResponse);
        } catch (Exception e) {
            realizarRollback(articuloCarritoInfoResponse.getArticulos());
            throw e;
        }
    }

    private void realizarRollback(List<ArticuloCarritoInfo> articulosInfo) {
        for (ArticuloCarritoInfo articulo : articulosInfo) {
            carritoPersistencePort.agregarArticuloACarrito(articulo.getArticuloID(), articulo.getCantidadEnCarrito());
        }

        for (ArticuloCarritoInfo articulo : articulosInfo) {
            articuloPersistencePort.sumarExistencia(articulo.getArticuloID(), articulo.getCantidadEnCarrito());
        }

        ventaPersistencePort.eliminarUltimaVenta();

        throw new VentaFallidaException();
    }

    private void generarReporteVenta(ArticuloCarritoInfoResponse articuloCarritoInfoResponse) {
        ReporteVenta reporteVenta = new ReporteVenta();
        reporteVenta.setCostoTotal(articuloCarritoInfoResponse.getPrecioTotal());
        reporteVenta.setFechaCompra(LocalDateTime.now());
        List<ArticuloCarritoInfo> articulosComprados = articuloCarritoInfoResponse.getArticulos();
        List<Articulos> listaArticulos = articulosComprados.stream()
                .map(this::convertirArticulo)
                .toList();
        reporteVenta.setArticulosComprados(listaArticulos);
        reporteVentaPersistencePort.saveReporteVenta(reporteVenta);
    }

    private Articulos convertirArticulo(ArticuloCarritoInfo articuloInfo) {
        Articulos articulo = new Articulos();
        articulo.setNombreArticulo(articuloInfo.getNombre());
        articulo.setCantidadComprada(articuloInfo.getCantidadEnCarrito());
        articulo.setPrecioUnitario(articuloInfo.getPrecio());
        return articulo;
    }

    private ArticuloRequest crearRequest() {
        ArticuloRequest articuloRequest = new ArticuloRequest();
        articuloRequest.setArticuloIds(List.of(DEFAULT_ARTICULO_ID));
        articuloRequest.setSortBy(SORT_BY_NAME);
        articuloRequest.setOrder(ORDER_ASC);
        articuloRequest.setCategoriaNombre(null);
        articuloRequest.setMarcaNombre(null);
        ArticuloRequest.Pageable pageable = new ArticuloRequest.Pageable(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        articuloRequest.setPageable(pageable);
        return articuloRequest;
    }

    private void validarStockDeArticulos(List<ArticuloCarritoInfo> articulosInfo) {
        for (ArticuloCarritoInfo articulo : articulosInfo) {
            if (articulo.getCantidad() < articulo.getCantidadEnCarrito()) {
                throw new ArticuloSinStockException(articulo.getNombre());
            }
        }
    }

    private void restarStock(List<ArticuloCarritoInfo> articulosInfo) {
        for (ArticuloCarritoInfo articulo : articulosInfo) {
            articuloPersistencePort.restarExistencia(articulo.getArticuloID(), articulo.getCantidadEnCarrito());
        }
    }

    private void eliminarArticulosDelCarrito(List<ArticuloCarritoInfo> articulosInfo) {
        for (ArticuloCarritoInfo articulo : articulosInfo) {
            carritoPersistencePort.eliminarArticuloDeCarrito(null, articulo.getArticuloID());
        }
    }

    private void guardarVenta(ArticuloCarritoInfoResponse articuloCarritoInfoResponse) {
        Long usuarioID = usuarioPersistencePort.obtenerUsuarioID();
        Long cantidad = articuloCarritoInfoResponse.getTotalCount();
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Double precioTotal = articuloCarritoInfoResponse.getPrecioTotal();
        Venta venta = new Venta(usuarioID, cantidad, fechaCreacion, precioTotal);
        ventaPersistencePort.guardarVenta(venta);
    }
}