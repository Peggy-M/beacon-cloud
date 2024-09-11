package com.peppa.api.server;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "beacon-cache")
public interface BeaconCacheClient {

}