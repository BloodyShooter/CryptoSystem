package org.gvozdetscky.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Egor on 19.08.2017.
 */
public interface FileService {

    String saveFileOnServer(MultipartFile file) throws IOException;

}
