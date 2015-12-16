/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.util</li>
 * <li>文件名称: ExcelUtils.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.util;

import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtils {
    private ExcelUtils() {
    }

    private static Workbook createWorkbook(String filePath) throws FileNotFoundException, IOException {
        Workbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(filePath);
        Sheet sheet = createSheet(wb, "sh");

        Header header = sheet.getHeader();
        header.setCenter("Center Header");
        header.setLeft("Left Header");
        header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") +
                HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");


        wb.write(fileOut);
        fileOut.close();
        return wb;
    }

    private static void closeWorkbook(Workbook wb) throws IOException {
        wb.close();
    }

    private static Sheet createSheet(Workbook wb) {
        return wb.createSheet();
    }

    private static Sheet createSheet(Workbook wb, String name) {
        return wb.createSheet(WorkbookUtil.createSafeSheetName(name));
    }

    private static Sheet createSheet(Workbook wb, String name, char replaceChar) {
        return wb.createSheet(WorkbookUtil.createSafeSheetName(name, replaceChar));
    }

    private static void createCell(Workbook wb, Row row, int column, Font font, short halign, short valign, String value) {
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cellStyle.setWrapText(true);
        cellStyle.setFont(font);
        cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
        cellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());

        Cell cell = row.createCell(column);
        cell.setCellValue(createHelper.createRichTextString(value));
        cell.setCellStyle(cellStyle);
    }

    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<>();
        CellStyle cellStyle;

        //表头行样式
        cellStyle = wb.createCellStyle();
        //设置对齐方式
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        //设置背景色
//        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        //设置边框
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        //设置字体
        Font fontHeader = wb.createFont();
        fontHeader.setFontHeightInPoints((short) 12);
        fontHeader.setFontName("宋体");
        cellStyle.setFont(fontHeader);
        styles.put("header", cellStyle);

        //描述行样式
        cellStyle = wb.createCellStyle();
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        cellStyle.setWrapText(true);
        Font fontDesc = wb.createFont();
        fontDesc.setFontHeightInPoints((short) 9);
        fontDesc.setFontName("宋体");
        cellStyle.setFont(fontDesc);
        styles.put("desc", cellStyle);
        return styles;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<Map<String, String>> wbDefs = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Map<String, String> map = new HashMap<>();
            long identity = Math.round(Math.random() * 100);
            map.put("列名" + identity, "说明：必填，必须与岗位码表中内容一致" + identity);
            wbDefs.add(map);
        }
        createWorkbook("WorkbookTest.xls", wbDefs);
    }

    public static void createWorkbook(String filePath, List<Map<String, String>> colMapList) throws FileNotFoundException, IOException {
        Workbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(filePath);
        Sheet sheet = createSheet(wb, "数据列");

        Map<String, CellStyle> styles = createStyles(wb);

        CreationHelper createHelper = wb.getCreationHelper();

        //行0
        Row rowDesc = sheet.createRow(0);
        sheet.setDefaultColumnWidth(18);
        rowDesc.setHeightInPoints(80);
        //行1
        Row rowHeader = sheet.createRow(1);

        //遍历list，生成excel
        for (int i = 0; i < colMapList.size(); i++) {
            //获取每列对应的map keySet
            Set<String> keySet = colMapList.get(i).keySet();
            Iterator iterator = keySet.iterator();
            //遍历map keySet
            while (iterator.hasNext()) {
                //获取每列对应的map
                Map<String, String> colMap = colMapList.get(i);
                //创建行1，列 i 的Cell
                Cell cellHeader = rowHeader.createCell(i);
                //获取map key（表头的数据）
                String key = (String) iterator.next();
                cellHeader.setCellValue(createHelper.createRichTextString(key));
                cellHeader.setCellStyle(styles.get("header"));

                //创建行0，列 i 的Cell
                Cell cellDesc = rowDesc.createCell(i);
                //获取map value（描述行的数据）
                String value = colMap.get(key);
                cellDesc.setCellValue(createHelper.createRichTextString(value));
                cellDesc.setCellStyle(styles.get("desc"));
            }
        }

//        for (int i = 0; i < colMapList.size()-2; i++) {
//            sheet.setColumnWidth(i, 18 * 256);  //18 characters wide
//        }

        wb.write(fileOut);
        fileOut.close();
    }
}
