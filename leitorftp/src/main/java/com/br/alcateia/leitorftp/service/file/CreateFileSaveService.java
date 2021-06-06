package com.br.alcateia.leitorftp.service.file;

import com.br.alcateia.leitorftp.domain.FileSave;
import com.br.alcateia.leitorftp.domain.enums.FileStatusEnum;
import com.br.alcateia.leitorftp.gateway.repository.FileSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateFileSaveService {

    @Autowired
    private FileSaveRepository repository;

    @Transactional
    public String execute(FileSave fileSave) {
        fileSave.setStatus(FileStatusEnum.RECEBIDO.toString());
        repository.save(fileSave);
        return fileSave.getId().toString();
    }
}
