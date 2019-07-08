package application.NotificationRules;

import application.ApartmentInfo.SubArea;

import java.util.HashMap;
import java.util.Map;

public class RoomRules implements Rules {

    private final Map<SubArea, SizeRules> roomRules;

    public RoomRules(final Map<SubArea, SizeRules> roomRules) {
        this.roomRules = roomRules;
    }

    public RoomRules(final RuleInfo ruleInfo){
        roomRules = new HashMap<>();
        addRule(ruleInfo);
    }

    @Override
    public void addRule(final RuleInfo ruleInfo) {

    }
}
