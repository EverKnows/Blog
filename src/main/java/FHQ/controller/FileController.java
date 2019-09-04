package FHQ.controller;

import FHQ.Utils.MultiFileToFileUtils;
import FHQ.Utils.QiniuUtils;
import FHQ.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {

//    @RequestMapping("/load")
//    public String load() {
//        return "Editor";
//    }

    @RequestMapping(value = "/articleupload", method = RequestMethod.POST)
    @ResponseBody
    public Map upLoadImg(@RequestParam(value="editormd-image-file", required = false) MultipartFile img, HttpSession session) throws Exception {
        String filename = img.getOriginalFilename();
        String type = img.getContentType();
        System.out.println("filename : " + filename);
        System.out.println("type : " + type);
        Map<String, Object> map = new HashMap<String, Object>();
        File file = MultiFileToFileUtils.transferToFile(img);
        if (file == null) {
            map.put("success", 0);
            map.put("message", "上传的图片为空");
            return map;
        }

        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        String key = "/user_" + userId + "/article/" + new Date().getTime();
        boolean blog = QiniuUtils.overrideUpload(file, key, "blog");
        if (blog) {
            map.put("success", 1);
            map.put("message", "200");
            map.put("url", QiniuUtils.getDomain() + '/' + key + "?time=" + new Date().getTime());
        } else {
            map.put("success", "0");
            map.put("message", "失败");
        }

        System.out.println(map.get("url"));
        return map;
    }


//    @RequestMapping("/article")
//    @ResponseBody
//    public Map upLoadArticle(@RequestParam(value="text", required = false)String text) throws Exception {
//        System.out.println(text);
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("status", "200");
//        return map;
//    }

}
