package com.br.alcateia.leitorftp.service.ftp;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class TransferFTPService {

    @Value("${ftp.server}")
    private String server;
    @Value("${ftp.port}")
    private String port;
    @Value("${ftp.user}")
    private String user;
    @Value("${ftp.pass}")
    private String pass;

    @Autowired
    private UploadS3Service uploadS3Service;

    public List<FileTransfer> execute() throws IOException {
        FTPClient ftpClient = new FTPClient();

        List<FileTransfer> returnList = new ArrayList<>();

        try {
            // configuração do FTP Client
            ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            ftpClient.connect(server, Integer.parseInt(port));
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String[] args = ftpClient.listNames();

            for (String itemFile : args) {
                String remoteFile1 = itemFile;
                File tmpDownload = new File(itemFile);

                // baixa o arquivo
                OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(tmpDownload));
                boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
                outputStream1.close();

                if (success) {
                    log.info("Arquivo recebido com sucesso, enviando para o S3...");

                    FileTransfer fileTransfer = uploadS3Service.execute(itemFile, tmpDownload);
                    fileTransfer.setPathLocal(tmpDownload);
                    returnList.add(fileTransfer);

                    log.info("Arquivo enviado para o S3.");

                    ftpClient.deleteFile(itemFile);

                    log.info("Arquivo deletado no servidor.");
                }
            }

        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return returnList;
    }
}
