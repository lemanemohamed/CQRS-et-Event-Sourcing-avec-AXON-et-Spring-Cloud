package enset.ma.demoescqrsaxon.commands.controller;

import enset.ma.demoescqrsaxon.commands.command.AddAcountCommand;
import enset.ma.demoescqrsaxon.commands.dtos.AddNewAccountRequestDTO;
import org.apache.el.stream.Stream;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {
    private CommandGateway commandGateway;

    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    private EventStore eventStore;


    @PostMapping("/add")
    public String addNewAccount(@RequestBody AddNewAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new AddAcountCommand(
                UUID.randomUUID().toString(),
                request.initialBalance(),
                request.currency()
        ));
        return response;
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        return e.getMessage();
    }
    @GetMapping("/events/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }
}
