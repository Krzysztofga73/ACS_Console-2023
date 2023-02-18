package org.example.Building;

import org.example.Staff.AccessToZones;
import org.example.Staff.StaffMember;
import org.example.Staff.StaffMembersDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ACS_System implements AccessToZones {
    private final Building building;
    private final StaffMembersDAO dao;
    private Map<StaffMember, Zones> accessMap;

    public ACS_System(Building building, StaffMembersDAO dao) {
        this.building = building;
        this.dao = dao;
        this.accessMap = new HashMap<>();
    }


    @Override
    public Boolean giveAccessForDepartment(Zones zone) {
        return null;
    }

    @Override
    public Boolean giveAccess(Integer id, Zones zone) {
        Optional<StaffMember> staffMember = building.getStaffMember(id);
        if (staffMember.isPresent()) {
            accessMap.put(staffMember.get(), zone);
            System.out.println("Staff memeber: " + staffMember.get().getName() + " " + staffMember.get().getSurname() + " ID: "
                    + staffMember.get().getId() + " has access to zone " + zone);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Boolean checkAccess(StaffMember staffMember, Rooms room) {
        return null;
    }

    @Override
    public Boolean giveAccessToRoom(StaffMember staffMember, Rooms room) {
        return null;
    }


}
