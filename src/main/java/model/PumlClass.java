package model;

import java.util.HashSet;
import java.util.Set;

public class PumlClass {
    String name;
    String type;
    Set<String> attributes = new HashSet<>();
    Set<String> relations = new HashSet<>();

    public PumlClass(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getRelations() {
        return relations;
    }

    public void setRelations(Set<String> relations) {
        this.relations = relations;
    }

    public void addRelation(String relation) {
        relations.add(relation);
    }

    public Set<String> getAttributes() {
        return attributes;
    }

    public void addAttribute(String attrubute) {
        if (attrubute.contains("new ")) {
            return;
        }
        attributes.add(attrubute.trim());
    }

}
