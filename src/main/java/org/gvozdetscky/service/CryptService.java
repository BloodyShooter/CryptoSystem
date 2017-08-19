package org.gvozdetscky.service;

import java.io.FileNotFoundException;

/**
 * Created by Egor on 13.08.2017.
 */
public interface CryptService {
    String encrypt(String pathFile, String password,
                   boolean isStatusArch, boolean isStatusBase64) throws FileNotFoundException;

    String decrypt(String pathFile, String password,
                   boolean isStatusArch, boolean isStatusBase64) throws FileNotFoundException;
}
