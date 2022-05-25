package com.example.config.multitenant.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TenantDataSource implements Serializable {

    private final HashMap<String, DataSource> dataSources = new HashMap<>();

    @Autowired
    private DataSourceConfigRepository dataSourceConfigRepository;

    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) {
            dataSources.put(name, dataSource);
        }
        return dataSource;
    }

    public Map<String, DataSource> getAll() {
        List<DataSourceConfig> configList = dataSourceConfigRepository.findAll();
        Map<String, DataSource> result = new HashMap<>();
        for (DataSourceConfig config : configList) {
            DataSource dataSource = getDataSource(config.getName());
            result.put(config.getName(), dataSource);
        }
        return result;
    }

    private DataSource createDataSource(String name) {
        DataSourceConfig config = dataSourceConfigRepository.findByName(name);
        if (config != null) {
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(config.getDriverClassName()).username(config.getUsername()).password(config.getPassword()).url(config.getUrl());
            DataSource dataSource = factory.build();
            if (config.getInitialize()) {
                initialize(dataSource);
            }
            return dataSource;
        }
        return null;
    }

    private void initialize(DataSource dataSource) {

    }

}
