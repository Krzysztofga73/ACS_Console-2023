package org.example.Building;

import org.example.Staff.StaffMember;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private final String adress;
    private final String name;
    private final List<Rooms> rooms;
    private List<StaffMember> staffMembers;
    public Building(String adress, String name) {
        this.adress = adress;
        this.name = name;
        this.rooms = new ArrayList<Rooms>();
        this.staffMembers = new ArrayList<StaffMember>();
        createRooms();

    }
    private void createRooms() {
        Rooms[] roomsEnum = Rooms.values();
        for (Rooms room : roomsEnum) {
            rooms.add(room);
        }
    }


    public String getAdress() {
        return adress;
    }

    public String getName() {
        return name;
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


