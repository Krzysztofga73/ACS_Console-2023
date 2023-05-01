package org.example.Building;

import org.example.Staff.StaffMember;
import org.example.Staff.StaffMembersDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Building {
    private final String adress;
    private final String name;
    private final List<Rooms> rooms;
    private List<Optional<StaffMember>> staffMembers;
    private StaffMembersDAO dao;

    public Building(String adress, String name) {
        this.adress = adress;
        this.name = name;
        this.rooms = new ArrayList<Rooms>();
        this.staffMembers = new ArrayList<Optional<StaffMember>>();
        createRooms();
    }


    private void createRooms() {
        Rooms[] roomsEnum = Rooms.values();
        for (Rooms room : roomsEnum) {
            rooms.add(room);
        }
    }

    public void setDao(StaffMembersDAO dao) {
        this.dao = dao;
    }

    public String getAdress() {
        return adress;
    }

    public String getName() {
        return name;
    }

    public Boolean loadStaffMembers() {
        staffMembers = this.dao.readAll();
        if (!(staffMembers.isEmpty())) {
            return true;
        } else {
            return false;
        }
    }

    public List<Optional<StaffMember>> getStaffMembers() {
        return staffMembers;
    }

    public Optional<StaffMember> getStaffMember(Integer id) {
        for (var s : staffMembers) {
            if (s.get().getId().equals(id)) {
                return s;
            }
        }
        return Optional.empty();
    }

    public void printStaffMembers() {
        staffMembers.stream().forEach(staffMember -> System.out.println(staffMember.get()));
    }

    public List<Rooms> getRooms() {
        return rooms;
    }


    public void printAllRoomsInBuilding() {
        if (!rooms.isEmpty()) {
            for (Rooms room : rooms) {
                System.out.println(room);
            }
        } else {
            System.out.println("Rooms were not added to the building!");
        }
    }

}


