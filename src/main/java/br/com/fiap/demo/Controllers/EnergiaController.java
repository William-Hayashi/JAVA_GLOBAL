package br.com.fiap.demo.Controllers;

import br.com.fiap.demo.DTOs.EnergiaRecordDto;
import br.com.fiap.demo.Repositories.EnergiaRepository;
import br.com.fiap.demo.models.EnergiaModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EnergiaController {

    @Autowired
    EnergiaRepository energiaRepository;

    @PostMapping("/user")
    public ResponseEntity<EnergiaModel> saveProduct(@RequestBody @Valid EnergiaRecordDto EnergiaRecordDto) {
        var energiaModel = new EnergiaModel(); // Usando convenção camelCase
        BeanUtils.copyProperties(EnergiaRecordDto, energiaModel); // Conversao do Dto para o Model
        return ResponseEntity.status(HttpStatus.CREATED).body(energiaRepository.save(energiaModel));
    }

    @GetMapping("/user")
    public ResponseEntity<List<EnergiaModel>> getAllClient() {
        List<EnergiaModel> clientList = energiaRepository.findAll();
        if (!clientList.isEmpty()) {
            for (EnergiaModel client : clientList) {
                UUID id = client.getId();
                client.add(linkTo(methodOn(EnergiaController.class).getOneClient(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientList);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="id") UUID id) {
        Optional<EnergiaModel> energia0 = energiaRepository.findById(id);
        if(energia0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cadastro not found");
        }
        energia0.get().add(linkTo(methodOn(EnergiaController.class).getAllClient()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(energia0.get());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value="id") UUID id, @RequestBody @Valid EnergiaRecordDto energiaRecordDto) {
        Optional<EnergiaModel> energia0 = energiaRepository.findById(id);
        if (energia0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cadastro not found");
        }
        var energiaModel = energia0.get();
        BeanUtils.copyProperties(energiaRecordDto, energiaModel);
        return ResponseEntity.status(HttpStatus.OK).body(energiaRepository.save(energiaModel));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value="id") UUID id) {
        Optional<EnergiaModel> energia0 = energiaRepository.findById(id);
        if(energia0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cadastro not found");
        }
        energiaRepository.delete(energia0.get());
        return ResponseEntity.status(HttpStatus.OK).body("cadastro deleted successfully");
    }
}
