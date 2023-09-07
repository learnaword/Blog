package com.mjl.blog.controller.page.soft;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.PageInfo;
import com.mjl.blog.controller.page.soft.vo.*;
import com.mjl.blog.convert.page.SoftConvert;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.dal.dataobject.TypeDO;
import com.mjl.blog.service.page.blog.BlogService;
import com.mjl.blog.service.page.soft.SoftService;
import com.mjl.blog.service.page.type.TypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SoftController {

    @Resource
    private SoftService softService;
    @Resource
    private BlogService blogService;
    @Resource
    TypeService typeService;

    @RequestMapping("/soft.html")
    public String index(Model model, SoftReqVO softReqVO){
        //获取软件类型
        List<TypeDO> typeList = typeService.getList();

        PageResult<SoftListRespVO> softList = SoftConvert.INSTANCE.covert(softService.getList(softReqVO));

        PageInfo<SoftListRespVO> pageInfo = new PageInfo<>(softList, softReqVO,3);
        //返回数据
        model.addAttribute("softList", softList.getList());
        model.addAttribute("typeList", typeList);
        model.addAttribute("pageInfo", pageInfo);

        return "/page/soft";
    }

    @RequestMapping({"/softDetail/{softId}.html"})
    public String softDetail(Model model, @PathVariable Long softId, SoftDetailsReqVO softDetailsReqVO){
        //获取软件类型
        List<TypeDO> typeList = typeService.getList();

        PageResult<SoftDetailsRespVO> softDetailsBlog =  SoftConvert.INSTANCE.covertDetails(blogService.getSoftDetails(softDetailsReqVO,softId));

        //根据softID获取soft信息
        SoftDO soft = softService.selectById(softId);

        PageInfo<SoftDetailsRespVO> pageInfo = new PageInfo<>(softDetailsBlog,softDetailsReqVO,3);

        model.addAttribute("typeList", typeList);
        model.addAttribute("blogList", softDetailsBlog.getList());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("softInfo", soft);

        return "/page/softDetail";
    }

    @RequestMapping({"/softInfo/{typeId}.html"})
    public String softInfo(Model model,@PathVariable Long typeId, SoftInfoReqVO SoftInfoReqVO){

        //获取软件类型
        List<TypeDO> typeList = typeService.getList();

        //获取软件全部软件
        PageResult<SoftInfoRespVO> respVOPageResult = SoftConvert.INSTANCE.covertInfo(softService.getSoftInfos(SoftInfoReqVO,typeId));

        PageInfo<SoftInfoRespVO> pageInfo = new PageInfo<>(respVOPageResult,SoftInfoReqVO,3);

        //返回数据
        model.addAttribute("softList", respVOPageResult.getList());
        model.addAttribute("typeList", typeList);
        model.addAttribute("pageInfo", pageInfo);

        return "/page/softType";
    }

    @RequestMapping({"/softDetail/{softId}/{softSection}.html"})
    public String softDetailType(Model model,
                                 @PathVariable Long softId,
                                 @PathVariable Integer softSection,
                                 SoftDetailTypeReqVO softDetailTypeReqVO){
        //获取软件类型
        List<TypeDO> typeList = typeService.getList();

        PageResult<SoftDetailTypeRespVO> detailTypeRespVOPageResult =   SoftConvert.INSTANCE.covertDetailType(blogService.softDetailTypeBlogs(softDetailTypeReqVO,softId,softSection));

        PageInfo<SoftDetailTypeRespVO> pageInfo = new PageInfo<>(detailTypeRespVOPageResult,softDetailTypeReqVO,3);

        //根据softID获取soft信息
        SoftDO soft = softService.selectById(softId);

        model.addAttribute("blogList", detailTypeRespVOPageResult.getList());
        model.addAttribute("typeList", typeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("softInfo", soft);

        return "/page/softDetail";
    }
}
