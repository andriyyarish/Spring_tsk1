package epamUniversity.services;

import epamUniversity.model.Account;
import epamUniversity.model.User;

/**
 * Created by Andriy_Yarish on 1/24/2017.
 */
public interface AccountService extends AbstractDomainObjectService<Account> {

    double refillAccount(User user, double amount);
}
