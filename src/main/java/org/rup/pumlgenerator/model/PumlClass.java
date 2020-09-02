package org.rup.pumlgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PumlClass {

    private String name;
    private String type;
    private Set<String> attributes = new HashSet<>();
    private Set<String> relations = new HashSet<>();

    public PumlClass(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void addRelation(String relation) {
        relation = relation.replace("-right-", "--");
        relation = relation.replace("-left-", "--");
        relation = relation.replace("-up-", "--");
        relation = relation.replace("-down-", "--");
        relation = relation.replace(".right.", "..");
        relation = relation.replace(".left.", "..");
        relation = relation.replace(".up.", "..");
        relation = relation.replace(".down.", "..");
        relations.add(relation);
    }

    public void addAttribute(String attrubute) {
        if (attrubute.contains("new ")) {
            return;
        }
        attributes.add(attrubute.trim());
    }

}
