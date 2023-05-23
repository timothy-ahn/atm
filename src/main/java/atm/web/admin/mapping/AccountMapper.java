package atm.web.admin.mapping;

import org.mapstruct.Mapper;

import atm.entity.Account;
import atm.web.admin.model.AccountItemDTO;

import org.mapstruct.InjectionStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountMapper {

    AccountItemDTO toAccountItemDto(Account account);
}
