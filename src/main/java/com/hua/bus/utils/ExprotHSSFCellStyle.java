package com.hua.bus.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * @author cyh
 * @date 2020/9/25 17:38
 */
public class ExprotHSSFCellStyle {

    /**
     * 创建基础样式
     * 水平和垂直居中
     */
    public static HSSFCellStyle createBaseStyle(HSSFWorkbook hssfWorkbook){
        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        //设置水平居中
        hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直居中
        hssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return hssfCellStyle;
    }

    /**
     * 创建数据表格的头的样式
     */
    public static HSSFCellStyle createTableTitleStyle(HSSFWorkbook hssfWorkbook){
        HSSFCellStyle hssfCellStyle = createBaseStyle(hssfWorkbook);

        //设置字体
        HSSFFont font = hssfWorkbook.createFont();
        //加粗
        font.setBold(true);
        //斜体
        font.setItalic(true);
        //设置字体大小
        font.setFontHeightInPoints((short)20);
        //设置颜色
        font.setColor(HSSFColor.HSSFColorPredefined.DARK_YELLOW.getIndex());
        font.setFontName("黑体");
        hssfCellStyle.setFont(font);

        return hssfCellStyle;
    }

    /**
     * 创建小标题样式
     */
    public static HSSFCellStyle createSubTitleStyle(HSSFWorkbook hssfWorkbook){
        HSSFCellStyle hssfCellStyle = createBaseStyle(hssfWorkbook);

        //设置字体
        HSSFFont font = hssfWorkbook.createFont();
        font.setBold(true);
        font.setItalic(true);
        font.setFontHeightInPoints((short)25);
        font.setColor(HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex());
        font.setFontName("黑体");

        hssfCellStyle.setFont(font);
        return hssfCellStyle;
    }

    /**
     * 创建标题样式
     */
    public static HSSFCellStyle createTitleStyle(HSSFWorkbook hssfWorkbook){
        HSSFCellStyle hssfCellStyle = createBaseStyle(hssfWorkbook);

        HSSFFont font = hssfWorkbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short)35);
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        font.setFontName("华文行楷");

        hssfCellStyle.setFont(font);
        return hssfCellStyle;
    }
}
