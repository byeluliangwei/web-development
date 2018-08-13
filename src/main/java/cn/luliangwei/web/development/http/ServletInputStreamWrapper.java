package cn.luliangwei.web.development.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class ServletInputStreamWrapper extends ServletInputStream{

    private byte[] data;
    
    public ServletInputStreamWrapper(byte[] data) {
        if (data == null) {
            data = new byte[0];
        }
        this.data = data;
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener arg0) {
        
    }

    @Override
    public int read() throws IOException {
        ByteArrayInputStream bio = new ByteArrayInputStream(data);
        return bio.read();
    }

}
