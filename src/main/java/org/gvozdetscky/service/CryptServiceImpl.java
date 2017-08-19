package org.gvozdetscky.service;

import org.gvozdetscky.logic.CryptographerOFB;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Egor on 12.08.2017.
 */
@Service
public class CryptServiceImpl implements CryptService {

    public String decrypt(String pathFile, String password,
                          boolean isStatusArch, boolean isStatusBase64) throws FileNotFoundException {
        CryptographerOFB cryptographerOFB = new CryptographerOFB();

        return cryptographerOFB.decrypt(pathFile, password, false, false);
    }

    public String encrypt(String pathFile, String password,
                          boolean isStatusArch, boolean isStatusBase64) throws FileNotFoundException {
        CryptographerOFB cryptographerOFB = new CryptographerOFB();

        return cryptographerOFB.encrypt(pathFile, password, false, false);
    }
}
