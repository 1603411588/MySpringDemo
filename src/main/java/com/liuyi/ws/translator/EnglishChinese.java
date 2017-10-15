
package com.liuyi.ws.translator;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * <a href="http://www.webxml.com.cn/" target="_blank">WebXml.com.cn</a> <strong>中文&lt;-&gt;英文双向翻译WEB服务，永久免费</strong>。提供词典翻译、音标（拼音）、解释、相关词条、例句、读音（英文Only）、候选词等功能。</br>传递中文参数请使用UTF-8编码，使用本站WEB服务请注明或链接本站：http://www.webxml.com.cn/ 感谢大家的支持！<br /><br /><img alt="Ject.cn" title="www.Ject.cn" src="http://www.ject.cn/images/ject_logo_1616.gif" style="vertical-align: middle;" /> <a href="http://www.ject.cn/" target="_blank">中英文双向翻译网站 <img alt="Zip" title="Zip file" src="http://www.ject.cn/images/icon/zip.gif" style="vertical-align: middle;" /> .Net实例下载</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="PDF" title="PDF file" src="http://www.ject.cn/images/icon/pdf.gif" style="vertical-align: middle;" /> <a href="http://fy.webxml.com.cn/files/TranslatorWebServiceHelp.pdf" target="_blank">接口开发帮助文档</a><br /><br />&nbsp;
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EnglishChinese", targetNamespace = "http://WebXml.com.cn/", wsdlLocation = "file:/C:/Users/liuyi/Desktop/EnglishChinese.xml")
public class EnglishChinese
    extends Service
{

    private final static URL ENGLISHCHINESE_WSDL_LOCATION;
    private final static WebServiceException ENGLISHCHINESE_EXCEPTION;
    private final static QName ENGLISHCHINESE_QNAME = new QName("http://WebXml.com.cn/", "EnglishChinese");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/liuyi/Desktop/EnglishChinese.xml");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ENGLISHCHINESE_WSDL_LOCATION = url;
        ENGLISHCHINESE_EXCEPTION = e;
    }

    public EnglishChinese() {
        super(__getWsdlLocation(), ENGLISHCHINESE_QNAME);
    }

    public EnglishChinese(WebServiceFeature... features) {
        super(__getWsdlLocation(), ENGLISHCHINESE_QNAME, features);
    }

    public EnglishChinese(URL wsdlLocation) {
        super(wsdlLocation, ENGLISHCHINESE_QNAME);
    }

    public EnglishChinese(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ENGLISHCHINESE_QNAME, features);
    }

    public EnglishChinese(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EnglishChinese(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EnglishChineseSoap
     */
    @WebEndpoint(name = "EnglishChineseSoap")
    public EnglishChineseSoap getEnglishChineseSoap() {
        return super.getPort(new QName("http://WebXml.com.cn/", "EnglishChineseSoap"), EnglishChineseSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EnglishChineseSoap
     */
    @WebEndpoint(name = "EnglishChineseSoap")
    public EnglishChineseSoap getEnglishChineseSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://WebXml.com.cn/", "EnglishChineseSoap"), EnglishChineseSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ENGLISHCHINESE_EXCEPTION!= null) {
            throw ENGLISHCHINESE_EXCEPTION;
        }
        return ENGLISHCHINESE_WSDL_LOCATION;
    }

}
