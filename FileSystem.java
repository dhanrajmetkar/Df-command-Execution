import java.util.Arrays;

public class FileSystem {
    String fileSystem;
    int size;
    int used;
    int available;

    public void set(String[] string) {
       this.fileSystem=string[0];
       this.size=Integer.parseInt(string[1])/1000;
       this.used=Integer.parseInt(string[2])/1000;
       this.available=Integer.parseInt((string[3]))/1000;
    }

    public String getFileSystem() {
        return fileSystem;
    }

    public int getSize() {
        return size;
    }

    public int getAvailable() {
        return available;
    }

    public int getUsed() {
        return used;
    }
}
