import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DownloadQueue {
    //CopyOnWriteArrayList<File> fileList = new CopyOnWriteArrayList<>();
    ArrayList<File> fileList = new ArrayList<>();
    Downloader downloader = new Downloader();
    FileReader data_read;
    Scanner scan;
    String fileListLocal = "fileList";
    //AtomicLong total_bytes = new AtomicLong(0);
    long total_bytes;


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
            fileList.add(new File(file[0], file[1]));
        }
        System.out.println(fileList.size() + " files ready to download.");

    }

    public void totalBytesRecieved(){
        System.out.println("Total bytes recieved: "+ total_bytes);
    }

    public void downloadAllMultiThread(int max_threads) throws InterruptedException {
        int maximum_threads = max_threads;
        total_bytes = 0;
        //total_bytes.set(0);
        while (fileList.size() > 0 || Thread.activeCount() > 2 ) {
            for (File file : fileList) {
                if (Thread.activeCount() < max_threads + 2) {
                    new Thread(new DownloaderMultiThread(file, this)).start();
                    fileList.remove(file);
                }
            }
            totalBytesRecieved();
            Thread.sleep(100);
        }
        totalBytesRecieved();
    }
    //    public void downloadAll(int max_threads) throws InterruptedException {
    //        int maximum_threads = max_threads;
    //        total_bytes.set(0);
    //        while (fileList.size() > 0 || Thread.activeCount() > 2 ) {
    //            for (File file : fileList) {
    //                if (Thread.activeCount() < max_threads + 2) {
    //                    new Thread(new DownloaderMultiThread(file, this)).start();
    //                    fileList.remove(file);
    //                }
    //            }
    //            totalBytesRecieved();
    //            Thread.sleep(100);
    //        }
    //        totalBytesRecieved();
    //    }

}