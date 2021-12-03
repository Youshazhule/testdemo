//package com.ss.jmail.demo6;
//
//import com.sun.mail.imap.IMAPFolder;
//import com.sun.mail.imap.IMAPStore;
//
//import javax.mail.*;
//import java.io.IOException;
//import java.util.Properties;
//
///**
// * @author ChengXiao
// * @date 2021/11/17 11:22
// **/
//public class Email3 {
//
//    public static void main(String[] args) throws Exception {
//        String user = "hcx313318@163.com";// 邮箱的用户名
//        String password = "JHOVGHVURUENNTXV"; // 邮箱的密码
//
//        Properties prop = System.getProperties();
//        prop.put("mail.store.protocol", "imap");
//        prop.put("mail.imap.host", "imap.163.com");
//
//        Session session = Session.getInstance(prop);
//
//        int total = 0;
//        IMAPStore store = (IMAPStore) session.getStore("imap"); // 使用imap会话机制，连接服务器
//        store.connect(user, password);
//        IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱
//        folder.open(Folder.READ_WRITE);
//        // 获取总邮件数
//        total = folder.getMessageCount();
//        System.out.println("-----------------共有邮件：" + total
//                + " 封--------------");
//        // 得到收件箱文件夹信息，获取邮件列表
//        System.out.println("未读邮件数：" + folder.getUnreadMessageCount());
//        Message[] messages = folder.getMessages();
//        int messageNumber = 0;
//        for (Message message : messages) {
//            System.out.println("发送时间：" + message.getSentDate());
//            System.out.println("主题：" + message.getSubject());
//            System.out.println("内容：" + message.getContent());
//            Flags flags = message.getFlags();
//            if (flags.contains(Flags.Flag.SEEN))
//                System.out.println("这是一封已读邮件");
//            else {
//                System.out.println("未读邮件");
//            }
//            System.out.println("========================================================");
//            System.out.println("========================================================");
//            //每封邮件都有一个MessageNumber，可以通过邮件的MessageNumber在收件箱里面取得该邮件
//            messageNumber = message.getMessageNumber();
//        }
//        Message message = folder.getMessage(messageNumber);
//        System.out.println(message.getContent()+message.getContentType());
//        // 释放资源
//        if (folder != null)
//            folder.close(true);
//        if (store != null)
//            store.close();
//    }
//}
