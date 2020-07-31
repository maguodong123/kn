package cn.kn.service;

import cn.kn.dao.excel.Vehicle;
import cn.kn.dao.mapper.SHyongdaMapper;
import cn.kn.utility.excel.ReadExcel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/7/20 10:48
 * @Description 永达配置车系层级懒人版
 */
@RestController
public class VehicleLevel {

    private SHyongdaMapper sHyongdaMapper;

    public VehicleLevel(SHyongdaMapper sHyongdaMapper) {
        this.sHyongdaMapper = sHyongdaMapper;
    }

    @GetMapping(value = "vehicleLevel")
    public void vehicleLevel() throws IOException {
        //第一步读取excel表格中的编码数据并且一次性查询
        ReadExcel readExcel = new ReadExcel();
        List<Vehicle> list = readExcel.readYD();
        String manufacturerId = "";//制造商
        String superid = "";//父级id
        String classification = "";
        String code = "";
        boolean state = true;
        try {
            for (Vehicle vehicle : list) {
         /*
           现在这里写一下思路，我发现excel读取到的,如果有的第四级车系如果有多个那么前面的将是空的,所以我要先设计一个临时变量
           用来存放前一个空的情况
         */
                //这是第二级
                if (!"".equals(vehicle.getManufacturer())) {
                    manufacturerId = sHyongdaMapper.getSuperId(vehicle.getManufacturer());
                }
            /*
            这里我解释一下,以为有的车系归类编码即第三级,后面可能有多个4级,按照原来程序的逻辑,每一个4级都会
            插入一次三级，这就造成了第二次查询的时候,会出现2个值造成无效的列
             */
                if ("".equals(vehicle.getClassification()) && "".equals(vehicle.getCode())) {
                    state = false;
                }
                //这是第三级
                if (!"".equals(vehicle.getClassification()) && !"".equals(vehicle.getCode())) {
                    state = true;
                    classification = vehicle.getClassification();
                    code = vehicle.getCode();
                }
                if (state) {
                    sHyongdaMapper.insertBaseData
                            (classification, code, "3", manufacturerId);
                }
                //这是第四级
                superid = sHyongdaMapper.getSuperId(classification);
                sHyongdaMapper.insertBaseData(vehicle.getVehicle(), vehicle.getVehicle(), "4", superid);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("程序结束");
    }
}



