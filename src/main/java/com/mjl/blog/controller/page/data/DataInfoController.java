package com.mjl.blog.controller.page.data;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.data.vo.ButtonInfoRespVo;
import com.mjl.blog.controller.page.data.vo.TableReqVO;
import com.mjl.blog.controller.page.data.vo.TableRespVO;
import com.mjl.blog.convert.admin.SoftAdminConvert;
import com.mjl.blog.convert.page.DataInfoConvert;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.dataobject.ButtonInfoDO;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.service.page.blog.BlogService;
import com.mjl.blog.service.page.data.ButtonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RequestMapping("/data")
@RestController
public class DataInfoController {

    @Autowired
    ButtonInfoService buttonInfoService;

    @Autowired
    BlogService blogService;

    @PostMapping("button-info")
    public void ButtonInfo(@RequestBody ButtonInfoRespVo buttonInfoRespVo){
        buttonInfoService.insert(buttonInfoRespVo);
    }

    @GetMapping("button-table")
    public CommonResult<PageResult<TableRespVO>> buttonInfoTable(TableReqVO tableReqVO){
        PageResult<ButtonInfoDO> ButtonInfoDOPageResult = buttonInfoService.getButtonInfoList(tableReqVO);

        List<TableRespVO> buttonInfoDOList = new ArrayList<>( ButtonInfoDOPageResult.getList().size() );
        ButtonInfoDOPageResult.getList().forEach(item ->{
            TableRespVO tableRespVO = DataInfoConvert.INSTANCE.convert2(item);
            BlogDO blogDO= blogService.selectBlogById(item.getBlogId());
            tableRespVO.setBlogId(blogDO.getId());
            tableRespVO.setBlogTitle(blogDO.getTitle());
            buttonInfoDOList.add( tableRespVO );
        });

        return success(new PageResult<>(buttonInfoDOList,ButtonInfoDOPageResult.getTotal()));

    }
}
