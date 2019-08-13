package FHQ.Utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class QiniuUtils {
    private static final String accessKey = "BRtjlc8BWUSQyOMdkdXfaCVbgVZ9im31L2IORy8O";

    private static final String secretKey = "gh_VW9_TfF6Uli4WllJbQ0nNP_vb4rtQz0dEmEPH";

    private static final String domain = "http://pvy9f9yid.bkt.clouddn.com";

    private static Configuration cfg = new Configuration(Zone.zone2());

    private static Auth auth = Auth.create(getAccessKey(), getSecretKey());

    private static UploadManager uploadManager = new UploadManager(cfg);

    public static String getDomain() {
        return domain;
    }

    public static String getAccessKey() {
        return accessKey;
    }

    public static String getSecretKey() {
        return secretKey;
    }



    public static String getUpToken(String bucketName, String key) {
        return auth.uploadToken(bucketName, key, 3600, new StringMap().put("insertOnly", 0));
    }

    /**
     * 上传方法3 覆盖上传
     * @param file
     *            上传文件
     * @param bucketName
     *            空间名
     * @param key
     *            文件名
     */
    public static boolean overrideUpload(File file, String key, String bucketName) {
        String token = null;
        Response response = null;
        try {
            token = getUpToken(bucketName, key);//获取 token
            response = uploadManager.put(file, key, token);//执行上传，通过token来识别 该上传是“覆盖上传”
            System.out.println(response.toString());
        } catch (QiniuException e) {
            System.out.println(e.response.statusCode);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
