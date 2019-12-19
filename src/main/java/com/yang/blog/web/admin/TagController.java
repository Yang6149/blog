package com.yang.blog.web.admin;

import com.yang.blog.po.Tag;
import com.yang.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Path;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){

        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        if(tagService.getTag(tag.getId())!=null){
            tagService.uptateTag(tag.getId(),tag);
        }else{
            tagService.saveTag(tag);
        }

        if(result.hasErrors()){
            return "admin/tags-input";
        }
        if(tag!=null){
            attributes.addFlashAttribute("message","操作成功");
        }else{
            attributes.addFlashAttribute("message","操作失败");
        }
        return "redirect:/admin/tags";

    }

    @GetMapping("/tags/{id}/input")
    public String update(@PathVariable("id")Long id,Model model){
        Tag t=tagService.getTag(id);
        model.addAttribute("tag",t);
        return "admin/tags-input";
    }




    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id")Long id){
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }


}
