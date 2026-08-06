package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;

public interface ApArticleService extends IService<ApArticle>{
    /**
     * 加载文章列表
     * @param dao
     * @param type 1加载更多，2加载更新
     * @return
     */
    public ResponseResult load(ArticleHomeDto dao,Short type);

    /**
     * 保存app端相关文章
     * @param dto
     * @return
     */
    public ResponseResult saveArticle(ArticleDto dto) throws InvocationTargetException, IllegalAccessException;


}



