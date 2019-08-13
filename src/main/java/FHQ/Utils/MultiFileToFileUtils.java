package FHQ.Utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class MultiFileToFileUtils {

    public static File transferToFile(MultipartFile file) throws IOException {
        //判断文件是否为空
        if (file == null || file.getSize() <= 0) {
            return null;
        }
        String originalFilenamename = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        File target = new File(originalFilenamename);
        OutputStream os = new FileOutputStream(target);
        byte[] buff = new byte[1024*1024*6];
        int len = 0;
        int index = 0;
        while ((len = is.read(buff, 0 , 1024*1024)) != -1) {
            os.write(buff, index, len);
            index += len;
        }
        return target;
    }

}
