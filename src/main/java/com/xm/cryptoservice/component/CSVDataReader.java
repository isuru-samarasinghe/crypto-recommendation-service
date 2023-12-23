package com.xm.cryptoservice.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.xm.cryptoservice.model.CryptoData;

@Component
public class CSVDataReader {

    public List<CryptoData> readCryptoData() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:*.csv");

        List<CryptoData> cryptoDataList = new ArrayList<>();
        for (Resource resource : resources) {
            try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                CsvToBean<CryptoData> csvToBean = new CsvToBeanBuilder<CryptoData>(reader)
                        .withType(CryptoData.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                cryptoDataList.addAll(csvToBean.parse());
            }
        }
        return cryptoDataList;
    }
}