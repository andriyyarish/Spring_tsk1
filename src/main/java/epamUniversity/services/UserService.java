package epamUniversity.services;

/**
 * Created by Andriy_Yarish on 1/3/2017.
 */
import epamUniversity.model.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     *
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable User getUserByEmail(@Nonnull String email);

}
