package com.br.alcateia.leitorftp.service.ftp;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Builder
@Data
public class FileTransfer {

    private String uuid;
    private String path;
    private File pathLocal;
}
