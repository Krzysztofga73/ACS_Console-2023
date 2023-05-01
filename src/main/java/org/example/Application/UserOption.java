package org.example.Application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserOption {

    LIST_STAFF_MEMBER("List staff member"),
    LIST_STAFF_MEMBERS("List all staff members from building"),
    CREATE_STAFF_MEMBER("Create staff member"),
    DELETE_STAFF_MEMBER("Delete staff members"),
    UPDATE_STAFF_MEMBER("Update staff members"),
    GIVE_ACCESS_TO_ZONE("Give access to zone"),
    GIVE_ACCESS_TO_ROOM("Give access to room"),
    CHECK_ACCESS("Check if staff member has access to room"),
    EXIT("EXIT"),
    INVALID_OPTION("Invalid option");
    @Getter
    private final String description;


    public static UserOption getOptionByIndex(String index) {
        int idx = Integer.parseInt(index);
        UserOption[] values = UserOption.values();
        return values[idx];
    }
}
