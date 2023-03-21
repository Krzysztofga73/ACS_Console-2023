package org.example.Staff;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StaffMembersDAO implements DAO<StaffMember> {

    private final String dataBaseFilePath;

    public StaffMembersDAO(String dataBaseFilePath) {
        this.dataBaseFilePath = dataBaseFilePath;
    }

    @Override
    public Boolean create(StaffMember newObj) {
        checkIfExistAndCreateIfNot(dataBaseFilePath);
        List<StaffMember> staffMembers = getStaffMembersFromOptional();
        for (StaffMember s : staffMembers) {
            if (s.getId().equals(newObj.id)) {
                System.out.println("Użytkownik o takim ID już istnieje!!!");
                return false;
            }
        }
        String[] record = newObj.toStringRecord();
        return writeStaffMemberToDataBase(this.dataBaseFilePath, record);
    }

    @Override
    public Optional<StaffMember> read(Integer id) {
        return recordToOptionalStaffMember(readStaffMember(dataBaseFilePath, id));
    }

    @Override
    public Optional<StaffMember> read(StaffMember objToRead) {
        return recordToOptionalStaffMember(readStaffMember(dataBaseFilePath, objToRead.getId()));
    }

    @Override
    public List<Optional<StaffMember>> readAll() {
        return readStaffMembers(this.dataBaseFilePath).stream().map(StaffMembersDAO::recordToOptionalStaffMember).toList();
    }

    @Override
    public Boolean update(Integer id, StaffMember updatedObj) {
        List<StaffMember> staffMembers = getStaffMembersFromOptional();
        boolean staffMemeberToDeleteIsInDataBase = false;
        for (StaffMember s : staffMembers) {
            if (s.getId() == id) {
                staffMemeberToDeleteIsInDataBase = true;
            }
        }
        if (staffMembers.contains(updatedObj)) {
            return false;
        } else if (staffMemeberToDeleteIsInDataBase == false) {
            return false;
        } else {
            delete(id);
            create(updatedObj);
            sortStaffMembersInDataBase();
            return true;
        }
    }


    @Override
    public Boolean delete(Integer id) {
        List<StaffMember> staffMembers = getStaffMembersFromOptional();
        boolean staffMemeberToDeleteIsInDataBase = false;
        for (StaffMember s : staffMembers) {
            if (s.getId() == id) {
                staffMemeberToDeleteIsInDataBase = true;
            }
        }
        if (staffMemeberToDeleteIsInDataBase == true) {
            return deleteStaffMemberFromDataBase(this.dataBaseFilePath, id) & sortStaffMembersInDataBase();
        }
        return false;
    }


    public static Boolean checkIfExistAndCreateIfNot(String dataBaseFilePath) {
        try {
            File myFile = new File(dataBaseFilePath);
            if (!myFile.exists()) {
                return myFile.createNewFile();
            } else {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String[]> readStaffMembers(String dataBaseFilePath) {
        try {
            FileReader reader = new FileReader(dataBaseFilePath);
            CSVReader readerCSV = new CSVReader(reader);
            return readerCSV.readAll();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static Optional<String[]> readStaffMember(String dataBaseFilePath, Integer id) {
        List<String[]> record = readStaffMembers(dataBaseFilePath);
        for (String[] strings : record) {
            if (strings[0].equals(Integer.toString(id))) {
                return Optional.of(strings);
            }
        }
        return Optional.empty();
    }

    public static Boolean writeStaffMemberToDataBase(String dataBaseFilePath, String[] record) {
        checkIfExistAndCreateIfNot(dataBaseFilePath);
        try {
            FileWriter writer = new FileWriter(dataBaseFilePath, true);
            CSVWriter writerCSV = new CSVWriter(writer);
            writerCSV.writeNext(record);
            writerCSV.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public static Optional<StaffMember> recordToOptionalStaffMember(Optional<String[]> record) {
        if (record.isPresent()) {
            String[] recordString = record.get();
            return Optional.of(new StaffMember(recordString));
        } else {
            return Optional.empty();
        }
    }

    public static Optional<StaffMember> recordToOptionalStaffMember(String[] record) {
        try {
            return Optional.of(new StaffMember(record));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public List<StaffMember> getStaffMembersFromOptional() {
        List<Optional<StaffMember>> staffMembers = readAll();
        List<StaffMember> staffMembersNotOptional = new ArrayList<>();
        for (Optional<StaffMember> staffMember : staffMembers) {
            staffMembersNotOptional.add(staffMember.get());
        }
        return staffMembersNotOptional;
    }


    public Boolean deleteStaffMemberFromDataBase(String dataBaseFilePath, Integer id) {
        List<String[]> record = readStaffMembers(dataBaseFilePath);
        for (int i = 0; i < record.size(); i++) {
            if (record.get(i)[0].equals(Integer.toString(id))) {
                record.remove(i);
            }
        }
        try {
            File myFile = new File(dataBaseFilePath);
            FileWriter writer = new FileWriter(dataBaseFilePath, false);
            CSVWriter writerCSV = new CSVWriter(writer);
            for (int i = 0; i < record.size(); i++) {
                writerCSV.writeNext(record.get(i));
            }
            writerCSV.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Boolean sortStaffMembersInDataBase() {
        List<StaffMember> staffMembers = getStaffMembersFromOptional();
        Collections.sort(staffMembers, (s1, s2) -> {
            return s2.getId() - s1.getId();
        });
        try {
            FileWriter writer = new FileWriter(this.dataBaseFilePath, false);
            CSVWriter writerCSV = new CSVWriter(writer);
            for (StaffMember s : staffMembers) {
                writerCSV.writeNext(s.toStringRecord());
            }
            writerCSV.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
