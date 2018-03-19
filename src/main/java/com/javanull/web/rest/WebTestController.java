package com.javanull.web.rest;

import com.javanull.web.config.UserUtil;
import com.javanull.web.config.UserUtilOne;
import com.javanull.web.config.WebConfig;
import com.javanull.web.domain.User;
import com.javanull.web.domain.UserRepository;
import com.javanull.web.domain.UserSheet;
import com.javanull.web.test.RedisAndMysqlTest;
import com.xuxueli.poi.excel.ExcelExportUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by lizhiming on 2018/3/13.
 */
@RestController
public class WebTestController {

    @Resource
    private RedisAndMysqlTest redisAndMysqlTest;


    @Resource
    private UserRepository userRepository;

    @GetMapping("/getkey")
    public String getkey(@RequestParam(defaultValue = "") String key) {
        String returnKey = redisAndMysqlTest.get(key);
        return StringUtils.isEmpty(returnKey) ? "null" : returnKey;
    }


    @GetMapping("/getUser")
    public List<User> getkey() {
        List<User> list = userRepository.findAll();
        return list;
    }

    @Autowired
    private WebConfig webConfig;




    @GetMapping("/down")
    public void down(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        try {
            String downloadFilename = "中文.zip";//文件的名称
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");//转换中文否则可能会产生乱码
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            String[] files = new String[]{"https://wx1.sinaimg.cn/mw690/9ff831e3gy1fpdn20qjcpj20rs0rs75v.jpg", "https://wx4.sinaimg.cn/mw690/9ff831e3gy1fpdn20qhfxj20rs0rst9g.jpg"};
            for (int i = 0; i < files.length; i++) {
                URL url = new URL(files[i]);
                zos.putNextEntry(new ZipEntry(i + ".jpg"));
                //FileInputStream fis = new FileInputStream(new File(files[i]));
                InputStream fis = url.openConnection().getInputStream();
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }
            zos.flush();
            zos.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/testDownload")
    public void testDownload(HttpServletRequest httpServletRequest, HttpServletResponse res) {
        String fileName = "xiaoqu.png";
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("/Users/lizhiming/aixuexi/qiniu/20180207/gaoduedu/"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    @GetMapping("/down2")
    public void down2(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        try {
            String downloadFilename = "中文.zip";//文件的名称
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");//转换中文否则可能会产生乱码
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());

            List<User> list = userRepository.findAll();

            //
            List<UserSheet> userSheets = new ArrayList<>();
            for (User user : list) {
                UserSheet sheet = new UserSheet();
                sheet.setId(user.getId());
                sheet.setName(user.getName());
                userSheets.add(sheet);
            }
            byte[] bytes = ExcelExportUtil.exportToBytes(userSheets);
            zos.putNextEntry(new ZipEntry( "fist.xls"));
            zos.write(bytes, 0, bytes.length);


            byte[] buffer = new byte[1024];
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                URL url = new URL(user.getUrl());
                zos.putNextEntry(new ZipEntry(i + ".jpg"));
                InputStream fis = url.openConnection().getInputStream();

                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }


            zos.flush();
            zos.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
