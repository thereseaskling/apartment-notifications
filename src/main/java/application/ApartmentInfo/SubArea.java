package application.ApartmentInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum SubArea {
  SODERMALM("Södermalm"),
  RAGSVED("Rågsved"),
  MIDSOMMARKRANSEN("Midsommarkransen"),
  FRUANGEN("Fruängen"),
  HOKARANGEN("Hökarängen"),
  URSVIK("Ursvik"),
  VALLENTUNA_C("Vallentuna C"),
  FLEMINGSBERG("Flemingsberg"),
  TENSTA("Tensta"),
  ULRIKSDAL("Ulriksdal"),
  LARSBERG("Larsberg"),
  KISTA("Kista"),
  RINKEBY("Rinkeby"),
  FARSTA("Farsta"),
  FARSTA_STRAND("Farsta Strand"),
  LILJEHOLMEN("Liljeholmen"),
  BREDANG("Bredäng"),
  RASUNDA("Råsunda"),
  HOGDALEN("Högdalen"),
  GUBBANGEN("Gubbängen"),
  SKONDAL("Sköndal"),
  HAGERSTEN("Hägersten"),
  ALPHYDDAN("Alphyddan"),
  STORA_ESSINGEN("Stora Essingen"),
  VARBY("Vårby"),
  VASTBERGA("Västberga"),
  UPPLANDS_VASBY("Upplands Väsby"),
  TALLKROGEN("Tallkrogen"),
  NORRA_DJURGARDEN("Norra Djurgården"),
  HASTHAGEN("Hästhagen"),
  BAGARMOSSEN("Bagarmossen"),
  BOTKYRKA("Botkyrka"),
  MARSTA("Märsta"),
  NORSBORG("Norsborg"),
  STADSHAGEN("Stadshagen"),
  VASASTADEN("Vasastaden"),
  HAGSATRA("Hagsätra"),
  SKARPNACKS_GARD("Skarpnäcks Gård"),
  SKOGAS("Skogås"),
  SJODALEN_FULLERSTA("Sjödalen-Fullersta"),
  KUNGSHOLMEN("Kungsholmen"),
  BLOMBACKA("Blombacka"),
  ENSKEDE_GARD("Enskede Gård"),
  LILLA_ESSINGEN("Lilla Essingen"),
  OSTERMALM("Östermalm"),
  GRIMSTA("Grimsta"),
  HJORTHAGEN("Hjorthagen"),
  HASSELBY_GARD("Hässelby Gård"),
  KARRTORP("Kärrtorp"),
  MARIEHALL("Mariehäll"),
  RACKSTA("Råcksta"),
  SKARHOLMEN("Skärholmen"),
  SVEDMYRA("Svedmyra"),
  SODRA_HAMMARBYHAMNEN("Södra Hammarbyhamnen"),
  ULVSUNDA_INDUSTRIOMRADE("Ulvsunda Industriområde"),
  VASTERTORP("Västertorp"),
  ARSTA("Årsta"),
  BECKOMBERGA("Beckomberga"),
  BLACKEBERG("Blackeberg"),
  LANGBRO("Långbro"),
  GRONDAL("Gröndal"),
  STUREBY("Stureby"),
  OSTBERGA("Östberga"),
  HAGERSTENSASEN("Hägerstensåsen"),
  ALVSJO("Älvjö"),
  ASPUDDEN("Aspudden"),
  AXELSBERG("Axelsberg"),
  ORNSBERG("Örnsberg"),
  EMPTY("empty");

  private final String areaValue;

  SubArea(final String areaValue) {
    this.areaValue = areaValue;
  }

  String getAreaValue() {
    return areaValue;
  }

  public static Optional<SubArea> getApartmentArea(final String area) {
    return Arrays.stream(SubArea.values())
        .filter(apartmentArea -> apartmentArea.areaValue.equalsIgnoreCase(area)).findAny();
  }

  public List<SubArea> getAllApartmentAreas() {
    return Arrays.asList(SubArea.values());
  }
}
