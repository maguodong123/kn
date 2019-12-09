package cn.kn.mapper;

import cn.kn.dao.entity.ProcessModel;
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

    @Test
    public void getProcessModel(){
        List<ProcessModel> processModels = wcm.getProcessModel("正式物资编码流程");
    }







}
