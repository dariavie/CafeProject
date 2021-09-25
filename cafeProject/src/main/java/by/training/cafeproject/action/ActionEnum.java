package by.training.cafeproject.action;

public enum ActionEnum {
    LOGIN {
        {
            this.command = new LoginAction();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutAction();
        }
    },
    SIGNUP {
        {
            this.command = new RegistrationAction();
        }
    };

    ActionManager command;

    public ActionManager getCurrentCommand() {
        return command;
    }
}
