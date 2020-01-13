package cn.kn.utility.excel;


import cn.kn.dao.excel.ExcelCode;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class CreateExcel {

    public static void exportExcelPaper(List<ExcelCode> list) {
        // 标题
        String[] title = {"编码", "旧编码"};
        // 创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        // 设置列宽
        setColumnWidth(sheet, 8);
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        // 创建一个单元格
        HSSFCell cell = null;
        // 创建表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            // 设置样式
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            // 设置字体
            HSSFFont font = workbook.createFont();
            font.setFontName("楷体");
            font.setFontHeightInPoints((short) 13);
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title[i]);
        }

        System.out.println(list);
        // 从第二行开始追加数据
        for (int i = 1; i < (list.size() + 1); i++) {
            // 创建第i行
            HSSFRow nextRow = sheet.createRow(i);
            for (int j = 0; j < 2; j++) {
                ExcelCode eQuestion = list.get(i - 1);
                HSSFCell cell2 = nextRow.createCell(j);
                if (j == 0) {
                    cell2.setCellValue(eQuestion.getCode());
                }
                if (j == 1) {
                    cell2.setCellValue(eQuestion.getOldCode());
                }
            }
        }
        // 创建一个文件
        File file = new File("D:/1.xls");
        try {
            // 打开文件流
            FileOutputStream outputStream = FileUtils.openOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 设置列宽()
     * @param sheet 工作页
     * @param collum 根
     */
    private static void setColumnWidth(HSSFSheet sheet, int collum) {
        for (int i = 0; i < collum; i++) {
            int v = 0;
            v = Math.round(Float.parseFloat("15.0") * 37F);
            v = Math.round(Float.parseFloat("20.0") * 267.5F);
            sheet.setColumnWidth(i, v);
        }
    }

}
