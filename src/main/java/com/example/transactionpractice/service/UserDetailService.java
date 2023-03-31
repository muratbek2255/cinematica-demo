package com.example.transactionpractice.service;

import com.example.transactionpractice.dto.DeviceInfo;
import com.example.transactionpractice.entity.UserDevice;
import com.example.transactionpractice.entity.token.RefreshToken;

import java.util.Optional;

public interface UserDetailService {

    public Optional<UserDevice> findDeviceByUserId(Long userId, String deviceId);

    public Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);

    public UserDevice createUserDevice(DeviceInfo deviceInfo);

    public void verifyRefreshAvailability(RefreshToken refreshToken);
}
