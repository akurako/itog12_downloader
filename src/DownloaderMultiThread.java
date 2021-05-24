import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloaderMultiThread implements Runnable {
    String url, localFile;
    public DownloaderMultiThread(String url, String localFile){
        this.url = url;
        this.localFile = localFile;
    }

    public void download(){
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(localFile)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            System.out.println(url + " -----> " + localFile + " Downloaded.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        download();
    }
}
