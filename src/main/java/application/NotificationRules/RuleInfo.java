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
  private Integer sizeFrom;
  private Integer sizeTo;
  private Boolean student;
  private Boolean senior;
  private Boolean elevator;
  private Boolean balcony;
  private Boolean shortTime;
  private Boolean youth;
  private Boolean temporaryQueue;
  private Boolean newProduction;

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
    if (sizeFrom == null && sizeTo == null) {
      return Optional.empty();
    }
    if (sizeTo == null) {
      return Optional.of(Pair.of(sizeFrom, 150));
    }
    if (sizeFrom == null) {
      return Optional.of(Pair.of(1, sizeTo));
    }

    return Optional.of(Pair.of(sizeFrom, sizeTo));
  }

  public void setSizeRange(final Pair<Integer, Integer> sizeRange) {
    this.sizeFrom = sizeRange.getLeft();
    this.sizeTo = sizeRange.getRight();
  }

  public void setSizeFrom(final Integer sizeFrom) {
    this.sizeFrom = sizeFrom;
  }

  public void setSizeTo(final Integer sizeTo) {
    this.sizeTo = sizeTo;
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

  public Optional<Boolean> getShortTime() {
    return Optional.ofNullable(shortTime);
  }

  public void setShortTime(final Boolean shortTime) {
    this.shortTime = shortTime;
  }

  public Optional<Boolean> getYouth() {
    return Optional.ofNullable(youth);
  }

  public void setYouth(final Boolean youth) {
    this.youth = youth;
  }

  public Optional<Boolean> getTemporaryQueue() {
    return Optional.ofNullable(temporaryQueue);
  }

  public void setTemporaryQueue(final Boolean temporaryQueue) {
    this.temporaryQueue = temporaryQueue;
  }

  public Optional<Boolean> getNewProduction() {
    return Optional.ofNullable(newProduction);
  }

  public void setNewProduction(final Boolean newProduction) {
    this.newProduction = newProduction;
  }
}
