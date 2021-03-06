package th.ac.ku.bankaccount.Controller;

import org.springframework.web.bind.annotation.*;
import th.ac.ku.bankaccount.Model.BankAccount;
import th.ac.ku.bankaccount.data.BankAccountRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {

    private BankAccountRepository repository;

    public BankAccountRestController(BankAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<BankAccount> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public BankAccount getOne(@PathVariable int id) {
        try{
            return repository.findById(id).get();
        }catch (NoSuchElementException e){
            return null ;
        }
    }

    @GetMapping("/customer/{customerId}")
    public List<BankAccount> getCustomerAccounts(@PathVariable int customerId) {
        return repository.findByCustomerId(customerId);
    }

    @PostMapping
    public BankAccount create(@RequestBody BankAccount bankAccount){
        repository.save(bankAccount);
        return bankAccount;
    }

    @PutMapping("/{id}")
    public BankAccount update(@PathVariable int id,@RequestBody BankAccount bankAccount){
        BankAccount record = repository.findById(id).get();
        record.setBalance(bankAccount.getBalance());
        repository.save(record);
        return record;
    }

    @DeleteMapping("/{id}")
    public BankAccount delete(@PathVariable int id){
        BankAccount record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }


}
