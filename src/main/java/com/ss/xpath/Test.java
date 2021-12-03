package com.ss.xpath;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Xpath解析HTML文本
 * @author ChengXiao
 * @date 2021/9/1 11:36
 **/
public class Test {

    public static void main(String[] args) throws NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException {
        String xpath="//div[@class='mmsgLetterContent']/allText()";
//        String doc = "<div id=\"UserInputtedText\">Hi  dear customer , <br /><br /> I am sorry for the  trouble ,we  cannot see any photo of  the  item here ,  it onbly shows '' @font-face{font-family:Calibri;panose-1:2 15 5 2 2 2 4 3 2 4;}.How about $16?'''  , but no picture ,  $16 is  too much for us without checking items, how about  $14？</div>";
        String doc = "<div style=\"background-color:#d0d0d0;background-image:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg.png);text-align:center;padding:40px;\">\n" +
                "\t<div class=\"mmsgLetter\" style=\"width:580px;margin:0 auto;padding:10px;color:#333;background-color:#fff;border:0px solid #aaa;border-radius:5px;-webkit-box-shadow:3px 3px 10px #999;-moz-box-shadow:3px 3px 10px #999;box-shadow:3px 3px 10px #999;font-family:Verdana, sans-serif; \" >\n" +
                "\n" +
                "\t\t<div class=\"mmsgLetterHeader\" style=\"height:23px;background:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg_topline.png) repeat-x 0 0;\">\n" +
                "\t\t\t\n" +
                "\t\t</div>\n" +
                "\t\t<div class=\"mmsgLetterContent\" style=\"text-align:left;padding:30px;font-size:14px;line-height:1.5;background:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_mp_wmark.png) no-repeat top right;\">\n" +
                "\t\t\n" +
                "\t\t\t<div>\n" +
                "\t\t\t\n" +
                "\t\t\t\t<p>你好!</p>\n" +
                "\t\t\t\t<p>\n" +
                "\t\t\t\t\t感谢你注册微信公众平台。 <br/>\n" +
                "\t\t\t\t\t你的登录邮箱为：hcx313318@163.com。请回填如下6位验证码：\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t\t<p class=\"mmsgLetterDigital\">055508</p>\n" +
                "\t\t\t\t<p>\n" +
                "\t\t\t\t\t验证码在30分钟内有效，30分钟后需要重新激活邮箱\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t\n" +
                "\t\t\t</div>\t\n" +
                "\n" +
                "\t\t\t<div class=\"mmsgLetterInscribe\" style=\"padding:40px 0 0;\" >\n" +
                "\t\t\t\t<img class=\"mmsgAvatar\" src=\"http://wx.qlogo.cn/mmhead/Q3auHgzwzM6H8bJKHKyGY2mk0ljLfodkWnrRbXLn3P11f68cg0ePxA/64\" style=\"float:left; width: 40px; height: 40px; border-radius: 4px;\" />\n" +
                "\t\t\t\t<div class=\"mmsgSender\" style=\"margin:0 0 0 54px;\" >\n" +
                "\t\t\t\t\t<p class=\"mmsgName\" style=\"margin:0 0 10px;\" >微信团队</p>\n" +
                "\t\t\t\t\t<p class=\"mmsgInfo\" style=\"font-size:12px;margin:0;line-height:1.2;\">\n" +
                "\t\t\t\t\t\t微信，是一个生活方式<br/>\n" +
                "\t\t\t\t\t\t<a href=\"mailto:weixinmp@qq.com\" style=\"color:#407700;\">weixinmp@qq.com</a>\n" +
                "\t\t\t\t\t</p>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\t\t\t\n" +
                "\t\t</div>\n" +
                "\t\n" +
                "\t\t<div class=\"mmsgLetterFooter\" style=\"margin:16px;text-align:center;font-size:12px;color:#888;text-shadow:1px 0px 0px #eee;\">\n" +
                "\t\t\n" +
                "\t\t\t<img src=\"/cgi-bin/readtemplate?sid=$SID$&loc=pushmail1,weixiniphone,show,44\" style=\"width:1px;height:1px;\" />\n" +
                "\t\t\t<img src=\"http://weixin.qq.com/cgi-bin/reportforpromote?uin=$RCPTUIN$&sdate=$SDATE$&tver=$TVER$\" style=\"width:1px;height:1px;\" />\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\t\n" +
                "\t\n" +
                "</div>";
        JXDocument jxDocument = new JXDocument(doc);
        List<Object> rs = jxDocument.sel(xpath);
        for (Object o:rs){
            if (o instanceof Element){
                int index = ((Element) o).siblingIndex();
                System.out.println(index);
            }
            System.out.println(o.toString());
        }

//        String s = "https:www/dasdasdas/%s/";
//        System.out.println(String.format(s, "1111111111"));
    }
}
