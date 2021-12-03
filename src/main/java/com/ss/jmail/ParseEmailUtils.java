//package com.ss.jmail;
//
//
//import javax.mail.*;
//import javax.mail.internet.MimeMessage;
//import javax.mail.search.SearchTerm;
//import javax.mail.search.SubjectTerm;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//import java.util.TreeSet;
//
///**
// * 使用POP3协议解析邮件工具类
// *
// * @author bytearr.
// */
//public class ParseEmailUtils {
//
//    public static void main(String[] args) {
//
//    }
//
//    /**
//     * 获取邮箱信息
//     *
//     * @param projectName 要解析的主题名称
//     * @throws Exception
//     */
//    public String receive(String projectName) throws Exception {
//        Store store = null;
//        Folder folder = null;
//        try {
//            // 准备连接服务器的会话信息
//            Properties props = new Properties();
//            props.setProperty("mail.store.protocol", "pop3");  // 使用pop3协议
//            props.setProperty("mail.pop3.port", "110");           // 端口
//            props.setProperty("mail.pop3.host", "pop.163.com");    // pop3服务器
//
//            // 创建Session实例对象
//            Session session = Session.getInstance(props);
//            store = session.getStore("pop3");
//            store.connect("1095096098@qq.com", "cnjdcldganryjjdg");
//
//            // 获得收件箱
//            folder = store.getFolder("INBOX");
//            folder.open(Folder.READ_ONLY); //打开收件箱
//
//            SearchTerm st = new SubjectTerm(projectName);
//            Message[] messages = folder.search(st);
//
//            //解析邮件
//            if (messages == null || messages.length < 1) {
//                return "未找到要解析的邮件";
//            }
//
//            //获取此类邮件的发送时间
//            String last = getTime(messages);
//            MimeMessage newMsg = getNewMsg(last,messages);
//
//            StringBuffer content = new StringBuffer(30);
//            getMailTextContent(newMsg, content);
//
//            return "解析邮件成功";
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return e.getMessage();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return e.getMessage();
//        } finally {
//            //释放资源
//            folder.close(true);
//            store.close();
//        }
//    }
//
//    /**
//     * 获得邮件文本内容
//     *
//     * @param part    邮件体
//     * @param content 存储邮件文本内容的字符串
//     * @throws MessagingException
//     * @throws IOException
//     */
//    public void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
//        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
//        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
//        if (part.isMimeType("text/*") && !isContainTextAttach) {
//            content.append(part.getContent().toString());
//        } else if (part.isMimeType("message/rfc822")) {
//            getMailTextContent((Part) part.getContent(), content);
//        } else if (part.isMimeType("multipart/*")) {
//            Multipart multipart = (Multipart) part.getContent();
//            int partCount = multipart.getCount();
//            for (int i = 0; i < partCount; i++) {
//                BodyPart bodyPart = multipart.getBodyPart(i);
//                getMailTextContent(bodyPart, content);
//            }
//        }
//    }
//
//    /**
//     * 获得邮件发送时间
//     *
//     * @param msg 邮件内容
//     * @return yyyy年mm月dd日 星期X HH:mm
//     * @throws MessagingException
//     */
//    public String getSentDate(MimeMessage msg, String pattern) throws MessagingException {
//        Date receivedDate = msg.getSentDate();
//        if (receivedDate == null)
//            return "";
//        if (pattern == null || "".equals(pattern))
//            pattern = "yyyy-MM-dd HH:mm:ss";
//
//        return new SimpleDateFormat(pattern).format(receivedDate);
//    }
//
//    //从一堆邮件中获取最近的邮件的时间
//    public String getTime(Message[] messages) throws MessagingException {
//        //利用TreeSet自动排序,获取最新时间
//        //可以考虑使用jdk8新特性优化
//        TreeSet<String> treeSet = new TreeSet();
//        MimeMessage msg;
//        String sentDate;
//        for (int i = 0, count = messages.length; i < count; i++) {
//            msg = (MimeMessage) messages[i];
//            sentDate = getSentDate(msg, null);
//            treeSet.add(sentDate);
//        }
//        return treeSet.last();
//    }
//
//    /**
//     * 获取最新时间的邮件
//     * @param messages
//     * @return
//     */
//    public MimeMessage getNewMsg(String last,Message[] messages) throws MessagingException {
//
//        MimeMessage msg = null;
//        for (Message message : messages) {
//            MimeMessage mimeMessage = (MimeMessage) message;
//            if(getSentDate(mimeMessage,null).equals(last)){
//                msg = mimeMessage;
//            }
//        }
//        return msg;
//    }
//
//}