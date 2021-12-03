package com.ss.jmail.demo2;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sun.mail.pop3.POP3Folder;
import org.apache.commons.io.IOUtils;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jsoup.nodes.Element;
import org.springframework.util.ObjectUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 测试使用授权码方式拉取邮件信息
 */
public class ShowMail {

    public static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private MimeMessage mimeMessage;
    /**
     * 附件下载后的存放目录
     */
    private String saveAttachPath = "";
    /**
     * 存放邮件内容的StringBuffer对象
     */
    private StringBuffer bodyText = new StringBuffer();

    /**
     * 构造函数,初始化一个MimeMessage对象
     *
     * @param mimeMessage
     */
    public ShowMail(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    /**
     * 获得发件人的地址和姓名
     *
     * @return
     * @throws MessagingException
     */
    public String getFrom() throws MessagingException {
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();
        String from = address[0].getAddress();
        if (from == null) {
            from = "";
        }
        String personal = address[0].getPersonal();

        if (personal == null) {
            personal = "";
        }

        String fromAddr = null;
        if (personal != null || from != null) {
            fromAddr = personal + "<" + from + ">";
        }
        return fromAddr;
    }

    /**
     * 获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同
     *
     * @param type "to"----收件人　"cc"---抄送人地址　"bcc"---密送人地址
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public String getMailAddress(String type) throws MessagingException, UnsupportedEncodingException {
        if (ObjectUtils.isEmpty(type)) {
            return "";
        }

        String addType = type.toUpperCase();

        if (!addType.equals("TO") && !addType.equals("CC") && !addType.equals("BCC")) {
            return "";
        }
        InternetAddress[] address;

        if (addType.equals("TO")) {
            address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO);
        } else if (addType.equals("CC")) {
            address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC);
        } else {
            address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.BCC);
        }

        if (ObjectUtils.isEmpty(address)) {
            return "";
        }
        StringBuilder mailAddr = new StringBuilder();
        String emailAddr;
        String personal;
        for (int i = 0; i < address.length; i++) {
            emailAddr = address[i].getAddress();
            if (emailAddr == null) {
                emailAddr = "";
            } else {
                emailAddr = MimeUtility.decodeText(emailAddr);
            }
            personal = address[i].getPersonal();
            if (personal == null) {
                personal = "";
            } else {
                personal = MimeUtility.decodeText(personal);
            }
            mailAddr.append(",").append(personal).append("<").append(emailAddr).append(">");
        }
        return mailAddr.toString().substring(1);
    }

    /**
     * 获得邮件主题
     *
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public String getSubject() throws MessagingException, UnsupportedEncodingException {
        String subject = MimeUtility.decodeText(mimeMessage.getSubject());
        if (subject == null) {
            subject = "";
        }
        return subject;
    }

    /**
     * 获得邮件发送日期
     *
     * @return
     * @throws MessagingException
     */
    public String getSentDate() throws MessagingException {
        Date sentDate = mimeMessage.getSentDate();
        SimpleDateFormat format = new SimpleDateFormat(NORM_DATETIME_PATTERN);
        return format.format(sentDate);
    }

    /**
     * 获得邮件正文内容
     *
     * @return
     */
    public String getBodyText() {
        return bodyText.toString();
    }

    /**
     * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件
     * 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
     *
     * @param part
     * @throws MessagingException
     * @throws IOException
     */
    public void getMailContent(Part part) throws MessagingException, IOException {

        String contentType = part.getContentType();

        int nameIndex = contentType.indexOf("name");

        boolean conName = false;

        if (nameIndex != -1) {
            conName = true;
        }

        if (part.isMimeType("text/plain") && conName == false) {
            bodyText.append((String) part.getContent());
        } else if (part.isMimeType("text/html") && conName == false) {
            bodyText.append((String) part.getContent());
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                this.getMailContent(multipart.getBodyPart(i));
            }
        } else if (part.isMimeType("message/rfc822")) {
            this.getMailContent((Part) part.getContent());
        }
    }

    /**
     * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"
     *
     * @return
     * @throws MessagingException
     */
    public boolean getReplySign() throws MessagingException {

        boolean replySign = false;

        String needReply[] = mimeMessage.getHeader("Disposition-Notification-To");

        if (needReply != null) {
            replySign = true;
        }
        return replySign;
    }

