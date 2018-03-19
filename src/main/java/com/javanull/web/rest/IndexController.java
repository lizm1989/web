package com.javanull.web.rest;

import com.javanull.web.domain.User;
import com.javanull.web.domain.UserRepository;
import com.javanull.web.domain.UserSheet;
import com.xuxueli.poi.excel.ExcelExportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by lizhiming on 2018/3/19.
 */
@Controller
public class IndexController {

    @Resource
    private UserRepository userRepository;

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> list = userRepository.findAll();
        model.addAttribute("userList", list);
        model.addAttribute("name", "lizhiming");


        List<User> xsList = new ArrayList<>();
        User a = new User();
        a.setName("销售A");
        User b = new User();
        b.setName("销售B");

        xsList.add(a);
        xsList.add(b);
        model.addAttribute("xsList", xsList);
        return "index";
    }


    @RequestMapping(value = "/listdown", method = RequestMethod.POST)
    public void listdown(HttpServletResponse response, String name) {
        try {
            String downloadFilename = name + ".zip";//文件的名称
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");//转换中文否则可能会产生乱码
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());

            List<User> list = userRepository.findAll();

            //
            List<UserSheet> userSheets = new ArrayList<>();
            for (User user : list) {
                if (!name.equals(user.getName())) {
                    continue;
                }
                UserSheet sheet = new UserSheet();
                sheet.setId(user.getId());
                sheet.setName(user.getName());
                userSheets.add(sheet);
            }
            byte[] bytes = ExcelExportUtil.exportToBytes(userSheets);
            zos.putNextEntry(new ZipEntry(name + ".xls"));
            zos.write(bytes, 0, bytes.length);


            byte[] buffer = new byte[1024];
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                if (!name.equals(user.getName())) {
                    continue;
                }
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
