package com.deltaqin.sys.remote_data.service;

public interface HomePageService {
    int getInfluxSourceNum(Long cid);

    int getUserNum(Long cid);

    int getTodayLoginNum(long cid);

    void getSourceMap(Long cid);
}
