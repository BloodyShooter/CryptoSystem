package org.gvozdetscky.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Класс сервис для обработки запросов с файламми.
 */
@Service
public class FileServiceImpl implements FileService {

    private static final String PATH  = "D:/temp/";

    @Autowired
    private CryptService cryptService;

    @Override
    public String saveFileOnServer(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        String name = file.getOriginalFilename();

        File dir = new File("D:\\temp");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
        stream.write(bytes);
        stream.flush();
        stream.close();

        return name;
    }

    @Override
    public void downloadFileOnClient(String nameFile, HttpServletResponse response) throws IOException {
        System.out.println(nameFile);
        File downloadFile = new File(PATH + nameFile);
        System.out.println(downloadFile);

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

        System.out.println("Файл " + nameFile + " был скачен");

        fis.close();
        outputStream.close();
    }

    @Override
    public String encryptFile(MultipartFile file,
                            String password,
                            ModelMap model) throws IOException {
        String name = "";

        if (!file.isEmpty()) {
            try {

                name = saveFileOnServer(file);

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

                setModelMap(file, password, model, name, newFile);

                return "downloadFile";
            } catch (Exception e) {
                return "Неудача " + name + " -> " + e.getMessage();
            }
        } else {
            return "Неудача";
        }
    }

    @Override
    public String decryptFile(MultipartFile file,
                              String password,
                              ModelMap model) throws IOException {
        String name = "";

        if (!file.isEmpty()) {
            try {

                name = saveFileOnServer(file);

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

                newFile = newFile.substring(newFile.lastIndexOf("/") + 1, newFile.length());

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
