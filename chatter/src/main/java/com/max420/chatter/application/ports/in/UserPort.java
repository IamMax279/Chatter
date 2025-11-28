package com.max420.chatter.application.ports.in;

import com.max420.chatter.application.commands.user.ChangeBioCommand;
import com.max420.chatter.application.commands.user.ChangeUsernameCommand;

public interface UserPort {
    void changeUsername(ChangeUsernameCommand command);
    void changeBio(ChangeBioCommand command);
}
