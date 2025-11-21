package com.max420.chatter.application.ports.in.user;

import com.max420.chatter.application.commands.user.ChangeBioCommand;

public interface ChangeBioUseCase {
    void execute(ChangeBioCommand command);
}
