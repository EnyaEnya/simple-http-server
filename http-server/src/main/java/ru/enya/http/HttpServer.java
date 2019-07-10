package ru.enya.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.enya.http.Method.HEAD;

public class HttpServer {

    private final static Logger log = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) throws Throwable {
        ServerSocket ss = new ServerSocket(8080);
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        while (true) {
            Socket s = ss.accept();
            log.info("Client accepted, IP-address: {}", s.getInetAddress());
            threadPool.execute(new SocketProcessor(s));
        }
    }

    private static class SocketProcessor implements Runnable {

        private HttpRequest httpRequest;
        private Socket s;
        private InputStream is;
        private OutputStream os;
        private HttpResponse httpResponse;

        private SocketProcessor(Socket s) throws Throwable {
            this.s = s;
            this.is = s.getInputStream();
            this.os = s.getOutputStream();
            this.httpRequest = new HttpRequestImpl(is);
            this.httpResponse = new HttpResponseImpl(os);
        }

        public void run() {
            try {
                writeResponse();
            } catch (Throwable t) {
                log.error("Error", t);
            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                    /*do nothing*/
                }
            }
            log.info("Client processing finished, IP-address: {}", s.getInetAddress());
        }

        private void writeResponse() throws Throwable {
            File file = new File(httpRequest.getRequestURI());
            //todo encoding(understand cyrillic symbols)
            String mimeType = Files.probeContentType(file.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            //todo add answer "404 not found"
            //todo add answer "error"
            httpResponse.setCode(HttpStatusCode.OK);
            httpResponse.addHeader("Server", "EnyaServer");
            httpResponse.addHeader("Content-Type", mimeType);
            httpResponse.addHeader("Content-Length", Long.toString(file.length()));
            httpResponse.addHeader("Connection", "close");
            httpResponse.writeHeaders();
            if (httpRequest.getMethod() != HEAD) {
                byte[] buffer = new byte[1024];
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    while (true) {
                        int bytesRead = fileInputStream.read(buffer);
                        if (bytesRead < 0)
                            break;
                        os.write(buffer, 0, bytesRead);
                    }
                }
                os.flush();
            }
        }
    }
}