package com.demo.controller;

import com.demo.model.AlertDTO;
import com.demo.model.AlertType;
import com.demo.model.DepartmentDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
public class DepartmentController extends BaseController {

    @GetMapping(value = "/departmentOverview")
    public ModelAndView departmentOverview(@ModelAttribute(ALERT) AlertDTO alert, Authentication authentication, final Model model) {
        ModelAndView mav = new ModelAndView();
        addModelAttributes(model, authentication);
        List<DepartmentDto> departmentList = departmentService.getDepartmentList();
        model.addAttribute("departmentList", departmentList);
        model.addAttribute(ALERT, alert);
        mav.setViewName("departmentOverviewPage");
        return mav;
    }


    @RequestMapping(value = "/createDepartment", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView departmentForm(@ModelAttribute String formAction, Authentication authentication, final Model model) {
        ModelAndView mav = new ModelAndView();
        addModelAttributes(model, authentication);
        DepartmentDto department = new DepartmentDto();
        model.addAttribute("department", department);
        mav.setViewName("departmentForm");
        return mav;
    }

    @PostMapping(value = "/submitDepartment")
    public ModelAndView submitDepartment(@ModelAttribute("department") @Valid DepartmentDto department, BindingResult bindingResult, Model model, final HttpServletRequest request, RedirectAttributes redirectAttributes, Authentication authentication) {
        ModelAndView mav = new ModelAndView();
        String formAction = request.getParameter("formAction");
        addModelAttributes(model, authentication);
        AlertDTO alert = null;
        if (bindingResult.hasErrors()) {
            model.addAttribute("department", department);
            mav.setViewName("departmentForm");
        } else {
            int result;
            if ("updateDepartment".equals(formAction)) {
                result = departmentService.updateDepartment(department);
                if (result == department.getId()) {
                    alert = new AlertDTO("Update successful.", "The department #" + result + " was updated successfully.", AlertType.SUCCES);
                } else {
                    alert = new AlertDTO("Error during update.", "An error occured while trying to update the department #" + result + ".", AlertType.ERROR);
                }
            } else {
                result = departmentService.saveDepartment(department);
                if (result != 0) {
                    alert = new AlertDTO("Creation successful.", "The department #" + result + " was created successfully.", AlertType.SUCCES);
                } else {
                    alert = new AlertDTO("Error during department creation.", "An error occured while trying to create the department #" + result + ".", AlertType.ERROR);
                }
            }
            mav.setViewName("redirect:/departmentOverview");
        }
        redirectAttributes.addFlashAttribute(ALERT, alert);
        return mav;
    }

    @PostMapping(value = "/editDepartment")
    public ModelAndView editEmployee(@RequestParam("departmentId") int id, Authentication authentication, final Model model) {
        ModelAndView mav = new ModelAndView();
        addModelAttributes(model, authentication);
        DepartmentDto department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        model.addAttribute("formAction", "updateDepartment");
        mav.setViewName("departmentForm");
        return mav;
    }

    @PostMapping (value = "/deleteDepartment")
    public ModelAndView deleteEmployee(@RequestParam("departmentId") int id, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        AlertDTO alert;
        int result = departmentService.deleteDepartment(id);
        if (result != 0){
            alert = new AlertDTO("Operation successful.", "The department #" + result + " was deleted.", AlertType.SUCCES);
        }else{
            alert = new AlertDTO("Error during department deletion.", "An error occured while trying to delete the department #" + result + ".", AlertType.ERROR);
        }
        mav.setViewName("redirect:/departmentOverview");
        redirectAttributes.addFlashAttribute(ALERT, alert);
        return mav;
    }

}