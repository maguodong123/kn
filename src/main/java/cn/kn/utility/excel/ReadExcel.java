package cn.kn.utility.excel;

import cn.kn.dao.excel.ExcelCode;
import cn.kn.dao.excel.ExcelSAP;
import cn.kn.dao.excel.ExcelValue;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    HSSFSheet sheet = sheetExcel();
    int lastRowIndex = sheet.getLastRowNum();

    public ReadExcel() throws IOException {
    }

    /**
     * 只读取一个字段
     *
     * @return
     * @throws IOException
     */
    public List<String> readExcelString() throws IOException {
        DataFormatter formatter = new DataFormatter();
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            ExcelValue excelValue = new ExcelValue();
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                list.add(i, formatter.formatCellValue(row.getCell(0)));
                break;
            }
        }
        return list;
    }


    public List<ExcelValue> readExcelValue() throws IOException {
        DataFormatter formatter = new DataFormatter();
        List<ExcelValue> list = new ArrayList<>();
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            ExcelValue excelValue = new ExcelValue();
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                excelValue.setValue(formatter.formatCellValue(row.getCell(0)));
                excelValue.setProperties(formatter.formatCellValue(row.getCell(1)));
                excelValue.setTaskBill(formatter.formatCellValue(row.getCell(2)));
                break;
            }
            list.add(i, excelValue);
        }
        return list;
    }


    public List<ExcelSAP> readExcelSAP() throws IOException {
        DataFormatter formatter = new DataFormatter();
        List<ExcelSAP> list = new ArrayList<>();
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            ExcelSAP excelSAP = new ExcelSAP();
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                excelSAP.setTaskBill((int) row.getCell(0).getNumericCellValue());
                excelSAP.setDataName(formatter.formatCellValue(row.getCell(1)));
                excelSAP.setCode(formatter.formatCellValue(row.getCell(2)));
                break;
            }
            list.add(i, excelSAP);
        }
        return list;
    }


    public List<ExcelCode> readExcelMap() throws IOException {
        List<ExcelCode> list = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            ExcelCode excelMap = new ExcelCode();
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                excelMap.setCode(formatter.formatCellValue(row.getCell(0)));
                excelMap.setOldCode(formatter.formatCellValue(row.getCell(1)));
                break;
            }
            list.add(i, excelMap);
        }
        return list;
    }

    private HSSFSheet sheetExcel() throws IOException {
        String filePath = "D:/read.xls";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        return sheet;
    }
}
