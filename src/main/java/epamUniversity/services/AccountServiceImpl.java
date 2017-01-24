package epamUniversity.services;

import epamUniversity.dao.AccountRepository;
import epamUniversity.model.Account;
import epamUniversity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by Andriy_Yarish on 1/24/2017.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public double refillAccount(User user, double amount) {
        Account account = user.getAccount();
        double ballance = account.getBallance() + amount;
        account.setBallance(ballance);
        return accountRepository.saveAndFlush(account).getBallance();
    }

    @Override
    public Account save(@Nonnull Account object) {
        return null;
    }

    @Override
    public void remove(@Nonnull Account object) {

    }

    @Override
    public Account getById(@Nonnull Integer id) {
        return null;
    }

    @Nonnull
    @Override
    public Collection<Account> getAll() {
        return null;
    }
}
