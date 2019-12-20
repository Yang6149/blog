package com.yang.blog.web;

import com.yang.blog.service.ArchiveMap;
import com.yang.blog.service.BlogService;
import com.yang.blog.vo.ArchiveMAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private ArchiveMap archiveMap;
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",archiveMap.getMap().getArchive());
        model.addAttribute("blogCount",blogService.count());
        return "archives";
    }
}
