package org.example;

import org.glassfish.jersey.linking.InjectLink;

public class Link {
    private InjectLink.Style style;
    private String value;
    private String relation;
    private String title;
    private String condition;

    public static Link SelfLinkOnPrimary(String resourceName) {
        return new Link(
                InjectLink.Style.ABSOLUTE, "/" + resourceName + "/${instance.id}", "self", "self",
                "${instance.primaryId == 0}"
        );
    }

    public static Link SelfLinkOnSecondary(String primaryResourceName, String secondaryResourceName) {
        return new Link(
                InjectLink.Style.ABSOLUTE,
                "/" + primaryResourceName + "/${instance.primaryId}/" + secondaryResourceName + "/${instance.id}",
                "self", "self", "${instance.primaryId != 0}"
        );
    }

    public Link(InjectLink.Style style, String value, String relation, String title, String condition) {
        this.style = style;
        this.value = value;
        this.relation = relation;
        this.title = title;
        this.condition = condition;
    }

    public InjectLink.Style getStyle() {
        return style;
    }

    public void setStyle(InjectLink.Style style) {
        this.style = style;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
