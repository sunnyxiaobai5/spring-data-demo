/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.util</li>
 * <li>文件名称: PdfTableUtils.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sunnyxiaobai5.common.annotation.ExportAnnotation;
import com.sunnyxiaobai5.common.enumeration.ExceptionEnum;
import com.sunnyxiaobai5.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.util.FieldUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfTableUtils {

    private static final Logger log = LoggerFactory.getLogger(PdfTableUtils.class);

    //字体文件地址
    private static final String FONT = ClassLoader.getSystemResource("").toString() + "/fonts/YaHei.Consolas.1.12.ttf";

    //构造函数私有化
    private PdfTableUtils() {
    }

    /**
     * 获取字体
     *
     * @param size 字体大小
     * @return Font
     * @throws DocumentException
     * @throws IOException
     */
    private static Font getFont(float size) throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, false);

        return new Font(baseFont, size);
    }

    /**
     * 创建pdf文件
     *
     * @param dest     生成文件地址
     * @param headers  表格表头
     * @param dataList 数据列表
     * @throws BaseException 外部异常
     */
    public static void createPdf(String dest, List<String> headers, List<String> fieldNames, List<?> dataList) throws BaseException {
        //列长度为0时抛出外部异常
        if (headers.isEmpty()) {
            throw new BaseException(-1, ExceptionEnum.EXPORT_NO_COLUMN.getMessage());
        }

        //创建pdf文件目录
        File file = new File(dest);
        file.getParentFile().mkdirs();

        Document document = null;
        try {
            //创建Document
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();

            //创建Table
            PdfPTable table = new PdfPTable(headers.size());

            //添加表头
            Font fontHeader = getFont(12);
            headers.parallelStream().sequential().forEach(h -> table.addCell(new Paragraph(h, fontHeader)));

            Font fontBody = getFont(10);
            //添加数据
            for (Object obj : dataList) {
                for (String fieldName : fieldNames) {
                    Object fieldValueObj = FieldUtils.getFieldValue(obj, fieldName);
                    String fieldValueStr = null == fieldValueObj ? "" : fieldValueObj.toString();
                    table.addCell(new Paragraph(fieldValueStr, fontBody));
                }
            }

            //TODO 添加表注（底部行）

            //将Table添加到Document中
            document.add(table);

        } catch (DocumentException | IOException | IllegalAccessException e) {
            log.error("create pdf error");
            log.warn("context", e);
            e.printStackTrace();
        } finally {
            //关闭Document
            if (null != document) {
                document.close();
            }
        }
    }

    /**
     * 根据表格对应的定义类创建pdf文件
     *
     * @param dest     生成文件地址
     * @param title    表格标题
     * @param clazz    表格对应的类定义
     * @param dataList 数据列表
     * @throws BaseException     内部异常
     * @throws DocumentException
     * @throws IOException
     */
    public static void createPdf(String dest, String title, Class<?> clazz, List<?> dataList) throws BaseException, DocumentException, IOException {
        //获取要导出的相关信息
        ObjInfo objInfo = PdfTableUtils.ObjInfo.getObjInfo(clazz);

        //列长度为0时抛出内部异常
        if (objInfo.getHeaders().isEmpty()) {
            throw new BaseException(-1, ExceptionEnum.EXPORT_NO_ANNOTATION.getMessage());
        }

        //创建pdf文件
        createPdf(dest, objInfo.getHeaders(), objInfo.getFieldNames(), dataList);
    }

    /**
     * 表格对应的定义类相关信息
     */
    private static class ObjInfo {
        /**
         * 表格列头
         */
        private List<String> headers = new ArrayList<>();

        /**
         * 要导出的字段名称
         */
        private List<String> fieldNames = new ArrayList<>();

        /**
         * 从Class 中获取表格列头和导出的字段名称
         *
         * @param clazz <? extends Object>
         * @return ObjInfo
         */
        private static ObjInfo getObjInfo(Class<?> clazz) {
            ObjInfo objInfo = new ObjInfo();

            //获取类的Field（属性）
            Arrays.asList(clazz.getDeclaredFields()).parallelStream().sequential()
                    .filter(field -> field.getAnnotation(ExportAnnotation.class) != null)
                    .forEach(field -> {
                                objInfo.headers.add(field.getAnnotation(ExportAnnotation.class).name());
                                objInfo.fieldNames.add(field.getName());
                            }
                    );

            return objInfo;
        }

        public List<String> getHeaders() {
            return headers;
        }

        public List<String> getFieldNames() {
            return fieldNames;
        }

        @Override
        public String toString() {
            headers.forEach(System.out::println);
            fieldNames.forEach(System.out::println);
            return "";
        }
    }
}
