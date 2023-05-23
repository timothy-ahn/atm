package atm.web.admin.mapping;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import atm.entity.Customer;
import atm.web.admin.model.CustomerCreateDTO;
import atm.web.admin.model.CustomerDetailsDTO;
import atm.web.admin.model.CustomerItemDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

    @Mapping(target = "birthDate", dateFormat = "dd.MM.yyyy")
    CustomerItemDTO toCustomerItemDto(Customer customer);
    List<CustomerItemDTO> toCustomerListDto(List<Customer> customers);

    @Mapping(target = "birthDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "docIssueDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "docExpiryDate", dateFormat = "dd.MM.yyyy")
    CustomerDetailsDTO toCustomerDetailsDto(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Customer toCustomer(CustomerCreateDTO customer);
}
