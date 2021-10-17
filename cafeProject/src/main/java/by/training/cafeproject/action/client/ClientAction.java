package by.training.cafeproject.action.client;

import by.training.cafeproject.action.Action;
import by.training.cafeproject.domain.Role;

public abstract class ClientAction extends Action {
    public ClientAction() {
        getAllowRoles().add(Role.CLIENT);
    }
}
