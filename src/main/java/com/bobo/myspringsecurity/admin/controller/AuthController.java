package com.bobo.myspringsecurity.admin.controller;

import com.bobo.myspringsecurity.admin.dto.JwtUserDto;
import com.bobo.myspringsecurity.admin.dto.MenuIndexDto;
import com.bobo.myspringsecurity.admin.service.MenuService;
import com.bobo.myspringsecurity.common.utils.RedisUtils;
import com.bobo.myspringsecurity.common.utils.Result;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Description 验证
 * @Date 2021/10/10 20:51
 * @Created by bobo
 */
@RestController
public class AuthController {

    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "验证码生成")
    @GetMapping("/captcha")
    public Result captcha() throws IOException {
        String key = UUID.randomUUID().toString(); //生成uuid
        String code = producer.createText();//生成code
        BufferedImage image = producer.createImage(code);//生成验证码

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        //转base64
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        //存入redis中
        redisUtils.hset("captcha", key, code, 120);
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key);
        return Result.ok().data("base64Img", base64Img).data("key", key);
    }

    //获取菜单
    @GetMapping(value = "api/index")
    @ResponseBody
    @ApiOperation(value = "通过用户id获取菜单")
    public Result getMenu() {
        JwtUserDto userDto = (JwtUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDto.getMyUser().getUserId();
        List<MenuIndexDto> menu = menuService.getMenu(userId);
        return Result.ok().data("menus",menu);
    }
}
