package com.hua.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hua.sys.entity.News;
import com.hua.sys.entity.News;
import com.hua.sys.mapper.NewsMapper;
import com.hua.sys.mapper.NewsMapper;
import com.hua.sys.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.vo.NewsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyh
 * @since 2020-09-01
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        IPage<News> page = new Page<News>(newsVo.getPage(),newsVo.getLimit());
        //构造模糊查询
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        //降序
        queryWrapper.orderByDesc("createtime");
        if (StringUtils.isNotEmpty(newsVo.getTitle()) || StringUtils.isNotEmpty(newsVo.getContent())){
            queryWrapper.and(i -> i.like("title",newsVo.getTitle()).like("content",newsVo.getContent()));
        }
        if (newsVo.getStartTime() != null){
            queryWrapper.and(i->i.ge("createtime",newsVo.getStartTime()));
        }
        if (newsVo.getEndTime() != null){
            queryWrapper.and(i->i.le("createtime",newsVo.getEndTime()));
        }
        page = newsMapper.selectPage(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public void addNews(NewsVo newsVo) {
        this.newsMapper.insert(newsVo);
    }

    @Override
    public void deleteNews(Integer logInfoid) {
        this.newsMapper.deleteById(logInfoid);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer integer : ids) {
            this.deleteNews(integer);
        }
    }
}
