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
import org.springframework.security.util.FieldUtils;

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

//    public static void main(String[] args) throws FileNotFoundException, IOException, IllegalAccessException {
//        class DTO {
//            private String name;
//            private Integer age;
//            private Date birthDay;
//            private List<?> dynamic;
//
//            public void setDynamic(List<?> dynamic) {
//                this.dynamic = dynamic;
//            }
//
//            public DTO(String name, Integer age) {
//                this.name = name;
//                this.age = age;
//            }
//
//            public DTO(String name, Integer age, Date birthDay) {
//                this.name = name;
//                this.age = age;
//                this.birthDay = birthDay;
//            }
//        }
//        class Dynamic {
//            private String field;
//            private String value;
//
//            public Dynamic(String field, String value) {
//                this.field = field;
//                this.value = value;
//            }
//        }
//
//        Map<String, String> wbDefMap = new LinkedHashMap<>();
//        wbDefMap.put("name", "姓名");
//        wbDefMap.put("birthDay", "出生日期");
//        int dynamicNum = 3;
//        wbDefMap.put("dynamic", "动态数据");
//        for (int i = 0; i < dynamicNum; i++) {
//            wbDefMap.put("dynamicField" + i, "动态列头" + i);
//        }
//
//        List dataList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            DTO dto = new DTO("name" + i, i, new Date());
//            List dynamics = new ArrayList<>();
//            for (int j = 0; j < dynamicNum; j++) {
//                dynamics.add(new Dynamic("dynamicField" + j, "动态列值_" + i + "_" + j));
//            }
//            dto.setDynamic(dynamics);
//            dataList.add(dto);
//        }
//        createWorkbook("WorkbookTest.xls", wbDefMap, dataList, 2);
//    }

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

    /**
     * 动态创建excel
     *
     * @param filePath
     * @param colDefMap    列头定义，key为对象属性名，value为列头显示值，key序号（0开始）为staticColNum的为对象中的动态List属性名，
     *                     后面的key为List<T> T中的filed对应的值，value为列头显示值
     * @param dataList     数据列表()
     * @param staticColNum 固定列数量
     * @throws FileNotFoundException
     * @throws IOException
     * @throws IllegalAccessException
     */
    public static void createWorkbook(String filePath, Map<String, String> colDefMap, List<?> dataList, int staticColNum) throws FileNotFoundException, IOException, IllegalAccessException {
        Workbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(filePath);
        Sheet sheet = createSheet(wb, "数据列");

        Map<String, CellStyle> styles = createStyles(wb);

        CreationHelper createHelper = wb.getCreationHelper();

        //header
        sheet.setDefaultColumnWidth(18);
        Row rowHeader = sheet.createRow(0);

        //获取每列对应的map keySet
        Set<String> keySet = colDefMap.keySet();
        Iterator iterator = keySet.iterator();

        //生成表头
        int keyIndex = 0;
        while (iterator.hasNext()) {
            //获取map key（表头的数据）
            String key = (String) iterator.next();
            Cell cellHeader = null;
            if (keyIndex < staticColNum) {
                //列 i 的Cell
                cellHeader = rowHeader.createCell(keyIndex);
            } else if (keyIndex == staticColNum) {
                keyIndex++;
                continue;
            } else {
                //列 i 的Cell
                cellHeader = rowHeader.createCell(keyIndex - 1);
            }
            cellHeader.setCellValue(createHelper.createRichTextString(colDefMap.get(key)));
            cellHeader.setCellStyle(styles.get("header"));
            keyIndex++;
        }

        //生成数据行列
        for (int i = 0; i < dataList.size(); i++) {
            //创建行i(i从1开始，0为表头)
            Row row = sheet.createRow(i + 1);
            //创建行i，列 j 的Cell
            iterator = keySet.iterator();
            int j = 0;
            while (iterator.hasNext()) {
                //获取map key（字段key）
                String fieldKey = (String) iterator.next();
                //创建行j，列 j 的Cell
                Cell cell = null;
                //固定列
                if (j < staticColNum) {
                    cell = row.createCell(j);
                    Object fieldValue = FieldUtils.getFieldValue(dataList.get(i), fieldKey);
                    cell.setCellValue(createHelper.createRichTextString(String.valueOf(fieldValue)));
                }
                //动态列
                else if (j == staticColNum) {
                    Collection dynamics = (Collection) FieldUtils.getFieldValue(dataList.get(i), fieldKey);
                    for (Object obj : dynamics) {
                        cell = row.createCell(j++);
                        Object fieldValue = FieldUtils.getFieldValue(obj, "value");
                        cell.setCellValue(createHelper.createRichTextString(String.valueOf(fieldValue)));
                    }
                }
                j++;
            }
        }

        wb.write(fileOut);
        fileOut.close();
    }
}
