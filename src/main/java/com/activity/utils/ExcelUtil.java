package com.activity.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Create by ky.bai on 2018-02-28 14:28
 */
public class ExcelUtil {

    private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * Created by ky.bai on 2018-02-28 14:59
     *
     * @param sheetTitle  工作表名称
     * @param titleMap    标题列，一定要是LinkedHashMap  字段名_列宽:字段显示名称，其中字段名中下划线右边是列的宽度
     * @param dataMapList 数据集
     *
     * @return 生成excel文件
     */
    public static byte[] create(String sheetTitle, Map<String, String> titleMap, List<Map<String, Object>> dataMapList) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetTitle);

        // 标题样式
        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setFillPattern(HSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);          // 上边框
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);        // 右边框
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);       // 下边框
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);         // 左边框
        Font font = wb.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setFontName("微软雅黑");
        titleStyle.setFont(font);

        // 创建单元格样式
        CellStyle dataCellStyle = wb.createCellStyle();
        //dataCellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        dataCellStyle.setWrapText(true);
        dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        dataCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        dataCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);          // 上边框
        dataCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);        // 右边框
        dataCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);       // 下边框
        dataCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);         // 左边框
        Font dataCellFont = wb.createFont();
        dataCellFont.setColor(HSSFColor.BLACK.index);
        dataCellFont.setFontHeightInPoints((short) 11);
        dataCellStyle.setFont(dataCellFont);

        // 存放了列名
        List<String> titleList = new ArrayList<>();
        // 第一行(标题行)
        int column = 0;
        HSSFRow firstRow = sheet.createRow(0);
        firstRow.setHeightInPoints(25);
        for (Map.Entry<String, String> entry : titleMap.entrySet()) {
            HSSFCell cell = firstRow.createCell(column);
            cell.setCellValue(entry.getValue());
            // 设置样式
            String[] key = entry.getKey().split("_");
            sheet.setColumnWidth(column, Integer.valueOf(key[1]));
            cell.setCellStyle(titleStyle);
            sheet.createFreezePane(0, 1);
            titleList.add(key[0]);
            column++;
        }

        // 填充数据
        for (int r = 0; r < dataMapList.size(); r++) {
            HSSFRow row = sheet.createRow(r + 1);       // 创建一行
            Map<String, Object> dataMap = dataMapList.get(r);   // 拿一行数据
            for (int c = 0; c < titleList.size(); c++) {
                HSSFCell cell = row.createCell(c);
                String value = dataMap.get(titleList.get(c)) != null ? dataMap.get(titleList.get(c)).toString() : "";
                if (StringUtils.isNotBlank(value) && !"null".equals(value) && StringUtils.isNumeric(value)) {
                    cell.setCellValue(Double.parseDouble(value));
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                } else {
                    cell.setCellValue(value);
                }
                // 设置样式
                cell.setCellStyle(dataCellStyle);
            }
        }

        byte[] bytes = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            wb.write(byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("export has error: " + e.getMessage());
        }

        return bytes;
    }

}
