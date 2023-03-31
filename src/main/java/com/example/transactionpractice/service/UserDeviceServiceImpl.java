package com.example.transactionpractice.service;

import com.example.transactionpractice.dto.DeviceInfo;
import com.example.transactionpractice.entity.UserDevice;
import com.example.transactionpractice.entity.token.RefreshToken;
import com.example.transactionpractice.exception.TokenRefreshException;
import com.example.transactionpractice.repository.UserDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDeviceServiceImpl implements UserDeviceService {


    private final UserDeviceRepository userDeviceRepository;

    @Autowired
    public UserDeviceServiceImpl(UserDeviceRepository userDeviceRepository) {
        this.userDeviceRepository = userDeviceRepository;
    }

    @Override
    public Optional<UserDevice> findDeviceByUserId(Long userId, String deviceId) {
        return userDeviceRepository.findByUserIdAndDeviceId(userId, deviceId);
    }

    @Override
    public Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken) {
        return userDeviceRepository.findByRefreshToken(refreshToken);
    }

    @Override
    public UserDevice createUserDevice(DeviceInfo deviceInfo) {

        UserDevice userDevice = new UserDevice();

        userDevice.setDeviceId(deviceInfo.getDeviceId());
        userDevice.setDeviceType(deviceInfo.getDeviceType());
        userDevice.setNotificationToken(deviceInfo.getNotificationToken());
        userDevice.setIsRefreshActive(true);

        return userDevice;
    }

    @Override
    public void verifyRefreshAvailability(RefreshToken refreshToken) {
        UserDevice userDevice = findByRefreshToken(refreshToken)
                .orElseThrow(() -> new TokenRefreshException(refreshToken.getToken(), "Устройство для соответствующего токена не найдено. Пожалуйста, войдите снова"));

        if (!userDevice.getIsRefreshActive()) {
            throw new TokenRefreshException(refreshToken.getToken(), "Обновление заблокировано для устройства. Пожалуйста, войдите через другое устройство");
        }
    }
}
