package cn.kn.ydservice;

import cn.kn.dao.mapper.SHyongdaMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/4/27 21:43
 * @Description
 */
@RestController
public class DeleteData {

    private SHyongdaMapper sHyongdaMapper;

    public DeleteData(SHyongdaMapper sHyongdaMapper) {
        this.sHyongdaMapper = sHyongdaMapper;
    }

    @GetMapping(value = "deleteData")
    public void deleteData() {
        String cr;
        try {
            List<String> stringList = sHyongdaMapper.getWhole();
            for (String str : stringList) {
                cr = sHyongdaMapper.getCodeProp(str);
                if (!"".equals(cr) && cr != null) {
                    sHyongdaMapper.deleteb_propcode(str, cr);
                }
                deleteDatas(str, cr);
            }
            System.out.println("程序执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void deleteDatas(String taskId, String cr) {
        try {
            sHyongdaMapper.deleteL_DATALIBRAY2TASK(taskId);
            sHyongdaMapper.deleteL_DATALIBRAY2TASK1(taskId);
            sHyongdaMapper.deleteMDM_PROPTYPE(taskId);
            sHyongdaMapper.deleteMDM_VIEWCODE(taskId);
            sHyongdaMapper.deleteL_RUTASK2TASKBILL(taskId);
            sHyongdaMapper.deleteMDM_DATALIBRAY(taskId);
            sHyongdaMapper.deleteb_taskproperties(taskId);
            sHyongdaMapper.deleteb_taskbill(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
