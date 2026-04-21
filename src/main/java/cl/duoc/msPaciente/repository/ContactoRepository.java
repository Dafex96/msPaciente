package cl.duoc.msPaciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.duoc.msPaciente.model.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer>{

}
