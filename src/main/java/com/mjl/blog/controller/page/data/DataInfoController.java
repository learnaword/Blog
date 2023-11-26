package com.mjl.blog.controller.page.data;

import com.mjl.blog.controller.page.data.vo.ButtonInfoRespVo;
import com.mjl.blog.service.page.data.ButtonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/data")
@RestController
public class DataInfoController {

    @Autowired
    ButtonInfoService buttonInfoService;

    @PostMapping("button-info")
    public void ButtonInfo(@RequestBody ButtonInfoRespVo buttonInfoRespVo){
        buttonInfoService.insert(buttonInfoRespVo);
        System.out.println(buttonInfoRespVo.toString());
    }
}
