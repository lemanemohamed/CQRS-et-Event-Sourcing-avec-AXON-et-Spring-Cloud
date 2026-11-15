package enset.ma.demoescqrsaxon.events;

import enset.ma.demoescqrsaxon.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private double initialbalace;
    private AccountStatus status;
    private String currency;
}
