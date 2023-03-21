package org.example.Building;

public enum Rooms  {
    MMR01(10.6, Zones.VITAL_OWNER),
    IDF01(2.8, Zones.VITAL_OWNER ),
    COR01(15.8, Zones.SECURE),
    COR02(15.1, Zones.SECURE),
    TECH01(8.6, Zones.SECURE_TECHNICAL),
    DM01(30, Zones.VITAL_TENAT),
    SEC01(4, Zones.VITAL_OWNER),
    LOB01(5.5, Zones.PUBLIC),
    WC01(3.1, Zones.SECURE),
    STO01(8.6, Zones.ONLY_OWNER),
    STO02(4.5, Zones.ONLY_TENENT);




    private double area;
    private Zones securityLevel;

    Rooms(double area, Zones securityLevel){
        this.area = area;
        this.securityLevel = securityLevel;
    }

    public Zones getSecurityLevel() {
        return securityLevel;
    }

    @Override
    public String toString() {
        return "Rooms{"  + name() + ": " +
                "area=" + area +
                ", securityLevel=" + securityLevel +
                '}';
    }

}
