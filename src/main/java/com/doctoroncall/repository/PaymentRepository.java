package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
