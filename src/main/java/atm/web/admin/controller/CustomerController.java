package atm.web.admin.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import atm.entity.Customer;
import atm.entity.Customer.DocumentType;
import atm.service.CustomerService;
import atm.web.admin.mapping.CustomerMapper;
import atm.web.admin.model.CustomerCreateDTO;
import atm.web.admin.model.CustomerDetailsDTO;
import atm.web.admin.model.CustomerEditDTO;
import atm.web.admin.model.CustomerItemDTO;
import jakarta.validation.Valid;
import javassist.NotFoundException;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
    
    private final CustomerService customerService;
    private final CustomerMapper mapper;

    public CustomerController(CustomerService customerService, CustomerMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @GetMapping
    public String list(Model model) {
        List<CustomerItemDTO> customers = mapper.toCustomerListDto(
            customerService.getAll()
        );
        model.addAttribute("customers", customers);
        return "admin/customer/list";
    }

    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model) throws NotFoundException {
        CustomerDetailsDTO customer = mapper.toCustomerDetailsDto(
            customerService.findById(id)
        );
        
        model.addAttribute("customer", customer);
        return "admin/customer/details";
    }

    @ModelAttribute(name = "sexs")
    public List<String> sexs() {
        return List.of("Male", "Female");
    }

    @ModelAttribute(name = "docTypes")
    public List<DocumentType> docTypes() {
        return Arrays.asList(DocumentType.values());
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("customer", new CustomerCreateDTO());

        return "admin/customer/create";
    }
    
    @PostMapping("create")
    public String create(@Valid @ModelAttribute("customer") CustomerCreateDTO createDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin/customer/create";
        }
        
        Customer customer = mapper.toCustomer(createDTO);
        customerService.createCustomer(customer);

        return "redirect:/admin/customer";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) throws NotFoundException {
        CustomerDetailsDTO customer = mapper.toCustomerDetailsDto(
            customerService.findById(id)
        );
        
        model.addAttribute("customer", customer);
        return "admin/customer/edit";
    }

    @PostMapping("edit")
    public String edit(@Valid @ModelAttribute("customer") CustomerEditDTO editDTO, BindingResult bindingResult) {   

        if (bindingResult.hasErrors()) {
            return "admin/customer/edit";
        }

        Customer editedCustomer = new Customer();
        editedCustomer.setId(editDTO.getId());
        editedCustomer.setFirstName(editDTO.getFirstName());
        editedCustomer.setLastName(editDTO.getLastName());
        editedCustomer.setMiddleName(editDTO.getMiddleName());
        editedCustomer.setResidenceAddress(editDTO.getResidenceAddress());
        editedCustomer.setActualAddress(editDTO.getActualAddress());
        editedCustomer.setPhoneNumber(editDTO.getPhoneNumber());
        editedCustomer.setEmail(editDTO.getEmail());

        customerService.updateCustomer(editedCustomer);

        return "redirect:/admin/customer/" + editedCustomer.getId();
    }

    @PostMapping("delete/{id}")
    public String edit(@PathVariable Integer id) { 
        customerService.deleteCustomer(id);
        return "redirect:/admin/customer";
    }
}
