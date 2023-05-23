package atm.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import atm.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/admin/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public String list(Model model) {
        model.addAttribute("transactions", transactionService.getAll());
        return "admin/transaction/list";
    }
    
}
