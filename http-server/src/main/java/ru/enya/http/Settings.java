package ru.enya.http;

public class Settings {

    private int port;
    private int threads;


    public Settings(String[] args) {
        parseArgs(args);
    }

    public int getPort() {
        return this.port;
    }

    public int getThreads() {
        return this.threads;
    }

    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if ("-port".equals(args[i])) {
                this.port = Integer.parseInt(args[i + 1]);
            }
            if ("-t".equals(args[i]) || "-threads".equals(args[i])) {
                this.threads = Integer.parseInt(args[i + 1]);
            }
        }
    }

}
