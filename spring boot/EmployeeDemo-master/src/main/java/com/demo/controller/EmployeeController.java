package com.demo.controller;

import com.demo.model.AlertDTO;
import com.demo.model.AlertType;
import com.demo.model.DepartmentDto;
import com.demo.model.EmployeeDto;
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
public class EmployeeController extends BaseController {

    @GetMapping(value = "/employeeOverview")
    public ModelAndView employeeOverview(@ModelAttribute("alert") AlertDTO alert, Authentication authentication, final Model model) {
        ModelAndView mav = new ModelAndView();
        addModelAttributes(model, authentication);
        List<EmployeeDto> employeeList = employeeService.getEmployeeList();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute(ALERT, alert);
        mav.setViewName("employeeOverviewPage");
        return mav;
    }

    @RequestMapping(value = "/createEmployee", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView employeeForm(@ModelAttribute String formAction, Authentication authentication, final Model model) {
        ModelAndView mav = new ModelAndView();
        addModelAttributes(model, authentication);
        EmployeeDto employee = new EmployeeDto();
        model.addAttribute("employee", employee);
        List<DepartmentDto> departmentList = departmentService.getDepartmentList();
        model.addAttribute("departmentList", departmentList);
        mav.setViewName("employeeForm");
        return mav;
    }

    @PostMapping(value = "/submitEmployee")
    public ModelAndView submitEmployee(@ModelAttribute("employee") @Valid EmployeeDto employee, BindingResult bindingResult, Model model, final HttpServletRequest request, RedirectAttributes redirectAttributes, Authentication authentication) {
        ModelAndView mav = new ModelAndView();
        String formAction = request.getParameter("formAction");
        addModelAttributes(model, authentication);
        AlertDTO alert = null;
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            List<DepartmentDto> departmentList = departmentService.getDepartmentList();
            model.addAttribute("departmentList", departmentList);
            mav.setViewName("employeeForm");
        } else {
            int result;
            if ("updateEmployee".equals(formAction)) {
                result = employeeService.updateEmployee(employee);
                if (result == employee.getId()) {
                    alert = new AlertDTO("Update successful.", "The employee #" + result + " was updated successfully.", AlertType.SUCCES);
                } else {
                    alert = new AlertDTO("Error during update.", "An error occured while trying to update the employee #" + result + ".", AlertType.ERROR);
                }
            } else {
                result = employeeService.saveEmployee(employee);
                if (result != 0) {
                    alert = new AlertDTO("Creation successful.", "The employee #" + result + " was created successfully.", AlertType.SUCCES);
                } else {
                    alert = new AlertDTO("Error during employee creation.", "An error occured while trying to create the employee #" + result + ".", AlertType.ERROR);
                }
            }
            mav.setViewName("redirect:/employeeOverview");
        }
        redirectAttributes.addFlashAttribute("alert", alert);
        return mav;
    }

    @PostMapping(value = "/editEmployee")
    public ModelAndView editEmployee(@RequestParam("employeeId") int id, Authentication authentication, final Model model) {
        ModelAndView mav = new ModelAndView();
        addModelAttributes(model, authentication);
        List<DepartmentDto> departmentList = departmentService.getDepartmentList();
        model.addAttribute("departmentList", departmentList);
        EmployeeDto employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("formAction", "updateEmployee");
        mav.setViewName("employeeForm");
        return mav;
    }

    @PostMapping(value = "/deleteEmployee")
    public ModelAndView deleteEmployee(@RequestParam("employeeId") int id, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        AlertDTO alert;
        int result = employeeService.deleteEmployee(id);
        if (result != 0) {
            alert = new AlertDTO("Operation successful.", "The department was deleted.", AlertType.SUCCES);
        } else {
            alert = new AlertDTO("Error during department deletion.", "An error occured while trying to delete the department.", AlertType.ERROR);
        }
        mav.setViewName("redirect:/employeeOverview");
        redirectAttributes.addFlashAttribute("alert", alert);
        return mav;
    }

}