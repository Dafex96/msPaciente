package cl.duoc.msPaciente.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cl.duoc.msPaciente.model.Paciente;
import cl.duoc.msPaciente.repository.PacienteRepository;

@Service
public class PacienteService {

    private PacienteRepository repo;

    public List<Paciente> listarPacientes() {
        return repo.findAll();
    }

    public Paciente buscarPorId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public Paciente buscarPorRut(String rut){
        return repo.findByRut(rut).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public void eliminar(Integer id){
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Paciente no encontrado");
        }
    }

    public Paciente agregarPaciente(Paciente paciente){
        if (paciente.getDireccion() != null) {
            paciente.getDireccion().setPaciente(paciente);
        }
        if (paciente.getContacto() != null) {
            paciente.getContacto().setPaciente(paciente);
        }
        return repo.save(paciente);
    }

    public Paciente actualizar(Integer id, Paciente pacienteActualizado) {

        Paciente pacienteAnt = repo.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        pacienteAnt.setNombre(pacienteActualizado.getNombre());
        pacienteAnt.setApellido(pacienteActualizado.getApellido());
        pacienteAnt.setEdad(pacienteActualizado.getEdad());
        pacienteAnt.setRut(pacienteActualizado.getRut());

        if (pacienteActualizado.getDireccion() != null) {
            pacienteActualizado.getDireccion().setPaciente(pacienteAnt);
            pacienteAnt.setDireccion(pacienteActualizado.getDireccion());
        }
        if (pacienteActualizado.getContacto() != null) {
            pacienteActualizado.getContacto().setPaciente(pacienteAnt);
            pacienteAnt.setContacto(pacienteActualizado.getContacto());
        }
        return repo.save(pacienteAnt);
    }



}
