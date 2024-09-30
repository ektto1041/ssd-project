package fileio;

public interface FileIO {
    String read(String fileName);

    void write(String fileName, String value);
}
