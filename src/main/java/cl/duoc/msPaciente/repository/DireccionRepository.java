package cl.duoc.msPaciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.duoc.msPaciente.model.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer>{

}
