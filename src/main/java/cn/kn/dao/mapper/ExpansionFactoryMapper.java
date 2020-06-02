package cn.kn.dao.mapper;

import cn.kn.dao.entity.BaseData;
import cn.kn.dao.entity.table.Maps;

import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/3/23 15:58
 * @Description
 */
public interface ExpansionFactoryMapper {


    List<Integer> getDataRuleId();

    /**
     * 获取物料编码下的所有大类
     * @return 所有大类
     */
    List<BaseData> getOneList();

    /**
     * 查询小类
     * @param superId 父级ID
     * @return 集合
     */
    List<BaseData> getSuperList(String superId);


    Maps getRule(String id);

    List<String> getRuleId(String id);



}
