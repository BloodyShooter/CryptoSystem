package org.gvozdetscky.controller;

import org.gvozdetscky.service.CryptServiceImpl;
import org.gvozdetscky.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Класс где обрабатывается запросы с файлами.
 */
@Controller
public class FileManagerController {

    @Autowired
    private FileServiceImpl fileService;

    @RequestMapping(value = "/encryptFile", method = RequestMethod.GET)
    public @ResponseBody String uploadFileInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(value = "/download")
    public void downloadFile(@RequestParam(value = "nameFile") String nameFile,
                             HttpServletResponse response) throws IOException {
        fileService.downloadFileOnClient(nameFile, response);
    }

    @RequestMapping(value = "/encryptFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("password") String password,
                             ModelMap model) throws IOException {
        return fileService.encryptFile(file, password, model);
    }

    @RequestMapping(value = "/decryptFile", method = RequestMethod.POST)
    public String decryptFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("password") String password,
                             ModelMap model) throws IOException {

        return fileService.decryptFile(file, password, model);
    }
}
