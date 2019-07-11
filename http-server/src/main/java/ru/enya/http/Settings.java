package ru.enya.http;

public class Settings {

    private int port;
    private int threads;
    private String dir;
    private String file;

    public Settings(String[] args) {
        parseArgs(args);
    }

    public int getPort() {
        return this.port;
    }

    public int getThreads() {
        return this.threads;
    }

    public String getDir() {
        return this.dir;
    }

    public String getFile() {
        return this.file;
    }

    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if ("-port".equals(args[i])) {
                this.port = Integer.parseInt(args[i + 1]);
            }
            if ("-threads".equals(args[i])) {
                this.threads = Integer.parseInt(args[i + 1]);
            }
            if ("-dir".equals(args[i])) {
                this.dir = args[i + 1];
            }
            if ("-file".equals(args[i])) {
                this.file = args[i + 1];
            }
        }
    }

}
