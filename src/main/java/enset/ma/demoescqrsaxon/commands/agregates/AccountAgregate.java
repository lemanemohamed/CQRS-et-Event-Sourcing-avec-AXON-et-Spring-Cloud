package enset.ma.demoescqrsaxon.commands.agregates;

import enset.ma.demoescqrsaxon.commands.command.AddAcountCommand;
import enset.ma.demoescqrsaxon.enums.AccountStatus;
import enset.ma.demoescqrsaxon.events.AccountCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAgregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private AccountStatus accountStatus;

    public AccountAgregate(){}
    @CommandHandler
    public AccountAgregate(AddAcountCommand command){
        log.info("############ AddAccountMessage Received ############");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("############ AddAccountMessage Occured ############");
        this.accountId=event.getAccountId();
        this.accountStatus=event.getStatus();
        this.balance=event.getInitialbalace();
    }
}
