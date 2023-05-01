package org.example;

import org.example.Application.UserOption;
import org.example.Building.ACS_System;
import org.example.Building.Building;
import org.example.Building.Rooms;
import org.example.Building.Zones;
import org.example.Staff.StaffMember;
import org.example.Staff.StaffMembersDAO;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Building dataCenter = new Building("Warszawa", "DC1");
        StaffMembersDAO dao = new StaffMembersDAO("C:\\Krzysztof\\IT_PROJECTS\\00_Kontrola_dostępu\\ACS_DC_Simulator\\StaffMembers.csv");
        dataCenter.setDao(dao);
        ACS_System acsSystemForDataCenter = new ACS_System(dataCenter, dao);
        dataCenter.loadStaffMembers();
        String userInput;
        UserOption userOption;

        do {
            Arrays.stream(UserOption.values()).forEach(option -> System.out.printf("%d-%s\n", option.ordinal(), option.getDescription()));
            userInput = scanner.nextLine();
            userOption = UserOption.getOptionByIndex(userInput);
            switch (userOption) {
                case LIST_STAFF_MEMBERS:
                    dao.readAll().stream().forEach(System.out::println);
                    break;
                case LIST_STAFF_MEMBER:
                    System.out.println("Give staff member id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(dao.read(id));
                    break;
                case CREATE_STAFF_MEMBER:
                    System.out.println("Give staff members data: ");
                    System.out.println("Name:");
                    String name = scanner.nextLine();
                    System.out.println("Surname");
                    String surname = scanner.nextLine();
                    System.out.println("Id:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Department:");
                    String department = scanner.nextLine();
                    StaffMember staffMember = new StaffMember(name, surname, id, department);
                    dao.create(staffMember);
                    dataCenter.loadStaffMembers();
                    break;
                case DELETE_STAFF_MEMBER:
                    System.out.println("Give staff member id");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    dao.delete(id);
                    dataCenter.loadStaffMembers();
                    break;
                case UPDATE_STAFF_MEMBER:
                    System.out.println("Give id of staff member, who will be updated:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Give updated staff members data: ");
                    System.out.println("Name:");
                    name = scanner.nextLine();
                    System.out.println("Surname");
                    surname = scanner.nextLine();
                    System.out.println("Id:");
                    int updatedId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Department:");
                    department = scanner.nextLine();
                    StaffMember updatedStaffMember = new StaffMember(name, surname, updatedId, department);
                    dao.update(id, updatedStaffMember);
                    dataCenter.loadStaffMembers();
                    break;
                case GIVE_ACCESS_TO_ZONE:
                    System.out.println("Choose zone to which you want to grant access:");
                    Arrays.stream(Zones.values()).forEach(zone -> System.out.printf("%d-%s\n", zone.ordinal(), zone.name()));
                    String zoneInd = scanner.nextLine();
                    Zones zone = Zones.getZonesByIndex(zoneInd);
                    System.out.println("Give id of staff member who access should be granted:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    acsSystemForDataCenter.giveAccess(id, zone);
                    break;
                case GIVE_ACCESS_TO_ROOM:
                    System.out.println("Choose room to which you want to grant access:");
                    Arrays.stream(Rooms.values()).forEach(room -> System.out.printf("%d-%s\n", room.ordinal(), room.name()));
                    String roomInd = scanner.nextLine();
                    Rooms room = Rooms.getRoomByIndex(roomInd);
                    System.out.println("Give id of staff member who access should be granted:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    acsSystemForDataCenter.giveAccessToRoom(id, room);
                    break;
                case CHECK_ACCESS:
                    System.out.println("Choose room to which access you want to check:");
                    Arrays.stream(Rooms.values()).forEach(room1 -> System.out.printf("%d-%s\n", room1.ordinal(), room1.name()));
                    roomInd = scanner.nextLine();
                    room = Rooms.getRoomByIndex(roomInd);
                    System.out.println("Give id of staff member who yoy want to check:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(acsSystemForDataCenter.checkAccess(id, room));
                    // acsSystemForDataCenter.checkAccess(3,Rooms.COR01);
                    break;
                case EXIT:
                    System.out.println("FINISHING PROGRAM......");
                    break;
                default:
                    System.err.println("Invalid option");
                    break;
            }

        } while (!UserOption.EXIT.equals(userOption));
        scanner.close();
    }
}
