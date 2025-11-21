package com.max420.chatter.application.ports.in.user;

import com.max420.chatter.application.commands.user.ChangeUsernameCommand;

public interface ChangeUsernameUseCase {
    void execute(ChangeUsernameCommand command);
}
