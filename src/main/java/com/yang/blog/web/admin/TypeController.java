package com.yang.blog.web.admin;

import com.yang.blog.po.Type;
import com.yang.blog.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){


        model.addAttribute("page",typeService.listType(pageable));

        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());

        return "admin/types-input";
    }
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result,RedirectAttributes attributes){

        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t=null;
        if(type.getId()!=null){
            t=typeService.uptateType(type.getId(),type);
        }else{
            t=typeService.saveType(type);
        }

        if(t!=null){
            attributes.addFlashAttribute("message","操作成功");
        }else{
            attributes.addFlashAttribute("message","操作失败");

        }

        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/input")
    public String update(@PathVariable("id")Long id, Model model){
        Type t=typeService.getType(id);
        model.addAttribute("type",t);
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id")Long id){
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }

}
