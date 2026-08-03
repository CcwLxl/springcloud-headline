package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;

public interface ApArticleService extends IService<ApArticle>{
    /**
     * 加载文章列表
     * @param dao
     * @param type 1加载更多，2加载更新
     * @return
     */
    public ResponseResult load(ArticleHomeDto dao,Short type);

}



