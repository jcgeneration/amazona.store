package com.amazona.store.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazona.store.ProductosService;
import com.amazona.store.model.productos;
@RestController
@RequestMapping("/api/productos")
public class ProductosController {
	private final ProductosService productosService;
	@Autowired
	public ProductosController(ProductosService productosService) {
		this.productosService = productosService;
	} // constructor
	@GetMapping
	public List<productos> getAllProducts(){
		return productosService.getAllProductos();
	}//getAllProducts
	@GetMapping(path = "{id}")    // /api/productos/1
    public productos getProducts(@PathVariable("id") Long id){
        return productosService.getProductos(id);
    }//getAllProducts
  @DeleteMapping(path = "{id}")    // /api/productos/1
    public void deleteProducts(@PathVariable("id") Long id){
         productosService.deleteProductos(id);
    }//deleteProducts	
  	@PostMapping
  	public void addProducts(@RequestBody productos producto) {
  		productosService.addProductos(producto);
  	} //addProducts
  	@PutMapping(path = "{id}")
  	public void updateProducts(@PathVariable("id") Long id,
  			@RequestParam(required = false) String descripcion,
  			@RequestParam(required = false) String imagenURL,
  			@RequestParam(required = false) double precio) {
  		productosService.updateProductos(id, descripcion, imagenURL, precio);
  	}//updateProducts
} //class ProductosController
