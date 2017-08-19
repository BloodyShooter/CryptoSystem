package org.gvozdetscky.controller;

import org.gvozdetscky.logic.CryptographerOFB;
import org.gvozdetscky.service.CryptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Класс где обрабатывается запросы с файлами.
 */
@Controller
public class FileManagerController {

    private static final String PATH = "D:\\temp\\";

    @Autowired
    private CryptServiceImpl cryptService;

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
                             HttpServletResponse response, ModelMap model) {

        String name = null;

        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                File dir = new File("D:\\temp");

                if (!dir.exists()) {
                    dir.mkdir();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                String newFile;

                if (!password.equals("")) {
                    newFile = cryptService.encrypt(uploadedFile.getAbsolutePath(), password, false, false);
                } else {
                    newFile = cryptService.encrypt(uploadedFile.getAbsolutePath(), null, false, false);
                }

                System.out.println(newFile);

                newFile = newFile.substring(newFile.lastIndexOf("\\") + 1, newFile.length());

                System.out.println(newFile);

                model.addAttribute("name", name);
                model.addAttribute("newFile", newFile);
                model.addAttribute("bytes", bytes.length);

                if (!password.equals("")) {
                    model.addAttribute("password", true);
                } else {
                    model.addAttribute("password", false);
                    model.addAttribute("fileKey", "key");
                    System.out.println("Пароля нет");
                }

                return "downloadFile";
            } catch (Exception e) {
                return "Неудача " + name + " -> " + e.getMessage();
            }
        } else {
            return "Неудача";
        }
    }
}
