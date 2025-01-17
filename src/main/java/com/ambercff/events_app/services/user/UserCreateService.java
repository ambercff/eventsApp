package com.ambercff.events_app.services.user;

import com.ambercff.events_app.dtos.user.UserCreateDTO;
import com.ambercff.events_app.dtos.user.UserDTO;
import com.ambercff.events_app.models.User;

public interface UserCreateService {
    User createUser(UserCreateDTO user);
}
