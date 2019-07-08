package application.ApartmentInfo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public enum ApartmentArea {
    STOCKHOLM("Stockholm", SubArea.HOGDALEN, SubArea.SODERMALM, SubArea.RAGSVED, SubArea.MIDSOMMARKRANSEN,
            SubArea.FRUANGEN, SubArea.HOKARANGEN, SubArea.TENSTA, SubArea.KISTA, SubArea.RINKEBY, SubArea.FARSTA_STRAND,
            SubArea.LILJEHOLMEN, SubArea.BREDANG, SubArea.HOGDALEN, SubArea.GUBBANGEN, SubArea.SKONDAL, SubArea.HAGERSTEN,
            SubArea.STORA_ESSINGEN, SubArea.VASTBERGA, SubArea.VASASTADEN, SubArea.KUNGSHOLMEN, SubArea.TALLKROGEN,
            SubArea.NORRA_DJURGARDEN, SubArea.BAGARMOSSEN, SubArea.STADSHAGEN, SubArea.FARSTA, SubArea.HAGSATRA,
            SubArea.SKARPNACKS_GARD, SubArea.ENSKEDE_GARD, SubArea.LILLA_ESSINGEN),
    HUDDINGE("Huddinge", SubArea.FLEMINGSBERG, SubArea.VARBY, SubArea.SKOGAS, SubArea.SJODALEN_FULLERSTA),
    SUNDBYBERG("Sundbyberg", SubArea.URSVIK),
    VALLENTUNA("Vallentuna", SubArea.VALLENTUNA_C),
    BOTKYRKA("Botkyrka", SubArea.BOTKYRKA, SubArea.NORSBORG),
    SODERTALGE("Södertälje", SubArea.BLOMBACKA),
    SIGTUNA("Sigtuna", SubArea.MARSTA),
    SOLNA("Solna", SubArea.ULRIKSDAL, SubArea.RASUNDA),
    LIDINGO("Lidingö", SubArea.LARSBERG),
    UPPLANDS_VASBY("Upplands väsby", SubArea.UPPLANDS_VASBY),
    NACKA("Nacka", SubArea.ALPHYDDAN),
    VARMDO("Varmdö", SubArea.HASTHAGEN);

    private final String areaValue;
    private final List<SubArea> subareas;

    ApartmentArea(final String areaValue, final SubArea... subareas) {
        this.areaValue = areaValue;
        this.subareas = Arrays.asList(subareas);
    }

    public String getAreaValue() {
        return areaValue;
    }

    public static Optional<ApartmentArea> getApartmentArea(final String area) {
        return Arrays.stream(ApartmentArea.values())
                .filter(apartmentArea -> apartmentArea.areaValue.equals(area)).findAny();
    }

    public List<ApartmentArea> getAllApartmentAreas() {
        return Arrays.asList(ApartmentArea.values());
    }
}
