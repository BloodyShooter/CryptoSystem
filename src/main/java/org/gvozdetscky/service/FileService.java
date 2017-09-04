package org.gvozdetscky.service;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Egor on 19.08.2017.
 */
public interface FileService {

    String saveFileOnServer(MultipartFile file) throws IOException;

    void downloadFileOnClient(String nameFile, HttpServletResponse response) throws IOException;

    String encryptFile(MultipartFile file, String password, ModelMap model) throws IOException;

    String decryptFile(MultipartFile file, String password, ModelMap model) throws IOException;

}
