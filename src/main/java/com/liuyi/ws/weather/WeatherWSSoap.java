
package com.liuyi.ws.weather;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WeatherWSSoap", targetNamespace = "http://WebXml.com.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WeatherWSSoap {


    /**
     * <br /><h3>����й�ʡ�ݡ�ֱϽ�С���������ƣ����⣩����֮��Ӧ��ID</h3><p>��������ޣ�������ݣ�DataSet��</p><br />
     * 
     * @return
     *     returns com.liuyi.webservice.weather.GetRegionDatasetResponse.GetRegionDatasetResult
     */
    @WebMethod(action = "http://WebXml.com.cn/getRegionDataset")
    @WebResult(name = "getRegionDatasetResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getRegionDataset", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetRegionDataset")
    @ResponseWrapper(localName = "getRegionDatasetResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetRegionDatasetResponse")
    public com.liuyi.ws.weather.GetRegionDatasetResponse.GetRegionDatasetResult getRegionDataset();

    /**
     * <br /><h3>����й�ʡ�ݡ�ֱϽ�С��������֮��Ӧ��ID</h3><p>��������ޣ�������ݣ�һά�ַ����顣</p><br />
     * 
     * @return
     *     returns com.liuyi.webservice.weather.ArrayOfString
     */
    @WebMethod(action = "http://WebXml.com.cn/getRegionProvince")
    @WebResult(name = "getRegionProvinceResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getRegionProvince", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetRegionProvince")
    @ResponseWrapper(localName = "getRegionProvinceResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetRegionProvinceResponse")
    public ArrayOfString getRegionProvince();

    /**
     * <br /><h3>��ù�������ƺ���֮��Ӧ��ID</h3><p>��������ޣ�������ݣ�һά�ַ����顣</p><br />
     * 
     * @return
     *     returns com.liuyi.webservice.weather.ArrayOfString
     */
    @WebMethod(action = "http://WebXml.com.cn/getRegionCountry")
    @WebResult(name = "getRegionCountryResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getRegionCountry", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetRegionCountry")
    @ResponseWrapper(localName = "getRegionCountryResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetRegionCountryResponse")
    public ArrayOfString getRegionCountry();

    /**
     * <br /><h3>���֧�ֵĳ���/������ƺ���֮��Ӧ��ID</h3><p>�������theRegionCode = ʡ�С����ID����ƣ�������ݣ�DataSet��</p><br />
     * 
     * @param theRegionCode
     * @return
     *     returns com.liuyi.webservice.weather.GetSupportCityDatasetResponse.GetSupportCityDatasetResult
     */
    @WebMethod(action = "http://WebXml.com.cn/getSupportCityDataset")
    @WebResult(name = "getSupportCityDatasetResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getSupportCityDataset", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetSupportCityDataset")
    @ResponseWrapper(localName = "getSupportCityDatasetResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetSupportCityDatasetResponse")
    public com.liuyi.ws.weather.GetSupportCityDatasetResponse.GetSupportCityDatasetResult getSupportCityDataset(
        @WebParam(name = "theRegionCode", targetNamespace = "http://WebXml.com.cn/")
        String theRegionCode);

    /**
     * <br /><h3>���֧�ֵĳ���/������ƺ���֮��Ӧ��ID</h3><p>�������theRegionCode = ʡ�С����ID����ƣ�������ݣ�һά�ַ����顣</p><br />
     * 
     * @param theRegionCode
     * @return
     *     returns com.liuyi.webservice.weather.ArrayOfString
     */
    @WebMethod(action = "http://WebXml.com.cn/getSupportCityString")
    @WebResult(name = "getSupportCityStringResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getSupportCityString", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetSupportCityString")
    @ResponseWrapper(localName = "getSupportCityStringResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetSupportCityStringResponse")
    public ArrayOfString getSupportCityString(
        @WebParam(name = "theRegionCode", targetNamespace = "http://WebXml.com.cn/")
        String theRegionCode);

    /**
     * <br /><h3>�������Ԥ�����</h3><p>����������/����ID����ƣ�������ݣ�һά�ַ����顣</p><br />
     * 
     * @param theUserID
     * @param theCityCode
     * @return
     *     returns com.liuyi.webservice.weather.ArrayOfString
     */
    @WebMethod(action = "http://WebXml.com.cn/getWeather")
    @WebResult(name = "getWeatherResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getWeather", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetWeather")
    @ResponseWrapper(localName = "getWeatherResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.liuyi.webservice.weather.GetWeatherResponse")
    public ArrayOfString getWeather(
        @WebParam(name = "theCityCode", targetNamespace = "http://WebXml.com.cn/")
        String theCityCode,
        @WebParam(name = "theUserID", targetNamespace = "http://WebXml.com.cn/")
        String theUserID);

}
