package com.ss.xmltest;

public class DemoA {

    private DemoB demoB;

    private String otherAttributes;

    public DemoB getDemoB() {
        return demoB;
    }

    public void setDemoB(DemoB demoB) {
        this.demoB = demoB;
    }

    public String getOtherAttributes() {
        return otherAttributes;
    }

    public void setOtherAttributes(String otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    @Override
    public String toString() {
        return "DemoA{" +
                "demoB=" + demoB +
                ", otherAttributes='" + otherAttributes + '\'' +
                '}';
    }
}
