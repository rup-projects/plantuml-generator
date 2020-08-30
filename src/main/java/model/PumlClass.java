package model;

import java.util.ArrayList;
import java.util.List;

public class PumlClass {
    String name;
    String type;
    List<String> attributes = new ArrayList<>();
    List<String> relations = new ArrayList<>();

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

    public List<String> getRelations() {
        return relations;
    }

    public void setRelations(List<String> relations) {
        this.relations = relations;
    }

    public void addRelation(String relation) {
        relations.add(relation);
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void addAttribute(String attrubute) {
        attributes.add(attrubute);
    }

}
