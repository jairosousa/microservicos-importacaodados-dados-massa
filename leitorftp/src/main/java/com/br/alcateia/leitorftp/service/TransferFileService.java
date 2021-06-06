package com.br.alcateia.leitorftp.service;

import com.br.alcateia.leitorftp.domain.FileSave;
import com.br.alcateia.leitorftp.service.file.CreateFileSaveService;
import com.br.alcateia.leitorftp.service.ftp.FileTransfer;
import com.br.alcateia.leitorftp.service.ftp.TransferFTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TransferFileService {

    @Autowired
    private TransferFTPService transferFTPService;

    @Autowired
    private CreateFileSaveService createFileSaveService;
//
//    @Autowired
//    private SendFileKakfaService sendFileKakfaService;

    @Scheduled(fixedRate = 100 * 60)
    public void execute() throws IOException {
        List<FileTransfer> files = transferFTPService.execute();

        for (FileTransfer fileTransfer : files) {
            String uui = createFileSaveService.execute(
                    FileSave
                            .builder()
                            .path(fileTransfer.getPath())
                            .build());
            fileTransfer.setUuid(uui);
        }

    }
}
