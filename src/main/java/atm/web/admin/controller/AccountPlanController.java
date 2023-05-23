package atm.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import atm.service.AccountPlanService;

@Controller
@RequestMapping("/admin/account-plan")
public class AccountPlanController {
    
    private final AccountPlanService accountPlanService;

    public AccountPlanController(AccountPlanService accountPlanService) {
        this.accountPlanService = accountPlanService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("accountPlans", accountPlanService.getAll());
        return "admin/account-plan/list";
    }
}
