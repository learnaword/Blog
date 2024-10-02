package com.mjl.blog.controller.page.technology.soft;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.PageInfo;
import com.mjl.blog.controller.page.technology.blog.vo.NewBlogsRespVO;
import com.mjl.blog.controller.page.technology.blog.vo.NewListRespVO;
import com.mjl.blog.controller.page.technology.blog.vo.OrderListRespVO;
import com.mjl.blog.controller.page.technology.soft.vo.*;
import com.mjl.blog.convert.page.TechnologyBlogConvert;
import com.mjl.blog.convert.page.TechnologySoftConvert;
import com.mjl.blog.dal.dataobject.TechnologySoftDO;
import com.mjl.blog.dal.dataobject.TypeDO;
import com.mjl.blog.service.page.technology.blog.TechnologyBlogService;
import com.mjl.blog.service.page.technology.soft.TechnologySoftService;
import com.mjl.blog.service.page.technology.type.TechnologyTypeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/technology")
public class TechnologySoftController {

    @Resource
    private TechnologySoftService technologySoftService;
    @Resource
    private TechnologyBlogService technologyBlogService;
    @Resource
    TechnologyTypeService technologyTypeService;

    @Value("${app.base-url}")
    private String baseUrl;

    @RequestMapping("/soft.html")
    public String index(Model model, SoftReqVO softReqVO){
        //获取软件类型
        List<TypeDO> typeList = technologyTypeService.getList();

        PageResult<SoftListRespVO> softList = TechnologySoftConvert.INSTANCE.covert(technologySoftService.getList(softReqVO));

        PageInfo<SoftListRespVO> pageInfo = new PageInfo<>(softList, softReqVO,3);
        List<OrderListRespVO> blogOrderList = TechnologyBlogConvert.INSTANCE.covertOrder(technologyBlogService.getOrderList());
        List<NewBlogsRespVO> newBlogs = TechnologyBlogConvert.INSTANCE.convertNew(technologyBlogService.getNewBlogs());

        model.addAttribute("topBlogs", blogOrderList);
        model.addAttribute("newBlogs", newBlogs);
        //返回数据
        model.addAttribute("softList", softList.getList());
        model.addAttribute("typeList", typeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("baseUrl",baseUrl);

        return "/page/technology/technology";
    }

    @RequestMapping({"/softDetail/{softId}.html"})
    public String softDetail(Model model, @PathVariable Long softId, SoftDetailsReqVO softDetailsReqVO){
        //获取软件类型
        List<TypeDO> typeList = technologyTypeService.getList();

        PageResult<SoftDetailsRespVO> softDetailsBlog =  TechnologySoftConvert.INSTANCE.covertDetails(technologyBlogService.getSoftDetails(softDetailsReqVO,softId));

        //根据softID获取soft信息
        TechnologySoftDO soft = technologySoftService.selectById(softId);

        PageInfo<SoftDetailsRespVO> pageInfo = new PageInfo<>(softDetailsBlog,softDetailsReqVO,3);

        List<OrderListRespVO> blogOrderList = TechnologyBlogConvert.INSTANCE.covertOrder(technologyBlogService.getOrderList());
        List<NewBlogsRespVO> newBlogs = TechnologyBlogConvert.INSTANCE.convertNew(technologyBlogService.getNewBlogs());

        model.addAttribute("topBlogs", blogOrderList);
        model.addAttribute("newBlogs", newBlogs);
        model.addAttribute("typeList", typeList);
        model.addAttribute("blogList", softDetailsBlog.getList());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("softInfo", soft);
        model.addAttribute("baseUrl",baseUrl);

        return "/page/technology/technologyDetail";
    }

    @RequestMapping({"/softInfo/{typeId}.html"})
    public String softInfo(Model model,@PathVariable Long typeId, SoftInfoReqVO SoftInfoReqVO){

        //获取软件类型
        List<TypeDO> typeList = technologyTypeService.getList();

        //获取软件全部软件
        PageResult<SoftInfoRespVO> respVOPageResult = TechnologySoftConvert.INSTANCE.covertInfo(technologySoftService.getSoftInfos(SoftInfoReqVO,typeId));

        PageInfo<SoftInfoRespVO> pageInfo = new PageInfo<>(respVOPageResult,SoftInfoReqVO,3);
        List<OrderListRespVO> blogOrderList = TechnologyBlogConvert.INSTANCE.covertOrder(technologyBlogService.getOrderList());
        List<NewBlogsRespVO> newBlogs = TechnologyBlogConvert.INSTANCE.convertNew(technologyBlogService.getNewBlogs());

        model.addAttribute("topBlogs", blogOrderList);
        model.addAttribute("newBlogs", newBlogs);
        //返回数据
        model.addAttribute("softList", respVOPageResult.getList());
        model.addAttribute("typeList", typeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("baseUrl",baseUrl);

        return "/page/technology/softType";
    }

    @RequestMapping({"/softDetail/{softId}/{softSection}.html"})
    public String softDetailType(Model model,
                                 @PathVariable Long softId,
                                 @PathVariable Integer softSection,
                                 SoftDetailTypeReqVO softDetailTypeReqVO){
        //获取软件类型
        List<TypeDO> typeList = technologyTypeService.getList();

        PageResult<SoftDetailTypeRespVO> detailTypeRespVOPageResult = TechnologySoftConvert.INSTANCE.covertDetailType(technologyBlogService.softDetailTypeBlogs(softDetailTypeReqVO,softId,softSection));

        PageInfo<SoftDetailTypeRespVO> pageInfo = new PageInfo<>(detailTypeRespVOPageResult,softDetailTypeReqVO,3);

        //根据softID获取soft信息
        TechnologySoftDO soft = technologySoftService.selectById(softId);

        List<OrderListRespVO> blogOrderList = TechnologyBlogConvert.INSTANCE.covertOrder(technologyBlogService.getOrderList());
        List<NewBlogsRespVO> newBlogs = TechnologyBlogConvert.INSTANCE.convertNew(technologyBlogService.getNewBlogs());

        model.addAttribute("topBlogs", blogOrderList);
        model.addAttribute("newBlogs", newBlogs);
        model.addAttribute("blogList", detailTypeRespVOPageResult.getList());
        model.addAttribute("typeList", typeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("softInfo", soft);
        model.addAttribute("baseUrl",baseUrl);

        return "/page/technology/technologyDetail";
    }
}
