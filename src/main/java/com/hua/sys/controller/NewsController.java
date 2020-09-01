package com.hua.sys.controller;


import com.hua.sys.entity.News;
import com.hua.sys.entity.User;
import com.hua.sys.service.NewsService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.utils.ResultObj;
import com.hua.sys.utils.WebUtils;
import com.hua.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyh
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 加载公告列表返回DataGridView
     */
    @RequestMapping("/loadAllNews")
    public DataGridView loadAllNews(NewsVo newsVo) {
        return newsService.queryAllNews(newsVo);
    }

    /**
     * 添加公告
     */
    @RequestMapping("/addNews")
    public ResultObj addNews(NewsVo newsVo) {
        try {
            newsVo.setCreatetime(new Date());
            User user=(User) WebUtils.getHttpSession().getAttribute("user");
            newsVo.setOpername(user.getRealname());
            newsService.save(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改公告
     */
    @RequestMapping("/updateNews")
    public ResultObj updateNews(NewsVo newsVo) {
        try {
            this.newsService.updateById(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除公告
     */
    @RequestMapping("/deleteNews")
    public ResultObj deleteNews(NewsVo newsVo) {
        try {
            newsService.deleteNews(newsVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     */
    @RequestMapping("/deleteBatchNews")
    public ResultObj deleteBatchNews(NewsVo newsVo) {
        try {
            newsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询公告
     */
    @RequestMapping("/loadNewsById")
    public News loadNewsById(Integer id){
        return newsService.getById(id);
    }
}

