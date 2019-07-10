package application.NotificationRules;

import application.ApartmentInfo.ApartmentArea;
import application.ApartmentInfo.SubArea;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;

public class RuleInfo {

  private List<ApartmentArea> areas;
  private List<SubArea> subAreas;
  private List<Integer> rooms;
  private Pair<Integer, Integer> sizeRange;
  private boolean student;
  private boolean senior;

  private final String email;

  public RuleInfo(final String email) {
    this.email = email;
    areas = new ArrayList<>();
    subAreas = new ArrayList<>();
    rooms = new ArrayList<>();
  }

  public List<ApartmentArea> getAreas() {
    return areas;
  }

  public void setAreas(final List<ApartmentArea> areas) {
    this.areas = areas;
  }

  public List<SubArea> getSubAreas() {
    return subAreas;
  }

  public void setSubAreas(final List<SubArea> subAreas) {
    this.subAreas = subAreas;
  }

  public List<Integer> getRooms() {
    return rooms;
  }

  public void setRooms(final List<Integer> rooms) {
    this.rooms = rooms;
  }

  public Optional<Pair<Integer, Integer>> getSizeRange() {
    return Optional.ofNullable(sizeRange);
  }

  public void setSizeRange(final Pair<Integer, Integer> sizeRange) {
    this.sizeRange = sizeRange;
  }

  public Optional<Boolean> isStudent() {
    return Optional.ofNullable(student);
  }

  public void setStudent(final boolean student) {
    this.student = student;
  }

  public Optional<Boolean> isSenior() {
    return Optional.ofNullable(senior);
  }

  public void setSenior(final boolean senior) {
    this.senior = senior;
  }

  public String getEmail() {
    return email;
  }
}
