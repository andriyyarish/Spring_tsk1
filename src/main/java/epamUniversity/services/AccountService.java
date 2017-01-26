package epamUniversity.services;

import epamUniversity.model.Account;
import epamUniversity.model.User;

/**
 * Created by Andriy_Yarish on 1/24/2017.
 */
public interface AccountService extends AbstractDomainObjectService<Account> {

    /**
     * Add amount to current ballance
     * @param user
     * @param amount
     * @return
     */
    double refillBalance(User user, double amount);

    /**
     * Method get current ballance perform actual = current - amount and update ballance.
     * In case when actual is less than 0 exception will be thrown
     * @param user
     * @param amount
     * @return
     */
    double chargeBalance(User user, double amount);
}
