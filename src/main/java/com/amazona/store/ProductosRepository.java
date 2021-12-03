package com.amazona.store;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.amazona.store.model.productos;

public interface ProductosRepository extends JpaRepository<productos, Long> {
	@Query("SELECT p FROM productos p WHERE p.nombre=?1")
	Optional<productos> findByName(String name);
} //interface ProductosRepository