    /**
     * 判断此邮件是否已读，如果未读返回false,反之返回true
     *
     * @return
     * @throws MessagingException
     */
    public boolean isNew() throws MessagingException {
        boolean isNew = false;
        Flags flags = mimeMessage.getFlags();
        Flags.Flag[] flag = flags.getSystemFlags();
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == Flags.Flag.SEEN) {
                isNew = true;
            }
        }
        return isNew;
    }

    /**
     * 判断此邮件是否包含附件
     *
     * @param part
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public boolean isContainAttach(Part part) throws MessagingException, IOException {
        boolean attachFlag = false;
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            BodyPart mPart;
            String conType;
            for (int i = 0; i < mp.getCount(); i++) {
                mPart = mp.getBodyPart(i);
                String disposition = mPart.getDisposition();
                if (Part.ATTACHMENT.equals(disposition) || Part.INLINE.equals(disposition)) {
                    attachFlag = true;
                } else if (mPart.isMimeType("multipart/*")) {
                    attachFlag = this.isContainAttach(mPart);
                } else {
                    conType = mPart.getContentType();
                    if (conType.toLowerCase().indexOf("application") != -1 || conType.toLowerCase().indexOf("name") != -1) {
                        attachFlag = true;
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            attachFlag = isContainAttach((Part) part.getContent());
        }
        return attachFlag;
    }

    /**
     * 保存附件
     *
     * @param part
     * @throws MessagingException
     * @throws IOException
     */
    /*public void saveAttachMent(Part part) throws MessagingException, IOException {
        String fileName;
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();// 复杂体邮件
            BodyPart mPart;
            int partCount = mp.getCount();
            for (int i = 0; i < partCount; i++) {
                // 获得复杂体邮件中其中一个邮件体
                mPart = mp.getBodyPart(i);
                // 某一个邮件体也有可能是由多个邮件体组成的复杂体
                String disposition = mPart.getDisposition();
                if (disposition != null && (Part.ATTACHMENT.equals(disposition) || Part.INLINE.equals(disposition))) {
                    fileName = mPart.getFileName();
                    if (null != fileName && fileName.toLowerCase().indexOf("gb2312") != -1) {
                        fileName = MimeUtility.decodeText(fileName);
                    }
                    this.saveFile(fileName, mPart.getInputStream());
                } else if (mPart.isMimeType("multipart/*")) {
                    this.saveAttachMent(mPart);
                } else {
                    fileName = mPart.getFileName();
                    if ((fileName != null) && (fileName.toLowerCase().indexOf("GB2312") != -1)) {
                        fileName = MimeUtility.decodeText(fileName);
                        this.saveFile(fileName, mPart.getInputStream());
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            this.saveAttachMent((Part) part.getContent());
        }
    }*/
    public static void saveAttachment(Part part, String destDir) throws UnsupportedEncodingException, MessagingException,
            FileNotFoundException, IOException {
        if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();    //复杂体邮件
            //复杂体邮件包含多个邮件体
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                //获得复杂体邮件中其中一个邮件体
                BodyPart bodyPart = multipart.getBodyPart(i);
                //某一个邮件体也有可能是由多个邮件体组成的复杂体
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    InputStream is = bodyPart.getInputStream();
                    saveFile(is, destDir, decodeText(bodyPart.getFileName()));
                } else if (bodyPart.isMimeType("multipart/*")) {
                    saveAttachment(bodyPart,destDir);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
                        saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()));
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachment((Part) part.getContent(),destDir);
        }
    }

    /**
     * 设置附件存放路径
     *
     * @param attachPath
     */
    public void setAttachPath(String attachPath) {
        this.saveAttachPath = attachPath;
    }

    /**
     * 获得附件存放路径
     *
     * @return
     */
    public String getAttachPath() {
        return saveAttachPath;
    }

    /**
     * 真正的保存附件到指定目录里
     *
     * @param fileName
     * @param in
     * @throws IOException
     */
    /*private void saveFile(String fileName, InputStream in) throws IOException {
        String suffix = getFileExtension(fileName);
        fileName = System.currentTimeMillis() + "." + suffix;
//        String osName = System.getProperty("os.name");
//        String storeDir = this.getAttachPath();
//        if (null == osName) {
//            osName = "";
//        }
//        if (osName.toLowerCase().indexOf("win") != -1) {
//            if (ObjectUtils.isEmpty(storeDir))
//                storeDir = "C:\\tmp";
//        } else {
//            storeDir = "/tmp";
//        }
//        FileOutputStream fos = new FileOutputStream(new File(storeDir + File.separator + fileName));
//
//        IOUtils.copy(in, fos);
//        IOUtils.closeQuietly(fos);
//        IOUtils.closeQuietly(in);
        downloadFile(fileName, "D:\\\\Download\\\\mailFile\\\\", in);
    }*/
    /**
     * 读取输入流中的数据保存至指定目录
     * @param is 输入流
     * @param fileName 文件名
     * @param destDir 文件存储目录
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void saveFile(InputStream is, String destDir, String fileName)
            throws FileNotFoundException, IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(new File(destDir + fileName)));
        int len = -1;
        while ((len = bis.read()) != -1) {
            bos.write(len);
            bos.flush();
        }
        bos.close();
        bis.close();
    }

    public String downloadFile(String fileName, String dir, InputStream inputStream) {
        String fileDir = "";
        try {
            File dirs = new File(dir);
            File file = new File(dir + fileName);
            if (!dirs.isDirectory()) {
                dirs.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fileDir = dir + fileName;

            writeToLocal(fileDir, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileDir;

    }

    private void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

    /**
     * 根据文件名取得文件扩展名
     *
     * @param fileName
     * @return
     */
    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        int lastPosDot = -1;
        String ret = null;
        if ((lastPosDot = fileName.lastIndexOf(".")) > 0) {
            ret = fileName.substring(lastPosDot + 1, lastPosDot + 4);
        }
        return ret;
    }

    /**
     * 获取阿里云邮箱信息
     *
     * @param host     邮件服务器
     * @param username 邮箱名
     * @param password 密码
     * @param protocol 协议
     * @return
     * @throws MessagingException
     */
    public static Message[] getALiYunMessage(String host, String username, String password, String protocol) throws MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        Store store = session.getStore(protocol);
        store.connect(host, username, password);

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
        return folder.getMessages();
    }

    /**
     * 获取163邮箱信息
     *
     * @param host
     * @param username
     * @param password
     * @param protocol
     * @return
     * @throws MessagingException
     */
    public static Message[] getWEMessage(String host, String username, String password, String protocol) throws MessagingException {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", protocol);
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore(protocol);
        store.connect(host, username, password);
        Folder folder = store.getFolder("INBOX");
        SearchTerm sender = new FromTerm(new InternetAddress("marketplace.amazon.com"));


        if (folder instanceof IMAPFolder) {
            IMAPFolder imapFolder = (IMAPFolder) folder;
            //javamail中使用id命令有校验checkOpened, 所以要去掉id方法中的checkOpened();
            imapFolder.doCommand(new IMAPFolder.ProtocolCommand() {
                public Object doCommand(IMAPProtocol p) throws com.sun.mail.iap.ProtocolException {
                    p.id("FUTONG");
                    return null;
                }
            });
        }

        if (folder != null) {
            folder.open(Folder.READ_WRITE);
        }

        return folder.search(sender);
//        return folder.getMessages();
    }

    /**
     * 过滤邮箱信息
     *
     * @param messages
     * @param fromMail  只读取该邮箱发来的邮件，如果为空则不过滤
     * @param startDate 只读取该日期以后的邮件，如果为空则不过滤
     * @return
     * @throws MessagingException
     */
    public static List<Message> filterMessage(Message[] messages, String fromMail, String startDate) throws MessagingException, ParseException {
        List<Message> messageList = new ArrayList<>();
        if (ObjectUtils.isEmpty(messages)) {
            return messageList;
        }
        boolean isEnptyFromMail = ObjectUtils.isEmpty(fromMail);
        boolean isEnptyStartDate = ObjectUtils.isEmpty(startDate);
        if (isEnptyFromMail && isEnptyStartDate) {
            return Arrays.asList(messages);
        }

        String from;
        for (Message message : messages) {
            from = (message.getFrom()[0]).toString();
            if (isEnptyFromMail) {
                if (new SimpleDateFormat(NORM_DATETIME_PATTERN).parse(startDate).getTime() > message.getSentDate().getTime()) {
                    continue;
                }
            } else if (null != from && from.contains(fromMail)) {
                if (!isEnptyStartDate && new SimpleDateFormat(NORM_DATETIME_PATTERN).parse(startDate).getTime() > message.getSentDate().getTime()) {
                    continue;
                }
            } else {
                continue;
            }
            messageList.add(message);
        }
        return messageList;
    }

    // jssonp解析固定格式html
    public static String parseBodyText(String bodyText) throws NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException {
        String xpath = "//div[@class='mmsgLetterContent']/allText()";
        JXDocument jxDocument = new JXDocument(bodyText);
        List<Object> rs = jxDocument.sel(xpath);
        StringBuilder bodyContent = new StringBuilder();
        for (Object o : rs) {
            if (o instanceof Element) {
                int index = ((Element) o).siblingIndex();
            }
            bodyContent.append(o.toString());
        }
        return bodyContent.append("\r\n").toString();
    }


    /**
     * 文本解码
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本
     * @return 解码后的文本
     * @throws UnsupportedEncodingException
     */
    public static String decodeText(String encodeText) throws UnsupportedEncodingException {
        if (encodeText == null || "".equals(encodeText)) {
            return "";
        } else {
            return MimeUtility.decodeText(encodeText);
        }
    }

    /**
     * 打印邮件
     *
     * @param messageList
     * @throws IOException
     * @throws MessagingException
     */
    public static void printMailMessage(List<Message> messageList) throws Exception {
        System.out.println("邮件数量:" + messageList.size());
        ShowMail re;
        Message message;
        int messageNumber;
        for (int i = 0, leng = messageList.size(); i < leng; i++) {
            message = messageList.get(i);
            messageNumber = message.getMessageNumber();

            System.out.println("邮件ID：" + messageNumber);
            re = new ShowMail((MimeMessage) message);

            System.out.println("邮件【" + i + "】主题:" + re.getSubject());
            System.out.println("邮件【" + i + "】发送时间:" + re.getSentDate());
            System.out.println("邮件【" + i + "】是否需要回复:" + re.getReplySign());
            boolean oldIsNew = re.isNew();
            System.out.println("邮件【" + i + "】是否已读:" + re.isNew());
            System.out.println("邮件【" + i + "】是否包含附件:" + re.isContainAttach(message));
            System.out.println("邮件【" + i + "】发送人地址:" + re.getFrom());
            System.out.println("邮件【" + i + "】收信人地址:" + re.getMailAddress("to"));
            System.out.println("邮件【" + i + "】抄送:" + re.getMailAddress("cc"));
            System.out.println("邮件【" + i + "】暗抄:" + re.getMailAddress("bcc"));
            System.out.println("邮件【" + i + "】发送时间:" + re.getSentDate());
            System.out.println("邮件【" + i + "】邮件ID:" + ((MimeMessage) message).getMessageID());
            re.getMailContent(message);// 这一步会改变已读状态
            System.out.println("邮件【" + i + "】再次确认是否已读:" + re.isNew());
            System.out.println("邮件【" + i + "】正文内容解析前:\r\n" + re.getBodyText());
            System.out.println("邮件【" + i + "】正文内容解析后:\r\n" + parseBodyText(re.getBodyText()));
            if (!oldIsNew) {
                System.out.println("有未读的");
                message.setFlag(Flags.Flag.SEEN, false);
            }
//            re.setAttachPath("D:\\Download\\mailFile\\");
//            if (re.isContainAttach(message)) {
//                saveAttachment(message, "D:\\Download\\mailFile\\");
//            }
        }

    }

    public static void main(String[] args) throws Exception {
        // 阿里云登录信息
//        String host = "imap.mxhichina.com";
//        String username = "huangcx@suncent.net";
//        String password = "ZC313318.";
//        String protocol = "imaps";
////        String fromMail = "XXX@163.com";
//        String startDate = "2020-2-24 23:35:40";
//        getUid(host, username, password, protocol);
//        Message[] aLiYunMessage = getALiYunMessage(host, username, password, protocol);
//        List<Message> messageList = filterMessage(aLiYunMessage, null, startDate);

//        // 163登录信息
//        String ihost = "imap.163.com";
//        String iusername = "wulianb97308@163.com";
//        String ipassword = "tiany31398";
//        String iprotocol = "imaps";
////        String ifromMail = "liwei@xiaostudy.com";// 只读取该邮箱发来的邮件，如果为空则不过滤
////        String istartDate = "2020-2-24 23:35:40";
//        Message[] weMessage = getWEMessage(ihost, iusername, ipassword, iprotocol);
//        List<Message> messageList = filterMessage(weMessage, null, null);

        // outlook登录信息
//        String ihost = "imap.outlook.com";
//        String iusername = "jack_caitj@outlook.com";
//        String ipassword = "ZC313318.....";
//        String iprotocol = "imaps";
//        String ifromMail = "liwei@xiaostudy.com";// 只读取该邮箱发来的邮件，如果为空则不过滤
//        String istartDate = "2020-2-24 23:35:40";
//        Message[] weMessage = getWEMessage(ihost, iusername, ipassword, iprotocol);
//        List<Message> messageList = filterMessage(weMessage, null, istartDate);

        // 126登录信息
        String ihost = "imap.126.com";
        String iusername = "jishengwuliu1@126.com";
        String ipassword = "VRMRHECYSWGXAJXI";
        String iprotocol = "imaps";
        String ifromMail = "@marketplace.amazon.com";// 只读取该邮箱发来的邮件，如果为空则不过滤
//        String istartDate = "2020-2-24 23:35:40";
        Message[] weMessage = getWEMessage(ihost, iusername, ipassword, iprotocol);
        List<Message> messageList = filterMessage(weMessage, null, null);

        printMailMessage(messageList);
        System.out.println("结束");
    }
}