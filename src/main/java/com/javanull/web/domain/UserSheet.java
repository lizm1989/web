package com.javanull.web.domain;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import org.apache.poi.hssf.util.HSSFColor;

import javax.persistence.Column;

/**
 * Created by lizhiming on 2018/3/18.
 */
@ExcelSheet(name = "商户列表", headColor = HSSFColor.HSSFColorPredefined.LIGHT_GREEN)
public class UserSheet {
    @ExcelField(name = "商户ID")
    private Long id;

    @ExcelField(name = "商户名称")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
