package cn.kn.utility.excel;

import cn.kn.dao.excel.ExcelCode;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/1/8 16:31
 * @Description
 */
public class WriteExcel {

    public static void writeExcel(List<ExcelCode> list) {

        // 创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        // 创建一个单元格
        HSSFCell cell = null;
        HSSFRow nextRow = null;
        HSSFCell cell2 = null;
        List<ExcelCode> list1 = list;

        for (int i = 0; i < list.size() + 1; i++) {
            // 创建第i行
            nextRow = sheet.createRow(i);
            for (ExcelCode lists : list1) {
                assert cell2 != null;
                cell2.setCellValue(lists.getCode());
                cell2.setCellValue(lists.getOldCode());
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
