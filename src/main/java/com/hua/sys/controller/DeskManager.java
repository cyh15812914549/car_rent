package com.hua.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cyh
 * @date 2020/8/12 10:15
 */
@Controller
@RequestMapping("/desk")
public class DeskManager {

    @RequestMapping("/toDeskManager")
    public String toDeskManager(){
        return "system/main/deskManager";
    }
}



