package cn.kn.utility.excel;


import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExcel {

//    public static void exportExcelPaper() {
//        // 创建一个工作簿
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 创建一个工作表sheet
//        HSSFSheet sheet = workbook.createSheet();
//        // 设置列宽
//        setColumnWidth(sheet, 8);
//        // 创建第一行
//        HSSFRow row = sheet.createRow(0);
//        // 创建一个单元格
//        HSSFCell cell = null;
//        // 创建表头
//        for (int i = 0; i < title.length; i++) {
//            cell = row.createCell(i);
//            // 设置样式
//            HSSFCellStyle cellStyle = workbook.createCellStyle();
//            // 设置字体
//            HSSFFont font = workbook.createFont();
//            font.setFontName("楷体");
//            font.setFontHeightInPoints((short) 13);
//            cellStyle.setFont(font);
//            cell.setCellStyle(cellStyle);
//
//            cell.setCellValue(title[i]);
//        }
//
//        System.out.println(list);
//        // 从第二行开始追加数据
//        for (int i = 1; i < (list.size() + 1); i++) {
//            // 创建第i行
//            HSSFRow nextRow = sheet.createRow(i);
//            for (int j = 0; j < 17; j++) {
//                CRM eQuestion = list.get(i-1);
//                HSSFCell cell2 = nextRow.createCell(j);
//                if (j == 0) {
//                    cell2.setCellValue( eQuestion.getZiCode());
//                }
//                if (j == 1) {
//                    cell2.setCellValue(eQuestion.getZiName());
//                }
//                if (j == 2) {
//                    cell2.setCellValue(eQuestion.getZhuCode());
//                }
//                if (j == 3) {
//                    cell2.setCellValue(eQuestion.getProductName());
//                }
//                if (j == 4) {
//                    cell2.setCellValue(eQuestion.getProductModel());
//                }
//                if (j == 5) {
//                    cell2.setCellValue(eQuestion.getGeneralList());
//                }
//                if (j == 6) {
//                    cell2.setCellValue(eQuestion.getCompanyCode());
//                }
//                if (j == 7) {
//                    cell2.setCellValue(eQuestion.getCompanyName());
//                }
//                if (j == 8) {
//                    cell2.setCellValue(eQuestion.getProfit());
//                }
//                if (j == 9) {
//                    cell2.setCellValue(eQuestion.getProject());
//                }
//                if (j == 10) {
//                    cell2.setCellValue(eQuestion.getRegionCode());
//                }
//                if (j == 11) {
//                    cell2.setCellValue(eQuestion.getRegionName());
//                }
//                if (j == 12) {
//                    cell2.setCellValue(eQuestion.getOtherCode());
//                }
//                if (j == 13) {
//                    cell2.setCellValue(eQuestion.getOtherName());
//                }
//                if (j == 14) {
//                    cell2.setCellValue(eQuestion.getCustomer());
//                }
//                if (j == 15) {
//                    cell2.setCellValue(eQuestion.getCustomerCode());
//                }
//                if (j == 16) {
//                    cell2.setCellValue(eQuestion.getFactory());
//                }
//            }
//        }
//
//        // 创建一个文件
//        File file = new File("D:/1.xls");
//        try {
//            file.createNewFile();
//            // 打开文件流
//            FileOutputStream outputStream = FileUtils.openOutputStream(file);
//            workbook.write(outputStream);
//            outputStream.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    // 设置列宽()
    private static void setColumnWidth(HSSFSheet sheet, int colNum) {
        for (int i = 0; i < colNum; i++) {
            int v = 0;
            v = Math.round(Float.parseFloat("15.0") * 37F);
            v = Math.round(Float.parseFloat("20.0") * 267.5F);
            sheet.setColumnWidth(i, v);
        }
    }

}
