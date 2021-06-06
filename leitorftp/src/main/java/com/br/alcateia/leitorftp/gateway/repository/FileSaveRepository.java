package com.br.alcateia.leitorftp.gateway.repository;

import com.br.alcateia.leitorftp.domain.FileSave;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FileSaveRepository extends CrudRepository<FileSave, UUID> {
}
