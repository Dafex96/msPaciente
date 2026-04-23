package cl.duoc.msPaciente.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.duoc.msPaciente.dto.PacienteDTO;
import cl.duoc.msPaciente.model.Paciente;
import cl.duoc.msPaciente.service.PacienteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar(){

        List<Paciente> listaPacientes = service.listarPacientes();
        
        if (listaPacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaPacientes);
        }
    } 

    @GetMapping("/id/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id){
        try {
            Paciente paciente = service.buscarPorId(id);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Paciente> buscarPorRut(@PathVariable String rut){
        try {
            Paciente paciente = service.buscarPorRut(rut);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){
        Paciente nuevoPaciente = service.agregarPaciente(paciente);
        return ResponseEntity.ok(nuevoPaciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Integer id, @RequestBody Paciente paciente){
        try {
            Paciente pacienteActualizado = service.actualizar(id, paciente);
            return ResponseEntity.ok(pacienteActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<PacienteDTO> obtenerPacienteDTO(@PathVariable Integer id){
        try {
            Paciente paciente = service.buscarPorId(id);
            
            PacienteDTO dto = new PacienteDTO();

            dto.setNombre(paciente.getNombre());
            dto.setApellido(paciente.getApellido());
            dto.setId(paciente.getId());

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    

}
