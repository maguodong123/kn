package cn.kn.utility.excel;


import cn.kn.dao.excel.ExcelMDM;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {

//    public List<ExcelMap> readExcelMap() throws IOException {
//        HSSFSheet sheet = readExcel();
//        int lastRowIndex = sheet.getLastRowNum();
//        List<ExcelMap> list = new ArrayList<>();
//        for (int i = 0; i <= lastRowIndex; i++) {
//            HSSFRow row = sheet.getRow(i);
//            if (row == null) {
//                break;
//            }
//            ExcelMap excelMap = new ExcelMap();
//            short lastCellNum = row.getLastCellNum();
//            for (int j = 0; j < lastCellNum; j++) {
//                excelMap.setKey((int) row.getCell(0).getNumericCellValue());
//                excelMap.setValue((int) row.getCell(1).getNumericCellValue());
//                break;
//            }
//            list.add(i, excelMap);
//        }
//        return list;
//    }



//    public List<ExcelSAP> readinsert() throws IOException {
//        HSSFSheet sheet = readExcel();
//        int lastRowIndex = sheet.getLastRowNum();
//        List<ExcelSAP> list = new ArrayList<>();
//
//        for (int i = 0; i <= lastRowIndex; i++) {
//            HSSFRow row = sheet.getRow(i);
//            if (row == null) {
//                break;
//            }
//            ExcelSAP excelSAP = new ExcelSAP();
//            short lastCellNum = row.getLastCellNum();
//            for (int j = 0; j < lastCellNum; j++) {
//                excelSAP.setOldcode(row.getCell(0).getStringCellValue());
//                excelSAP.setFactory(row.getCell(1).getStringCellValue());
//                excelSAP.setMRPcg(row.getCell(2).getStringCellValue());
//                excelSAP.setMRPts(row.getCell(3).getStringCellValue());
//                excelSAP.setCG(row.getCell(4).getStringCellValue());
//                excelSAP.setJH(row.getCell(5).getStringCellValue());
//                excelSAP.setCC(row.getCell(6).getStringCellValue());
//                excelSAP.setZJ(row.getCell(7).getStringCellValue());
//                excelSAP.setKJ(row.getCell(8).getStringCellValue());
//                break;
//            }
//            list.add(i, excelSAP);
//        }
//        return list;
//    }


    public List<ExcelMDM> readExcelist() throws IOException {
        HSSFSheet sheet = readExcel();
        int lastRowIndex = sheet.getLastRowNum();
        List<ExcelMDM> list = new ArrayList<>();
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            ExcelMDM excelDAO = new ExcelMDM();
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                excelDAO.setCode(row.getCell(0).getStringCellValue());
//                excelDAO.setOldcode(row.getCell(1).getStringCellValue());
//                excelDAO.setFactory(row.getCell(2).getNumericCellValue());
//                excelDAO.setViewname(row.getCell(3).getStringCellValue());
                break;
            }
            list.add(i, excelDAO);
        }
        return list;
    }


    private HSSFSheet readExcel() throws IOException {
        String filePath = "D:/1.xls";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        return sheet;
    }


}
