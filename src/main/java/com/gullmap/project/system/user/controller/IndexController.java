package com.gullmap.project.system.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gullmap.framework.config.GullMapConfig;
import com.gullmap.framework.web.controller.BaseController;
import com.gullmap.project.system.menu.domain.Menu;
import com.gullmap.project.system.menu.service.IMenuService;
import com.gullmap.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author gullmap
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private GullMapConfig gullMapConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(Model model)
    {
        // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("user", user);
        model.addAttribute("copyrightYear", gullMapConfig.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(Model model)
    {
        model.addAttribute("version", gullMapConfig.getVersion());
        return "main";
    }

}
