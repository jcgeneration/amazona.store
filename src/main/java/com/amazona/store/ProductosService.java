package com.amazona.store;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.amazona.store.model.productos;
@Service
public class ProductosService {
	private final ProductosRepository productosRepository;
	@Autowired
	public ProductosService(ProductosRepository productosRepository) {
		this.productosRepository = productosRepository;
	} //constructor
	public List<productos> getAllProductos() {
		return productosRepository.findAll();
	} // getAllProductos
	public productos getProductos(Long id) {
        return productosRepository.findById(id).
      orElseThrow(()-> new IllegalStateException("El Producto con el id " + id +
                " no existe")) ;
    }// getProductos
    public void deleteProductos(Long id) {
        if (productosRepository.existsById(id)) {
            productosRepository.deleteById(id);
        } else {
         throw  new IllegalStateException("El Producto con el id " + id + 
        		 " no existe");
        }// else
    } // deleteProductos
    public void addProductos (productos producto) {
    	Optional<productos> prodByName= 
    			productosRepository.findByName(producto.getNombre());
    	if (prodByName.isPresent()) {
    		throw  new IllegalStateException("El Producto con el nombre [" + 
    				producto.getNombre() + "] ya existe");		
    	} // isPresent
    	productosRepository.save(producto);
    }// addProductos 
    @Transactional
    public void updateProductos(Long id, String descripcion, 
    		String imagenURL, double precio) {
    	productos producto = productosRepository.findById(id).
       orElseThrow(()-> new IllegalStateException("El Producto con el id " + id +
        " no existe")) ;    	
    	if (descripcion != null)
	    	if ( (!descripcion.isEmpty()) &&  
	    			(! descripcion.equals(producto.getDescripcion()))	) {
	    		producto.setDescripcion(descripcion);
	    	}// if descripcion 
    	if ( (imagenURL!= null) ) 
    		if ((!imagenURL.isEmpty()) &&
    			(! imagenURL.equals(producto.getImagenURL())) ) {
    		producto.setImagenURL(imagenURL);
    	}// if imagenURL
    	if  ( (precio > 0) && (precio!=producto.getPrecio())  ){
    		producto.setPrecio(precio);
    	} // precio >0
    }//updateProductos
} //class ProductosService
