package com.ss.xmltest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author ChengXiao
 * @date 2021/4/21 16:09
 **/
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "Message")
public class DemoC implements Serializable {

    private Integer MessageID;

//    private DemoD ProcessingReport;
//
//    public Integer getMessageID() {
//        return MessageID;
//    }
//
//    public void setMessageID(Integer messageID) {
//        MessageID = messageID;
//    }
//
//    public DemoD getProcessingReport() {
//        return ProcessingReport;
//    }
//
//    public void setProcessingReport(DemoD processingReport) {
//        ProcessingReport = processingReport;
//    }
//
//    @Override
//    public String toString() {
//        return "DemoC{" +
//                "MessageID=" + MessageID +
//                ", ProcessingReport=" + ProcessingReport +
//                '}';
//    }
}
