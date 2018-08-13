package cn.luliangwei.web.development.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper{

    
    public static final Logger LOG = LoggerFactory.getLogger(HttpServletRequestWrapper.class);
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    //-------------------------------------------------
    private HttpServletRequest request;
    private byte[] rawData;
    
    
    public HttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
        parseRequest();
    }

    
    public HttpServletRequest getRequest() {
        return this.request;
    }
    
    public byte[] getRawData() {
        return this.rawData;
    }
    
    public void setRawData(byte[] data) {
        this.rawData = data;
    }
    
    private void parseRequest() {
        
        //----------------------------解析请求体------------------------------------------------
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ServletInputStream input = request.getInputStream();
            if(input == null) {
                rawData = new byte[0];
            }
            int len = 0;
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            while((len=input.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            rawData = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //----------------------------解析param------------------------------------------------
        
    }
    
    
    @Override
    public ServletInputStream getInputStream() {
        return new ServletInputStream() {
            ByteArrayInputStream bis = new ByteArrayInputStream(rawData);
            @Override
            public int read() throws IOException {
                return bis.read();
            }
            
            @Override
            public void setReadListener(ReadListener listener) {
                
            }
            
            @Override
            public boolean isReady() {
                return false;
            }
            
            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }
    
    @Override
    public BufferedReader getReader() throws IOException {
        String charset = request.getCharacterEncoding() == null ? "UTF-8" : request.getCharacterEncoding();
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(rawData),charset));
    }
}
