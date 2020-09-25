package com.hua.stat.controller;

import com.hua.stat.entity.BaseEntity;
import com.hua.stat.service.StatService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cyh
 * @date 2020/9/18 15:03
 */
@RequestMapping("/stat")
@Controller
public class StatController {

    @Autowired
    private StatService statService;

    /**
     * 跳转到客户地区统计页面
     */
    @RequestMapping("/toCustomerAreaStat")
    public String toCustomerAreaStat(){
        return "stat/customerAreaStat";
    }

    /**
     * 加载客户地区数据
     */
    @RequestMapping("/loadCustomerAreaStatJson")
    @ResponseBody
    public List<BaseEntity> loadCustomerAreaStatJson(){
        return statService.loadCustomerAreaStatList();
    }

    /**
     * 跳转到业务员年度统计数据页面
     */
    @RequestMapping("/toOpernameYearGradeStat")
    public String toOpernameYearGradeStat(){
        return "stat/opernameYearGradeStat";
    }

    /**
     *  加载业务员年度统计数据
     */
    @RequestMapping("/loadOpernameYearGradeStat")
    @ResponseBody
    public Map<String,Object> loadOpernameYearGradeStat(String year){
        List<BaseEntity> entities = statService.loadOpernameYearGradeStatList(year);
        Map<String,Object> map = new HashMap<>();
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        for (BaseEntity baseEntity : entities) {
            names.add(baseEntity.getName());
            values.add(baseEntity.getValue());
        }
        map.put("name",names);
        map.put("value",values);
        return map;
    }

    /**
     * 跳转到业务员年度统计数据页面
     */
    @RequestMapping("/toCompanyYearGradeStat")
    public String toCompanyYearGradeStat(){
        return "stat/companyYearGradeStat";
    }


    /**
     * 加载业务员年度统计数据
     */
    @RequestMapping("/loadCompanyYearGradeStat")
    @ResponseBody
    public List<Double> loadCompanyYearGradeStat(String year){
        List<Double> entities = statService.loadCompanyYearGradeStatList(year);
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) == null){
                entities.set(i,0.0);
            }
        }
        return entities;
    }
}
