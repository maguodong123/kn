package cn.kn.dao.mapper;


import cn.kn.dao.entity.PropertiesData;
import cn.kn.dao.entity.SelectData;
import cn.kn.dao.entity.Values;

import java.util.List;

public interface SelectDataMapper {

    //一上来把查询出来重复属性的结果存储到一个list集合中
    List<SelectData> QueryAll();

    //根据CODE查询属性对应的单据id
    Integer QueryTask(String code);


    List<Values> getValues(String task);

    Values getOneValues(String task);



    /**
     * 根据新编码获取旧编码
     *
     * @param code
     * @return
     */
    String getOldCode(String code);

    /**
     * 根据编码获取最新的任务单
     * @param code
     * @return
     */
    String getTask(String code);

    /**
     * 根据属性字段propertiesid和任务单id,查询出重复字段的详细信息
     * 重复字段的id,单据id,属性id,propvalue重复的字段值
     * 重复的字段名称,视图id,视图名称
     * 好与查询出来的做对比
     */
    List<PropertiesData> getPropData(Integer propertiesid, Integer taskproperties_taskbill);

//    /**
//     * 根据编码获取旧编码
//     * @param code 编码
//     * @return 旧编码
//     */
//    List<String> getOldCode(String code);


    //删除一条属性字段要删除两张表中的数据
    //删除B_TASKPROPERTIES表中的数据,返回受到影响的行数
    Integer deleteTaskProperties(Integer id);

    //删除Mdm_Proptype属性表中的数据,返回受到影响的行数
    Integer deleteMdmProptype(Integer taskprop);

    /**
     * 根据编码返回最新传送的json数据
     *
     * @param code    编码
     * @param name    姓名
     * @param factory 工厂
     * @return 字符串
     */
    String getDataInterface(String code, String name, String factory);

    String getDataInterfaceError(String code, String name, String factory);


}
