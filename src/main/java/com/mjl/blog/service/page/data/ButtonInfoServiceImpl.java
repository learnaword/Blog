package com.mjl.blog.service.page.data;

import com.mjl.blog.controller.page.data.vo.ButtonInfoRespVo;
import com.mjl.blog.dal.dataobject.ButtonInfoDO;
import com.mjl.blog.dal.mysql.ButtonInfoMapper;
import com.mjl.blog.utils.UserIpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ButtonInfoServiceImpl implements ButtonInfoService{

    @Autowired
    ButtonInfoMapper buttonInfoMapper;

    @Override
    public void insert(ButtonInfoRespVo buttonInfoRespVo) {
        ButtonInfoDO buttonInfoDO = new ButtonInfoDO();

        //获取ip
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String ip = UserIpUtil.getIp(request);

        buttonInfoDO.setBlogId(buttonInfoRespVo.getBlogId())
                .setButtonInfo(buttonInfoRespVo.getButtonInfo())
                .setPosition(buttonInfoRespVo.getPosition())
                .setIp(ip)
                .setCreateTime(System.currentTimeMillis())
                .setUpdateTime(System.currentTimeMillis());

        buttonInfoMapper.insert(buttonInfoDO);
    }
}
