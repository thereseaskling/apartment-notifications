package application.NotificationRules;

import application.ApartmentInfo.ApartmentArea;
import application.ApartmentInfo.SubArea;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;

public class RuleInfo implements Serializable {

  private List<ApartmentArea> areas;
  private List<SubArea> subAreas;
  private List<Integer> rooms;
  private Pair<Integer, Integer> sizeRange;
  private Boolean student;
  private Boolean senior;
  private Boolean elevator;
  private Boolean balcony;

  private final String email;

  public RuleInfo(final String email) {
    this.email = email;
    areas = new ArrayList<>();
    subAreas = new ArrayList<>();
    rooms = new ArrayList<>();
  }

  public RuleInfo(final List<ApartmentArea> areas, final List<SubArea> subAreas, final List<Integer> rooms,
      final Pair<Integer, Integer> sizeRange, final String email) {
    this.areas = areas;
    this.subAreas = subAreas;
    this.rooms = rooms;
    this.sizeRange = sizeRange;
    this.email = email;
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

  public Optional<Boolean> isElevator() {
    return Optional.ofNullable(elevator);
  }

  public void setElevator(final boolean elevator) {
    this.elevator = elevator;
  }

  public Optional<Boolean> isBalcony() {
    return Optional.ofNullable(balcony);
  }

  public void setBalcony(final boolean balcony) {
    this.balcony = balcony;
  }
}
