package org.gvozdetscky.logic.utils;

import java.io.*;

/**
 * Created by Егор on 30.03.2017.
 */
public class FilesManager {

    private static final int EOF = -1;

    public static byte[] readFile(File f) throws FileNotFoundException {
        byte[] buffer = new byte[(int) f.length()];
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(f))) {
            reader.read(buffer);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("файл не найден");
        } catch (IOException e) {
            throw new FileNotFoundException("ошибка ввода/вывода");
        }
        return buffer;
    }

    public static void readFile(BufferedInputStream bis, byte[] buffer) throws IOException {
        bis.read(buffer);
    }

    public static int readFile(FileInputStream fis, byte[] buffer) throws IOException {
        int size = 0;
        int tempByte;
        for (int i = 0; i < 8; i++) {
            tempByte = fis.read();
            if (tempByte != EOF) {
                buffer[i] = (byte) tempByte;
                size++;
            } else buffer[i] = 0;
        }
        return size;
    }

    public static void writeFile(String pathFile, byte[] data) {
        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(pathFile))) {//запись файла побайтово
            writer.write(data);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода\\вывода");
        }
    }

    public static void writeFile(FileOutputStream fos, byte[] buffer, int size) throws IOException {
        fos.write(buffer, 0, size);
    }

    public static void writeFile(BufferedOutputStream bos, byte[] buffer) throws IOException {
        bos.write(buffer);
    }
}
