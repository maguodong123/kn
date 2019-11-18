package cn.kn.service;



import cn.kn.dao.entity.TaskProperties;
import cn.kn.dao.excel.ExcelMDM;
import cn.kn.dao.mapper.SelectDataMapper;
import cn.kn.dao.mapper.SelectViewMapper;
import cn.kn.utility.excel.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 该类用来删除MDM中有的视图、而SAP中没有的视图，并且数据有问题的（比如必填项为空）
 */
@RestController
public class DeleteView {

    @Autowired
    SelectDataMapper sd;

    @Autowired
    SelectViewMapper sv;

    @RequestMapping(value = "DeleteView", method = RequestMethod.GET)
    public void DeleteView() throws IOException {
        System.err.println("程序开始执行!");
        ReadExcel readExcel = new ReadExcel();
        List<ExcelMDM> listdata = readExcel.readExcelist();
        int count = 0;
        //第一个for循环主要是遍历22603条数据，excel表中的
        for (ExcelMDM excelDAO:listdata){
            List<TaskProperties> getTaskProperties = sv.getTaskProperties(excelDAO.getCode(),excelDAO.getViewName());
                for (TaskProperties taskProperties:getTaskProperties){//第二个for循环删除b_taskproperties 数据表的id字段行;
                    deleteData(taskProperties.getID());
                    count++;
                }
        }
        System.err.println(count);
    }


    @Transactional
    void deleteData(Integer id) {//这个方法删两张表，先把视图中的属性字段删除
        Integer Mdm_Proptype = sd.deleteMdmProptype(id);
        Integer B_TASKPROPERTIES = sd.deleteTaskProperties(id);
        if (Mdm_Proptype != 1 && B_TASKPROPERTIES != 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }


}
