package org.example.Staff;

import org.example.Building.Rooms;
import org.example.Building.Zones;

public interface AccessToZones {

    Boolean giveAccess(Integer id, Zones zone);
    Boolean checkAccess(Integer id, Rooms room);
    Boolean giveAccessToRoom(Integer id, Rooms room);

}
