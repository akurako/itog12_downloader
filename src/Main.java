import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        DownloadQueue update = new DownloadQueue("http://45.148.31.78/Mod12itog/fileList");

        update.downloadAllMultiThread(2);
    }
}


