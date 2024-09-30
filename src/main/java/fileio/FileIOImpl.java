package fileio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOImpl implements FileIO {
    @Override
    public String read(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/java/" + fileName);

        StringBuilder sb = new StringBuilder();

        int data;
        while((data = fis.read()) != -1) {
            sb.append((char) data);
        }

        fis.close();

        return sb.toString();
    }

    @Override
    public void write(String fileName, String value) throws IOException {
        FileOutputStream fos = new FileOutputStream("src/main/java/" + fileName);

        char[] chList = value.toCharArray();
        for(int i=0; i<chList.length; i++) {
            fos.write(chList[i]);
        }

        fos.flush();
        fos.close();
    }
}
