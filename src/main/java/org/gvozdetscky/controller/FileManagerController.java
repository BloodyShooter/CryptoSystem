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
    public void downloadFile(@RequestParam(value = "nameFile") String nameFile, HttpServletResponse response, HttpServletRequest request) throws IOException {

        request.setCharacterEncoding("UTF-8");
        File downloadFile = new File(PATH + nameFile);

        System.out.println("Стадия 1");

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

        System.out.println("Стадия 2");

        fis.close();
        outputStream.close();
    }

    @RequestMapping(value="/test")
    @ResponseBody
    public String method9(@RequestParam("name") String name){
        return "test with name= " + name;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("password") String password, HttpServletResponse response, ModelMap model) {

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

                String newFile = cryptService.encrypt(uploadedFile.getAbsolutePath(), password, false, false);

                System.out.println(newFile);

                newFile = newFile.substring(newFile.lastIndexOf("\\") + 1, newFile.length());

                System.out.println(newFile);

                model.addAttribute("name", name);
                model.addAttribute("newFile", newFile);
                model.addAttribute("bytes.length", bytes.length);

//                return "<html><head>\n" +
//                        "  <meta charset=\"utf-8\"></head><body>" +
//                        "<h1>Удача " + name + "</h1><br>" + "" +
//                        "<a href=\"/download?nameFile=" + newFile + "\">download file " + newFile + "</a><br>" +
//                        "<h1>" + bytes.length + "</h1>" +
//                        "</body>" +
//                        "</html>";
                return "downloadFile";
            } catch (Exception e) {
                return "Неудача " + name + " -> " + e.getMessage();
            }
        } else {
            return "Неудача";
        }
    }
}