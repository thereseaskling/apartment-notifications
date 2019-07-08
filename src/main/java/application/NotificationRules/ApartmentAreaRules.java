package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import application.ApartmentInfo.ApartmentArea;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ApartmentAreaRules implements Rules {

    final Map<ApartmentArea, SubAreaRules> areas;

    public ApartmentAreaRules(final Map<ApartmentArea, SubAreaRules> areas) {
        this.areas = areas;
    }

    public List<String> getMathingRecipients(final ApartmentAd apartmentAd){
        return Collections.emptyList();
    }

    public void addRule(final RuleInfo rule){
        rule.getAreas().forEach(area -> addRule(area, rule));
    }

    private void addRule(final ApartmentArea area, final RuleInfo rule) {
        if(areas.containsKey(area)){
            areas.get(area).addRule(rule);
        }
        else {
            createNewRule(area, rule);
        }
    }

    private void createNewRule(ApartmentArea area, final RuleInfo rule) {
        areas.put(area, new SubAreaRules(rule));
    }
}
