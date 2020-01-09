package cn.kn.utility.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/1/8 16:31
 * @Description
 */
public class WriteExcel {

    public static void writeExcel(Map list) {
        // 创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        // 创建一个单元格
        HSSFCell cell = null;
        for (int i = 0; i < (list.size() + 1); i++) {
            // 创建第i行
            HSSFRow nextRow = sheet.createRow(i);
            for (int j = 0; j < 3; j++) {
                HSSFCell cell2 = nextRow.createCell(j);
                if (j == 0) {
                    cell2.setCellValue(list.get("key").toString());
                }
                if (j == 1) {
                    cell2.setCellValue(list.get("value").toString());
                }
            }
            // 创建一个文件
            File file = new File("D:/1.xls");
            try {
                file.createNewFile();
                // 打开文件流
                FileOutputStream outputStream = FileUtils.openOutputStream(file);
                workbook.write(outputStream);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}