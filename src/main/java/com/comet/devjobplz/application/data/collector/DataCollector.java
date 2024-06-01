package com.comet.devjobplz.application.data.collector;

public interface DataCollector {

    void init();

    void process();

    void end();
}
