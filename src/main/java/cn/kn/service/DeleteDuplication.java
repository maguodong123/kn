package cn.kn.service;
import cn.kn.dao.entity.PropertiesData;
import cn.kn.dao.entity.SelectData;
import cn.kn.dao.mapper.SelectDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DeleteDuplication {

    @Autowired
    SelectDataMapper sd;

    @RequestMapping(value = "DeleteDuplication", method = RequestMethod.GET)
    public void DeleteDuplication() throws FileNotFoundException {
        System.err.println("程序开始执行!");
        List<SelectData> selectDataList = sd.QueryAll();//把查询出来的重复数据全部存储到该集合中
        System.err.println("程序开始循环!");
        PrintStream out = System.out;
        PrintStream ps = new PrintStream("./deletedata.txt");
        System.setOut(ps);
        //开始循环删除
        for (SelectData selectData : selectDataList) {
            Integer taskproperties = sd.QueryTask(selectData.getCode());//根据编码获取当前的任务单id
            Integer propertiesid = selectData.getPropertiesid();
            List<PropertiesData> propertiesData = sd.getPropData(propertiesid, taskproperties);//根据当前的任务单id获取详细的属性数据

            for (int i = 0; i < propertiesData.size(); i++) {
                if (propertiesData.size() <= 1) {
                    break;
                }
                if (propertiesData.get(i).getTaskprvalue() == null ||
                        "".equals(propertiesData.get(i).getTaskprvalue().trim()) ||
                        "on".equals(propertiesData.get(i).getTaskprvalue().trim())) {
                    if (propertiesData.get(i).getLviewdataview().equals(selectData.getViewid())) {
                        if (propertiesData.get(i).getDataname().equals(selectData.getViewname())) {
                            if (propertiesData.get(i).getTaskprties().equals(selectData.getPropertiesid())) {
                                if (propertiesData.get(i).getPropname().equals(selectData.getPropertiessname())) {
                                    Integer taskprid = propertiesData.get(i).getTaskprid();
                                    deleteData(taskprid);
                                    System.out.println("编码:" + selectData.getCode() +
                                            ",单据id:" + taskproperties +
                                            ",视图id:" + selectData.getViewid() +
                                            ",视图名称:" + selectData.getViewname() +
                                            ",属性字段名称:" + selectData.getPropertiessname() +
                                            ",删除的属性id:" + taskprid);
                                    propertiesData.remove(i);
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < propertiesData.size(); i++) {
                if (propertiesData.size() <= 1) {
                    break;
                }

                if (propertiesData.get(i).getLviewdataview().equals(selectData.getViewid())) {
                    if (propertiesData.get(i).getDataname().equals(selectData.getViewname())) {
                        if (propertiesData.get(i).getTaskprties().equals(selectData.getPropertiesid())) {
                            if (propertiesData.get(i).getPropname().equals(selectData.getPropertiessname())) {
                                Integer taskprid = propertiesData.get(i).getTaskprid();
                                deleteData(taskprid);
                                System.out.println("编码:" + selectData.getCode() +
                                        ",单据id:" + taskproperties +
                                        ",视图id:" + selectData.getViewid() +
                                        ",视图名称:" + selectData.getViewname() +
                                        ",属性字段名称:" + selectData.getPropertiessname() +
                                        ",删除的属性id:" + taskprid);
                                propertiesData.remove(i);
                            }
                        }
                    }
                }

            }

            propertiesData.clear();
        }
        System.setOut(out);
        System.err.println("数据删除完毕!");
    }

    @Transactional
    void deleteData(Integer id) {
        Integer Mdm_Proptype = sd.deleteMdmProptype(id);
        Integer B_TASKPROPERTIES = sd.deleteTaskProperties(id);
        if (Mdm_Proptype != 1 && B_TASKPROPERTIES != 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }




}
