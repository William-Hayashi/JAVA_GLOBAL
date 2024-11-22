package br.com.fiap.demo.Controllers;

import br.com.fiap.demo.DTOs.EnergiaRecordDto;
import br.com.fiap.demo.Repositories.EnergiaRepository;
import br.com.fiap.demo.models.EnergiaModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/energia") // Adicionado base path para a API
@Tag(name = "API_Global_Solution", description = "API para gerenciamento de energia")
public class EnergiaController {

    @Autowired
    EnergiaRepository energiaRepository;

    @Operation(summary = "Salvar um novo cadastro de energia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping("/user")
    public ResponseEntity<EnergiaModel> saveProduct(@RequestBody @Valid EnergiaRecordDto energiaRecordDto) {
        var energiaModel = new EnergiaModel();
        BeanUtils.copyProperties(energiaRecordDto, energiaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(energiaRepository.save(energiaModel));
    }

    @Operation(summary = "Obter todos os cadastros de energia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cadastros obtida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum cadastro encontrado")
    })
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

    @Operation(summary = "Obter um cadastro de energia pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro obtido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cadastro não encontrado")
    })
    @GetMapping("user/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="id") UUID id) {
        Optional<EnergiaModel> energia0 = energiaRepository.findById(id);
        if(energia0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cadastro not found");
        }
        energia0.get().add(linkTo(methodOn(EnergiaController.class).getAllClient()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(energia0.get());
    }

    @Operation(summary = "Atualizar um cadastro de energia pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cadastro não encontrado")
    })
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

    @Operation(summary = "Deletar um cadastro de energia pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cadastro não encontrado")
    })
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
