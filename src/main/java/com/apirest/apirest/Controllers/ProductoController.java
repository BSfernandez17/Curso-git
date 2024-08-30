package com.apirest.apirest.Controllers;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.apirest.apirest.Entities.Producto;
import com.apirest.Repositories.ProductoRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @GetMapping("/{id}")
    public Producto geProductoById(@RequestParam Integer id) {
        return productoRepository.findById(id).
        orElseThrow(()->new RuntimeException("No se encontro el Id"));
    }
    

    @PutMapping
    public Producto updateProducto(@PathVariable Integer id, @RequestBody Producto detallesProducto){
        Producto producto= productoRepository.findById(id).
        orElseThrow(()->new RuntimeException("No se encontro el Id"));
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());
        
        return productoRepository.save(producto);
    }
    @DeleteExchange("/{id}")
    public String borrarProducto(@RequestParam Integer id){
        Producto producto= productoRepository.findById(id).
        orElseThrow(()->new RuntimeException("No se encontro el Id"));
        productoRepository.delete(producto);

        return "el producto con el Id: "+id+" fue eliminado";
    }


}
