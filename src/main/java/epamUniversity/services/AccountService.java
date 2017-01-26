package epamUniversity.services;

import epamUniversity.model.Account;
import epamUniversity.model.User;

/**
 * Created by Andriy_Yarish on 1/24/2017.
 */
public interface AccountService extends AbstractDomainObjectService<Account> {

    double refillBalance(User user, double amount);

    double chargeBalance(User user, double amount);
}
