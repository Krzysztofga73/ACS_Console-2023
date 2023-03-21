package org.example.Building;

import org.example.Staff.AccessToZones;
import org.example.Staff.StaffMember;
import org.example.Staff.StaffMembersDAO;

import java.util.*;

public class ACS_System implements AccessToZones {
    private final Building building;
    private final StaffMembersDAO dao;

    private Map<StaffMember, Zones> accessToZonesMao;
    private Map<StaffMember, Rooms> accessToRoomsMap;

    public ACS_System(Building building, StaffMembersDAO dao) {
        this.building = building;
        this.dao = dao;
        this.accessToZonesMao = new HashMap<>();
        this.accessToRoomsMap = new HashMap<>();
    }


    @Override
    public Boolean giveAccess(Integer id, Zones zone) {
        Optional<StaffMember> staffMember = building.getStaffMember(id);
        if (staffMember.isPresent()) {
            accessToZonesMao.put(staffMember.get(), zone);
            System.out.println("Staff memeber: " + staffMember.get().getName() + " " + staffMember.get().getSurname() + " ID: "
                    + staffMember.get().getId() + " has access to zone " + zone);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Boolean checkAccess(Integer id, Rooms room) {
        var staffMember = building.getStaffMember(id);
        if ((!staffMember.equals(Optional.empty())) && accessToZonesMao.containsKey(staffMember.get()) && building.getRooms().contains(room)) {
            if (accessToZonesMao.get(staffMember.get()).equals(room.getSecurityLevel())) {
                System.out.println("Access to room : " + room + " is granted! " + staffMember.get().getName() + " " +
                        staffMember.get().getSurname() + " can enter this room!");
                return true;
            } else if (accessToRoomsMap.get(staffMember.get()).equals(room)) {
                System.out.println("Access to room : " + room.name() + " is granted! " + staffMember.get().getName() + " " +
                        staffMember.get().getSurname() + " can enter this room!");
                return true;
            } else {
                System.out.println("Access denied! " + staffMember.get().getName() + " " + staffMember.get().getSurname() +
                        " can't enter this room!");
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Boolean giveAccessToRoom(Integer id, Rooms room) {
        var staffMember = building.getStaffMember(id);
        if (accessToRoomsMap.containsKey(staffMember.get()) && accessToRoomsMap.get(staffMember.get()).equals(room)){
            return false;
        }
        else if (staffMember.isPresent()) {
            accessToRoomsMap.put(staffMember.get(), room);
            System.out.println("Staff memeber: " + staffMember.get().getName() + " " + staffMember.get().getSurname() + " ID: "
                    + staffMember.get().getId() + " has access to room " + room);
            return true;
        } else {
            return false;
        }

    }


}
