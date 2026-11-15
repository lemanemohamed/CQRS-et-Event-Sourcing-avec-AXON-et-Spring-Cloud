package enset.ma.demoescqrsaxon.commands.agregates;

import enset.ma.demoescqrsaxon.commands.command.AddAcountCommand;
import enset.ma.demoescqrsaxon.enums.AccountStatus;
import enset.ma.demoescqrsaxon.events.ACcountCreatedEvent;
import enset.ma.demoescqrsaxon.events.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAgregate {
    private String accountId;
    private double balance;
    private AccountStatus accountStatus;

    public AccountAgregate(){}
    @CommandHandler
    public AccountAgregate(AddAcountCommand command){
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ))

    }
}
