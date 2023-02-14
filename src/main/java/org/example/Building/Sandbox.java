package org.example.Building;

import org.example.Staff.StaffMember;
import org.example.Staff.StaffMembersDAO;

import java.util.List;

public class Sandbox {
    public static void main(String[] args) {
        //Building dc1 = new Building("Warszawa", "DC1");
        /*
        System.out.println(dc1.getRooms());
        dc1.printAllRoomsInBuilding();

        StaffMember staffMember = new StaffMember("Krz", "Gad", 555, "Technic");
        System.out.println(staffMember);

        StaffMembersDAO.checkIfExistAndCreateIfNot("C:\\Krzysztof\\IT_PROJECTS\\00_Kontrola_dostępu\\ACS_DC_Simulator\\StaffMembers.csv");
        String[] record = {"1", "Test", "!", "", ""};
        for (String string : record) {
            System.out.println(string);
        }
        StaffMembersDAO.writeStaffMemberToDataBase("C:\\Krzysztof\\IT_PROJECTS\\00_Kontrola_dostępu\\ACS_DC_Simulator\\StaffMembers.csv", record);
        List<String[]> list = StaffMembersDAO.readStaffMembers("C:\\Krzysztof\\IT_PROJECTS\\00_Kontrola_dostępu\\ACS_DC_Simulator\\StaffMembers.csv");

        list.stream().forEach(e -> System.out.println(e[0] + e[1]));
 */
        test();


    }

    public static void test(){
        Building dc1 = new Building("Warszawa", "DC1");
        StaffMembersDAO dao = new StaffMembersDAO("C:\\Krzysztof\\IT_PROJECTS\\00_Kontrola_dostępu\\ACS_DC_Simulator\\StaffMembers.csv");

        dao.create(new StaffMember("Gad","Krz", 1 , "Security Engineer"));
        dao.create(new StaffMember("Krz","Krz", 2 , "Security Lead"));
        dao.create(new StaffMember("Krz","Gad", 3 , "Electrical Engineer"));
        dao.create(new StaffMember("Krzysztof","Gad", 4 , "Electrical Engineer"));

        System.out.println(dao.read(3).get());
        System.out.println("odczyt z cratu");
        dao.readAll().stream().forEach(System.out::println);
        System.out.println("Test updatu i deletu");

        dao.delete(3);

        dao.update(1, new StaffMember("Krz","Krzysztof", 3 , "Security Lead"));
        dao.update(4, new StaffMember("Krzysztof","Gad", 5 , "Electrical Engineer"));
        dao.create(new StaffMember("Gad","Krz", 1 , "Security Engineer"));
 /*
        dao.readAll().stream().forEach(System.out::println);
*/
        System.out.println();











    }

}
