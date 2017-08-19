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

    private static final String PATH = "D:/temp/";

    @Autowired
    private CryptServiceImpl cryptService;

    @Autowired
    private FileServiceImpl fileService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public @ResponseBody String uploadFileInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(value = "/download")
    public void downloadFile(@RequestParam(value = "nameFile") String nameFile,
                             HttpServletResponse response) throws IOException {
        File downloadFile = new File(PATH + nameFile);

        FileInputStream fis = new FileInputStream(downloadFile);

        response.setContentType("application/octet-stream");
        response.setContentLength((int) downloadFile.length());

        String headerKey = "Content-Disposition";
        System.out.println(downloadFile.getName());
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = fis.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        System.out.println("Файл был скачен");

        fis.close();
        outputStream.close();
    }

    @RequestMapping(value="/test")
    @ResponseBody
    public String method9(@RequestParam("name") String name){
        return "test with name= " + name;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("password") String password,
                             ModelMap model) {

        String name = "";

        if (!file.isEmpty()) {
            try {

                name = fileService.saveFileOnServer(file);

                String newFile;

                if (!password.equals("")) {
                    newFile = cryptService.encrypt(PATH + name,
                            password,
                            false,
                            false);
                } else {
                    newFile = cryptService.encrypt(PATH + name,
                            null,
                            false,
                            false);
                }

                newFile = newFile.substring(newFile.lastIndexOf("/") + 1, newFile.length());

                System.out.println(newFile);

                setModelMap(file, password, model, name, newFile);

                return "downloadFile";
            } catch (Exception e) {
                return "Неудача " + name + " -> " + e.getMessage();
            }
        } else {
            return "Неудача";
        }
    }

    @RequestMapping(value = "/decryptFile", method = RequestMethod.POST)
    public String decryptFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("password") String password,
                             ModelMap model) {

        String name = "";

        if (!file.isEmpty()) {
            try {

                name = fileService.saveFileOnServer(file);

                String newFile;

                if (!password.equals("")) {
                    newFile = cryptService.decrypt(PATH + name,
                            password,
                            false,
                            false);
                } else {
                    newFile = cryptService.decrypt(PATH + name,
                            null,
                            false,
                            false);
                }

                System.out.println(newFile);

                newFile = newFile.substring(newFile.lastIndexOf("/") + 1, newFile.length());

                System.out.println(newFile);

                setModelMap(file, password, model, name, newFile);

                return "downloadFile";
            } catch (Exception e) {
                return "Неудача " + name + " -> " + e.getMessage();
            }
        } else {
            return "Неудача";
        }
    }

    private void setModelMap(@RequestParam("file") MultipartFile file,
                             @RequestParam("password") String password,
                             ModelMap model, String name,
                             String newFile) throws IOException {
        model.addAttribute("name", name);
        model.addAttribute("newFile", newFile);
        model.addAttribute("bytes", file.getBytes().length);

        if (!password.equals("")) {
            model.addAttribute("password", true);
        } else {
            model.addAttribute("password", false);
            model.addAttribute("fileKey", "key");
        }
    }
}
