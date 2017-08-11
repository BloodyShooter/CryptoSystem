package org.gvozdetscky.controller;

import org.gvozdetscky.logic.CryptographerOFB;
import org.gvozdetscky.logic.utils.FilesManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Egor on 10.08.2017.
 */
@Controller
public class MyController {

    @RequestMapping(value = "/")
    public String showHello() {
        return "hello.html";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {

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

                CryptographerOFB cryptographerOFB = new CryptographerOFB();

                String newFile = cryptographerOFB.encrypt(uploadedFile.getAbsolutePath(), null, false, false);

                byte[] bytes1 = FilesManager.readFile(new File(newFile));

                return "<h1>Удача " + name + "</h1><br>" + new String(bytes1) + "<br><h1>" + bytes.length + "</h1>";
            } catch (Exception e) {
                return "Неудача " + name + " -> " + e.getMessage();
            }
        } else {
            return "Неудача";
        }
    }

}
