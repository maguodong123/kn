package cn.kn.utility.excel;

import cn.kn.dao.excel.ExcelMDM;
import cn.kn.dao.excel.ExcelSAP;
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


    public List<ExcelMDM> readExcelMap() throws IOException {
        List<ExcelMDM> list = new ArrayList<>();
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            ExcelMDM excelMap = new ExcelMDM();
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                excelMap.setKey((int) row.getCell(0).getNumericCellValue());
                excelMap.setValue((int) row.getCell(1).getNumericCellValue());
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
