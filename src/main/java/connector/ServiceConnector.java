package connector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public class ServiceConnector {
    //------------------------------------------------------------------------------------------------------------------
    static final Logger logger = Logger.getLogger(ServiceConnector.class);
    //------------------------------------------------------------------------------------------------------------------
    public static String get(String endPoint) {
        String responseData = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet getRequest = new HttpGet(endPoint);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();
            getRequest.setConfig(requestConfig);
            getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
            getRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            // response part
            CloseableHttpResponse response = httpClient.execute(getRequest);
            try {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    // return it as a String
                    responseData = EntityUtils.toString(responseEntity);
                }
            } catch (Exception e) {
                logger.error(e);
                System.err.println("[error] "+e);
            } finally {
                response.close();
                System.out.println("[info] close the response connection");
            }
        } catch (Exception e) {
            logger.error(e);
            System.err.println("[error] "+e);
        } finally {
            try {
                httpClient.close();
                System.out.println("[info] close the http connection");
            } catch (IOException e) {
                logger.error(e);
                System.err.println("[error] "+e);
            }
        }
        return responseData;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String getQueryParam(String endPoint, List<NameValuePair> paramList) {
        String responseData = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet getRequest = new HttpGet(endPoint);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();
            getRequest.setConfig(requestConfig);
            getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
            getRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            URI uri = new URIBuilder(getRequest.getURI()).addParameters(paramList).build();
            ((HttpRequestBase) getRequest).setURI(uri);
            // response part
            CloseableHttpResponse response = httpClient.execute(getRequest);
            try {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    // return it as a String
                    responseData = EntityUtils.toString(responseEntity);
                }
            } catch (Exception e) {
                logger.error(e);
                System.err.println("[error] "+e);
            } finally {
                response.close();
                System.out.println("[info] close the response connection");
            }
        } catch (Exception e) {
            logger.error(e);
            System.err.println("[error] "+e);
        } finally {
            try {
                httpClient.close();
                System.out.println("[info] close the http connection");
            } catch (IOException e) {
                logger.error(e);
                System.err.println("[error] "+e);
            }
        }
        return responseData;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String post(String endPoint, String bodyJson) {
        String responseData = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost postRequest = new HttpPost(endPoint);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();
            postRequest.setConfig(requestConfig);
            postRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
            postRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            postRequest.setEntity(new StringEntity(bodyJson));
            // response part
            CloseableHttpResponse response = httpClient.execute(postRequest);
            try {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    // return it as a String
                    responseData = EntityUtils.toString(responseEntity);
                }
            } catch (Exception e) {
                logger.error(e);
                System.err.println("[error] "+e);
            } finally {
                response.close();
                System.out.println("[info] close the response connection");
            }
        } catch (Exception e) {
            logger.error(e);
            System.err.println("[error] "+e);
        } finally {
            try {
                httpClient.close();
                System.out.println("[info] close the http connection");
            } catch (IOException e) {
                logger.error(e);
                System.err.println("[error] "+e);
            }
        }
        return responseData;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String put(String endPoint, String bodyJson) {
        String responseData = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPut putRequest = new HttpPut(endPoint);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();
            putRequest.setConfig(requestConfig);
            putRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
            putRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            putRequest.setEntity(new StringEntity(bodyJson));
            // response part
            CloseableHttpResponse response = httpClient.execute(putRequest);
            try {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    // return it as a String
                    responseData = EntityUtils.toString(responseEntity);
                }
            } catch (Exception e) {
                logger.error(e);
                System.err.println("[error] "+e);
            } finally {
                response.close();
                System.out.println("[info] close the response connection");
            }
        } catch (Exception e) {
            logger.error(e);
            System.err.println("[error] "+e);
        } finally {
            try {
                httpClient.close();
                System.out.println("[info] close the http connection");
            } catch (IOException e) {
                logger.error(e);
                System.err.println("[error] "+e);
            }
        }
        return responseData;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String delete(String endPoint) {
        String responseData = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpDelete deleteRequest = new HttpDelete(endPoint);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();
            deleteRequest.setConfig(requestConfig);
            deleteRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
            deleteRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            // response part
            CloseableHttpResponse response = httpClient.execute(deleteRequest);
            try {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    // return it as a String
                    responseData = EntityUtils.toString(responseEntity);
                }
            } catch (Exception e) {
                logger.error(e);
                System.err.println("[error] "+e);
            } finally {
                response.close();
                System.out.println("[info] close the response connection");
            }
        } catch (Exception e) {
            logger.error(e);
            System.err.println("[error] "+e);
        } finally {
            try {
                httpClient.close();
                System.out.println("[info] close the http connection");
            } catch (IOException e) {
                logger.error(e);
                System.err.println("[error] "+e);
            }
        }
        return responseData;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String deleteQueryParam(String endPoint, List<NameValuePair> paramList) {
        String responseData = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpDelete deleteRequest = new HttpDelete(endPoint);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();
            deleteRequest.setConfig(requestConfig);
            deleteRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
            deleteRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            URI uri = new URIBuilder(deleteRequest.getURI()).addParameters(paramList).build();
            ((HttpRequestBase) deleteRequest).setURI(uri);
            // response part
            CloseableHttpResponse response = httpClient.execute(deleteRequest);
            try {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    // return it as a String
                    responseData = EntityUtils.toString(responseEntity);
                }
            } catch (Exception e) {
                logger.error(e);
                System.err.println("[error] "+e);
            } finally {
                response.close();
                System.out.println("[info] close the response connection");
            }
        } catch (Exception e) {
            logger.error(e);
            System.err.println("[error] "+e);
        } finally {
            try {
                httpClient.close();
                System.out.println("[info] close the http connection");
            } catch (IOException e) {
                logger.error(e);
                System.err.println("[error] "+e);
            }
        }
        return responseData;
    }
    //------------------------------------------------------------------------------------------------------------------
}
