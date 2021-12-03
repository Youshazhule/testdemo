package com.ss.xmltest;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author ChengXiao
 * @date 2021/4/21 16:30
 **/
public class Result {

    private Integer MessageID;
    private String ResultCode;
    private Integer ResultMessageCode;
    private String ResultDescription;

    @XmlAttribute(name="MessageID")
    public Integer getMessageID() {
        return MessageID;
    }

    public void setMessageID(Integer messageID) {
        MessageID = messageID;
    }

    @XmlAttribute(name="ResultCode")
    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String resultCode) {
        ResultCode = resultCode;
    }

    @XmlAttribute(name="ResultMessageCode")
    public Integer getResultMessageCode() {
        return ResultMessageCode;
    }

    public void setResultMessageCode(Integer resultMessageCode) {
        ResultMessageCode = resultMessageCode;
    }

    @XmlAttribute(name="ResultDescription")
    public String getResultDescription() {
        return ResultDescription;
    }

    public void setResultDescription(String resultDescription) {
        ResultDescription = resultDescription;
    }

    @Override
    public String toString() {
        return "Result{" +
                "MessageID=" + MessageID +
                ", ResultCode='" + ResultCode + '\'' +
                ", ResultMessageCode=" + ResultMessageCode +
                ", ResultDescription='" + ResultDescription + '\'' +
                '}';
    }

    public Result() {
    }
}
