package application.NotificationRules;

import application.ApartmentInfo.ApartmentArea;
import application.ApartmentInfo.SubArea;

import java.util.List;

public class RuleInfo {
    private List<ApartmentArea> areas;
    private List<SubArea> subAreas;
    private List<Integer> rooms;
    private List<Integer> size;
    private boolean student;
    private boolean senior;

    private final String email;

    public RuleInfo(List<ApartmentArea> areas, List<SubArea> subAreas, List<Integer> rooms, List<Integer> size, boolean student, boolean senior, String email) {
        this.areas = areas;
        this.subAreas = subAreas;
        this.rooms = rooms;
        this.size = size;
        this.student = student;
        this.senior = senior;
        this.email = email;
    }

    public List<ApartmentArea> getAreas() {
        return areas;
    }

    public List<SubArea> getSubAreas() {
        return subAreas;
    }

    public List<Integer> getRooms() {
        return rooms;
    }

    public List<Integer> getSize() {
        return size;
    }

    public boolean isStudent() {
        return student;
    }

    public boolean isSenior() {
        return senior;
    }
}
