package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.dto.AccountDTO;
import vn.edu.tdtu.exam.dto.StudentDTO;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.Student;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.repository.StudentRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account add(AccountDTO accountDTO) {
//        Account account = new Account();
//        account.setEmail(accountDTO.getEmail());
//        account.setPassword(accountDTO.getPassword());
//        return accountRepository.save(account);
        return null;
    }

    public Boolean find(Account account) {
        Account foundAccount = accountRepository.findByEmailAndPassword(account.getEmail(), account.getPassword());
        return foundAccount != null;
    }
}
