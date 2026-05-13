package br.com.fiap.projectreviewer.repository;

import br.com.fiap.projectreviewer.model.Correcao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrecaoRepository extends JpaRepository<Correcao, Long> {
}