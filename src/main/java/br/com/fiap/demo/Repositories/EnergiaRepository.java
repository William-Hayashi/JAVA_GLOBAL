package br.com.fiap.demo.Repositories;

import br.com.fiap.demo.models.EnergiaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnergiaRepository extends JpaRepository<EnergiaModel, UUID> {
}
