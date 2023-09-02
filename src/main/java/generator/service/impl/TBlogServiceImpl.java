package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mjl.blog.dal.dataobject.BlogDO;
import generator.service.TBlogService;
import generator.mapper.TBlogMapper;
import org.springframework.stereotype.Service;

/**
* @author majunliang
* @description 针对表【t_blog】的数据库操作Service实现
* @createDate 2023-09-02 16:16:59
*/
@Service
public class TBlogServiceImpl extends ServiceImpl<TBlogMapper, BlogDO>
    implements TBlogService{

}




