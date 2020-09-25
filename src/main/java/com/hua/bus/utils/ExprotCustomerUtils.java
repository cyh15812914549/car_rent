package com.hua.bus.utils;

import com.hua.bus.entity.Customer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 导出客户数据
 * @author cyh
 * @date 2020/9/25 18:09
 */
public class ExprotCustomerUtils {

    public static ByteArrayOutputStream exportCustomer(List<Customer> customers,String sheetName){
        //创建工作薄
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建样式
        HSSFCellStyle baseStyle = ExprotHSSFCellStyle.createBaseStyle(hssfWorkbook);
        HSSFCellStyle subTitleStyle = ExprotHSSFCellStyle.createSubTitleStyle(hssfWorkbook);
        HSSFCellStyle tableTitleStyle = ExprotHSSFCellStyle.createTableTitleStyle(hssfWorkbook);
        HSSFCellStyle titleStyle = ExprotHSSFCellStyle.createTitleStyle(hssfWorkbook);

        //在工作簿创建sheet
        HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);

        //设置sheet默认宽度
        sheet.setDefaultColumnWidth(25);

        //合并
        CellRangeAddress region1 = new CellRangeAddress(0,0,0,6);
        CellRangeAddress region2 = new CellRangeAddress(1,1,0,6);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);

        //创建第一行
        int index = 0;
        HSSFRow row1 = sheet.createRow(index);
        //在第一行里面创建一个单元格
        HSSFCell row1_cell1 = row1.createCell(0);
        //设置标题样式
        row1_cell1.setCellStyle(titleStyle);
        //设置单元格内容
        row1_cell1.setCellValue("客户数据列表");

        //第二行
        index++;
        HSSFRow row2  = sheet.createRow(index);


        return null;

    }
}
