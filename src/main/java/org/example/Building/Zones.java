package org.example.Building;

import org.example.Application.UserOption;

public enum Zones {

    PUBLIC(1),
    SECURE(2),
    ONLY_OWNER(3),
    ONLY_TENENT(4),
    SECURE_TECHNICAL(5),
    VITAL_OWNER(6),
    VITAL_TENAT(7);

    private Integer securityLevel;

    Zones(Integer securityLevel) {
        this.securityLevel = securityLevel;
    }

    public static Zones getZonesByIndex(String index) {
        int idx = Integer.parseInt(index);
        Zones[] values = Zones.values();
        return values[idx];
    }
}
