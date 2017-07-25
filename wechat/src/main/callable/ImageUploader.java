package main.callable;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Lucio.li on 2017/7/19.
 */
public class ImageUploader extends Thread{
    private File file;
    private String imageName;
    private String path;
    public ImageUploader(File file, String imageName, String path){
        this.file = file;
        this.imageName = imageName;
        this.path = path;
    }

    public void run(){
        if (file != null) {
            File destPath = new File(path);
            if (!destPath.exists()) {
                destPath.mkdir();
            }
            File destFile = new File(path, imageName);
            try {
                FileUtils.copyFile(file, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
