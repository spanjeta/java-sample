package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.DeviceDetail;

public interface DeviceDetailRepository extends JpaRepository<DeviceDetail, Long> {

}
