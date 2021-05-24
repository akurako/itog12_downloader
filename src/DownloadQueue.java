import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DownloadQueue {
    ArrayList<File> fileList = new ArrayList<>();
    Downloader downloader = new Downloader();
    FileReader data_read ;
    Scanner scan ;
    String fileListLocal = "fileList";



    public DownloadQueue(String fileListURL) throws FileNotFoundException {
        downloader.download(fileListURL, fileListLocal);
        System.out.println("Downloading file list.");
        loadFileList();
    }

    public void loadFileList() throws FileNotFoundException {
        data_read = new FileReader(fileListLocal);
        scan = new Scanner(data_read);
        while (scan.hasNext() == true) {
            String[] file = new String[2];
            file = scan.nextLine().split(",");
            fileList.add(new File(file[0],file[1]));
        }
        System.out.println( fileList.size() + " files ready to download.");

    }

    public void downloadAll(){
        if (fileList.size() != 0) {
            for (File file : fileList) {
                downloader.download(file.url, file.localFile);
            }
        }

    }

//    public void downloadAllMultiThread(){
//        if (fileList.size() != 0) {
//            for (File file : fileList) {
//                new Thread(new DownloaderMultiThread(file.url,file.localFile)).start();
//            }
//        }
//    }
}
