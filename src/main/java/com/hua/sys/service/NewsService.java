package com.hua.sys.service;

import com.hua.sys.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.vo.NewsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyh
 * @since 2020-09-01
 */
public interface NewsService extends IService<News> {

    /**
     * 查询所有日志
     * @param newsVo
     * @return
     */
    DataGridView queryAllNews(NewsVo newsVo);
    /**
     * 添加日志
     * @param newsVo
     */
    void addNews(NewsVo newsVo);
    /**
     * 根据id删除日志
     * @param newsid
     */
    void deleteNews(Integer newsid);
    /**
     * 批量删除日志
     */
    void deleteBatchNews(Integer [] ids);
}
