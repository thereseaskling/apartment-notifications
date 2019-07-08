package application.NotificationRules;

import application.ApartmentInfo.ApartmentAd;
import application.ApartmentInfo.SubArea;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubAreaRules implements Rules {

    private final Map<SubArea, RoomRules> subAreas;

    public SubAreaRules(final Map<SubArea, RoomRules> subAreas) {
        this.subAreas = subAreas;
    }

    SubAreaRules(final RuleInfo ruleInfo) {
        subAreas = new HashMap<>();
        addRule(ruleInfo);
    }

    public void addRule(final RuleInfo rule) {
        rule.getSubAreas().forEach(subArea -> addRule(subArea, rule));
    }

    private void addRule(final SubArea subArea, final RuleInfo rule) {
        if(subAreas.containsKey(subArea)){
            subAreas.get(subArea).addRule(rule);
        }
        else {
            subAreas.put(subArea, new RoomRules(rule));
        }
    }

    public List<String> getMathingRecipients(final ApartmentAd apartmentAd){
        return Collections.emptyList();
    }



}
