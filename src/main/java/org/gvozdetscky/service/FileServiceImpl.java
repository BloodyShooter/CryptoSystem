package org.gvozdetscky.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Egor on 19.08.2017.
 */
@Service
public class FileServiceImpl implements FileService {
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
}
