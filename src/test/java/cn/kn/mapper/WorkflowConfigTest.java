package cn.kn.mapper;

import cn.kn.dao.entity.ProcessModel;
import cn.kn.dao.mapper.HandlePropertiesMapper;
import cn.kn.dao.mapper.WorkflowConfigurationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkflowConfigTest {
    @Autowired
    WorkflowConfigurationMapper wcm;

    @Autowired
    HandlePropertiesMapper handlePropertiesMapper;

    @Test
    public void getProcessModel(){
        List<ProcessModel> processModels = wcm.getProcessModel("正式物资编码流程");
    }


    @Test
    public void uodataprop(){
        setTaskAndCode("否",2870053,5402710);
        setTaskAndCode("否",2870053,5402711);
        setTaskAndCode("否",2874333,5402712);
        setTaskAndCode("否",2874003,5402713);
    }

    public void setTaskAndCode(String value, Integer properties, Integer taskBill) {
        try {
            Integer taskPropertiesID = handlePropertiesMapper.getTaskPropertiesID(properties, taskBill);
            handlePropertiesMapper.updateTaskProperties(value, properties, taskBill);
            handlePropertiesMapper.updateCodePropType(value, properties, taskPropertiesID);
        } catch (Exception e) {
            e.toString();
        }
    }


}
