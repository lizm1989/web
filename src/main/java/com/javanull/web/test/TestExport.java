package com.javanull.web.test;

import com.javanull.web.domain.UserSheet;
import com.xuxueli.poi.excel.ExcelExportUtil;
import com.xuxueli.poi.excel.ExcelImportUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhiming on 2018/3/19.
 */
public class TestExport {
    public static void main(String[] args) {

        /**
         * Mock数据，Java对象列表
         */
        List<UserSheet> shopDTOList = new ArrayList<UserSheet>();
        for (int i = 0; i < 100; i++) {
            UserSheet sheet = new UserSheet();
            sheet.setId(Long.valueOf(i));
            sheet.setName("name" + i);
            shopDTOList.add(sheet);
        }
        String filePath = "/Users/lizhiming/aixuexi/qiniu/20180207/demo-sheet.xls";

        /**
         * Excel导出：Object 转换为 Excel
         */
        ExcelExportUtil.exportToFile(shopDTOList, filePath);

        /**
         * Excel导入：Excel 转换为 Object
         */
        List<Object> list = ExcelImportUtil.importExcel(UserSheet.class, filePath);

        System.out.println(list);

    }
}
