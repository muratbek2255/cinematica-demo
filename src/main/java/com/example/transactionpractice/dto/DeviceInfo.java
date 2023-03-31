package com.example.transactionpractice.dto;


import com.example.transactionpractice.entity.DeviceType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class DeviceInfo {

    private String deviceId;

    private DeviceType deviceType;

    private String notificationToken;
}
