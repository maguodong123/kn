package cn.kn.service;

import cn.kn.dao.mapper.ExpansionFactoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/3/23 15:53
 * @Description
 * 康尼扩充工厂程序
 */
@RestController
public class ExpansionFactory {

    private final Logger logger = LoggerFactory.getLogger(Supplement.class);
    private ExpansionFactoryMapper efm;
    public ExpansionFactory(ExpansionFactoryMapper efm) {
        this.efm = efm;
    }

    @GetMapping(value = "expansionFactory")
    public void expansionFactory(){
        //扩充的工厂名
        String factory = "2800";
        //模型全部选择就是全部
        String range = "范围";
        //类型物料供应商客户等等
        String type = "物料";
        //规则id？？
        String ruleid = "";

        //查出所有要扩充模型的id存入一个list集合之中








    }





}
