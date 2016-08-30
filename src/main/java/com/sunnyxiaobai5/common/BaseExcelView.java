/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.common</li>
 * <li>文件名称: BaseExcelView.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.security.util.FieldUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BaseExcelView extends AbstractExcelView {

    /**
     * 提供一个通用创建Document方法，若有特殊导出，子类覆写改方法
     *
     * @param model    数据模型Map
     * @param workbook workbook
     * @param request  in case we need locale etc. Shouldn't look at attributes.
     * @param response in case we need to set cookies. Shouldn't write to it.
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = (String) model.get("title");
        Map<String, String> headerMap = (Map<String, String>) model.get("headerMap");
        String[] fieldArray = headerMap.keySet().toArray(new String[0]);
        String[] headerArray = headerMap.values().toArray(new String[0]);
        List dataList = (List) model.get("dataList");

        HSSFSheet sheet = buildSheet(workbook, title);

        int row = buildTitle(workbook, sheet, title, fieldArray.length);

        row = buildHeader(workbook, sheet, headerArray, row);

        buildData(sheet, fieldArray, dataList, row);
    }

    protected HSSFSheet buildSheet(HSSFWorkbook workbook, String title) {
        return workbook.createSheet(title);
    }

    /**
     * 创建title
     *
     * @param workbook    workbook
     * @param sheet       sheet
     * @param title       sheet标题
     * @param mergeColNum 标题所占列数量
     * @return 已使用行数
     */
    protected int buildTitle(HSSFWorkbook workbook, HSSFSheet sheet, String title, int mergeColNum) {
        HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, title);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, mergeColNum - 1));

        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 24);

        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(font);
        cell.setCellStyle(style);
        return 1;
    }

    /**
     * 创建表头
     *
     * @param workbook    workbook
     * @param sheet       sheet
     * @param headerArray 表头数据数组
     * @param row         表头起始行下标
     * @return 已使用行数
     */
    protected int buildHeader(HSSFWorkbook workbook, HSSFSheet sheet, String[] headerArray, int row) {
        Font font = workbook.createFont();
        font.setBold(true);

        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(font);

        for (int i = 0; i < headerArray.length; i++) {
            HSSFCell cell = getCell(sheet, row, i);
            setText(cell, headerArray[i]);
            cell.setCellStyle(style);
        }
        return row + 1;
    }

    /**
     * 填充数据
     *
     * @param sheet      sheet
     * @param fieldArray fieldArray
     * @param dataList   数据集合
     * @param row        数据起始行下标
     * @return 已使用行数
     * @throws IllegalAccessException
     */
    protected <T> int buildData(HSSFSheet sheet, String[] fieldArray, List<T> dataList, int row) throws IllegalAccessException {
        for (int rowIndex = row; rowIndex < dataList.size() + row; rowIndex++) {
            for (int col = 0; col < fieldArray.length; col++) {
                HSSFCell cell = getCell(sheet, rowIndex, col);
                Object value = FieldUtils.getFieldValue(dataList.get(rowIndex - row), fieldArray[col]);
                setText(cell, String.valueOf(value));
            }
        }
        return row + dataList.size();
    }


    //TODO 下面方法应该单独抽取到工具类中

    /**
     * 手动生成一个Workbook
     *
     * @param title     sheet标题
     * @param headerMap dataList中对象字段名和标题展示值的Map({"id"->"ID","name"->"名称"})
     * @param dataList  数据集合
     * @return HSSFWorkbook
     * @throws Exception
     */
    public HSSFWorkbook buildExcelDocument(String title, Map<String, String> headerMap, List<?> dataList) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();

        String[] fieldArray = headerMap.keySet().toArray(new String[0]);
        String[] headerArray = headerMap.values().toArray(new String[0]);

        HSSFSheet sheet = buildSheet(workbook, title);

        int row = buildTitle(workbook, sheet, title, fieldArray.length);

        row = buildHeader(workbook, sheet, headerArray, row);

        buildData(sheet, fieldArray, dataList, row);

        return workbook;
    }

    /**
     * 下载workbook
     *
     * @param workbook
     * @param response
     * @throws IOException
     */
    public void download(Workbook workbook, HttpServletResponse response) throws IOException {
        response.setContentType(getContentType());

        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
}
